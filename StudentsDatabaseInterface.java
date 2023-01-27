package com.example.softwaredesignproject4;

interface StudentsDatabaseInterface {
    String ddlCreateTableSchedule = "CREATE TABLE Schedule( " +
            "courseId CHAR(12) NOT NULL UNIQUE, " +
            "sectionNumber VARCHAR(8) NOT NULL UNIQUE, " +
            "title VARCHAR(64), " +
            "year INT, " +
            "semester CHAR(16), " +
            "instructor VARCHAR(24), " +
            "department CHAR(16), " +
            "program VARCHAR(48), " +
            "PRIMARY KEY(courseID, sectionNumber))";

    String ddlCreateTableStudents = "CREATE TABLE Students(" +
            "emplID Int PRIMARY KEY, " +
            "firstName VARCHAR(32) not null, " +
            "lastName VARCHAR(32) not null, " +
            "email VARCHAR(64) not null, " +
            "gender CHAR CHECK (gender = 'F' OR gender = 'M' OR gender = 'U'))";
    String ddlCreateTableCourses = "CREATE TABLE Courses(" +
            "courseID CHAR(12) PRIMARY KEY, " +
            "title VARCHAR(64), " +
            "department CHAR(16), " +
            "program VARCHAR(48))";
    String ddlCreateTableClasses = "CREATE TABLE Classes(" +
            "courseID CHAR(12), " +
            "emplID Int " +
            "sectionNumber VARCHAR(8), " +
            "year INT, " +
            "semester CHAR(16), " +
            "grade CHAR CHECK (grade = 'A' OR grade = 'B' OR grade = 'C' OR grade = 'D' OR grade = 'F' OR grade = 'W'), " +
            "PRIMARY KEY(empID, courseID, sectionNumber))";

    String ddlInsertTableStudents = "INSERT INTO Students VALUES (0000001, 'F-NAME-01', 'L-NAME-01', '0001@GMAIL.COM', 'M'), " +
            "(0000002, 'F-NAME-02', 'L-NAME-02', '0002@GMAIL.COM', 'F'), " +
            "(0000003, 'F-NAME-03', 'L-NAME-03', '0003@GMAIL.COM', 'M'), " +
            "(0000004, 'F-NAME-04', 'L-NAME-04', '0004@GMAIL.COM', 'F'), " +
            "(0000005, 'F-NAME-05', 'L-NAME-05', '0005@GMAIL.COM', 'M'), " +
            "(0000006, 'F-NAME-06', 'L-NAME-06', '0006@GMAIL.COM', 'F'), " +
            "(0000007, 'F-NAME-07', 'L-NAME-07', '0007@GMAIL.COM', 'M'), " +
            "(0000008, 'F-NAME-08', 'L-NAME-08', '0008@GMAIL.COM', 'F'), " +
            "(0000009, 'F-NAME-09', 'L-NAME-09', '0009@GMAIL.COM', 'M'), " +
            "(0000010, 'F-NAME-10', 'L-NAME-10', '0010@GMAIL.COM', 'F')";

    static String ddlInsertTableCourses(String nameToTable, String nameFromTable){
        return "INSERT INTO " + nameToTable +
                " SELECT courseId, title, department, program" +
                " FROM " + nameFromTable;
    }

    String ddlCreateTableAggregateGrades = "CREATE TABLE AggregateGrades(grade CHAR, numberStudents INT";
}