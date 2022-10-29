package com.example.task2attempt3.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * File conversion service used to convert files of type MultipartFile to File.
 */
@Service
public class FileConverterService {
    private final static Logger LOGGER = LoggerFactory.getLogger(FileConverterService.class);
    public File convertMultipartFileToFile(MultipartFile multipartFile) {
        File convertedFile = new File("./src/main/resources/static/uploadedClientFiles/" +
                                        multipartFile.getOriginalFilename());
        try {
            convertedFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convertedFile);
            fos.write(multipartFile.getBytes());
            fos.close();
        } catch (IOException e) {
            LOGGER.error("Some error happened while converting the file!");
            throw new RuntimeException(e);
        }

        return convertedFile;
    }
}
