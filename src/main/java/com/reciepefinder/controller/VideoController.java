package com.reciepefinder.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/api")
public class VideoController {

    @PostMapping("/upload")
    public ResponseEntity<?> uploadVideo(@RequestParam("file") MultipartFile file) {
//        try {
//            // Zapis pliku wejściowego tymczasowo na dysk
//            File inputFile = new File("/tmp/input.mp4");
//            try (FileOutputStream fos = new FileOutputStream(inputFile)) {
//                fos.write(file.getBytes());
//            }
//
//            // Wywołanie skryptu Pythona YOLO
//            ProcessBuilder pb = new ProcessBuilder("python3", "detect.py", "/tmp/input.mp4", "/tmp/output.mp4");
//            pb.directory(new File(System.getProperty("user.dir"))); // Upewnij się, że detect.py jest w katalogu backend
//            pb.redirectErrorStream(true);
//            Process process = pb.start();
//
//            // Odczyt wyjścia procesu do logów
//            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    System.out.println(line);
//                }
//            }
//
//            int exitCode = process.waitFor();
//            if (exitCode != 0) {
//                return ResponseEntity.status(500).body("Błąd podczas przetwarzania wideo w Pythonie");
//            }
//
//            return ResponseEntity.ok("Wideo zostało przetworzone");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(500).body("Wystąpił błąd serwera");
//        }
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/get-video")
    public ResponseEntity<?> getVideo() {
        try {
            File outputFile = new File("/tmp/output.mp4");
            if (!outputFile.exists()) {
                return ResponseEntity.status(404).body("Brak przetworzonego wideo");
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.valueOf("video/mp4"))
                    .body(new InputStreamResource(new FileInputStream(outputFile)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Wystąpił błąd serwera");
        }
    }
}
