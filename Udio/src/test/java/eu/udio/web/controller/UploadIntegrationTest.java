package eu.udio.web.controller;
import static org.junit.Assert.*;

import java.awt.print.Printable;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import static org.mockito.Mockito.*;

import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import eu.udio.core.service.UploadService;
import eu.udio.core.service.args.UploadFileArgs;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


public class UploadIntegrationTest {	
	@InjectMocks
	UploadController uploadController;
		
	MockMvc mockMvc;
	
	@Mock
	UploadService uploadService;
			
	@Before
    public void setup() {
		MockitoAnnotations.initMocks(this);						
		mockMvc = standaloneSetup(uploadController).build();		
	}
		
	@Test
	public void postFileExpectUploadServiceCalled() throws Exception {
		byte[] testBytes = "test".getBytes();	
		String testFileName = "original name";
		MockMultipartFile file = new MockMultipartFile("file[]", testFileName, "text/plain", testBytes);
		ArgumentCaptor<UploadFileArgs> uploadFileCaptor = ArgumentCaptor.forClass(UploadFileArgs.class);
		
		mockMvc.perform(fileUpload("/upload").file(file).accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andDo(print());				
				
		verify(uploadService,atLeastOnce()).uploadFile(uploadFileCaptor.capture());		
		assertNotNull(uploadFileCaptor.getValue());
		byte[] resultBytes = new byte[testBytes.length]; 
		uploadFileCaptor.getValue().inputStream.read(resultBytes);
		assertArrayEquals(testBytes, resultBytes);
		assertEquals(testFileName, uploadFileCaptor.getValue().originalFileName);
	}

}
