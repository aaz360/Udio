package eu.udio.core.service.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import eu.udio.core.service.UploadService;
import eu.udio.core.service.args.UploadFileArgs;

public class AwsUploadService implements UploadService{
	
	private AmazonS3 s3;	
	private String bucket;

	public AwsUploadService(AmazonS3 s3, String bucket){
		this.s3 = s3;
		this.bucket = bucket;
	}
	
	@Override
	public void uploadFile(UploadFileArgs args) throws Exception {
		s3.putObject(bucket, args.originalFileName, args.inputStream, new ObjectMetadata());		
	}

}
