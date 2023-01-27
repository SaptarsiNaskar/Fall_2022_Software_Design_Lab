package com.example.softwaredesignproject4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

interface TableInterface {

    //Abstract Method implemented in extended classes
    Connection getConnection(String url, String username, String password);

    //Static Method Creates a Schema(look up lacking understanding Sap).
    static void createSchema(Connection connection, String nameSchema) throws SQLException {

        PreparedStatement psCreateTable = connection.prepareStatement("CREATE SCHEMA " + nameSchema);

        try {
            psCreateTable.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }

    //Drop a Table Status: ? I've to try in code
    static void dropTable(Connection connection, String nameTable) throws SQLException {

        PreparedStatement psDropTable = connection.prepareStatement("DROP TABLE IF EXISTS " + nameTable);

        try {
            psDropTable.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }

    static void createTable(Connection connection, String ddlCreateTable) throws SQLException {

        PreparedStatement psCreateTable = connection.prepareStatement(ddlCreateTable);

        try {
            psCreateTable.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }

    //Set Local in-file parameter for local data loading: MySQL server(takes data from the txt file)
    static void setLocalInFileLoading(Connection connection) throws SQLException {

        PreparedStatement psSetLocalInFileLoading = connection.prepareStatement("SET GLOBAL local_infile = 1");

        try {
            psSetLocalInFileLoading.executeUpdate();
            System.out.println("\nGlobal local-infile set successfully");
        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }

    //takes data from file and creates table ?
    static String loadDataInFileTable(String nameFile, String nameTable) {
        return "LOAD DATA LOCAL INFILE '" + nameFile + "' INTO TABLE " + nameTable +
                " COLUMNS TERMINATED BY '\t'" +
                " LINES TERMINATED BY '\n'" +
                " IGNORE 1 LINES";
    }

    static void populateTable(Connection connection, String ddlPopulateTable) throws SQLException {

        PreparedStatement psPopulateTable = connection.prepareStatement(ddlPopulateTable);

        try {
            psPopulateTable.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }

    static void updateField(Connection connection, String ddlUpdateField) throws SQLException{

        PreparedStatement psUpdateField = connection.prepareStatement(ddlUpdateField);

        try {
            psUpdateField.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e);
        }

    }

    //Delete a record from a table by specifying Primary Key I would assume
    static void deleteRecord(Connection connection, String ddlDeleteRecord) throws SQLException {

        PreparedStatement psDeleteRecord = connection.prepareStatement(ddlDeleteRecord);

        try{
            psDeleteRecord.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }

    //Get a Table by name
    static ResultSet getTable(Connection connection, String nameTable) throws SQLException {

        ResultSet RS = null;

        PreparedStatement psDisplayTable = connection.prepareStatement("SELECT * FROM " + nameTable);

        try {
            RS = psDisplayTable.executeQuery();
        }
        catch (SQLException e) {
            System.out.println(e);
        }

        return RS;
    }

    static void insertFromSelect(Connection connection, String ddlPopulateTable) throws SQLException{

        PreparedStatement psInsertFromSelect = connection.prepareStatement(ddlPopulateTable);

        try{
            psInsertFromSelect.executeUpdate();
        }catch(SQLException e){
            System.out.println(e);
        }
    }



}
