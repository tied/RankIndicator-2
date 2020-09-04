package com.example.plugins.rank;

import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.plugin.webfragment.contextproviders.AbstractJiraContextProvider;
import com.atlassian.jira.plugin.webfragment.model.JiraHelper;
import com.atlassian.jira.user.ApplicationUser;
import com.github.jfasttext.JFastText;

import java.io.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static java.time.temporal.ChronoUnit.DAYS;

public class RankIndicator extends AbstractJiraContextProvider {

    @Override
    public Map<String, Object> getContextMap(ApplicationUser applicationUser, JiraHelper jiraHelper) {
        Map<String, Object> contextMap = new HashMap<>();
        Issue currentIssue = (Issue) jiraHelper.getContextParams().get("issue");
        JFastText jft = new JFastText();

            File file = null;

            InputStream input = com.atlassian.core.util.ClassLoaderUtils.getResourceAsStream("supervised_model.bin", this.getClass());
            try {
                file = File.createTempFile("tempfile", ".tmp");
            } catch (IOException e) {
                e.printStackTrace();
            }
            OutputStream out = null;
            try {
                out = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            int read = 0;
            byte[] bytes = new byte[1024];

            while (true) {
                try {
                    if (!((read = input.read(bytes)) != -1)) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    out.write(bytes, 0, read);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            jft.loadModel(file.getPath());

            // Do label prediction
            String text = currentIssue.getSummary();
            JFastText.ProbLabel probLabel = jft.predictProba(text);

            contextMap.put("label", probLabel.label);

        return contextMap;
    }
}
