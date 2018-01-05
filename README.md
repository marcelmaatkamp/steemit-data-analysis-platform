# Neo4j Steemit Ingest

Ingest steemit data into neo4j

# Compile, test and Run

```
{
    "files.autoSave": "afterDelay",
    "git.confirmSync": false,
    "git.autofetch": true,
    "git.enableSmartCommit": true,
    "java.trace.server": "messages",
    "java.jdt.ls.vmargs": "-DsocksProxyHost=127.0.0.1 -DsocksProxyPort=9050 -javaagent:/Users/marcel/.gradle/caches/modules-2/files-2.1/org.projectlombok/lombok/1.16.14/8486573ff5a5f17f48920c860caf534e7461976b/lombok-1.16.14.jar -Xbootclasspath/a:/Users/marcel/.gradle/caches/modules-2/files-2.1/org.projectlombok/lombok/1.16.14/8486573ff5a5f17f48920c860caf534e7461976b/lombok-1.16.14.jar"

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
$ gradle test
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
