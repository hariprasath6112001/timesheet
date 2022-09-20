package com.shsoftware.timesheet;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginPageController extends Database implements Initializable {
    @FXML
    private TextField userid, password;
    @FXML
    private GridPane loginScene;
    @FXML
    private Button loginBtn;

    Database database = new Database();

    //FXML Functions
    @FXML
    protected void onLoginBtnClicked(){
        if (!userid.getText().equals("") && !password.getText().equals("")){
            String state = database.Login(userid.getText(), password.getText());
            if (state.equals("Admin")){
                System.out.println("Admin Login Success");
                openAdminPage();
            }
            else if (state.contains("User")){
                System.out.println("Login Success");
                openDashboard();
            }
            else {
                System.out.println("Login Failed");
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("All Details Required");
            alert.setContentText("Fill in all Details to Login");
            alert.show();
        }
    }

    @FXML
    protected void onCloseBtnClicked(){
        Stage stage = (Stage) loginScene.getScene().getWindow();
        stage.close();
    }

    // User Functions
    public void openAdminPage(){
        try {
            Stage stage = (Stage) loginScene.getScene().getWindow();
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

    public void openDashboard(){
        try {
            Stage stage = (Stage) loginScene.getScene().getWindow();
            Stage primaryStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("dashboard.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
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
        loginBtn.setDefaultButton(true);
        userid.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (t1.length() > 10){
                    userid.setText(s);
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
    }
}