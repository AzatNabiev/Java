package ru.itis.javalab.services;

import java.io.IOException;

public interface DataService {
    void deleteAllDataOfUser(Long userId) throws IOException;
}
