package com.shsoftware.timesheet;

import javafx.scene.control.Alert;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Database {
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306";
    String user = "timesheet";
    String password = "timesheet";
    String command, Time, Date, UserId, HrId;
    String[] dt;

    ArrayList<String> Ids = new ArrayList<>(), Passwords = new ArrayList<>();

    public Database(){
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            command = "create database if not exists timesheet";
            statement.execute(command);
            command = "use timesheet";
            statement.execute(command);
            command = "create table if not exists Users_Data(firstName varchar(20), lastName varchar(20), id varchar(10), email varchar(100), password varchar(50), dob varchar(10), designation varchar(10), phoneNo varchar(10), presentAdd varchar(120), permanentAdd varchar(120))";
            statement.execute(command);
            command = "create table if not exists Logins_Log(userId varchar(10), time varchar(10), date varchar(10))";
            statement.execute(command);
            command = "create table if not exists Absence_Record(id varchar(10), fromDate varchar(10), toDate varchar(10), fromTime varchar(6), toTime varchar(6), category varchar(20), reasonShort varchar(20), reasonDescribed varchar(250))";
            statement.execute(command);
            command = "create table if not exists Notification(id varchar(10), fromId varchar(10), category varchar(20), shortDesc varchar(20), longDesc varchar(250), notification varchar(250))";
            statement.execute(command);
            command = "create table if not exists Jobs(id varchar(10), jobTopic varchar(50), jobDesc varchar(250), jobAssigned varchar(10), jobDue varchar(10))";
            statement.execute(command);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
            LocalDateTime now = LocalDateTime.now();
            dt = dtf.format(now).split(" ");
            Time = dt[0];
            Date = dt[1];
        }
        catch (Exception e){
            e.printStackTrace();
        }
        getData();
    }

    public void getData(){
        try{
            Ids.clear();
            Passwords.clear();
            command = "select id from Users_Data";
            resultSet = statement.executeQuery(command);
            while (resultSet.next()){
                Ids.add(resultSet.getString("id"));
            }
            command = "select password from Users_Data";
            resultSet = statement.executeQuery(command);
            while (resultSet.next()){
                Passwords.add(resultSet.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getIDs(){
        return Ids;
    }

    public ResultSet getNames(){
        try {
            command = "select firstName from Users_Data";
            resultSet = statement.executeQuery(command);
            return resultSet;
        } catch (Exception e){
           e.printStackTrace();
        }
        return null;
    }

    public void setJob(String Id, String jobTopic, String jobDesc, String jobAssigned, String jobDue){
        try {
            command = "insert into Jobs values('" + Id + "', '" + jobTopic + "', '" + jobDesc + "', '" + jobAssigned + "', '" + jobDue + "')";
            statement.execute(command);
            command = "insert into Notification values('" + Id + "', '" + "Nil" + "', '" + "Nil" + "', '" + "Nil" + "', '" + "Nil" + "', '" + "New Job has been Assigned" + "')";
            statement.execute(command);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public ResultSet getShortDesc(String Id){
        try{
            command = "select shortDesc from Notification where id = '" + Id + "'";
            resultSet = statement.executeQuery(command);
            return resultSet;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getFromIds(String Id){
        try{
            command = "select fromId from Notification where id = '" + Id + "'";
            resultSet = statement.executeQuery(command);
            return resultSet;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getLongDesc(String Id){
        try{
            command = "select longDesc from Notification where id = '" + Id + "'";
            resultSet = statement.executeQuery(command);
            return resultSet;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public ResultSet getCategory(String Id){
        try{
            command = "select category from Notification where id = '" + Id + "'";
            resultSet = statement.executeQuery(command);
            return resultSet;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public ResultSet getNotification(String Id){
        try{
            command = "select notification from Notification where id = '" + Id + "'";
            resultSet = statement.executeQuery(command);
            return resultSet;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public void DeleteNotifications(String Id){
        try {
            command = "delete from Notification where id='" + Id + "'";
            statement.execute(command);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public ResultSet getJobData(){
        try{
            command = "select * from Jobs";
            resultSet = statement.executeQuery(command);
            return resultSet;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public ResultSet getJobTopics(String Id){
        try{
            command = "select jobTopic from Jobs where id = '" + Id + "'";
            resultSet = statement.executeQuery(command);
            return resultSet;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public ResultSet getJobDescs(String Id){
        try{
            command = "select jobDesc from Jobs where id = '" + Id + "'";
            resultSet = statement.executeQuery(command);
            return resultSet;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public ResultSet getJobAssigns(String Id){
        try{
            command = "select jobAssigned from Jobs where id = '" + Id + "'";
            resultSet = statement.executeQuery(command);
            return resultSet;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public ResultSet getJobDues(String Id){
        try{
            command = "select jobDue from Jobs where id = '" + Id + "'";
            resultSet = statement.executeQuery(command);
            return resultSet;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public String getDesignation(String Id){
        try{
            command = "select designation from Users_Data where id = '" + Id + "'";
            resultSet = statement.executeQuery(command);
            resultSet.next();
            return resultSet.getString("designation");
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void AbsenceRecord(String Id, String FromDate, String ToDate, String FromTime, String ToTime, String Category, String ReasonShort, String ReasonDescribed){
        try {
            command = "insert into Absence_Record values('" + Id + "', '" + FromDate + "', '" + ToDate + "', '" + FromTime + "', '" + ToTime + "', '" + Category + "', '" + ReasonShort + "', '" + ReasonDescribed + "')";
            statement.execute(command);
            command = "insert into Notification values('" + getHrId() + "', '" + Id + "', '" + Category + "', '" + ReasonShort + "', '" + ReasonDescribed + "', '" + "NIL" + "')";
            statement.execute(command);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void Notification(String Id, String Notification){
        try {
            command = "insert into Notification values('" + Id + "', '" + "NIL" + "', '" + "NIL" + "', '" + "NIL" + "', '" + "NIL" + "', '" + Notification + "')";
            statement.execute(command);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getHrId() {
        try{
            command = "select id from Users_Data where designation = 'HR'";
            resultSet = statement.executeQuery(command);
            resultSet.next();
            HrId = resultSet.getString("id");
            return HrId;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean Register(String FirstName, String LastName, String Id, String Email, String Password, String Dob, String Designation, String PhoneNo, String PresentAdd, String PermanentAdd){
        if (Ids.contains(Id)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("ID Already Taken");
            alert.setContentText("Try new ID");
            alert.show();
        }
        else {
            try{
                command = "insert into Users_Data values('" + FirstName + "', '" + LastName + "', '" + Id + "', '" + Email + "', '" + Password + "', '" + Dob + "', '" + Designation + "', '" + PhoneNo + "', '" + PresentAdd + "', '" + PermanentAdd + "')";
                statement.execute(command);
                getData();
                return true;
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public String Login(String Id, String Password){
        if (Id.equals("admin") && Password.equals("Admin@123")){
            return "Admin";
        }
        else if (Ids.contains(Id) && Passwords.contains(Password) && (Ids.indexOf(Id) == Passwords.indexOf(Password))){
            try {
                command = "insert into Logins_Log values('" + Id + "', '" + Time + "', '" + Date + "')";
                statement.execute(command);
                return "User" + Id;
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid UserId or Password");
            alert.setContentText("Try Again");
            alert.show();
        }
        return "null";
    }

    public String getUserId(){
        try {
            command = "select userId from Logins_Log";
            resultSet = statement.executeQuery(command);
            while (resultSet.next()){
                UserId = resultSet.getString("userId");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return UserId;
    }

}