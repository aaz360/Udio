package eu.udio.config;

public class UploadSettings {
	private String storageURI = "file:/C:/test/";

	public String getStoragePath() {
		return storageURI;
	}

	public void setStorageURI(String storageURI) {
		this.storageURI = storageURI;
	}	
	
}
