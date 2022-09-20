package com.shsoftware.timesheet;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class JobDialogController {
    @FXML
    private VBox jobDialog;
    @FXML
    private CheckBox allCheck;
    @FXML
    private TextField jobTopic;
    @FXML
    private TextArea jobDesc;
    @FXML
    private DatePicker jobDue;
    @FXML
    private DatePicker jobAssigned;

    private String Id;
    ArrayList<String> Ids = new ArrayList<>(), selectedIds = new ArrayList<>();
    ArrayList<CheckBox> checkBoxes = new ArrayList<>();
    Database database = new Database();

    @FXML
    protected void allClicked(){
        boolean checkState = allCheck.isSelected();
        for (CheckBox checkBox: checkBoxes){
            checkBox.setSelected(checkState);
        }
    }

    @FXML
    protected void closeClicked(){
        Stage stage = (Stage) jobDialog.getScene().getWindow();
        stage.close();
    }
    @FXML
    protected void onSubmitBtnClicked(){
        selectedIds.clear();
        for (CheckBox checkBox: checkBoxes){
            if (checkBox.isSelected()){
                selectedIds.add(checkBox.getText());
            }
        }
        for (String id: selectedIds){
            database.setJob(id, jobTopic.getText(), jobDesc.getText(), String.valueOf(jobAssigned.getValue()), String.valueOf(jobDue.getValue()));
        }
    }

    public void setId(String id){
        Id = id;
    }

    public void setIds(){
        Ids.clear();
        checkBoxes.clear();
        Ids = database.getIDs();
        Ids.remove(Id);
        for (String Id: Ids){
            CheckBox checkBox = new CheckBox(Id);
            checkBoxes.add(checkBox);
        }
        jobDialog.getChildren().addAll(checkBoxes);
    }
}
