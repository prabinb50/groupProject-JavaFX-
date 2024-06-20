package org.example.groupproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.opencsv.CSVWriter;
import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class HelloController {
    @FXML
    private Label userINFO;

    @FXML
    private Label LoginINFO;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    // admin
    @FXML
    private TextField adminName;

    @FXML
    private TextField adminEmail;

    @FXML
    private  TextField adminUsername;

    @FXML
    private PasswordField adminPassword;

    @FXML
    private PasswordField adminConfirmPassword;



    String pathToCSV = "C:\\Users\\acer\\IdeaProjects\\GroupProject\\src\\main\\resources\\AdminData.csv";

    public void buttonForAdminSignIn() throws IOException{
        loadStage("/org/example/groupproject/Admin.fxml");
    }

    public void buttonForAdminSignUp() throws IOException{
        loadStage("/org/example/groupproject/AdminSignUp.fxml");
    }

    public void buttonForAdminRegister() {
        String AdminName = adminName.getText();
        String AdminEmail = adminEmail.getText();
        String AdminUsername = adminUsername.getText();
        String AdminPassword = adminPassword.getText();
        String AdminConformPassword = adminConfirmPassword.getText();
        try {
            FileWriter fileWriter = new FileWriter(pathToCSV, true);
            CSVWriter csvWriter = new CSVWriter(fileWriter);
            String[] data = {AdminName, AdminEmail, AdminUsername, AdminPassword, AdminConformPassword};
            csvWriter.writeNext(data);
            csvWriter.close();
            loadStage("/org/example/groupproject/Admin.fxml");

        } catch (Exception e) {
            userINFO.setText(e.getMessage());
        }
    }

    public void buttonForLogin(){
        String emailText = email.getText();
        String passwordText = password.getText();
        try {
            FileReader fileReader = new FileReader(pathToCSV);
            CSVReader csvReader = new CSVReader(fileReader);
            String[] rows;
            boolean found = false;
            while ((rows = csvReader.readNext()) != null){
                if (emailText.equals(rows[0]) && passwordText.equals(rows[1])){
                    LoginINFO.setText("Login Successful");
                    loadStage("/org/example/groupproject/AdminDashboard.fxml");
                    found = true;
                    break;
                }
            }
            if (!found) {
                LoginINFO.setText("Invalid Credentials");
            }
        } catch (Exception e) {
            System.out.println("error" + e);
        }
    }

    public void buttonForAdminLogin()throws IOException{
        loadStage("/org/example/groupproject/Admin.fxml");
    }

    @FXML
    public void loadStage(String sceneName) throws IOException{
        try {
            System.out.println("Loading FXML file: " + sceneName);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(sceneName));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) userINFO.getScene().getWindow();
            stage.setScene(new Scene (root));
            stage.show();
        }catch (IOException e){
            userINFO.setText("Failed to load scene: " + e.getMessage());
            e.printStackTrace();
        }
    }

}