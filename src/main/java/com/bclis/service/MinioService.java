package com.bclis.service;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.GetObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.errors.MinioException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

@Service
public class MinioService {

    private final MinioClient minioClient;

    @Value("${minio.bucket-name}")
    private String bucketName;

    public MinioService(@Value("${minio.url}") String url,
                        @Value("${minio.access-key}") String accessKey,
                        @Value("${minio.secret-key}") String secretKey) {
        this.minioClient = MinioClient.builder()
                .endpoint(url)
                .credentials(accessKey, secretKey)
                .build();
    }

    public String uploadFile(String originalFileName, InputStream inputStream, String contentType) {
        String objectName = UUID.randomUUID().toString(); // Generar un nombre único para el archivo

        try {
            // Subir el archivo a MinIO
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .contentType(contentType)
                            .stream(inputStream, inputStream.available(), -1)
                            .build()
            );
            return objectName; // Devolver el nombre del archivo en MinIO
        } catch (Exception e) {
            throw new RuntimeException("Error al subir el archivo a MinIO", e);
        }
    }

    public InputStream downloadFile(String objectName) {
        try {
            // Descargar el archivo desde MinIO
            return minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException("Error al descargar el archivo desde MinIO", e);
        }
    }

    public void deleteFile(String objectName) {
        try {
            // Eliminar el archivo desde MinIO
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el archivo desde MinIO", e);
        }
    }
}
