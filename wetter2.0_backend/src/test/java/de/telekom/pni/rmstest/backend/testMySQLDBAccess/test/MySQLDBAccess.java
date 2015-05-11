package de.telekom.pni.rmstest.backend.testMySQLDBAccess.test;


import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MySQLDBAccess {
    private Connection connect = null;
    private Statement statement = null;
    // private PreparedStatement preparedStatement = null;
    private ResultSet resultSet1 = null;
    private ResultSet resultSet2 = null;

    String password = "aaron123";
    String username = "aaron";
    String url = "//10.224.192.55/wetterdaten_next";
    String loginData = "jdbc:mysql:" + url + "?" + "user=" + username + "&password=" + password;

    public MySQLDBAccess() {

    }

    public void printDBData() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            //conn = DriverManager.getConnection(url, username, password);

            // setup the connection with the DB.
            //connect = DriverManager.getConnection("jdbc:mysql://10.224.192.55/wetterdaten_next?"+ "user=aaron&password=aaron123");
            connect = DriverManager.getConnection(loginData);
            //connect = DriverManager.getConnection(url, username, password);
            // statements allow to issue SQL queries to the database
            statement = connect.createStatement();

            // resultSet gets the result of the SQL query
            resultSet1 = statement.executeQuery("select * "
                    + "from ws "
                    + "where bundesland_name = \"hessen\" "
                    + "and LANDKREIS_BEZEICHNUNG = \"kreis\" "
                    + "and POPULATION > 7000;");

            writeResultSet(resultSet1);


            System.out.println(" Erfolgreich");
            System.out.println(" Erfolgreich");

            resultSet1 = statement.executeQuery("select * "
                    + "from ws "
                    + "ORDER  BY RAND() "
                    + "LIMIT 5 ;");

            writeResultSet(resultSet1);


        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    private void writeMetaData(ResultSet resultSet) throws SQLException {
        // now get some metadata from the database
        System.out.println("The columns in the table are: ");
        System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
        for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
            System.out.println("Column " + i + " " + resultSet.getMetaData().getColumnName(i));
        }
    }

    private void writeResultSet(ResultSet resultSet) throws SQLException {
        // resultSet is initialised before the first data set
        while (resultSet.next()) {
            // it is possible to get the columns via name
            // also possible to get the columns via the column number
            // which starts at 1
            // e.g., resultSet.getSTring(2);
            String user = resultSet.getString("bundesland_name");
            String website = resultSet.getString("landkreis_name");
            String summary = resultSet.getString("postleitzahl");
            String code = resultSet.getString("code_uni");
            String comment = resultSet.getString("url_toi");
            System.out.println("User: " + user);
            System.out.println("Website: " + website);
            System.out.println("Summary: " + summary);
            System.out.println("Date: " + code);
            System.out.println("Comment: " + comment);
        }
    }


    // you need to close all three to make sure
    private void close() {
        try {
            resultSet1.close();
            resultSet2.close();
            statement.close();
            connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        MySQLDBAccess myDB = new MySQLDBAccess();
        myDB.printDBData();
    }
}
