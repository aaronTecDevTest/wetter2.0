package de.telekom.pni.rmstest.backend.dataStorage;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import de.telekom.pni.rmstest.backend.manager.PropertiesManager_New;
import de.telekom.pni.rmstest.backend.dataStorage.interfaces.DBAccess;
import org.apache.log4j.Logger;

public class BackendDBAccessImpl implements DBAccess {

    private static final Logger log = Logger.getLogger(BackendDBAccessImpl.class);
    private static Connection connect = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;
//	private PreparedStatement preparedStatement = null;

    private static Properties DB = PropertiesManager_New.getInstance().getProperties("db");

    String password = DB.getProperty("general.password");//"aaron123";
    String username = DB.getProperty("general.username");//"aaron";
    String url = DB.getProperty("general.ip");//"//10.224.192.55";
    String dbName = DB.getProperty("general.DBname");//"/wetterdaten_next";

    String loginData1 = "jdbc:mysql://" + url + dbName + "?"
            + "user=" + username
            + "&password=" + password;
    String loginData2 = "jdbc:mysql://" + url + dbName + "?" + "user=" + username + "&password=" + password;


    public BackendDBAccessImpl() {
    }

    @Override
    public void getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(loginData1);
            statement = connect.createStatement();

            //    System.out.println("bin drin!!");
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            log.debug("DB Connection fail!");
        }
    }


    @Override
    public void closeConnection() {
        try {
            connect.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            log.debug("DB closding fail!");
        }
    }

    @Override
    public ResultSet getResult() {
        return resultSet;
    }


    @Override
    public List<WetterDeutschland> getWetterDeutschland(String sql) {

        List<WetterDeutschland> temp = new ArrayList<WetterDeutschland>();
        try {
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                WetterDeutschland wt = new WetterDeutschland();

                wt.setBUNDESLAND_NAME(resultSet.getString("bUNDESLAND_NAME"));
                wt.setBUNDESLAND_TYP(resultSet.getString("bUNDESLAND_TYP"));
                wt.setCODE_UNI(resultSet.getString("cODE_UNI"));
                wt.setCOUNTRY(resultSet.getString("cOUNTRY"));
                wt.setCOUNTRY_RANKING(resultSet.getString("cOUNTRY_RANKING"));
                wt.setCOUNTRY_SHORT(resultSet.getString("cOUNTRY_SHORT"));
                wt.setId(resultSet.getString("Id"));
                wt.setLANDKREIS_BEZEICHNUNG(resultSet.getString("lANDKREIS_BEZEICHNUNG"));
                wt.setLANDKREIS_NAME(resultSet.getString("lANDKREIS_NAME"));
                wt.setLANDKREIS_NAMENSZUSATZ(resultSet.getString("lANDKREIS_NAMENSZUSATZ"));
                wt.setLAT(resultSet.getString("lAT"));
                wt.setLOCATION_ROMAN(resultSet.getString("lOCATION_ROMAN"));
                wt.setLON(resultSet.getString("lON"));
                wt.setPf_LAT(resultSet.getString("pf_LAT"));
                wt.setPf_LOCATION(resultSet.getString("pf_LOCATION"));
                wt.setPf_LOCATION_ROMAN(resultSet.getString("pf_LOCATION_ROMAN"));
                wt.setPf_LOCATION_SEARCH_V1(resultSet.getString("pf_LOCATION_SEARCH_V1"));
                wt.setPf_LON(resultSet.getString("pf_LON"));
                wt.setPf_mCode(resultSet.getString("pf_mCode"));
                wt.setPf_PLACE_DESCR(resultSet.getString("pf_PLACE_DESCR"));
                wt.setPf_URL(resultSet.getString("pf_URL"));
                wt.setPf_ZIP(resultSet.getString("pf_ZIP"));
                wt.setPLACE_ID(resultSet.getString("pLACE_ID"));
                wt.setPOPULATION(resultSet.getString("pOPULATION"));
                wt.setPOSTLEITZAHL(resultSet.getString("pOSTLEITZAHL"));
                wt.setTOP_WOHNORT(resultSet.getString("tOP_WOHNORT"));
                wt.setURL_ALT(resultSet.getString("uRL_ALT"));
                wt.setURL_DEFAULT(resultSet.getString("uRL_DEFAULT"));
                wt.setURL_TOI(resultSet.getString("uRL_TOI"));
                wt.setUSE_STATION(resultSet.getString("USE_STATION"));
                wt.setWOHNORT_GEMEINDESCHLUESSEL(resultSet.getString("wOHNORT_GEMEINDESCHLUESSEL"));
                wt.setWOHNORT_LOKAL(resultSet.getString("wOHNORT_LOKAL"));
                wt.setWOHNORT_NAME(resultSet.getString("wOHNORT_NAME"));
                wt.setWOHNORT_NAMENSZUSATZ(resultSet.getString("wOHNORT_NAMENSZUSATZ"));
                wt.setWOHNORT_STADTTEIL_NAME(resultSet.getString("wOHNORT_STADTTEIL_NAME"));
                wt.setWOHNORT_SYNONYM(resultSet.getString("wOHNORT_SYNONYM"));
                wt.setWOHNORT_ZENTRUM(resultSet.getString("wOHNORT_ZENTRUM"));
                wt.setWOHORT_STADTEIL_ZENTRUM(resultSet.getString("wOHORT_STADTEIL_ZENTRUM"));

                temp.add(wt);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            log.debug("No Result for SQL:" + sql);
        }
        return temp;
    }

    @Override
    public List<WetterAusland> getWetterAusland(String sql) {
        List<WetterAusland> temp = new ArrayList<WetterAusland>();
        try {
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                WetterAusland wt = new WetterAusland();

                wt.setBUNDESLAND_NAME(resultSet.getString("bUNDESLAND_NAME"));
                wt.setBUNDESLAND_TYP(resultSet.getString("bUNDESLAND_TYP"));
                wt.setCODE_UNI(resultSet.getString("cODE_UNI"));
                wt.setCOUNTRY(resultSet.getString("cOUNTRY"));
                wt.setCOUNTRY_RANKING(resultSet.getString("cOUNTRY_RANKING"));
                wt.setCOUNTRY_SHORT(resultSet.getString("cOUNTRY_SHORT"));
                wt.setId(resultSet.getString("Id"));
                wt.setLANDKREIS_BEZEICHNUNG(resultSet.getString("lANDKREIS_BEZEICHNUNG"));
                wt.setLANDKREIS_NAME(resultSet.getString("lANDKREIS_NAME"));
                wt.setLANDKREIS_NAMENSZUSATZ(resultSet.getString("lANDKREIS_NAMENSZUSATZ"));
                wt.setLAT(resultSet.getString("lAT"));
                wt.setLOCATION_ROMAN(resultSet.getString("lOCATION_ROMAN"));
                wt.setLON(resultSet.getString("lON"));
                wt.setPLACE_ID(resultSet.getString("pLACE_ID"));
                wt.setPOPULATION(resultSet.getString("pOPULATION"));
                wt.setPOSTLEITZAHL(resultSet.getString("pOSTLEITZAHL"));
                wt.setTOP_WOHNORT(resultSet.getString("tOP_WOHNORT"));
                wt.setURL_ALT(resultSet.getString("uRL_ALT"));
                wt.setURL_DEFAULT(resultSet.getString("uRL_DEFAULT"));
                wt.setURL_TOI(resultSet.getString("uRL_TOI"));
                wt.setUSE_STATION(resultSet.getString("USE_STATION"));
                wt.setWOHNORT_GEMEINDESCHLUESSEL(resultSet.getString("wOHNORT_GEMEINDESCHLUESSEL"));
                wt.setWOHNORT_LOKAL(resultSet.getString("wOHNORT_LOKAL"));
                wt.setWOHNORT_NAME(resultSet.getString("wOHNORT_NAME"));
                wt.setWOHNORT_NAMENSZUSATZ(resultSet.getString("wOHNORT_NAMENSZUSATZ"));
                wt.setWOHNORT_STADTTEIL_NAME(resultSet.getString("wOHNORT_STADTTEIL_NAME"));
                wt.setWOHNORT_SYNONYM(resultSet.getString("wOHNORT_SYNONYM"));
                wt.setWOHNORT_ZENTRUM(resultSet.getString("wOHNORT_ZENTRUM"));
                wt.setWOHORT_STADTEIL_ZENTRUM(resultSet.getString("wOHORT_STADTEIL_ZENTRUM"));

                temp.add(wt);
            }

        } catch (SQLException e) {

            e.printStackTrace();
            log.debug("No Result for SQL:" + sql);
        }
        return temp;
    }


    public static void main(String[] args) {
        BackendDBAccessImpl test = new BackendDBAccessImpl();
        List<WetterDeutschland> wd = null;
        List<WetterAusland> wa = null;
        String sql1 = "select * "
                + "from ws "
                + "ORDER BY RAND() "
                + "LIMIT 2 ;";

        String sql2 = "select * "
                + "from ws_i "
                + "ORDER BY RAND() "
                + "LIMIT 2 ;";

        test.getConnection();
        wd = test.getWetterDeutschland(sql1);
        wa = test.getWetterAusland(sql2);
        test.closeConnection();

        for (WetterDeutschland wetterDeutschland : wd) {
            System.out.println(wetterDeutschland.getBUNDESLAND_NAME());
            System.out.println(wetterDeutschland.getCOUNTRY_RANKING());
            System.out.println(wetterDeutschland.getLANDKREIS_NAME());
            System.out.println(wetterDeutschland.getLAT());
            System.out.println(wetterDeutschland.getPf_URL());
        }

        System.out.println("\n\n");

        for (WetterAusland wetterAusland : wa) {
            System.out.println(wetterAusland.getBUNDESLAND_NAME());
            System.out.println(wetterAusland.getTOP_WOHNORT());
            System.out.println(wetterAusland.getUSE_STATION());
            System.out.println(wetterAusland.getCODE_UNI());

        }

    }
}
