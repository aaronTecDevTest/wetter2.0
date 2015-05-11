package de.telekom.pni.rmstest.backend.dataStorage;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WetterAusland {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String id = null;
    String COUNTRY_SHORT = null;
    String COUNTRY = null;
    String COUNTRY_RANKING = null;
    String BUNDESLAND_NAME = null;
    String BUNDESLAND_TYP = null;
    String LANDKREIS_BEZEICHNUNG = null;
    String LANDKREIS_NAME = null;
    String LANDKREIS_NAMENSZUSATZ = null;
    String POSTLEITZAHL = null;
    String WOHNORT_NAME = null;
    String WOHNORT_NAMENSZUSATZ = null;
    String WOHNORT_SYNONYM = null;
    String WOHNORT_LOKAL = null;
    String WOHNORT_ZENTRUM = null;
    String WOHNORT_STADTTEIL_NAME = null;
    String WOHORT_STADTEIL_ZENTRUM = null;
    String TOP_WOHNORT = null;
    String POPULATION = null;
    String CODE_UNI = null;
    String LAT = null;
    String LON = null;
    String URL_DEFAULT = null;
    String URL_TOI = null;
    String URL_ALT = null;
    String LOCATION_ROMAN = null;
    String WOHNORT_GEMEINDESCHLUESSEL = null;
    String PLACE_ID = null;
    String USE_STATION = null;


    public WetterAusland() {

    }


    /**
     * @return the id
     */
    public String getId() {
        return id;
    }


    /**
     * @return the cOUNTRY_SHORT
     */
    public String getCOUNTRY_SHORT() {
        return COUNTRY_SHORT;
    }


    /**
     * @return the cOUNTRY
     */
    public String getCOUNTRY() {
        return COUNTRY;
    }


    /**
     * @return the cOUNTRY_RANKING
     */
    public String getCOUNTRY_RANKING() {
        return COUNTRY_RANKING;
    }


    /**
     * @return the bUNDESLAND_NAME
     */
    public String getBUNDESLAND_NAME() {
        return BUNDESLAND_NAME;
    }


    /**
     * @return the bUNDESLAND_TYP
     */
    public String getBUNDESLAND_TYP() {
        return BUNDESLAND_TYP;
    }


    /**
     * @return the lANDKREIS_BEZEICHNUNG
     */
    public String getLANDKREIS_BEZEICHNUNG() {
        return LANDKREIS_BEZEICHNUNG;
    }


    /**
     * @return the lANDKREIS_NAME
     */
    public String getLANDKREIS_NAME() {
        return LANDKREIS_NAME;
    }


    /**
     * @return the lANDKREIS_NAMENSZUSATZ
     */
    public String getLANDKREIS_NAMENSZUSATZ() {
        return LANDKREIS_NAMENSZUSATZ;
    }


    /**
     * @return the pOSTLEITZAHL
     */
    public String getPOSTLEITZAHL() {
        return POSTLEITZAHL;
    }


    /**
     * @return the wOHNORT_NAME
     */
    public String getWOHNORT_NAME() {
        return WOHNORT_NAME;
    }


    /**
     * @return the wOHNORT_NAMENSZUSATZ
     */
    public String getWOHNORT_NAMENSZUSATZ() {
        return WOHNORT_NAMENSZUSATZ;
    }


    /**
     * @return the wOHNORT_SYNONYM
     */
    public String getWOHNORT_SYNONYM() {
        return WOHNORT_SYNONYM;
    }


    /**
     * @return the wOHNORT_LOKAL
     */
    public String getWOHNORT_LOKAL() {
        return WOHNORT_LOKAL;
    }


    /**
     * @return the wOHNORT_ZENTRUM
     */
    public String getWOHNORT_ZENTRUM() {
        return WOHNORT_ZENTRUM;
    }


    /**
     * @return the wOHNORT_STADTTEIL_NAME
     */
    public String getWOHNORT_STADTTEIL_NAME() {
        return WOHNORT_STADTTEIL_NAME;
    }


    /**
     * @return the wOHORT_STADTEIL_ZENTRUM
     */
    public String getWOHORT_STADTEIL_ZENTRUM() {
        return WOHORT_STADTEIL_ZENTRUM;
    }


    /**
     * @return the tOP_WOHNORT
     */
    public String getTOP_WOHNORT() {
        return TOP_WOHNORT;
    }


    /**
     * @return the pOPULATION
     */
    public String getPOPULATION() {
        return POPULATION;
    }


    /**
     * @return the cODE_UNI
     */
    public String getCODE_UNI() {
        return CODE_UNI;
    }


    /**
     * @return the lAT
     */
    public String getLAT() {
        return LAT;
    }


    /**
     * @return the lON
     */
    public String getLON() {
        return LON;
    }


    /**
     * @return the uRL_DEFAULT
     */
    public String getURL_DEFAULT() {
        return URL_DEFAULT;
    }


    /**
     * @return the uRL_TOI
     */
    public String getURL_TOI() {
        return URL_TOI;
    }


    /**
     * @return the uRL_ALT
     */
    public String getURL_ALT() {
        return URL_ALT;
    }


    /**
     * @return the lOCATION_ROMAN
     */
    public String getLOCATION_ROMAN() {
        return LOCATION_ROMAN;
    }


    /**
     * @return the wOHNORT_GEMEINDESCHLUESSEL
     */
    public String getWOHNORT_GEMEINDESCHLUESSEL() {
        return WOHNORT_GEMEINDESCHLUESSEL;
    }


    /**
     * @return the pLACE_ID
     */
    public String getPLACE_ID() {
        return PLACE_ID;
    }


    /**
     * @return the uSE_STATION
     */
    public String getUSE_STATION() {
        return USE_STATION;
    }


    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }


    /**
     * @param cOUNTRY_SHORT the cOUNTRY_SHORT to set
     */
    public void setCOUNTRY_SHORT(String cOUNTRY_SHORT) {
        COUNTRY_SHORT = cOUNTRY_SHORT;
    }


    /**
     * @param cOUNTRY the cOUNTRY to set
     */
    public void setCOUNTRY(String cOUNTRY) {
        COUNTRY = cOUNTRY;
    }


    /**
     * @param cOUNTRY_RANKING the cOUNTRY_RANKING to set
     */
    public void setCOUNTRY_RANKING(String cOUNTRY_RANKING) {
        COUNTRY_RANKING = cOUNTRY_RANKING;
    }


    /**
     * @param bUNDESLAND_NAME the bUNDESLAND_NAME to set
     */
    public void setBUNDESLAND_NAME(String bUNDESLAND_NAME) {
        BUNDESLAND_NAME = bUNDESLAND_NAME;
    }


    /**
     * @param bUNDESLAND_TYP the bUNDESLAND_TYP to set
     */
    public void setBUNDESLAND_TYP(String bUNDESLAND_TYP) {
        BUNDESLAND_TYP = bUNDESLAND_TYP;
    }


    /**
     * @param lANDKREIS_BEZEICHNUNG the lANDKREIS_BEZEICHNUNG to set
     */
    public void setLANDKREIS_BEZEICHNUNG(String lANDKREIS_BEZEICHNUNG) {
        LANDKREIS_BEZEICHNUNG = lANDKREIS_BEZEICHNUNG;
    }


    /**
     * @param lANDKREIS_NAME the lANDKREIS_NAME to set
     */
    public void setLANDKREIS_NAME(String lANDKREIS_NAME) {
        LANDKREIS_NAME = lANDKREIS_NAME;
    }


    /**
     * @param lANDKREIS_NAMENSZUSATZ the lANDKREIS_NAMENSZUSATZ to set
     */
    public void setLANDKREIS_NAMENSZUSATZ(String lANDKREIS_NAMENSZUSATZ) {
        LANDKREIS_NAMENSZUSATZ = lANDKREIS_NAMENSZUSATZ;
    }


    /**
     * @param pOSTLEITZAHL the pOSTLEITZAHL to set
     */
    public void setPOSTLEITZAHL(String pOSTLEITZAHL) {
        POSTLEITZAHL = pOSTLEITZAHL;
    }


    /**
     * @param wOHNORT_NAME the wOHNORT_NAME to set
     */
    public void setWOHNORT_NAME(String wOHNORT_NAME) {
        WOHNORT_NAME = wOHNORT_NAME;
    }


    /**
     * @param wOHNORT_NAMENSZUSATZ the wOHNORT_NAMENSZUSATZ to set
     */
    public void setWOHNORT_NAMENSZUSATZ(String wOHNORT_NAMENSZUSATZ) {
        WOHNORT_NAMENSZUSATZ = wOHNORT_NAMENSZUSATZ;
    }


    /**
     * @param wOHNORT_SYNONYM the wOHNORT_SYNONYM to set
     */
    public void setWOHNORT_SYNONYM(String wOHNORT_SYNONYM) {
        WOHNORT_SYNONYM = wOHNORT_SYNONYM;
    }


    /**
     * @param wOHNORT_LOKAL the wOHNORT_LOKAL to set
     */
    public void setWOHNORT_LOKAL(String wOHNORT_LOKAL) {
        WOHNORT_LOKAL = wOHNORT_LOKAL;
    }


    /**
     * @param wOHNORT_ZENTRUM the wOHNORT_ZENTRUM to set
     */
    public void setWOHNORT_ZENTRUM(String wOHNORT_ZENTRUM) {
        WOHNORT_ZENTRUM = wOHNORT_ZENTRUM;
    }


    /**
     * @param wOHNORT_STADTTEIL_NAME the wOHNORT_STADTTEIL_NAME to set
     */
    public void setWOHNORT_STADTTEIL_NAME(String wOHNORT_STADTTEIL_NAME) {
        WOHNORT_STADTTEIL_NAME = wOHNORT_STADTTEIL_NAME;
    }


    /**
     * @param wOHORT_STADTEIL_ZENTRUM the wOHORT_STADTEIL_ZENTRUM to set
     */
    public void setWOHORT_STADTEIL_ZENTRUM(String wOHORT_STADTEIL_ZENTRUM) {
        WOHORT_STADTEIL_ZENTRUM = wOHORT_STADTEIL_ZENTRUM;
    }


    /**
     * @param tOP_WOHNORT the tOP_WOHNORT to set
     */
    public void setTOP_WOHNORT(String tOP_WOHNORT) {
        TOP_WOHNORT = tOP_WOHNORT;
    }


    /**
     * @param pOPULATION the pOPULATION to set
     */
    public void setPOPULATION(String pOPULATION) {
        POPULATION = pOPULATION;
    }


    /**
     * @param cODE_UNI the cODE_UNI to set
     */
    public void setCODE_UNI(String cODE_UNI) {
        CODE_UNI = cODE_UNI;
    }


    /**
     * @param lAT the lAT to set
     */
    public void setLAT(String lAT) {
        LAT = lAT;
    }


    /**
     * @param lON the lON to set
     */
    public void setLON(String lON) {
        LON = lON;
    }


    /**
     * @param uRL_DEFAULT the uRL_DEFAULT to set
     */
    public void setURL_DEFAULT(String uRL_DEFAULT) {
        URL_DEFAULT = uRL_DEFAULT;
    }


    /**
     * @param uRL_TOI the uRL_TOI to set
     */
    public void setURL_TOI(String uRL_TOI) {
        URL_TOI = uRL_TOI;
    }


    /**
     * @param uRL_ALT the uRL_ALT to set
     */
    public void setURL_ALT(String uRL_ALT) {
        URL_ALT = uRL_ALT;
    }


    /**
     * @param lOCATION_ROMAN the lOCATION_ROMAN to set
     */
    public void setLOCATION_ROMAN(String lOCATION_ROMAN) {
        LOCATION_ROMAN = lOCATION_ROMAN;
    }


    /**
     * @param wOHNORT_GEMEINDESCHLUESSEL the wOHNORT_GEMEINDESCHLUESSEL to set
     */
    public void setWOHNORT_GEMEINDESCHLUESSEL(String wOHNORT_GEMEINDESCHLUESSEL) {
        WOHNORT_GEMEINDESCHLUESSEL = wOHNORT_GEMEINDESCHLUESSEL;
    }


    /**
     * @param pLACE_ID the pLACE_ID to set
     */
    public void setPLACE_ID(String pLACE_ID) {
        PLACE_ID = pLACE_ID;
    }


    /**
     * @param uSE_STATION the uSE_STATION to set
     */
    public void setUSE_STATION(String uSE_STATION) {
        USE_STATION = uSE_STATION;
    }


}
