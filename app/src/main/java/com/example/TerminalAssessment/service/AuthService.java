/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.TerminalAssessment.service;

/**
 *
 * @author jrbusadre
 */
import java.io.*;
import java.util.*;

public class AuthService {
    private final Map<String, String> accountMap = new HashMap<>();

    public AuthService(String accountsCsvFilePath) {
        loadAccountsFromCSV(accountsCsvFilePath);
    }

    private void loadAccountsFromCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // skip header line
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 2) {
                    String username = data[0].trim();
                    String password = data[1].trim();
                    accountMap.put(username, password);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading accounts CSV: " + e.getMessage());
        }
    }

    public boolean authenticate(String username, String password) {
        return accountMap.containsKey(username) && accountMap.get(username).equals(password);
    }
}
