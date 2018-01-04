# Documentation

 * https://github.com/mritschel/Oracle12cR2_Base
 * http://www.oracle.com/technetwork/database/appdev-with-soda-2606220.pdf
 * https://docs.oracle.com/cd/E63251_01/doc.12/e58124.pdf

# Oracle

## docker-compose.yml

```
version: '2'
services
  oracle:
    image: mritschel/oracle12cr2_base
    restart: always
    volumes:
      - oracle-data:/u01/oracle/oradata
    logging:
      options:
        max-size: 50m
volumes:
 oracle-data:
```

## Set accounts password

```
$ docker-compose exec oracle ./set_password.sh oracle
```

## Users

| Username | SID/Container | Password |
| ---------|:--------------| --------:|
| SYS      | ORCLCDB       | oracle   |
| SYSTEM   | ORCLCDB       | oracle   |
| PDBADMIN | ORCLPDB1      | oracle   |

## SQLPLUS

The accounts to connect to oracle via sqlplus are:

```
$ docker-compose exec oracle bash -c "sqlplus sys/oracle@ORCLCDB as sysdba"
$ docker-compose exec oracle bash -c "sqlplus system/oracle@ORCLCDB"
$ docker-compose exec oracle bash -c "sqlplus pdbadmin/oracle@ORCLPDB1"
```

## Enteprise Management

To enable Enteprise Management for container `ORCLPDB1`:

```
$ docker-compose exec oracle bash -c "sqlplus sys/oracle@ORCLCDB as sysdba"

SQL> exec dbms_xdb_config.SetGlobalPortEnabled(TRUE)
SQL> alter session set container=ORCLPDB1;
SQL> select dbms_xdb_config.getHttpsPort() from dual;
SQL> exec DBMS_XDB_CONFIG.SETHTTPSPORT(5502);
```

Connect to https://oracle:5502/em/ username = `sys`, password=`oracle` and `AS SYSDBA`

## Create user and rights for json insert via java/soda

Create a tablespace `oracle`, create a user `oracle` with password `oracle` grant rights to use soda:

```
$ docker-compose exec oracle bash -c "sqlplus sys/oracle@ORCLCDB as sysdba"

CREATE TABLESPACE oracle DATAFILE oracle.dat SIZE 10M AUTOEXTEND on;
CREATE TEMPORARY TABLESPACE oracle_temp TEMPFILE oracle_temp.dat SIZE 5M AUTOEXTEND on;
CREATE USER oracle identified by oracle temporary tablespace oracle_temp default tablespace oracle;

GRANT connect TO oracle;
GRANT resource to oracle;

GRANT create session TO oracle;
GRANT create table TO oracle;
GRANT create view TO oracle;
GRANT create any trigger TO oracle;
GRANT create any procedure TO oracle;
GRANT create sequence TO oracle;
GRANT create synonym TO oracle;

GRANT UNLIMITED TABLESPACE TO oracle;
```

## Soda

```
GRANT SODA_APP to oracle;
GRANT CREATE SESSION to oracle;
GRANT CREATE TABLE to oracle;
GRANT CREATE SEQUENCE to oracle;
GRANT CREATE TRIGGER to oracle;
GRANT CREATE PROCEDURE to oracle;
GRANT CREATE VIEW to oracle;
```

## Check inserted data

```
$ docker-compose exec oracle bash -c "sqlplus oracle/oracle@ORCLPDB1"

SQL> select * from json;
```

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
