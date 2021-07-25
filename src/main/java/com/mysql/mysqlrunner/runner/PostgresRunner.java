package com.mysql.mysqlrunner.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Component
public class PostgresRunner implements ApplicationRunner {
    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run( ApplicationArguments args ) {
        try {
            Connection connection = dataSource.getConnection();
            System.out.println( connection.getMetaData().getClass() );
            System.out.println( connection.getMetaData().getDriverName() );
            System.out.println( connection.getMetaData().getURL() );
            System.out.println( connection.getMetaData().getUserName() );

            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE ACCOUNT(ID INTEGER NOT NULL, NAME VARCHAR(255), PRIMARY KEY(ID))";
            statement.executeUpdate( sql );

            jdbcTemplate.execute( "INSERT INTO ACCOUNT VALUES (1, 'hermeswing')" );

        } catch( Exception e ) {
            e.printStackTrace();
        }
    }
}
