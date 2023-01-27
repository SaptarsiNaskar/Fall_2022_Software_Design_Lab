package com.example.softwaredesignproject4;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.stage.Stage;
import java.util.Map;


public class Tester extends Application {

    @Override
    public void start(Stage PrimaryStage) throws Exception {

        Group root = new Group();
        Scene scene = new Scene(root, 610, 610);
        Stage stage = new Stage();
        stage.setTitle("SoftwareDesignProject4MySQL");


        Canvas canvas = new Canvas(610, 610);
        GraphicsContext GC = canvas.getGraphicsContext2D();

        MyPoint point = new MyPoint(305, 305 , myColor.BLACK);


        String ddlCreateTable;
        String ddlPopulateTable;
        String nameFromTable, nameToTable;
        String filename, nameTable;
        String url, username, password;
        filename = "/Users/sap/IdeaProjects/SoftwareDesignProject4/ScheduleSpring2022.txt";
        url = "jdbc:mysql://localhost:3306/demo?allowLoadLocalInfile=true";
        username = "root";
        password = "46aec56a";
        StudentsDatabase studentsDatabase = new StudentsDatabase(url, username, password);
        String ddlCreateTable1;
        ddlCreateTable1 = "INSERT INTO AggregateValue VALUES ('A', 4)," + "('B', 5)" + "('C', 6)" + "('D', 7)";
        //studentsDatabase.getConnection(url, username, password);

        //Students. refers to Schema Name.
        nameTable = "demo.Schedule";
        ddlCreateTable = StudentsDatabaseInterface.ddlCreateTableSchedule;
        StudentsDatabase.Schedule schedule = studentsDatabase.new Schedule(ddlCreateTable, filename, nameTable);

        nameToTable = "demo.Courses";
        nameFromTable = "demo.Schedule";
        ddlCreateTable = StudentsDatabaseInterface.ddlCreateTableCourses;
        StudentsDatabase.Courses courses = studentsDatabase.new Courses(ddlCreateTable, nameToTable, nameFromTable);

        nameTable = "demo.Students";
        ddlCreateTable = StudentsDatabaseInterface.ddlCreateTableStudents;
        StudentsDatabase.Students students = studentsDatabase.new Students(ddlCreateTable, nameTable);

        nameTable = "demo.Classes";
        ddlCreateTable = StudentsDatabaseInterface.getDdlCreateTableClasses;
        StudentsDatabase.Classes classes = studentsDatabase.new Classes(ddlCreateTable, nameTable);

        ///*
        nameToTable = "demo.AggregateGrades";
        nameFromTable = "demo.Classes";
        ddlCreateTable = StudentsDatabaseInterface.ddlCreateTableAggregateGrades;
        StudentsDatabase.AggregateGrades aggregateGrades = studentsDatabase.new AggregateGrades(ddlCreateTable, nameToTable, nameFromTable);
        TableInterface.createTable(studentsDatabase.connection, ddlCreateTable1);
        Map<Character, Integer> aggGrades = aggregateGrades.getAggregateGrades(nameToTable);
        System.out.println("\nAggregate Grades: " + aggGrades);

        HistogramAlphaBet H =  new HistogramAlphaBet(aggGrades);
        HistogramAlphaBet.MyPieChart pieChart = H.new MyPieChart(6, point, 200, 0.0);
        pieChart.getMyPieChart();


        pieChart.draw(GC);


        TableInterface.updateField(studentsDatabase.connection, ddlCreateTable);
        TableInterface.deleteRecord(studentsDatabase.connection, ddlCreateTable);
        //*/

        root.getChildren().add(canvas);
        stage.setScene(scene);
        stage.show();
    }



    public static void main(String[] args) {
        launch();
    }
}