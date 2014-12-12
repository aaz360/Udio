package eu.udio.config;

import java.net.UnknownHostException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;

@Configuration
@EnableMongoRepositories(basePackages = "eu.udio.persistence.repository")
public class MongoConfig {
	public @Bean
	  MongoTemplate mongoTemplate(Mongo mongo) throws UnknownHostException {
	    return new MongoTemplate(mongo, "udio");
	  }

	  public @Bean Mongo mongo() throws UnknownHostException {
	    return new Mongo("localhost");
	  }
}
