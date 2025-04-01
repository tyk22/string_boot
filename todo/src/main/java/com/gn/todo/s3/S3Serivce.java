package com.gn.todo.s3;

import java.io.InputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.gn.todo.dto.AttachDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class S3Serivce {

	private final AmazonS3 amazonS3;
	
	@Value("${cloud.aws.s3.bucket}")
	private String bucket;
	
	// S3 서비스와 연결하기
	public S3Object getS3Object(String fileName) {
		S3Object s3Object = amazonS3.getObject(new GetObjectRequest(bucket,fileName));
		return s3Object;
	}
	
	public AttachDto uploadFile(MultipartFile file) {
		AttachDto dto = new AttachDto();
		try(InputStream is = file.getInputStream()){
			if(file==null||file.isEmpty()) {
				throw new Exception("존재하지 않는 파일입니다.");
			}
			
			long fileSize = file.getSize();
			if(fileSize>=1048576) {
				throw new Exception("허용 용량을 초과한 파일입니다.");
			}
			
			String oriName = file.getOriginalFilename();
			dto.setOri_name(oriName);
			
			String fileExt = oriName.substring(oriName.lastIndexOf("."),oriName.length());
			UUID uuid = UUID.randomUUID();
			String uniqueName = uuid.toString().replaceAll("-", "");
			String newName = uniqueName+fileExt;
			dto.setNew_name(newName);
			
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentType(file.getContentType());
			meta.setContentLength(file.getSize());
			
			amazonS3.putObject(new PutObjectRequest(bucket,newName,is,meta)
					.withCannedAcl(CannedAccessControlList.PublicRead));
			
			String region = amazonS3.getRegionName();
			String fileUrl = "https://"+bucket+".s3."+region+".amazonaws.com/"+newName;
			dto.setAttach_path(fileUrl);
			
			
		}catch (Exception e) {
			dto = null;
			e.printStackTrace();
		}
		return dto;
	}
}
