package ru.itis.javalab.services;

public interface BCrypterService {
    boolean checkPass(String authPass, String dbPass);
    String cryptPassword(String password);
}
