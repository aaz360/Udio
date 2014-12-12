package eu.udio.core.domain;

import java.util.Set;

import eu.udio.persistence.domain.Tag;

public class FileInfo {
	
	private String id;
	private String key;
	private String originalName;
	private Set<Tag> tags;
	
		
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public String getOriginalName() {
		return originalName;
	}
	
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	
	public Set<Tag> getTags() {
		return tags;
	}
	
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}	
	
}
