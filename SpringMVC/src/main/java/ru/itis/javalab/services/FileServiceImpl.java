package ru.itis.javalab.services;

import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.FileInfo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public void saveFileToStorage(InputStream file, String originalFileName, String contentType, Long size, Long id) {
        FileInfo fileInfo = FileInfo.builder()
                .originalFileName(originalFileName)
                .storageFileName("main")
                .size(size)
                .type(contentType)
                .build();
        StringBuilder savePath = new StringBuilder();
        savePath.append("C://files//").append(id.toString())
                .append(File.separator).append(fileInfo.getStorageFileName())
                .append(".").append("jpg");

        try {
            Path path = Paths.get("C://files/" + File.separator + id.toString());
            Files.createDirectories(path);
            if (new File(savePath.toString()).exists()) {
                Files.delete(Paths.get(savePath.toString()));
            }
            Files.copy(file, Paths.get(savePath.toString()));
            //filesRepository.save(fileInfo);

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

    }
    @Override
    public void writeFileFromStorage(Long fileId, OutputStream outputStream) {
//         нашли файл в базе
//         FileInfo fileInfo = filesRepository.findById(fileId);
//         нашли файл на диске
//        File file= new File("C://files//4//main.jpg");
//        try {
//            // записали его в ответ
//            Files.copy(file.toPath(), outputStream);
//        } catch (IOException e) {
//            throw new IllegalArgumentException();
//        }
    }
}


