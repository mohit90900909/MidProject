package com.Email.EmailApp;

import java.io.*;
import java.util.*;

import com.Email.EmailApp.GEMailSender;
import com.exception.SeverityClassifier;
import com.model.LowSeveritySolution;

public class IncidentClassifier {

    public static void main(String[] args) {
        String inputFile = "src/log_data.csv";

        try {
            List<String[]> incidents = readIncidents(inputFile);
            List<String[]> highSeverityIncidents = new ArrayList<>();
            List<String[]> mediumSeverityIncidents = new ArrayList<>();
            List<String[]> lowSeverityIncidents = new ArrayList<>();

            SeverityClassifier severityClassifier = new SeverityClassifier();
            LowSeveritySolution lowSeveritySolution = new LowSeveritySolution();

            for (String[] incident : incidents) {
                String server = incident[0];
                String incidentDetails = incident[0];
                String severity = severityClassifier.assignSeverity(server);

                switch (severity) {
                    case "High":
                        highSeverityIncidents.add(incident);
                        break;
                    case "Medium":
                        mediumSeverityIncidents.add(incident);
                        break;
                    case "Low":
                        lowSeverityIncidents.add(incident);
                        lowSeveritySolution.executeSolution(incidentDetails, inputFile);
                        break;
                }
            }

            // Save high, medium, and low severity incidents to CSV files
            saveToFile("high_severity_incidents.csv", highSeverityIncidents);
            saveToFile("medium_severity_incidents.csv", mediumSeverityIncidents);
            saveToFile("low_severity_incidents.csv", lowSeverityIncidents);

            // Send high, medium, and low severity incidents as attachments in the email
            GEMailSender emailSender = new GEMailSender();
            String toEmail = "ashamohit78612@gmail.com";
            String fromEmail = "ashamohit78612@gmail.com";

            // High Severity Incident Email
            String highSeveritySubject = "High Severity Incidents Report";
            String highSeverityText = "Please find the attached high severity incidents report.";
            emailSender.sendEmailWithAttachment(toEmail, fromEmail, highSeveritySubject, highSeverityText,
                    "high_severity_incidents.csv");
            System.out.println("Sent High Severity Incidents Report");

            // Medium Severity Incident Email
            String mediumSeveritySubject = "Medium Severity Incidents Report";
            String mediumSeverityText = "Please find the attached medium severity incidents report.";
            emailSender.sendEmailWithAttachment(toEmail, fromEmail, mediumSeveritySubject, mediumSeverityText,
                    "medium_severity_incidents.csv");
            System.out.println("Sent Medium Severity Incidents Report");


            // Low Severity Incident Email
            String lowSeveritySubject = "Low Severity Incidents Report";
            String lowSeverityText = "Please find the attached low severity incidents report.";
            emailSender.sendEmailWithAttachment(toEmail, fromEmail, lowSeveritySubject, lowSeverityText,
                    "low_severity_incidents.csv");
            System.out.println("Sent Low Severity Incidents Report");
            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String[]> readIncidents(String fileName) throws IOException {
        List<String[]> incidents = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            incidents.add(data);
        }
        reader.close();
        return incidents;
    }

    private static void saveToFile(String fileName, List<String[]> incidents) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        for (String[] incident : incidents) {
            writer.write(String.join(",", incident));
            writer.newLine();
        }
        writer.close();
    }
}
