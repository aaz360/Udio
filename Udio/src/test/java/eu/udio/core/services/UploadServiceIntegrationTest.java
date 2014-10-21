package eu.udio.core.services;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import eu.udio.core.service.UploadService;
import eu.udio.core.service.args.UploadFileArgs;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {eu.udio.config.CoreConfig.class})
public class UploadServiceIntegrationTest {
	@Autowired
	UploadService uploader;
		
	String testUploadUri = "file:/C:/test/";
	
	Path testFile;
	
	@Test
	public void uploadFile_expect_exists_on_file_system() throws Exception{
		
		byte[] testBytes = "test".getBytes();		
		UploadFileArgs args = new UploadFileArgs();
		args.inputStream =  new ByteArrayInputStream(testBytes);
		args.originalFileName = "test.txt";				 		
		
		uploader.uploadFile(args);
		fileExists("test.txt");				
	}
	
	private void fileExists(String fileName) throws Exception {
		fileName = fileName.replaceAll(" ", "%20");
		URI fileUri = new URI(testUploadUri + fileName);
		testFile = Paths.get(fileUri);
		assertTrue(Files.exists(testFile)); 
	}
	
	
	@Test
	public void uploadFile_with_spaces_in_name() throws Exception{
		
		byte[] testBytes = "test".getBytes();		
		UploadFileArgs args = new UploadFileArgs();
		args.inputStream =  new ByteArrayInputStream(testBytes);
		args.originalFileName = "test 1.txt";				 		
		
		uploader.uploadFile(args);
		fileExists("test 1.txt");				
	}
	
	@After
	public void cleanUp()throws Exception{
		Files.delete(testFile);
	}

}
