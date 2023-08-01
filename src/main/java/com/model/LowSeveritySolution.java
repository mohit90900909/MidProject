package com.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LowSeveritySolution {

    public void executeSolution(String incidentDetails, String lowSeverityFile) {
        // Add the logic for executing the solution for Low severity incidents here
        // For example, we will write the low severity incident to the low severity file
        writeLowSeverityIncident(incidentDetails, lowSeverityFile);
    }

    private void writeLowSeverityIncident(String incidentDetails, String lowSeverityFile) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(lowSeverityFile, true));
            writer.write(incidentDetails);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
