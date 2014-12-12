package eu.udio.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import eu.udio.persistence.domain.FileInfo;

public interface FileInfoRepository extends MongoRepository<FileInfo, String>{
			
	@Query("{ TagIds : { $in: ?0 }}")	
	public Page<FileInfo> findByTagIds(String[] tagId, Pageable pageable);
	
	@Query("{$or : [{OriginalName: { $in: ?0 }},{ TagIds : { $in: ?0 }}]}")
	public Page<FileInfo> search(String[] keywords, Pageable pageable);
	
	
}
