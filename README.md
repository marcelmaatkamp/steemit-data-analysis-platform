# Neo4j Steemit Ingest

Ingest steemit data into neo4j.

First project: Detect and display the relations between authors of posts and voters on posts.

# Documentation
  * https://neo4j.com/docs/ogm-manual/current/reference/
  * https://github.com/marvin-we/steem-java-api-wrapper/blob/master/sample/src/main/java/my/sample/project/SteemJUsageExample.java

![image](https://raw.githubusercontent.com/marcelmaatkamp/java-spring-boot-example-neo4j-steemit/master/documentation/images/graph_version_3.png)

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

# Neo4J

## docker-compose

```
version: '2'
services:

  rabbitmq:
    image: marcelmaatkamp/rabbitmq-mqtt-ldap
    restart: always
    hostname: rabbitmq
    environment:
      RABBITMQ_NODENAME: rabbitmq@rabbitmq
    volumes:
      - rabbitmq:/var/lib/rabbitmq/mnesia
      - ./etc/rabbitmq/rabbitmq.conf:/etc/rabbitmq/rabbitmq.config
      - nodepki-certs-rabbitmq:/certs/rabbitmq
    logging:
      options:
        max-size: 50m
        
  neo4j:
    image: neo4j
    restart: always
    volumes:
     - neo4j-data:/data
     - neo4j-logs:/logs
    logging:
      options:
        max-size: 50m
        
  steemit-amqp:
    restart: always
    image: marcelmaatkamp/steemit-amqp
    environment:
     - RABBITMQ_HOSTNAME=rabbitmq
     - RABBITMQ_EXCHANGE=steemit.api
    volumes:
     - ./credentials.py:/app/mycredentials.py
    logging:
      options:
        max-size: 50m
        
volumes:
 neo4j-data:
 neo4j-logs:
 rabbitmq:
 
```

http://neo4j:7474/browser/

## Display relationship data

```
MATCH (n) RETURN n LIMIT 1000;
```

## Delete neo4j data

```
MATCH (n) DETACH DELETE n;
```

# Development

## Tasks

Display all possible tasks:

```
$ gradle tasks
```
## Intellij

Build configuration, after which Intellij can open this directory as a new project:

```
$ gradle idea
```

## Build

Build code in terminal without an editor:

```
$ gradle assemble
```

or

```
$ gradle compileJava
```

## Test

Run all tests as defined in `src/test`

```
$ steemit_account_name=STEEM.USERNAME steemit_account_key_post=STEEM.POST_KEY steemit_account_key_active=STEEM.ACTIVE_KEY gradle test -i
```

## Run

Run the code in terminal

```
$ gradle bootRun
```

# Deploy

## Nexus

Push code as jar in repository:

`~/.gradle/gradle.properties`:

```
nexusUsername = admin
nexusPassword = admin123
```

```
$ gradle uploadArchives
```

## Docker

Push code as docker container in hub.docker.com:

```
$ gradle dockerPushImage
```
