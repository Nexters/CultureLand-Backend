package org.nexters.cultureland.api.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.nexters.cultureland.common.FileName;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

@Service
public class S3Service {
    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.bucket}")
    private String bucket;

    public S3Service() {
        AWSCredentials awsCredentials = new BasicAWSCredentials("", "");
        AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(awsCredentials);
        amazonS3 = AmazonS3Client.builder()
                .withCredentials(awsCredentialsProvider)
                .withRegion(Regions.AP_NORTHEAST_2)
                .enableForceGlobalBucketAccess()
                .build();
    }

    public String upload(MultipartFile file) throws IOException {
        File image = convert(file).orElseThrow(() -> new IllegalArgumentException("파일 변환 실패"));
        String fileName = image.getName();

        PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, fileName, image);

        amazonS3.putObject(putObjectRequest);
        return amazonS3.getUrl(bucket, fileName)
                .toString();
    }

    private Optional<File> convert(MultipartFile file) throws IOException {
        if (file == null) {
            throw new IllegalArgumentException();
        }

        String concertFileName = convertFileNameForSave(file.getOriginalFilename());
        File convertFile = new File(concertFileName);
        if (convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }
        return Optional.empty();
    }

    private String convertFileNameForSave(String originFileName) {
        String[] fileNameMetaData = originFileName.split("\\.");

        FileName fileName = new FileName(fileNameMetaData[0], fileNameMetaData[1]);

        return fileName.createFileNameForSave();
    }
}
