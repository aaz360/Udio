package eu.udio.core.service.fs;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import eu.udio.config.UploadSettings;
import eu.udio.core.service.UploadService;
import eu.udio.core.service.args.UploadFileArgs;

public class FileSystemUploadService implements UploadService {
	
	UploadSettings settings;
	
	public FileSystemUploadService(UploadSettings settings){
		this.settings = settings;
	}
			
	@Override
	public void uploadFile(UploadFileArgs args) throws Exception {		
		URI uri = buildUri(settings.getStoragePath(), args.originalFileName);		
		Path path  = Paths.get(uri);		
		Files.copy(args.inputStream,path);			
	}

	private URI buildUri(String storagePath, String fileName) throws Exception{
		String fullPath = storagePath + fileName;
		fullPath = fullPath.replaceAll(" ", "%20");		
		
		URI uri = new URI("file:/" + fullPath);
		return uri;
	}

}
