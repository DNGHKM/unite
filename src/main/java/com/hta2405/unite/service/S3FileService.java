package com.hta2405.unite.service;

import com.hta2405.unite.dto.FileDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class S3FileService implements FileService {
    @Value("${cloud.aws.s3.base-directory}")
    private String BASE_DIR;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final S3Client s3Client;

    public FileDTO uploadFile(MultipartFile file, String subDirectory) {
        try {
            String fileUUID = UUID.randomUUID().toString();
            String filePath = subDirectory.substring(1) + "/" + fileUUID + "_" + file.getOriginalFilename();

            // S3 객체 메타데이터 설정 (AWS SDK v2)
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucket)
                    .key(filePath)
                    .contentType(file.getContentType())
                    .contentLength(file.getSize())
                    .build();

            // S3에 파일 업로드 (AWS SDK v2 방식)
            PutObjectResponse response = s3Client.putObject(
                    putObjectRequest,
                    RequestBody.fromInputStream(file.getInputStream(), file.getSize())
            );

            log.info("File uploaded to S3: {} (ETag: {})", filePath, response.eTag());

            // 업로드된 파일의 URL 반환 (S3 기본 URL 사용)
            return new FileDTO(file.getOriginalFilename(), filePath, file.getContentType(), fileUUID);
        } catch (IOException e) {
            log.error("S3 파일 업로드 실패: {}", e.getMessage(), e);
            throw new RuntimeException("File upload failed", e);
        }
    }


    @Override
    public ResponseEntity<Resource> downloadFile(String subDirectory, String fileUUID, String fileName) {
        String filePath = subDirectory.substring(1) + "/" + fileUUID + "_" + fileName;
        log.info("filePath = {}", filePath);
        try {
            // S3에서 파일 가져오기 (AWS SDK v2 방식)
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(bucket)
                    .key(filePath)
                    .build();

            ResponseInputStream<GetObjectResponse> inputStream = s3Client.getObject(getObjectRequest);
            InputStreamResource resource = new InputStreamResource(inputStream);

            String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8).replace("+", "%20");

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedFileName + "\"; filename*=UTF-8''" + encodedFileName)
                    .body(resource);

        } catch (Exception e) {
            log.error("S3 파일 다운로드 실패: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public void deleteFile(String fileUUID, String subDirectory, String originalFileName) {
        String filePath = subDirectory.substring(1) + "/" + fileUUID + "_" + originalFileName;
        log.info("Deleting file from S3: {}", filePath);

        try {
            // S3에서 파일 삭제 (AWS SDK v2 방식)
            DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                    .bucket(bucket)
                    .key(filePath)
                    .build();

            s3Client.deleteObject(deleteObjectRequest);
            log.info("File successfully deleted from S3: {}", filePath);
        } catch (Exception e) {
            log.error("S3 파일 삭제 실패: {}", e.getMessage(), e);
        }
    }

    public long getFileSize(String subDirectory, String fileUUID, String fileName) {
        String filePath = subDirectory.substring(1) + "/" + fileUUID + "_" + fileName;
        try {
            // S3에서 파일 메타데이터 가져오기 (AWS SDK v2 방식)
            HeadObjectRequest headObjectRequest = HeadObjectRequest.builder()
                    .bucket(bucket)
                    .key(filePath)
                    .build();

            HeadObjectResponse metadata = s3Client.headObject(headObjectRequest);

            // 파일 사이즈 (Content-Length)
            long fileSize = metadata.contentLength();
            log.info("파일 사이즈: {} bytes", fileSize);
            return fileSize;
        } catch (Exception e) {
            log.error("S3 파일 사이즈 조회 실패: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to get file size from S3", e);
        }
    }
}

