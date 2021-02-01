package ru.itis.javalab.services;

import java.io.InputStream;
import java.io.OutputStream;

public interface FileService {
    void saveFileToStorage(InputStream file, String originalFileName, String contentType, Long size, Long id);

    void writeFileFromStorage(Long fileId, OutputStream outputStream);
}
