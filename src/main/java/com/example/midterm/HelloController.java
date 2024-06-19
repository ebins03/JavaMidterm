package com.example.midterm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloController {

    @FXML
    private TextField patientIDField, symptomsField, diagnosisField, medicinesField;
    @FXML
    private CheckBox wardRequiredCheckBox;
    @FXML
    private TableView<Diagnosis> tableView;
    @FXML
    private TableColumn<Diagnosis, String> col1, col2, col3, col4;

    private Connection connect() {
        String url = "dbc:mysql://localhost:3306/Midterm"; 
        String user = "root"; 
        String password = "Mysql2255"; 
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void addDiagnosis(ActionEvent event) {
        String sql = "INSERT INTO diagnosis(PatientID, Symptoms, Diagnosis, Medicines, WardRequired) VALUES(?,?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, patientIDField.getText());
            pstmt.setString(2, symptomsField.getText());
            pstmt.setString(3, diagnosisField.getText());
            pstmt.setString(4, medicinesField.getText());
            pstmt.setBoolean(5, wardRequiredCheckBox.isSelected());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void searchDiagnosis(ActionEvent event) {
        String sql = "SELECT * FROM diagnosis WHERE PatientID = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, patientIDField.getText());
            ResultSet rs = pstmt.executeQuery();

            tableView.getItems().clear();
            while (rs.next()) {
                Diagnosis diagnosis = new Diagnosis(rs.getString("PatientID"), rs.getString("Symptoms"),
                        rs.getString("Diagnosis"), rs.getString("Medicines"), rs.getBoolean("WardRequired"));
                tableView.getItems().add(diagnosis);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void initialize() {
        col1.setCellValueFactory(new PropertyValueFactory<>("patientID"));
        col2.setCellValueFactory(new PropertyValueFactory<>("symptoms"));
        col3.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));
        col4.setCellValueFactory(new PropertyValueFactory<>("medicines"));
    }

    @FXML
    private void handleClose(ActionEvent event) {
       
        System.exit(0);
    }
}
