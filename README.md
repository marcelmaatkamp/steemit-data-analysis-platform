# Neo4j Steemit Ingest

Ingest steemit data into neo4j

# Compile, test and Run

```
    "java.jdt.ls.vmargs": "-DsocksProxyHost=127.0.0.1 -DsocksProxyPort=9050"
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
