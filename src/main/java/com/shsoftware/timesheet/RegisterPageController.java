package com.shsoftware.timesheet;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterPageController extends Database implements Initializable {
    @FXML
    private TextField firstName, lastName, id, email, presentAdd, phoneNumber, permanentAdd;
    @FXML
    private PasswordField password;
    @FXML
    private DatePicker dob;
    @FXML
    private GridPane registerScene;
    @FXML
    private CheckBox empCheck, tlCheck, hrCheck;

    private String designation = "";

    Database database = new Database();

    @FXML
    protected void onRegBtnClicked(){
        if (!firstName.getText().equals("") && !lastName.getText().equals("") && !id.getText().equals("") && !email.getText().equals("") && !password.getText().equals("") && !dob.getValue().toString().equals("") && !designation.equals("") && !phoneNumber.getText() .equals("") && !presentAdd.getText() .equals("") && !permanentAdd.getText().equals("")){
            if (database.Register(firstName.getText(), lastName.getText(), id.getText(), email.getText(), password.getText(), String.valueOf(dob.getValue()), designation, phoneNumber.getText(), presentAdd.getText(), permanentAdd.getText())){
                firstName.clear();
                lastName.clear();
                id.clear();
                email.clear();
                password.clear();
                phoneNumber.clear();
                presentAdd.clear();
                permanentAdd.clear();
                empCheck.setSelected(false);
                tlCheck.setSelected(false);
                hrCheck.setSelected(false);
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("All Details Required");
            alert.setContentText("Fill in all Details to Register");
            alert.show();
        }
    }

    @FXML
    protected void onBackBtnClicked(){
        openAdminPage();
    }

    @FXML
    protected void onEmpChecked(){
        empCheck.setSelected(true);
        designation = "Employee";
        tlCheck.setSelected(false);
        hrCheck.setSelected(false);
    }

    @FXML
    protected void onTLChecked(){
        tlCheck.setSelected(true);
        designation = "TeamLeader";
        empCheck.setSelected(false);
        hrCheck.setSelected(false);
    }

    @FXML
    protected void onHRChecked(){
        hrCheck.setSelected(true);
        designation = "HR";
        empCheck.setSelected(false);
        tlCheck.setSelected(false);
    }

    public void openAdminPage(){
        try {
            Stage stage = (Stage) registerScene.getScene().getWindow();
            Stage primaryStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("adminDashboard.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            AdminDashboardController adminDashboardController = fxmlLoader.getController();
            adminDashboardController.getData();
            adminDashboardController.setData();
            scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.setFullScreen(true);
            primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
            primaryStage.show();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (t1.length() > 20){
                    firstName.setText(s);
                }
            }
        });
        lastName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (t1.length() > 20){
                    lastName.setText(s);
                }
            }
        });
        id.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (t1.length() > 10){
                    id.setText(s);
                }
            }
        });
        email.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (t1.length() > 100){
                    email.setText(s);
                }
            }
        });
        password.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (t1.length() > 50){
                    password.setText(s);
                }
            }
        });
        phoneNumber.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!t1.matches("\\d*")) {
                    phoneNumber.setText(t1.replaceAll("[^\\d]", ""));
                }
                if (t1.length() > 10){
                    phoneNumber.setText(s);
                }
            }
        });
        presentAdd.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (t1.length() > 120){
                    presentAdd.setText(s);
                }
            }
        });
        permanentAdd.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (t1.length() > 120){
                    permanentAdd.setText(s);
                }
            }
        });
    }
}