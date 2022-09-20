package com.shsoftware.timesheet;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.util.ArrayList;

public class NotificationDialogController {
    @FXML
    private VBox notificationBox;

    private String Id;
    ArrayList<String> ShortDesc = new ArrayList<>(), FromId = new ArrayList<>(), LongDesc = new ArrayList<>(), Category = new ArrayList<>(), Notifications = new ArrayList<>();
    ResultSet resultSet;

    Database database = new Database();

    @FXML
    protected void closeBtnClicked(){
        Stage stage = (Stage) notificationBox.getScene().getWindow();
        stage.close();
    }
    @FXML
    protected void onClearClicked(){
        ShortDesc.clear();
        FromId.clear();
        LongDesc.clear();
        Category.clear();
        Notifications.clear();
        notificationBox.getChildren().clear();
        Label label = new Label("No Notifications");
        notificationBox.setAlignment(Pos.CENTER);
        notificationBox.getChildren().add(label);
        database.DeleteNotifications(Id);
    }

    public void setId(String id) {
        Id = id;
    }

    public void setData(){
        ShortDesc.clear();
        FromId.clear();
        LongDesc.clear();
        Category.clear();
        Notifications.clear();
        resultSet = database.getShortDesc(Id);
        try{
            while (resultSet.next()){
                ShortDesc.add(resultSet.getString("shortDesc"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        resultSet = database.getFromIds(Id);
        try{
            while (resultSet.next()){
                FromId.add(resultSet.getString("fromId"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        resultSet = database.getLongDesc(Id);
        try{
            while (resultSet.next()){
                LongDesc.add(resultSet.getString("longDesc"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        resultSet = database.getCategory(Id);
        try{
            while (resultSet.next()){
                Category.add(resultSet.getString("category"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        resultSet = database.getNotification(Id);
        try{
            while (resultSet.next()){
                Notifications.add(resultSet.getString("notification"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        int length = ShortDesc.size();
        if (length == 0){
            Label label = new Label("No Notifications");
            notificationBox.setAlignment(Pos.CENTER);
            notificationBox.getChildren().add(label);
        }else if (database.getDesignation(Id).equals("HR")){
            for(int i = 0;i < length;i++){
                VBox vBox = new VBox();
                vBox.setSpacing(5);
                Label label = new Label(FromId.get(length-i-1) + ": " + Category.get(length-i-1));
                Label label1 = new Label(ShortDesc.get(length-i-1) + ": " + LongDesc.get(length-i-1));
                HBox hBox = new HBox();
                hBox.setSpacing(10);
                Button accept = new Button("Accept");
                Button deny = new Button("Deny");
                int finalI = i;
                accept.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        onAcceptClicked(accept, deny, FromId.get(length-finalI-1), Category.get(length-finalI-1));
                    }
                });
                deny.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        onDenyClicked(deny, accept, FromId.get(length-finalI-1), Category.get(length-finalI-1));
                    }
                });
                vBox.getChildren().add(label);
                vBox.getChildren().add(label1);
                hBox.getChildren().add(accept);
                hBox.getChildren().add(deny);
                vBox.getChildren().add(hBox);
                notificationBox.setSpacing(10);
                notificationBox.getChildren().add(vBox);
            }
        }else {
            int len = Notifications.size();
            for (int i = 0;i < len; i++){
                Label label = new Label(Notifications.get(len-i-1));
                notificationBox.getChildren().add(label);
            }
        }
    }

    public void onAcceptClicked(Button accept, Button deny, String Id, String Notification) {
        accept.setText("Accepted");
        accept.setDisable(true);
        deny.setDisable(true);
        database.Notification(Id, Notification + " Application has been Accepted.");
    }

    public void onDenyClicked(Button deny, Button accept, String Id, String Notification) {
        deny.setText("Denied");
        deny.setDisable(true);
        accept.setDisable(true);
        database.Notification(Id, Notification + " Application has been Denied.");
    }
}
