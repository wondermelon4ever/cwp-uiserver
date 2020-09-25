package example.cwp.uiserver;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidBucketNameException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.RegionConflictException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import okhttp3.HttpUrl;

public class MinioTest {

	public static void main(String[] args) {
		MinioClient minioClient =
			MinioClient.builder()
				.endpoint("http://minio.example.com")
			    .credentials("YOURACCESSKEY", "YOURSECRETKEY")
			    .build();
		
		try {
			boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket("ispark-bucket").build());
			if(isExist) {
				System.out.println("Bucket already exists.");
			} else {
				minioClient.makeBucket(MakeBucketArgs.builder().bucket("ispark-bucket").build());
			}
			
			minioClient.putObject("ispark-bucket","eclipse-che.cer", "d:\\eclipse-che.cer", null);
		    System.out.println("d:\\eclipse-che.cer is successfully uploaded as eclipse-che.cer to `ispark-bucket` bucket.");
		} catch (InvalidKeyException | ErrorResponseException | IllegalArgumentException | InsufficientDataException
				| InternalException | InvalidBucketNameException | InvalidResponseException | NoSuchAlgorithmException
				| ServerException | XmlParserException | IOException e) {
			e.printStackTrace();
		} catch (RegionConflictException e) {
			e.printStackTrace();
		}
	}
}
