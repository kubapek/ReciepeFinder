package com.reciepefinder.service;

import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class PythonService {

    public String runPythonScript() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("python", "path/to/script.py");
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error running Python script: " + e.getMessage();
        }
    }

    public String processUploadedVideo(Path videoPath) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("python", "path/to/script.py", videoPath.toString());
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error processing video: " + e.getMessage();
        }
    }
}
