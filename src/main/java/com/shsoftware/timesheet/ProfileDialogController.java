package com.shsoftware.timesheet;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class ProfileDialogController {
    @FXML
    private Label firstName, lastName, id, email, dob, designation, phoneNo, presentAdd, permanentAdd;
    @FXML
    private GridPane profileDialog;

    // FXML Functions
    @FXML
    protected void onCloseBtnClicked(){
        Stage stage = (Stage) profileDialog.getScene().getWindow();
        stage.close();
    }

    public void setData(String FirstName, String LastName, String Id, String Email, String Dob, String Designation, String PhoneNo, String PresentAdd, String PermanentAdd){
        firstName.setText(FirstName);
        lastName.setText(LastName);
        id.setText(Id);
        email.setText(Email);
        dob.setText(Dob);
        designation.setText(Designation);
        phoneNo.setText(PhoneNo);
        presentAdd.setText(PresentAdd);
        permanentAdd.setText(PermanentAdd);
    }
}
