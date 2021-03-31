# RankIndicator
Prototype developed to demonstrate the applicability of the NLP4RP approach.

### Pre-requisites

Java JDK 8 javac version [1.8.0_181]
Atlassian Plugin SDK version [6.3.10]

### How-to
1. Clone the repository
```
$ git clone https://github.com/user752/RankIndicator.git
```
2. Place the model file in RankIndicator/src/main/resources (Download from [resources.zip](https://github.com/user752/RankIndicator/releases/download/v1.0/resources.zip))

3. Change directory
```
$ cd RankIndicator
```

4. Install Atlassian Plugin SDK

5. Start using the Atlassian Plugin SDK by executing following commands:
```
$ atlas-run   -- installs this plugin into JIRA and starts it on http://localhost:2990/jira
$ atlas-clean -- removes files built from atlas-run
$ atlas-debug -- same as atlas-run, but allows a debugger to attach at port 5005
$ atlas-help  -- prints description for all commands in the SDK

```
6. Create new project-> Create new issue -> approximated rank will be generated based on the given title
