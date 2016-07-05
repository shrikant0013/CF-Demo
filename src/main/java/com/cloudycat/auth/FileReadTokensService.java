package com.cloudycat.auth;

import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by spandhare on 7/5/16.
 */

@Service
public class FileReadTokensService {
    //Only considers resources path
    public List<String> readTokensAndReturnList(String filePath) {
        try {
            URL pathUrl = this.getClass().getResource("/" + filePath);
            if (pathUrl != null) {
                System.out.println(pathUrl.toURI().toString());
                Path path = Paths.get(pathUrl.toURI());
                if (Files.exists(path) && Files.isRegularFile(path) && Files.isReadable(path)) {
                    try (Scanner scanner = new Scanner(Files.newBufferedReader(path))) {
                        List<String> tokens = new ArrayList<>();
                        while(scanner.hasNext()) {
                            tokens.add(scanner.next());
                        }
                        return tokens;
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> readTokensFromOldFileIO(String path) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(path).getFile());

        try (Scanner scanner = new Scanner(file)) {
            List<String> tokens = new ArrayList<>();
            while(scanner.hasNext()) {
                tokens.add(scanner.next());
            }
            return tokens;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<String> readTokensUsingStream(String path){
        InputStream in = getClass().getResourceAsStream(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        try (Scanner scanner = new Scanner(reader)) {
            List<String> tokens = new ArrayList<>();
            while (scanner.hasNext()) {
                tokens.add(scanner.next());
            }
            return tokens;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

