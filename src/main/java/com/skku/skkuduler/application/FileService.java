package com.skku.skkuduler.application;

import com.google.cloud.ReadChannel;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.Channels;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    @Value("${spring.cloud.gcp.storage.bucket}")
    private String bucketName;
    private final Storage storage;

    public String storeFile(MultipartFile file) throws IOException {
        if(file == null || file.isEmpty() || file.getOriginalFilename() == null) return "";
        String fileExtension = "";
        String originalFileName = file.getOriginalFilename();
        if (originalFileName.contains(".")) {
            fileExtension = file.getOriginalFilename().substring(originalFileName.lastIndexOf("."));
        }
        String fileName = UUID.randomUUID() + fileExtension;
        storage.create(
                BlobInfo.newBuilder(bucketName, fileName).build(),
                file.getBytes()
        );
        return fileName;

    }

    public InputStream loadFile(String fileName) {
        Blob blob = storage.get(bucketName,fileName);
        ReadChannel reader = blob.reader();
        return Channels.newInputStream(reader);
    }
}