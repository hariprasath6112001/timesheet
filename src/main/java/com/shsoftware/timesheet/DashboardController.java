package com.shsoftware.timesheet;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class DashboardController extends Database implements Initializable {
    @FXML
    private GridPane dashboardScene;
    @FXML
    private Label profileName, designationId, dateBox, jobBoxDate;
    @FXML
    private DatePicker selectDate;
    @FXML
    private Hyperlink r0c0, r0c1, r0c2, r0c3, r0c4, r0c5, r0c6, r1c0, r1c1, r1c2, r1c3, r1c4, r1c5, r1c6, r2c0, r2c1, r2c2, r2c3, r2c4, r2c5, r2c6, r3c0, r3c1, r3c2, r3c3, r3c4, r3c5, r3c6, r4c0, r4c1, r4c2, r4c3, r4c4, r4c5, r4c6, r5c0, r5c1;
    @FXML
    private VBox jobBoxText;

    String FirstName, LastName, Id, Email, DOB, Designation, PhoneNo, PresentAdd, PermanentAdd, Time, Date, selectedDate, TodayDate;
    String[] dt;
    ArrayList<String> Dates = new ArrayList<>(), jobsId = new ArrayList<>(), jobTopics = new ArrayList<>(), jobDescs = new ArrayList<>(), jobAssigns = new ArrayList<>(), jobDues = new ArrayList<>();
    ResultSet resultSet;

    // Extensions
    Database database = new Database();

    //FXML Functions
    @FXML
    protected void onHomeClicked(){
        System.out.println("Home Clicked");
    }
    @FXML
    protected void onDashboardClicked(){
        System.out.println("Dashboard Clicked");
    }
    @FXML
    protected void onLogoutBtnClicked(){
        openLoginPage();
    }
    @FXML
    protected void onShowMonthClicked(){
        String[] Date = String.valueOf(selectDate.getValue()).split("-");
        String finalDate = Date[2] + "/" + Date[1] + "/" + Date[0];
        int d = getDateStart(finalDate);
        int m = getMonth(finalDate);
        setDates(d, m);
    }
    @FXML
    protected void onTodayClicked(){
        int dateStart = getDateStart(Date);
        int month = getMonth(Date);
        setDates(dateStart, month);
    }
    @FXML
    protected void onProfileBtnClicked(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profileDialog.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 550, 460);
            ProfileDialogController profileDialogController = fxmlLoader.getController();
            profileDialogController.setData(FirstName, LastName, Id, Email, DOB, Designation, PhoneNo, PresentAdd, PermanentAdd);
            scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.setTitle("Profile");
            stage.setResizable(false);
            stage.showAndWait();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void onDateClicked(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("absenceDialog.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 615, 420);
            AbsenceDialogController absenceDialogController = fxmlLoader.getController();
            absenceDialogController.setId(Id);
            scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.setTitle("Absence Record");
            stage.setResizable(false);
            stage.showAndWait();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void onNotificationBtnClicked(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("notificationDialog.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            NotificationDialogController notificationDialogController = fxmlLoader.getController();
            notificationDialogController.setId(Id);
            notificationDialogController.setData();
            scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.setTitle("Notification");
            stage.setResizable(false);
            stage.showAndWait();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    protected void assignBtnClicked(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("jobDialog.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            JobDialogController jobDialogController = fxmlLoader.getController();
            jobDialogController.setId(Id);
            jobDialogController.setIds();
            scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.setTitle("Job");
            stage.setResizable(false);
            stage.showAndWait();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    // User Functions
    public void openLoginPage(){
        try {
            Stage stage = (Stage) dashboardScene.getScene().getWindow();
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
            System.out.println("Logout Success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getUserData(String uid){
        try {
            command = "select * from Users_Data where id = '" + uid + "'";
            resultSet = statement.executeQuery(command);
            while (resultSet.next()){
                FirstName = resultSet.getString("firstName");
                LastName = resultSet.getString("lastName");
                Id = resultSet.getString("id");
                Email = resultSet.getString("email");
                DOB = resultSet.getString("dob");
                Designation = resultSet.getString("designation");
                PhoneNo = resultSet.getString("phoneNo");
                PresentAdd = resultSet.getString("presentAdd");
                PermanentAdd = resultSet.getString("permanentAdd");
            }
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
            LocalDateTime now = LocalDateTime.now();
            dt = dtf.format(now).split(" ");
            Time = dt[0];
            Date = dt[1];
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setUserData(){
        profileName.setText("Welcome, " + FirstName);
        designationId.setText("(" + Designation + " | " + Id +")");
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date d = sdf.parse(Date.replace('/', '-'));
            sdf.applyPattern("EEEE d MMM yyyy");
            dateBox.setText(sdf.format(d));
            jobBoxDate.setText(sdf.format(d));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public int getDateStart(String Date){
        try {
            String[] date = Date.split("/");
            String finalDate = "01-" + date[1] + "-" + date[2];
            String dateSet = date[0] + "-" + date[1] + "-" + date[2];
            String selectDateSet = date[2] + "-" + date[1] + "-" + date[0];
            TodayDate = selectDateSet;
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date d = sdf.parse(finalDate);
            Date d1 = sdf.parse(dateSet);
            sdf.applyPattern("EEEE d MMM yyyy");
            String Day = sdf.format(d).split(" ")[0];
            String DateSet = sdf.format(d1);
            dateBox.setText(DateSet);
            jobBoxDate.setText(DateSet);
            selectDate.setValue(LocalDate.parse(selectDateSet));
            switch (Day) {
                case "Monday":
                    return 0;
                case "Tuesday":
                    return 1;
                case "Wednesday":
                    return 2;
                case "Thursday":
                    return 3;
                case "Friday":
                    return 4;
                case "Saturday":
                    return 5;
                case "Sunday":
                    return 6;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public boolean leapYear(int Year){
        if (Year % 4 == 0){
            if (Year % 100 == 0){
                return Year % 400 == 0;
            }
            else {
                return true;
            }
        }
        return false;
    }

    public int getMonth(String Date){
        try {
            String[] date = Date.split("/");
            selectedDate = date[0];
            int year = Integer.parseInt(date[2]);
            String finalDate = "01-" + date[1] + "-" + date[2];
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date d = sdf.parse(finalDate);
            sdf.applyPattern("EEEE d MMM yyyy");
            String Month = sdf.format(d).split(" ")[2];
            switch (Month){
                case "Jan":
                case "Mar":
                case "May":
                case "Jul":
                case "Aug":
                case "Oct":
                case "Dec":
                    return 31;
                case "Feb":
                    if (leapYear(year))
                        return 29;
                    else
                        return 28;
                case "Apr":
                case "Jun":
                case "Sep":
                case "Nov":
                    return 30;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return 31;
    }

    public void setDates(int d, int m){
        int date = 1;
        Dates.clear();
        for (int i = 0; i < 37; i++){
            if (i >= d && date <= m){
                Dates.add(String.valueOf(date));
                date++;
            }
            else {
                Dates.add("");
            }
        }
        r0c0.setText(Dates.get(0));
        r0c1.setText(Dates.get(1));
        r0c2.setText(Dates.get(2));
        r0c3.setText(Dates.get(3));
        r0c4.setText(Dates.get(4));
        r0c5.setText(Dates.get(5));
        r0c6.setText(Dates.get(6));
        r1c0.setText(Dates.get(7));
        r1c1.setText(Dates.get(8));
        r1c2.setText(Dates.get(9));
        r1c3.setText(Dates.get(10));
        r1c4.setText(Dates.get(11));
        r1c5.setText(Dates.get(12));
        r1c6.setText(Dates.get(13));
        r2c0.setText(Dates.get(14));
        r2c1.setText(Dates.get(15));
        r2c2.setText(Dates.get(16));
        r2c3.setText(Dates.get(17));
        r2c4.setText(Dates.get(18));
        r2c5.setText(Dates.get(19));
        r2c6.setText(Dates.get(20));
        r3c0.setText(Dates.get(21));
        r3c1.setText(Dates.get(22));
        r3c2.setText(Dates.get(23));
        r3c3.setText(Dates.get(24));
        r3c4.setText(Dates.get(25));
        r3c5.setText(Dates.get(26));
        r3c6.setText(Dates.get(27));
        r4c0.setText(Dates.get(28));
        r4c1.setText(Dates.get(29));
        r4c2.setText(Dates.get(30));
        r4c3.setText(Dates.get(31));
        r4c4.setText(Dates.get(32));
        r4c5.setText(Dates.get(33));
        r4c6.setText(Dates.get(34));
        r5c0.setText(Dates.get(35));
        r5c1.setText(Dates.get(36));
        // Show or Hide Unused Labels
        labelShowHide();
        setJobs();
    }

    public void labelShowHide(){
        r0c0.setVisible(!r0c0.getText().equals(""));
        r0c1.setVisible(!r0c1.getText().equals(""));
        r0c2.setVisible(!r0c2.getText().equals(""));
        r0c3.setVisible(!r0c3.getText().equals(""));
        r0c4.setVisible(!r0c4.getText().equals(""));
        r0c5.setVisible(!r0c5.getText().equals(""));
        r0c6.setVisible(!r0c6.getText().equals(""));
        r4c0.setVisible(!r4c0.getText().equals(""));
        r4c1.setVisible(!r4c1.getText().equals(""));
        r4c2.setVisible(!r4c2.getText().equals(""));
        r4c3.setVisible(!r4c3.getText().equals(""));
        r4c4.setVisible(!r4c4.getText().equals(""));
        r4c5.setVisible(!r4c5.getText().equals(""));
        r4c6.setVisible(!r4c6.getText().equals(""));
        r5c0.setVisible(!r5c0.getText().equals(""));
        r5c1.setVisible(!r5c1.getText().equals(""));
    }

    public void setJobs(){
        jobBoxText.getChildren().clear();
        jobsId.clear();
        jobTopics.clear();
        jobDescs.clear();
        jobAssigns.clear();
        jobDues.clear();
        if (Id.equals(database.getHrId())){
            resultSet = database.getJobData();
            try{
                while (resultSet.next()){
                    jobsId.add(resultSet.getString("id"));
                    jobTopics.add(resultSet.getString("jobTopic"));
                    jobDescs.add(resultSet.getString("jobDesc"));
                    jobAssigns.add(resultSet.getString("jobAssigned"));
                    jobDues.add(resultSet.getString("jobDue"));
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }else {
            resultSet = database.getJobTopics(Id);
            try{
                while (resultSet.next()){
                    jobTopics.add(resultSet.getString("jobTopic"));
                }
            } catch (Exception e){
                e.printStackTrace();
            }
            resultSet = database.getJobDescs(Id);
            try{
                while (resultSet.next()){
                    jobDescs.add(resultSet.getString("jobDesc"));
                }
            } catch (Exception e){
                e.printStackTrace();
            }
            resultSet = database.getJobAssigns(Id);
            try{
                while (resultSet.next()){
                    jobAssigns.add(resultSet.getString("jobAssigned"));
                }
            } catch (Exception e){
                e.printStackTrace();
            }
            resultSet = database.getJobDues(Id);
            try{
                while (resultSet.next()){
                    jobDues.add(resultSet.getString("jobDue"));
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        int len = jobTopics.size();
        if (len == 0 || !jobAssigns.contains(TodayDate)){
            Label label = new Label("No Jobs");
            jobBoxText.getChildren().add(label);
        }else if (jobAssigns.contains(TodayDate)){
            if (Id.equals(database.getHrId())){
                for (int i = 0;i < len; i++){
                    VBox vBox = new VBox();
                    vBox.setSpacing(10);
                    String css = "-fx-border-color: black;\n" +
                            "-fx-border-width: 2px;\n";
                    vBox.setStyle(css);
                    vBox.setPadding(new Insets(10, 10, 10, 10));
                    Label label0 = new Label("Id: " + jobsId.get(i));
                    label0.setStyle("-fx-font: 18;");
                    Label label = new Label("Job Topic: " + jobTopics.get(i));
                    label.setStyle("-fx-font: 18;");
                    Label label1 = new Label("Description: " + jobDescs.get(i));
                    label1.setStyle("-fx-font: 18;");
                    Label label2 = new Label("Assigned Date: " + jobAssigns.get(i));
                    label2.setStyle("-fx-font: 18;");
                    Label label3 = new Label("Due Date: " + jobDues.get(i));
                    label3.setStyle("-fx-font: 18");
                    vBox.getChildren().add(label0);
                    vBox.getChildren().add(label);
                    vBox.getChildren().add(label1);
                    vBox.getChildren().add(label2);
                    vBox.getChildren().add(label3);
                    jobBoxText.getChildren().add(vBox);
                }
            }else{
                for (int i = 0; i<len; i++){
                    if (jobAssigns.get(i).equals(TodayDate)){
                        VBox vBox = new VBox();
                        vBox.setSpacing(10);
                        String css = "-fx-border-color: black;\n" +
                                "-fx-border-width: 2px;\n";
                        vBox.setStyle(css);
                        vBox.setPadding(new Insets(10, 10, 10, 10));
                        Label label = new Label("Job Topic: " + jobTopics.get(i));
                        label.setStyle("-fx-font: 18;");
                        Label label1 = new Label("Description: " + jobDescs.get(i));
                        label1.setStyle("-fx-font: 18;");
                        Label label2 = new Label("Assigned Date: " + jobAssigns.get(i));
                        label2.setStyle("-fx-font: 18;");
                        Label label3 = new Label("Due Date: " + jobDues.get(i));
                        label3.setStyle("-fx-font: 18;");
                        vBox.getChildren().add(label);
                        vBox.getChildren().add(label1);
                        vBox.getChildren().add(label2);
                        vBox.getChildren().add(label3);
                        jobBoxText.getChildren().add(vBox);
                    }
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String uid = database.getUserId();
        getUserData(uid);
        setUserData();
        int dateStart = getDateStart(Date);
        int month = getMonth(Date);
        setDates(dateStart, month);
        setJobs();
    }
}
