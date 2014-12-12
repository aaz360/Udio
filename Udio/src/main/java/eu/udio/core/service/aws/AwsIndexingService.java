package eu.udio.core.service.aws;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import eu.udio.core.domain.FileInfo;
import eu.udio.core.service.IndexingService;

public class AwsIndexingService implements IndexingService {

	private AmazonS3 s3;	
	private String bucket;

	public AwsIndexingService(AmazonS3 s3, String bucket){
		this.s3 = s3;
		this.bucket = bucket;
	}
	
	
	@Override
	public List<FileInfo> listAllFiles() {
		ObjectListing listing = s3.listObjects(bucket, "test/");
		listing.getObjectSummaries();
		List<FileInfo> fileList = new ArrayList<FileInfo>();
		for (S3ObjectSummary objectSummary : listing.getObjectSummaries()) {
			FileInfo fileInfo = new FileInfo();
			fileInfo.setKey(objectSummary.getKey());
			fileList.add(fileInfo);			
		}
		return fileList;
	}


	@Override
	public List<FileInfo> FindFiles(String[] keywords) {		
		return null;
	}

}
