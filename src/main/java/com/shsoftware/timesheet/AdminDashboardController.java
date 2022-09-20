package com.shsoftware.timesheet;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminDashboardController {
    @FXML
    VBox adminVBox;

    Database database = new Database();
    ArrayList<String> IDs = new ArrayList<>(), Names = new ArrayList<>();
    ResultSet resultSet;

    @FXML
    protected void onNewClicked(){
        try {
            Stage stage = (Stage) adminVBox.getScene().getWindow();
            Stage primaryStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("registerPage.fxml"));
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

    @FXML
    protected void onModifyClicked(){
        try {
            Stage stage = (Stage) adminVBox.getScene().getWindow();
            Stage primaryStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("registerPage.fxml"));
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

    @FXML
    protected void onSignoutClicked(){
        try {
            Stage stage = (Stage) adminVBox.getScene().getWindow();
            Stage primaryStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("loginPage.fxml"));
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

    public void getData(){
        IDs = database.getIDs();
        resultSet = database.getNames();
        try {
            while (resultSet.next()){
                Names.add(resultSet.getString("firstName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setData(){
        int size = IDs.size();
        for(int i=0;i<size;i++){
            Hyperlink label = new Hyperlink(IDs.get(i) + " | " + Names.get(i));
            label.getStyleClass().add("Date");
            adminVBox.getChildren().add(label);
        }
    }
}
