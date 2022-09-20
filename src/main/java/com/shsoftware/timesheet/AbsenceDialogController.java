package com.shsoftware.timesheet;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AbsenceDialogController extends Database implements Initializable {
    @FXML
    private GridPane absenceDialog;
    @FXML
    private DatePicker fromDateWFH, toDateWFH, fromDateLeave, toDateLeave;
    @FXML
    private CheckBox checkMedicalWFH, checkPersonalWFH, checkOthersWFH, checkMedicalPermission, checkPersonalPermission, checkOthersPermission, checkMedicalLeave, checkPersonalLeave, checkOthersLeave, checkFromAM, checkFromPM, checkToAM, checkToPM;
    @FXML
    private Tab WFHPane, PermissionPane, LeavePane;
    @FXML
    private Spinner<Integer> fromTimeH, fromTimeM, toTimeH, toTimeM;
    @FXML
    private TextArea reasonWFH, reasonPermission, reasonLeave;

    private String reasonShort = "";
    private String fromMeridiem = "";
    private String toMeridiem = "";
    private String Id;

    // Extension
    Database database = new Database();

    @FXML
    protected void onCheckedMedicalWFH(){
        checkMedicalWFH.setSelected(true);
        reasonShort = "Medical";
        checkOthersWFH.setSelected(false);
        checkPersonalWFH.setSelected(false);
    }
    @FXML
    protected void onCheckedPersonalWFH(){
        checkPersonalWFH.setSelected(true);
        reasonShort = "Personal";
        checkMedicalWFH.setSelected(false);
        checkOthersWFH.setSelected(false);
    }
    @FXML
    protected void onCheckedOthersWFH(){
        checkOthersWFH.setSelected(true);
        reasonShort = "Other Issues";
        checkMedicalWFH.setSelected(false);
        checkPersonalWFH.setSelected(false);
    }
    @FXML
    protected void onCheckedMedicalPermission(){
        checkMedicalPermission.setSelected(true);
        reasonShort = "Medical";
        checkPersonalPermission.setSelected(false);
        checkOthersPermission.setSelected(false);

    }
    @FXML
    protected void onCheckedPersonalPermission(){
        checkPersonalPermission.setSelected(true);
        reasonShort = "Personal";
        checkMedicalPermission.setSelected(false);
        checkOthersPermission.setSelected(false);

    }
    @FXML
    protected void onCheckedOthersPermission(){
        checkOthersPermission.setSelected(true);
        reasonShort = "Other Issues";
        checkPersonalPermission.setSelected(false);
        checkMedicalPermission.setSelected(false);
    }
    @FXML
    protected void onCheckedMedicalLeave(){
        checkMedicalLeave.setSelected(true);
        reasonShort = "Medical";
        checkPersonalLeave.setSelected(false);
        checkOthersLeave.setSelected(false);
    }
    @FXML
    protected void onCheckedPersonalLeave(){
        checkPersonalLeave.setSelected(true);
        reasonShort = "Personal";
        checkMedicalLeave.setSelected(false);
        checkOthersLeave.setSelected(false);
    }
    @FXML
    protected void onCheckedOthersLeave(){
        checkOthersLeave.setSelected(true);
        reasonShort = "Other Issues";
        checkPersonalLeave.setSelected(false);
        checkMedicalLeave.setSelected(false);
    }
    @FXML
    protected void onCheckedFromAM(){
        checkFromAM.setSelected(true);
        fromMeridiem = "AM";
        checkFromPM.setSelected(false);
    }
    @FXML
    protected void onCheckedFromPM(){
        checkFromPM.setSelected(true);
        fromMeridiem= "PM";
        checkFromAM.setSelected(false);
    }
    @FXML
    protected void onCheckedToAM(){
        checkToAM.setSelected(true);
        toMeridiem = "AM";
        checkToPM.setSelected(false);
    }
    @FXML
    protected void onCheckedToPM(){
        checkToPM.setSelected(true);
        toMeridiem = "PM";
        checkToAM.setSelected(false);
    }
    @FXML
    protected void onSubmitClickedWFH(){
        if(!fromDateWFH.getValue().toString().equals("") && !toDateWFH.getValue().toString().equals("") && !reasonShort.equals("") && !reasonWFH.getText().equals("")){
            database.AbsenceRecord(Id, fromDateWFH.getValue().toString(), toDateWFH.getValue().toString(),"NIL","NIL", WFHPane.getText(), reasonShort , reasonWFH.getText());
        }
    }
    @FXML
    protected void onSubmitClickedPermission(){
        if(!reasonShort.equals("") && !reasonPermission.getText().equals("")) {
            database.AbsenceRecord(Id, "NIL", "NIL", fromTimeH.getValue().toString() + ':' + fromTimeM.getValue().toString() + fromMeridiem, toTimeH.getValue().toString() + ':' + toTimeM.getValue().toString() + toMeridiem, PermissionPane.getText(), reasonShort, reasonPermission.getText());
        }
    }
    @FXML
    protected void onSubmitClickedLeave(){
        if(!fromDateLeave.getValue().toString().equals("") && !toDateLeave.getValue().toString().equals("") && !reasonShort.equals("") && !reasonLeave.getText().equals(" ")){
            database.AbsenceRecord(Id, fromDateLeave.getValue().toString(), toDateLeave.getValue().toString(),"NIL","NIL", LeavePane.getText(), reasonShort , reasonLeave.getText());
        }
    }
    @FXML
    protected void onCloseClicked(){
        Stage stage = (Stage) absenceDialog.getScene().getWindow();
        stage.close();
    }

    public void setId(String id) {
        Id = id;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory.IntegerSpinnerValueFactory valueFactoryHours = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12);
        SpinnerValueFactory.IntegerSpinnerValueFactory valueFactoryMinutes = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60);
        SpinnerValueFactory.IntegerSpinnerValueFactory valueFactoryHours1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12);
        SpinnerValueFactory.IntegerSpinnerValueFactory valueFactoryMinutes1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60);
        fromTimeH.setValueFactory(valueFactoryHours);
        fromTimeM.setValueFactory(valueFactoryMinutes);
        toTimeH.setValueFactory(valueFactoryHours1);
        toTimeM.setValueFactory(valueFactoryMinutes1);
    }
}
