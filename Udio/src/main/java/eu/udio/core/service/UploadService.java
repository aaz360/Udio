package eu.udio.core.service;

import eu.udio.core.service.args.UploadFileArgs;

public interface UploadService {
	public void uploadFile(UploadFileArgs args) throws Exception;
}
