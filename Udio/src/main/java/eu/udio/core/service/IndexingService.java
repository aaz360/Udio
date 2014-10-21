package eu.udio.core.service;

import java.util.List;

import eu.udio.core.domain.FileInfo;

public interface IndexingService {
	public List<FileInfo> listAllFiles();
}
