import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttendanceManagementSystem extends Application {

    private Map<String, Teacher> teacherMap;
    private List<Student> studentList;

    private TextField idField;
    private TextField nameField;
    private ComboBox<Teacher> teacherComboBox;
    private ComboBox<String> classComboBox;
    private Button loginButton;
    private Button takeAttendanceButton;
    private ListView<Student> studentListView;
    private Label absentCountLabel;

    private Teacher loggedInTeacher;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        initializeData();
        setupUI(primaryStage);
    }

    private void initializeData() {
        teacherMap = new HashMap<>();
        studentList = new ArrayList<>();

        // Create instances of Teacher class
        teacherMap.put("ENG001", new Teacher("ENG001", "Mr. John Smith", "7A"));
        teacherMap.put("MATH002", new Teacher("MATH002", "Ms. Emily Johnson", "7B"));
        teacherMap.put("SCI003", new Teacher("SCI003", "Mr. David Anderson", "7C"));
        teacherMap.put("SS004", new Teacher("SS004", "Mrs. Sarah Davis", "7D"));
        teacherMap.put("CS005", new Teacher("CS005", "Mr. Michael Brown", "7E"));
        teacherMap.put("PE006", new Teacher("PE006", "Ms. Jessica Wilson", "7F"));
        teacherMap.put("ART007", new Teacher("ART007", "Mrs. Samantha Taylor", "7G"));
        teacherMap.put("MUS008", new Teacher("MUS008", "Mr. Christopher Martin", "7H"));
        teacherMap.put("BEN009", new Teacher("BEN009", "Ms. Priya Rahman", "7I"));
        teacherMap.put("ISL010", new Teacher("ISL010", "Mr. Mohammed Ali", "7J"));

        // Create instances of Student class
        studentList.add(new Student("701", "John Anderson"));
        studentList.add(new Student("702", "Emily Wilson"));
        studentList.add(new Student("703", "David Johnson"));
        studentList.add(new Student("704", "Sarah Davis"));
        studentList.add(new Student("705", "Michael Smith"));
        studentList.add(new Student("706", "Jessica Brown"));
        studentList.add(new Student("707", "Christopher Taylor"));
        studentList.add(new Student("708", "Samantha Martin"));
        studentList.add(new Student("709", "Priya Rahman"));
        studentList.add(new Student("710", "Mohammed Ali"));
        studentList.add(new Student("711", "Emma Anderson"));
        studentList.add(new Student("712", "Daniel Wilson"));
        studentList.add(new Student("713", "Olivia Johnson"));
        studentList.add(new Student("714", "Ethan Davis"));
        studentList.add(new Student("715", "Ava Smith"));
        studentList.add(new Student("716", "Noah Brown"));
        studentList.add(new Student("717", "Mia Taylor"));
        studentList.add(new Student("718", "James Martin"));
        studentList.add(new Student("719", "Sophia Rahman"));
        studentList.add(new Student("720", "Benjamin Ali"));
        studentList.add(new Student("721", "Isabella Anderson"));
        studentList.add(new Student("722", "Alexander Wilson"));
        studentList.add(new Student("723", "Mia Johnson"));
        studentList.add(new Student("724", "William Davis"));
        studentList.add(new Student("725", "Sophia Smith"));
        studentList.add(new Student("726", "Michael Brown"));
        studentList.add(new Student("727", "Emily Taylor"));
        studentList.add(new Student("728", "Daniel Martin"));
        studentList.add(new Student("729", "Olivia Rahman"));
        studentList.add(new Student("730", "Ethan Ali"));
    }

    private void setupUI(Stage primaryStage) {
        primaryStage.setTitle("Attendance Management System");

        // Login UI
        GridPane loginPane = new GridPane();
        loginPane.setAlignment(Pos.CENTER);
        loginPane.setHgap(10);
        loginPane.setVgap(10);
        loginPane.setPadding(new Insets(20));

        idField = new TextField();
        idField.setPromptText("Teacher ID");

        nameField = new TextField();
        nameField.setPromptText("Teacher Name");

        loginButton = new Button("Login");
        loginButton.setOnAction(e -> login(primaryStage));

        loginPane.add(new Label("Teacher ID:"), 0, 0);
        loginPane.add(idField, 1, 0);
        loginPane.add(new Label("Teacher Name:"), 0, 1);
        loginPane.add(nameField, 1, 1);
        loginPane.add(loginButton, 1, 2);

        Scene loginScene = new Scene(loginPane, 400, 200);

        // Attendance UI
        BorderPane attendancePane = new BorderPane();
        attendancePane.setPadding(new Insets(20));

        HBox headerBox = new HBox();
        headerBox.setSpacing(10);
        headerBox.setAlignment(Pos.CENTER_LEFT);

        teacherComboBox = new ComboBox<>();
        teacherComboBox.setPromptText("Select Teacher");
        teacherComboBox.setOnAction(e -> handleTeacherSelection());

        classComboBox = new ComboBox<>();
        classComboBox.setPromptText("Select Class");
        classComboBox.setDisable(true);
        classComboBox.setOnAction(e -> handleClassSelection());

        headerBox.getChildren().addAll(new Label("Teacher:"), teacherComboBox, new Label("Class:"), classComboBox);

        VBox centerBox = new VBox();
        centerBox.setSpacing(10);
        centerBox.setAlignment(Pos.TOP_CENTER);

        studentListView = new ListView<>();
        studentListView.setPrefHeight(300);

        HBox attendanceBox = new HBox();
        attendanceBox.setSpacing(10);
        attendanceBox.setAlignment(Pos.CENTER);

        Button presentButton = new Button("Present");
        presentButton.setOnAction(e -> markAttendance(true));

        Button absentButton = new Button("Absent");
        absentButton.setOnAction(e -> markAttendance(false));

        takeAttendanceButton = new Button("Take Attendance");
        takeAttendanceButton.setDisable(true);
        takeAttendanceButton.setOnAction(e -> takeAttendance());

        absentCountLabel = new Label("Absent Count: 0");

        attendanceBox.getChildren().addAll(presentButton, absentButton, takeAttendanceButton, absentCountLabel);

        centerBox.getChildren().addAll(new Label("Students:"), studentListView, attendanceBox);

        attendancePane.setTop(headerBox);
        attendancePane.setCenter(centerBox);

        Scene attendanceScene = new Scene(attendancePane, 800, 500);

        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    private void login(Stage primaryStage) {
        String teacherID = idField.getText();
        String teacherName = nameField.getText();

        if (validateTeacher(teacherID, teacherName)) {
            loggedInTeacher = teacherMap.get(teacherID);
            teacherComboBox.setValue(loggedInTeacher);
            primaryStage.setScene(new Scene(new BorderPane(), 800, 500));
            primaryStage.setScene(new Scene(new BorderPane(), 800, 500));
            primaryStage.setScene(new Scene(new BorderPane(), 800, 500));
            primaryStage.setScene(attendanceScene);
        } else {
            showAlert(Alert.AlertType.ERROR, "Invalid credentials", "The provided teacher ID or name is incorrect.");
        }
    }

    private boolean validateTeacher(String teacherID, String teacherName) {
        return teacherMap.containsKey(teacherID) && teacherMap.get(teacherID).getTeacherName().equals(teacherName);
    }

    private void handleTeacherSelection() {
        Teacher selectedTeacher = teacherComboBox.getValue();
        classComboBox.getItems().clear();
        classComboBox.setDisable(selectedTeacher == null);
        takeAttendanceButton.setDisable(true);

        if (selectedTeacher != null) {
            classComboBox.getItems().add(selectedTeacher.getClassName());
        }
    }

    private void handleClassSelection() {
        String selectedClass = classComboBox.getValue();
        takeAttendanceButton.setDisable(selectedClass == null);
        updateStudentList();
    }

    private void updateStudentList() {
        String selectedClass = classComboBox.getValue();
        studentListView.getItems().clear();

        if (selectedClass != null) {
            for (Student student : studentList) {
                studentListView.getItems().add(student);
            }
        }
    }

    private void markAttendance(boolean present) {
        Student selectedStudent = studentListView.getSelectionModel().getSelectedItem();

        if (selectedStudent != null) {
            selectedStudent.setAttendanceStatus(present ? "Present" : "Absent");
        }

        updateAbsentCount();
    }

    private void updateAbsentCount() {
        int absentCount = 0;
        for (Student student : studentList) {
            if (student.getAttendanceStatus().equals("Absent")) {
                absentCount++;
            }
        }

        absentCountLabel.setText("Absent Count: " + absentCount);
    }

    private void takeAttendance() {
        for (Student student : studentList) {
            if (student.getAttendanceStatus().equals("Absent")) {
                // Implement sending notifications to parents of absent students
            }
        }

        // Implement storing attendance records in a database

        showAlert(Alert.AlertType.INFORMATION, "Attendance Taken", "Attendance for the selected class has been recorded.");
        clearAttendance();
    }

    private void clearAttendance() {
        teacherComboBox.getSelectionModel().clearSelection();
        classComboBox.getSelectionModel().clearSelection();
        studentListView.getItems().clear();
        takeAttendanceButton.setDisable(true);
        absentCountLabel.setText("Absent Count: 0");
        loggedInTeacher = null;
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}