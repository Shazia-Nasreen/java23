package com.example.javafx1g;



import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.*;
import java.util.Collections;

public class DataSorterApp extends Application {
    private ObservableList<String> data = FXCollections.observableArrayList();
    private ListView<String> listView = new ListView<>(data);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Data Sorter");

        // Create buttons
        Button openJsonButton = new Button("Open sample.json");
        Button openCsvButton = new Button("Open sample.csv");
        Button openXmlButton = new Button("Open sample.xml");
        Button sortDataButton = new Button("Sort Data");
        Button saveDataButton = new Button("Save Data");
        Button saveSortedDataButton = new Button("Save Sorted Data");

        // Set actions for buttons
        openJsonButton.setOnAction(e -> loadDataFromFile("sample.json"));
        openCsvButton.setOnAction(e -> loadDataFromFile("sample.csv"));
        openXmlButton.setOnAction(e -> loadDataFromXml("sample.xml"));
        sortDataButton.setOnAction(e -> sortDataAlphabetically());
        saveDataButton.setOnAction(e -> saveDataToFile());
        saveSortedDataButton.setOnAction(e -> saveSortedDataToFile());

        // Create layout
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.getChildren().addAll(openJsonButton, openCsvButton, openXmlButton, sortDataButton, saveDataButton, saveSortedDataButton, listView);

        // Create scene
        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();
    }

    private void loadDataFromFile(String fileName) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Data Files", "*" + getFileExtension(fileName)));

        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try {
                // Read data from the selected file
                data.clear(); // Clear existing data
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    data.add(line);
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadDataFromXml(String fileName) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open XML File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML Files", "*.xml"));

        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try {
                // Parse XML file and extract data
                data.clear(); // Clear existing data

                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(file);

                NodeList nodeList = document.getElementsByTagName("row");
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node row = nodeList.item(i);
                    if (row.getNodeType() == Node.ELEMENT_NODE) {
                        Element rowElement = (Element) row;
                        NodeList rowDetails = rowElement.getChildNodes();
                        for (int j = 0; j < rowDetails.getLength(); j++) {
                            Node detail = rowDetails.item(j);
                            if (detail.getNodeType() == Node.ELEMENT_NODE) {
                                Element detailElement = (Element) detail;
                                data.add(detailElement.getTagName() + ": " + detailElement.getTextContent());
                            }
                        }
                    }
                }
            } catch (ParserConfigurationException | SAXException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sortDataAlphabetically() {
        Collections.sort(data);
    }
    private void sortDataAlphabeticallyAndStoreInArray() {
        // Sort data alphabetically
        Collections.sort(data);

        // Convert the ObservableList to a regular array
        String[] dataArray = data.toArray(new String[0]);

        // Print the sorted data in the console (you can modify this part as needed)
        System.out.println("Sorted Data:");
        for (String item : dataArray) {
            System.out.println(item);
        }
    }

    private void saveDataToFile() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Data to File");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

            File file = fileChooser.showSaveDialog(null);

            if (file != null) {
                // Save data to the selected file
                try (PrintWriter writer = new PrintWriter(file)) {
                    for (String item : data) {
                        writer.println(item);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveSortedDataToFile() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Sorted Data to File");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

            File file = fileChooser.showSaveDialog(null);

            if (file != null) {
                // Save sorted data to the selected file
                try (PrintWriter writer = new PrintWriter(file)) {
                    for (String item : data) {
                        writer.println(item);
                    }
                    System.out.println("Sorted Data saved successfully.");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex > 0) {
            return fileName.substring(lastDotIndex);
        }
        return "";
    }
}
