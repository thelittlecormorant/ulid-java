package com.coderabbit.demo;

import java.sql.*;
import java.util.*;

public class CodeRabbitTest {

    // Mutable shared state (Concurrency Issue)
    private static int counter = 0;

    // Poorly named and overly long method (Maintainability)
    public void doEverything(String userInput, String password, List<Integer> numbers) {
        incrementCounter();

        // Security Issue: SQL Injection
        String unsafeQuery = "SELECT * FROM users WHERE name = '" + userInput + "'";
        executeQuery(unsafeQuery);

        // Security Issue: Logging sensitive info
        System.out.println("User logged in with password: " + password);

        // Performance Issue: Nested loops
        for (int i = 0; i < numbers.size(); i++) {
            for (int j = 0; j < numbers.size(); j++) {
                System.out.println(numbers.get(i) + numbers.get(j));
            }
        }

        // Readability and Maintainability issue: Nested conditions
        if (userInput != null) {
            if (userInput.length() > 0) {
                if (userInput.contains("admin")) {
                    System.out.println("Admin user logged in.");
                }
            }
        }

        // Exception Handling Issue: Empty catch block
        try {
            int riskyOperation = 100 / 0;
        } catch (Exception e) {
            // do nothing
        }

        // Unused variable
        String unusedVariable = "I am not used anywhere";
    }

    // Thread-safety issue
    public void incrementCounter() {
        counter++;
    }

    // Security & Exception Handling issue
    public void executeQuery(String query) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "");
            Statement stmt = conn.createStatement();
            stmt.execute(query);
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                conn.close(); // Potential NullPointerException if conn was never established
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
}
