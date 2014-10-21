package eu.udio.core.services.aws;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import eu.udio.config.AwsConfig;
import eu.udio.core.service.UploadService;
import eu.udio.core.service.args.UploadFileArgs;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AwsConfig.class })
public class AwsUploadServiceIntegrationTest {

	private final String testFileName = "test/test.txt";
	
	@Autowired
	UploadService uploadService;
	
	@Autowired
	AmazonS3 s3;
	String bucket = AwsConfig.DEFAULT_BUCKET;
	
	@Before
	public void setup(){
		clearBucket();
	}
	
	@After
	public void cleanUp(){
		clearBucket();
	}
			
	
	
	@Test
	public void uploadFile() throws Exception{
		UploadFileArgs args = new UploadFileArgs();
		args.originalFileName = testFileName;
		args.inputStream = buildTestFileStream();
		uploadService.uploadFile(args);		
        
        S3Object reportObject = s3.getObject(bucket,testFileName);        
        assertNotNull(reportObject);
	}
	
	private InputStream buildTestFileStream(){
		byte[] testBytes = "test".getBytes();			
		return   new ByteArrayInputStream(testBytes);						
	}
		
	private void clearBucket(){
		 ObjectListing objectListing = s3.listObjects(new ListObjectsRequest()
        .withBucketName(bucket).withPrefix("test/"));        
		 
		 for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
			s3.deleteObject(bucket, objectSummary.getKey());
		}
		 
	}
	
	

}
