package com.example.softwaredesignproject4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class StudentsDatabase implements TableInterface, StudentsDatabaseInterface {

    String url, username, password;
    Connection connection;

    //Cons also connects to the database
    StudentsDatabase(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;

        this.connection = getConnection(url, username, password);
    }

    //Abstract Method from tableInterface return a connection object to the database complicated!!
    @Override
    public Connection getConnection(String url, String username, String password) {

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("\nConnection to the database server successful!");
        }
        catch (SQLException e) {
            System.out.println(e);
        }

        return connection;
    }

    class Schedule {

        String ddlCreateTable, ddlPopulateTable;
        String filename, nameTable;

        //Constructor
        Schedule(String ddlCreateTable, String filename, String nameTable) throws Exception {

            this.ddlCreateTable = ddlCreateTable;
            this.filename = filename;
            this.nameTable = nameTable;

            this.ddlPopulateTable = TableInterface.loadDataInFileTable(filename, nameTable);

            //Create Table I would guess
            TableInterface.dropTable(connection, nameTable);
            TableInterface.createTable(connection, ddlCreateTable);
            System.out.println("\nTable Schedule created successfully!");

            //Populate Table
            TableInterface.setLocalInFileLoading(connection);
            TableInterface.populateTable(connection, ddlPopulateTable);
            System.out.println("\nTable Schedule populated successfully!");
            ResultSet RS = TableInterface.getTable(connection, nameTable);
            System.out.println("\nQuery on Schedule executed successfully!");
        }

    }

    class Students {
        String CreateTable, PopulateTable;
        String nameTable;


        Students(String CreateTable, String nameTable) throws Exception {

            this.CreateTable = CreateTable;
            this.nameTable = nameTable;
            this.PopulateTable = StudentsDatabaseInterface.ddlInsertTableStudents;

            System.out.println(PopulateTable);

            //create Table
            TableInterface.dropTable(connection, nameTable);
            TableInterface.createTable(connection, CreateTable);
            System.out.println("\nTable Students created successfully");
            // populate Table
            TableInterface.populateTable(connection, PopulateTable);
            System.out.println("\nTable Students populated successfully");
            ResultSet RS = TableInterface.getTable(connection, nameTable);
            System.out.println("\nQuery on students executed successfully");


        }
    }

        class Courses {
            String CreateTable, PopulateTable;
            String nameToTable;
            String nameFromTable;

            Courses(String CreateTable, String nameToTable, String nameFromTable) throws SQLException {
                this.CreateTable = CreateTable;
                this.nameToTable = nameToTable;
                this.nameFromTable = nameFromTable;
                this.PopulateTable = StudentsDatabaseInterface.ddlInsertTableCourses(nameToTable, nameFromTable);

                System.out.println(PopulateTable);

                //create Table
                TableInterface.dropTable(connection, nameToTable);
                TableInterface.createTable(connection, CreateTable);
                System.out.println("\nTable Courses created successfully");
                //Populate Table
                TableInterface.insertFromSelect(connection, PopulateTable);
                System.out.println("\nTable Courses populated successfully");
                ResultSet RS = TableInterface.getTable(connection, nameToTable);
                System.out.println("\nQuery on Courses executed successfully");


            }

        }

        class Classes {
            String CreateTable, PopulateTable;
            String nameTable;

            Classes(String CreateTable, String nameTable) throws SQLException {
                this.CreateTable = CreateTable;
                this.nameTable = nameTable;
                this.PopulateTable = StudentsDatabaseInterface.ddlCreateTableClasses;
                System.out.println(PopulateTable);

                //create Table
                TableInterface.dropTable(connection, nameTable);
                TableInterface.createTable(connection, CreateTable);
                System.out.println("\nTable  Classes created successfully");
                //populate Table
                TableInterface.populateTable(connection, PopulateTable);
                System.out.println("\nTable Classes populated successfully");
                ResultSet RS = TableInterface.getTable(connection, nameTable);
                System.out.println("\nQuery on Classes executed successfully");

            }
        }

    class AggregateGrades {

        String ddlCreateTable, ddlPopulateTable;
        String nameToTable, nameFromTable;

        //Constructor
        AggregateGrades(String ddlCreateTable, String nameToTable, String nameFromTable) throws SQLException {

            this.ddlCreateTable = ddlCreateTable;
            this.nameToTable = nameToTable;
            this.nameFromTable = nameFromTable;

            this.ddlPopulateTable = StudentsDatabaseInterface.ddlCreateTableAggregateGrades;

            //Create Table I would guess
            TableInterface.dropTable(connection, nameToTable);
            TableInterface.createTable(connection, ddlCreateTable);
            System.out.println("\nTable AggregateGrades created successfully!");

            //Populate Table
            TableInterface.insertFromSelect(connection, ddlPopulateTable);
            System.out.println("\nTable Schedule populated successfully!");
            ResultSet RS = TableInterface.getTable(connection, nameToTable);
            System.out.println("\nQuery on AggregateGrades executed successfully!");
        }

        public Map<Character, Integer> getAggregateGrades(String nameToTable) {

            Map<Character, Integer> mapAggregateGrades = new HashMap<Character, Integer>();

            try {
                ResultSet RS = TableInterface.getTable(connection, nameToTable);

                while(RS.next()) {
                    mapAggregateGrades.put(RS.getString("grade").charAt(0), RS.getInt("numberStudents"));
                }
            }
            catch(SQLException e) {
                System.out.println(e);
            }
            return mapAggregateGrades;
        }
    }
}

