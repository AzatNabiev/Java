package ru.itis.javalab.services;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class DataServiceImpl implements DataService {
    @Override
    public void deleteAllDataOfUser(Long userId) throws IOException {
       File file=new File("C://files//" + userId.toString());
       if (file.exists()){
           FileUtils.deleteDirectory(file);
       }
    }
}
