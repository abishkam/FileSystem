package com.example.biganonproject.uploadingfiles;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.example.biganonproject.uploadingfiles.storage.StorageFileNotFoundException;
import com.example.biganonproject.uploadingfiles.storage.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/fileApi")
public class FileUploadController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final StorageService storageService;

    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/")
    public String listUploadedFiles(Model model) {

        model.addAttribute("files", storageService.loadAll().map(
                        path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                                "serveFile", path.getFileName().toString()).build().toUri().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        logger.info("{} download process", filename);
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        logger.info("handleFileUpload File:{} Date: {}", file.getOriginalFilename(), LocalDateTime.now());
        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
        return "redirect:/fileApi/";
    }

    @PostMapping("/delete")
    public String handleFileDelete(@RequestParam("sendFiles") String[] file) throws IOException {
        logger.info("handleFileDelete File:{} Date: {}", Arrays.stream(file).toList(), LocalDateTime.now());
        for (int i = 0; i < file.length; i++) {
            file[i] = file[i].replace("http://localhost:8080/fileApi/files/" , "");
            System.out.println(file[i]);
        }
        storageService.delete(file);
        return "redirect:/fileApi/";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}