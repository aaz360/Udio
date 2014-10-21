package eu.udio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

import eu.udio.core.service.IndexingService;
import eu.udio.core.service.UploadService;
import eu.udio.core.service.aws.AwsIndexingService;
import eu.udio.core.service.aws.AwsUploadService;

@Configuration
public class AwsConfig {
	public static final String DEFAULT_BUCKET = "aaz360-first-bucket";
	
	@Bean
	public AWSCredentials awsCredentials() throws Exception {
		return  new PropertiesCredentials(this.getClass().getClassLoader().getResourceAsStream("aws-credentials.props"));
	}
		
	@Bean
	public AmazonS3 amazonS3()throws Exception {				
		return new AmazonS3Client(awsCredentials());
	}
	
	@Bean
	public UploadService uploadService()throws Exception{
		return new AwsUploadService(amazonS3(),DEFAULT_BUCKET);
	}
	
	@Bean
	public IndexingService indexingService() throws Exception{
		return new AwsIndexingService(amazonS3(), DEFAULT_BUCKET);
	}
}
