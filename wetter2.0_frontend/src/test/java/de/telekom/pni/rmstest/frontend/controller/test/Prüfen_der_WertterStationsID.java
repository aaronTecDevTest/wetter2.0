/**
 *
 */
package de.telekom.pni.rmstest.frontend.controller.test;

import java.util.Properties;

import de.telekom.pni.rmstest.backend.core.GenericTest_New;
import de.telekom.pni.rmstest.backend.properties.PropertiesFacade;


/**
 * @author a.kutekidila
 */
public class Prüfen_der_WertterStationsID extends GenericTest_New {

    private PropertiesFacade propFacade = new PropertiesFacade();
    private Properties wetterStationsID = propFacade.getProperties("DB");
    private String stationsID = wetterStationsID.getProperty("general.DBname");


    /**
     *
     */
    public Prüfen_der_WertterStationsID() {

    }

    /* (non-Javadoc)
     * @see de.telekom.pni.rmstest.backend.core.interfaces.RMSTest_New#runTest()
     */
    @Override
    public void runTest() {
        fileToAarry();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Prüfen_der_WertterStationsID test = new Prüfen_der_WertterStationsID();
        test.before();
        test.runTest();
        test.after();
    }

    /*
     * Methoden
     *
     */
    private void fileToAarry() {
        String[] temp = stationsID.split(",");

        for (int i = 0; i < temp.length; i++) {
            String string = temp[i];
            System.out.println(string);
        }
    }

    public boolean check_H1_Tag() {
        boolean check = false;

        return check;
    }

    public boolean check_H2_Tag() {
        boolean check = false;

        return check;
    }

    public boolean check_H3_Tag() {
        boolean check = false;

        return check;
    }

    public boolean check_Titel() {
        boolean check = false;

        return check;
    }
}
