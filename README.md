# Release v1.0

https://github.com/marcelmaatkamp/java-spring-boot-example-neo4j-steemit/releases/tag/1.0

I hereby release the first version of my project where I ingest Steem data in JSON format, put it in RabbitMQ via and ingest the data via java Spring Boot into Neo4j where I can query the data to find interesting patterns. I focussed for the first version on the votes coming from Steem to analyse who is voting on what and made a screenshot available to show the first results of what I found in the data. 

Once the ingest is running Neo4j can be queried to find patterns in the data:
![graph_version_1.0.png](https://res.cloudinary.com/hpiynhbhq/image/upload/v1515327395/aww7yainsoqt0rhu79cf.png)

I do all this in my spare time so I hope that I can find some time to add more domain objects and make more sense of the data produced by the Steemit platform.

Documentation I added in this initial release:

 * Installation
 * Documentation on how/to ingest and display data
 * Added urls for external resources and
 * Screenshots added showing patterns in the data ( and thus mission v1.0 accomplished!) 

The code so far: 

 * is a fully working version 
 * has examples in src/scratchbook
 * contains unit and integration tests and queries in src/test/java
 * contains a dockerfile and has 
 * docker-compose file added to bootstrap the project


Plans to enhance this project could be:

 * To make the data accessible and interactive via a web-interface 
 * Generate a docker container with this project and  
 * Instantiate a working version on a platform like "Heroku".

Its a pity Steem does not let a author include interactive elements in its posts.

# Prerequisites

To run this project the system needs the following locally installed:

 * A recent version of java,
 * A recent version of gradle and have
 * Docker and docker-compose

# Documentation
  * https://neo4j.com/docs/ogm-manual/current/reference/
  * https://github.com/marvin-we/steem-java-api-wrapper/blob/master/sample/src/main/java/my/sample/project/SteemJUsageExample.java

![image](https://raw.githubusercontent.com/marcelmaatkamp/java-spring-boot-example-neo4j-steemit/master/documentation/images/graph_version_1.0.png)

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


# Installation

## Start the services

Clone the project in a folder and start the proxy, rabbitmq and neo4j services:

```
$ git clone https://github.com/marcelmaatkamp/java-spring-boot-example-neo4j-steemit.git &&\
   cd java-spring-boot-example-neo4j-steemit &&\ 
   docker-compose up -d socks-proxy rabbitmq neo4j
```
## Generate data

Connect to api.steem.com via https://github.com/pibara/steempersist and push the stream into RabbitMQ via docker:

```
$ docker-compose up steemit-amqp
```

## Ingest data into Neo4j

To start the project run the following command to start Spring boot and the consumer which will put the data from RabbitMQ into Neo4j database:

```
$ gradle bootRun
```

## Proxy

Set your browser proxy settings to connect to the services rabbitmq and neo4j via the following url:

```
socks5://localhost:9050`
```

## RabbitMQ

Once the proxy is installed goto http://rabbitmq:15672 and login with username `guest` and password `guest`. The created exchange is `steemit.api` and the queue is `steemit.api.votes`.

## Neo4j

Once the proxy is installed goto http://neo4j:7474 and query the database and generate beautiful screenshots :)

# Issues

Have fun and please contribute or report issue's in Github on
https://github.com/marcelmaatkamp/java-spring-boot-example-neo4j-steemit

<br /><hr/><em>Posted on <a href="https://utopian.io/utopian-io/@marcelmaatkamp/first-release-of-marcelmaatkamp-java-spring-boot-example-neo4j-steemit">Utopian.io -  Rewarding Open Source Contributors</a></em><hr/>


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
