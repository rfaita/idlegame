package com.idle.game.tests.config;

import com.github.fakemongo.Fongo;
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
@Profile({"test"})
@EnableMongoRepositories("com.idle.game.server.repository")
public class MongoEmbeddedConfiguration extends AbstractMongoConfiguration {

    @Value("${idle.config.mongodb.database}")
    private String mongoDB;

    @Override
    protected String getDatabaseName() {
        return mongoDB;
    }

    @Override
    @Bean
    public Mongo mongo() throws Exception {
        return new Fongo(getDatabaseName()).getMongo();
    }
//    @Override
//    @Bean
//    public MongoClient mongoClient() {
//        return new Fongo(getDatabaseName()).getMongo();
//    }

    @Override
    protected String getMappingBasePackage() {
        return "com.idle.game.model.mongo";
    }

}
