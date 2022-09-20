module com.shsoftware.timesheet {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires mysql.connector.java;
    requires java.sql;

    opens com.shsoftware.timesheet to javafx.fxml;
    exports com.shsoftware.timesheet;
}