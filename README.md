# Neo4j Steemit Ingest

Ingest steemit data into neo4j

# Documentation
  * https://neo4j.com/docs/ogm-manual/current/reference/
  * https://github.com/marvin-we/steem-java-api-wrapper/blob/master/sample/src/main/java/my/sample/project/SteemJUsageExample.java

![image](https://raw.githubusercontent.com/marcelmaatkamp/java-spring-boot-example-neo4j-steemit/master/documentation/images/graph_version_1.png)

# Compile, test and Run

VSCode settings
```
{
    "files.autoSave": "afterDelay",
    "git.confirmSync": false,
    "git.autofetch": true,
    "git.enableSmartCommit": true,
    "java.trace.server": "messages",
    "java.jdt.ls.vmargs": "-DsocksProxyHost=localhost -DsocksProxyPort=9050 -javaagent:/Users/marcel/.gradle/caches/modules-2/files-2.1/org.projectlombok/lombok/1.16.14/8486573ff5a5f17f48920c860caf534e7461976b/lombok-1.16.14.jar -Xbootclasspath/a:/Users/marcel/.gradle/caches/modules-2/files-2.1/org.projectlombok/lombok/1.16.14/8486573ff5a5f17f48920c860caf534e7461976b/lombok-1.16.14.jar"
}
```

VSCode launch.json
```
{
    // Use IntelliSense to learn about possible attributes.
    // Hover to view descriptions of existing attributes.
    // For more information, visit: https://go.microsoft.com/fwlink/?linkid=830387
    "version": "0.2.0",
    "configurations": [
        {
            "type": "java",
            "name": "Debug (Launch)-Application<java-spring-boot-example-neo4j-steemit>",
            "request": "launch",
            "cwd": "${workspaceFolder}",
            "console": "internalConsole",
            "stopOnEntry": false,
            "mainClass": "application.Application",
            "projectName": "java-spring-boot-example-neo4j-steemit",
            "vmArgs": "-Dsteemit.account.name=STEEM_USERNAME -Dsteemit.account.key.post=STEEM_POSTKEY -Dsteemit.account.key.active=STEEM_ACTIVEKEY",
            "args": ""
        }
    ]
}
```

## docker-compose

```
version: '2'
services:

  neo4j:
    image: neo4j
    restart: always
    volumes:
     - neo4j-data:/data
     - neo4j-logs:/logs
    logging:
      options:
        max-size: 50m
        
volumes:
 neo4j-data:
 neo4j-logs:
```

http://neo4j:7474/browser/

## Delete data

```
MATCH (n) DETACH DELETE n;
```

## Tasks
```
$ gradle tasks
```

## Build
```
$ gradle assemble
```

or

```
$ gradle compileJava
```

## Test
```
$ steemit_account_name=STEEM.USERNAME steemit_account_key_post=STEEM.POST_KEY steemit_account_key_active=STEEM.ACTIVE_KEY gradle test -i
```

## Run
```
$ gradle bootRun
```

# Deploy

## Nexus

`~/.gradle/gradle.properties`:

```
nexusUsername = admin
nexusPassword = admin123
```

```
$ gradle uploadArchives
```

## Docker
```
$ gradle dockerPushImage
```
