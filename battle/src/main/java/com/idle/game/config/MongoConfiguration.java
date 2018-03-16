package com.idle.game.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 *
 * @author rafael
 */
@Configuration
@Profile({"default"})
@EnableMongoRepositories("com.idle.game.server.repository")
public class MongoConfiguration extends AbstractMongoConfiguration {

    @Value("${idle.config.mongodb.hostname}")
    private String mongoHost;

    @Value("${idle.config.mongodb.port}")
    private String mongoPort;

    @Value("${idle.config.mongodb.database}")
    private String mongoDB;

    @Override
    @Bean
    public Mongo mongo() throws Exception {
        return new MongoClient(mongoHost + ":" + mongoPort);
    }
//     @Override
//    @Bean
//    public MongoClient mongoClient() {
//        return new MongoClient(mongoHost + ":" + mongoPort);
//    }

    @Override
    protected String getDatabaseName() {
        return mongoDB;
    }

    @Override
    protected String getMappingBasePackage() {
        return "com.idle.game.model.mongo";
    }
}
