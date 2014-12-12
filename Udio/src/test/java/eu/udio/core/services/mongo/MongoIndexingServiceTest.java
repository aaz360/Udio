package eu.udio.core.services.mongo;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import eu.udio.config.MongoConfig;
import eu.udio.persistence.domain.FileInfo;
import eu.udio.persistence.repository.FileInfoRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=MongoConfig.class)
public class MongoIndexingServiceTest {
	@Autowired
	private FileInfoRepository repository;
	
	
	
	@Before
	public void setup(){
		repository.deleteAll();
		List<String> tagIds = new ArrayList<String>();
		tagIds.add("123");
		tagIds.add("456");
		repository.save(new FileInfo("testName1", tagIds));
		repository.save(new FileInfo("testName2", null));
	}
	
	@After
	public void tearDown(){
		//repository.deleteAll();
	}
	
	@Test
	public void retriveAllFiles() {
		List<FileInfo> result = repository.findAll();
		assertEquals(2, result.size());
	}
		
	@Test
	public void findByTagIds(){
		Pageable pageable = new PageRequest(0, 10);
		Page<FileInfo> result = repository.findByTagIds(new String[]{"123"}, pageable);
		assertEquals(1, result.getNumberOfElements());
	}
	
	@Test
	public void findByNameOrTagId(){
		Pageable pageable = new PageRequest(0, 10);
		Page<FileInfo> result = repository.search(new String[]{"testName1" },pageable);
		assertEquals(1, result.getNumberOfElements());
	}

}
