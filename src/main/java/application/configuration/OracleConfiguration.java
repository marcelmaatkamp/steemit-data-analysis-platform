package application.configuration;

import oracle.soda.OracleCollection;
import oracle.soda.OracleDatabase;
import oracle.soda.OracleException;
import oracle.soda.rdbms.OracleRDBMSClient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class OracleConfiguration {

    @Autowired
    DataSource dataSource;


    @Value("${consumer.label}")
    String collectionName;

    @Bean
    OracleRDBMSClient oracleRDBMSClient() {
        OracleRDBMSClient oracleRDBMSClient = new OracleRDBMSClient();
        return oracleRDBMSClient;
    }

    @Bean
    OracleDatabase oracleDatabase() throws OracleException, SQLException {
        OracleDatabase oracleDatabase = oracleRDBMSClient().getDatabase(dataSource.getConnection());
        return oracleDatabase;
    }

    @Bean
    OracleCollection oracleCollection() throws OracleException, SQLException {
        OracleCollection oracleCollection = oracleDatabase().admin().createCollection(collectionName);
        return oracleCollection;
    }

}
