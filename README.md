# Neo4j Steemit Ingest

Ingest steemit data into neo4j

# Compile, test and Run

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
