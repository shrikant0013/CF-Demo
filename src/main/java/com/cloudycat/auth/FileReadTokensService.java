package com.cloudycat.auth;

import org.springframework.stereotype.Service;

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

/**
 * Reads file from given path and converts contents into list of tokens
 *
 */

@Service
public class FileReadTokensService {
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

