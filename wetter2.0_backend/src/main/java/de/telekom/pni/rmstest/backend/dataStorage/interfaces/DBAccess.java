package de.telekom.pni.rmstest.backend.dataStorage.interfaces;

import java.sql.ResultSet;
import java.util.List;

import de.telekom.pni.rmstest.backend.dataStorage.WetterAusland;
import de.telekom.pni.rmstest.backend.dataStorage.WetterDeutschland;


public interface DBAccess {

    public void getConnection();

    public void closeConnection();

    public ResultSet getResult();

    public List<WetterDeutschland> getWetterDeutschland(String sql);

    public List<WetterAusland> getWetterAusland(String sql);
}
