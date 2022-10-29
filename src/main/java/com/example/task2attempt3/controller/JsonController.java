package com.example.task2attempt3.controller;

import com.example.task2attempt3.service.FileConverterService;
import com.example.task2attempt3.service.JsonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * JSON Controller is used to process the .json file uploading.
 * You should upload only files with .json extension. If you upload some other files,
 * you'll receive an appropriate message in the console that file's extension is wrong.
 */
@Controller
@RequestMapping(value = "/json")
public class JsonController {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonController.class);
    private final FileConverterService fileConverterService;
    private final JsonService jsonService;

    @Autowired
    public JsonController(FileConverterService fileConverterService, JsonService jsonService) {
        this.fileConverterService = fileConverterService;
        this.jsonService = jsonService;
    }

    @GetMapping(value = "/uploadFile")
    public String uploadJsonFile() {
        return "upload";
    }

    @PostMapping(value = "uploadFile")
    public String processUploadedFile(@RequestParam("jsonFile") MultipartFile multipartFile,
                                      Model model) {
        File jsonFile = fileConverterService.convertMultipartFileToFile(multipartFile);
        LOGGER.info("File " + jsonFile.getName() + " uploaded!");
        String result = jsonService.parseDataApplyRulesAndGetResult(jsonFile);
        model.addAttribute("result", result);
        return "result";
    }
}
