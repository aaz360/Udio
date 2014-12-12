package eu.udio.persistence.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "FileInfo")
public class FileInfo {
	
	@Id
	private String id;
	@Indexed
	private String originalName;
	@Indexed()	
	private List<String> tagIds;
	
	public FileInfo(){
		
	}
	
	public FileInfo(String originalName, List<String> tagIds){
		this.originalName = originalName;
		this.tagIds = tagIds;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getOriginalName() {
		return originalName;
	}
	
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	
	public List<String> getTagIds() {
		return tagIds;
	}
	
	public void setTagIds(List<String> tagIds) {
		this.tagIds = tagIds;
	}
	
}
