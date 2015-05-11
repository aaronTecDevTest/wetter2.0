package de.telekom.pni.rmstest.backend.dataStorage;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WetterDeutschland {

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
    /*String Lat_d= null;
    String Lat_r= null;
    String Lon_d= null;
    String Lon_r= null;
    String h_GEMEINDE_NAME= null;
    String h_ISO_REGION_CODE= null;
    String h_REGIERUNGSBEZIRK_NAME= null;
    String h_GEMEINDE_NAMENSZUSATZ= null;
    String h_EDITIERT= null;
    String h_VORWAHL_ONKZ= null;
    String h_uni_neu= null;
    String h_CODE_UNI_ALT= null;
    String h_uni_alt= null;
    String h_t_1_cu_location= null;
    String h_t_1_pf_locations= null;
    String h_pf_PLACE_ID= null;
    String h_pf_TYPE= null;
    String h_t_1_run= null; */
    String pf_PLACE_DESCR = null;
    String pf_LOCATION = null;
    String pf_LOCATION_ROMAN = null;
    String pf_ZIP = null;
    /*String pf_CODE_UNI= null;
    String pf_CATEGORY= null;*/
    String pf_URL = null;
    //	String pf_USE_STATION= null;
    String pf_LOCATION_SEARCH_V1 = null;
    String pf_mCode = null;
    String pf_LAT = null;
    String pf_LON = null;

    public WetterDeutschland() {

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
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the lat_d
     *
    public String getLat_d() {
    return Lat_d;
    }

    /**
     * @return the lat_r
     *
    public String getLat_r() {
    return Lat_r;
    }

    /**
     * @return the lon_d
     *
    public String getLon_d() {
    return Lon_d;
    }

    /**
     * @return the lon_r
     *
    public String getLon_r() {
    return Lon_r;
    }

    /**
     * @return the h_GEMEINDE_NAME
     *
    public String getH_GEMEINDE_NAME() {
    return h_GEMEINDE_NAME;
    }*/

    /**
     * @return the h_ISO_REGION_CODE
     *
    public String getH_ISO_REGION_CODE() {
    return h_ISO_REGION_CODE;
    }

    /**
     * @return the h_REGIERUNGSBEZIRK_NAME
     *
    public String getH_REGIERUNGSBEZIRK_NAME() {
    return h_REGIERUNGSBEZIRK_NAME;
    }

    /**
     * @return the h_GEMEINDE_NAMENSZUSATZ
     *
    public String getH_GEMEINDE_NAMENSZUSATZ() {
    return h_GEMEINDE_NAMENSZUSATZ;
    }

    /**
     * @return the h_EDITIERT
     *
    public String getH_EDITIERT() {
    return h_EDITIERT;
    }

    /**
     * @return the h_VORWAHL_ONKZ
     *
    public String getH_VORWAHL_ONKZ() {
    return h_VORWAHL_ONKZ;
    }

    /**
     * @return the h_uni_neu
     *
    public String getH_uni_neu() {
    return h_uni_neu;
    }

    /**
     * @return the h_CODE_UNI_ALT
     *
    public String getH_CODE_UNI_ALT() {
    return h_CODE_UNI_ALT;
    }

    /**
     * @return the h_uni_alt
     *
    public String getH_uni_alt() {
    return h_uni_alt;
    }

    /**
     * @return the h_t_1_cu_location
     *
    public String getH_t_1_cu_location() {
    return h_t_1_cu_location;
    }

    /**
     * @return the h_t_1_pf_locations
     *
    public String getH_t_1_pf_locations() {
    return h_t_1_pf_locations;
    }

    /**
     * @return the h_pf_PLACE_ID
     *
    public String getH_pf_PLACE_ID() {
    return h_pf_PLACE_ID;
    }

    /**
     * @return the h_pf_TYPE
     *
    public String getH_pf_TYPE() {
    return h_pf_TYPE;
    }

    /**
     * @return the h_t_1_run
     *
    public String getH_t_1_run() {
    return h_t_1_run;
    }*/

    /**
     * @return the pf_PLACE_DESCR
     */
    public String getPf_PLACE_DESCR() {
        return pf_PLACE_DESCR;
    }

    /**
     * @return the pf_LOCATION
     */
    public String getPf_LOCATION() {
        return pf_LOCATION;
    }

    /**
     * @return the pf_LOCATION_ROMAN
     */
    public String getPf_LOCATION_ROMAN() {
        return pf_LOCATION_ROMAN;
    }

    /**
     * @return the pf_ZIP
     */
    public String getPf_ZIP() {
        return pf_ZIP;
    }

    /**
     * @return the pf_CODE_UNI
     *
    public String getPf_CODE_UNI() {
    return pf_CODE_UNI;
    }

    /**
     * @return the pf_CATEGORY
     *
    public String getPf_CATEGORY() {
    return pf_CATEGORY;
    }*/

    /**
     * @return the pf_URL
     */
    public String getPf_URL() {
        return pf_URL;
    }

    /**
     * @return the pf_USE_STATION
     *
    public String getPf_USE_STATION() {
    return pf_USE_STATION;
    }*/

    /**
     * @return the pf_LOCATION_SEARCH_V1
     */
    public String getPf_LOCATION_SEARCH_V1() {
        return pf_LOCATION_SEARCH_V1;
    }

    /**
     * @return the pf_mCode
     */
    public String getPf_mCode() {
        return pf_mCode;
    }

    /**
     * @return the pf_LAT
     */
    public String getPf_LAT() {
        return pf_LAT;
    }

    /**
     * @return the pf_LON
     */
    public String getPf_LON() {
        return pf_LON;
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

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @param lat_d the lat_d to set
     *
    public void setLat_d(String lat_d) {
    Lat_d = lat_d;
    }

    /**
     * @param lat_r the lat_r to set
     *
    public void setLat_r(String lat_r) {
    Lat_r = lat_r;
    }

    /**
     * @param lon_d the lon_d to set
     *
    public void setLon_d(String lon_d) {
    Lon_d = lon_d;
    }

    /**
     * @param lon_r the lon_r to set
     *
    public void setLon_r(String lon_r) {
    Lon_r = lon_r;
    }

    /**
     * @param h_GEMEINDE_NAME the h_GEMEINDE_NAME to set
     *
    public void setH_GEMEINDE_NAME(String h_GEMEINDE_NAME) {
    this.h_GEMEINDE_NAME = h_GEMEINDE_NAME;
    }

    /**
     * @param h_ISO_REGION_CODE the h_ISO_REGION_CODE to set
     *
    public void setH_ISO_REGION_CODE(String h_ISO_REGION_CODE) {
    this.h_ISO_REGION_CODE = h_ISO_REGION_CODE;
    }

    /**
     * @param h_REGIERUNGSBEZIRK_NAME the h_REGIERUNGSBEZIRK_NAME to set
     *
    public void setH_REGIERUNGSBEZIRK_NAME(String h_REGIERUNGSBEZIRK_NAME) {
    this.h_REGIERUNGSBEZIRK_NAME = h_REGIERUNGSBEZIRK_NAME;
    }

    /**
     * @param h_GEMEINDE_NAMENSZUSATZ the h_GEMEINDE_NAMENSZUSATZ to set
     *
    public void setH_GEMEINDE_NAMENSZUSATZ(String h_GEMEINDE_NAMENSZUSATZ) {
    this.h_GEMEINDE_NAMENSZUSATZ = h_GEMEINDE_NAMENSZUSATZ;
    }

    /**
     * @param h_EDITIERT the h_EDITIERT to set
     *
    public void setH_EDITIERT(String h_EDITIERT) {
    this.h_EDITIERT = h_EDITIERT;
    }

    /**
     * @param h_VORWAHL_ONKZ the h_VORWAHL_ONKZ to set
     *
    public void setH_VORWAHL_ONKZ(String h_VORWAHL_ONKZ) {
    this.h_VORWAHL_ONKZ = h_VORWAHL_ONKZ;
    }

    /**
     * @param h_uni_neu the h_uni_neu to set
     *
    public void setH_uni_neu(String h_uni_neu) {
    this.h_uni_neu = h_uni_neu;
    }

    /**
     * @param h_CODE_UNI_ALT the h_CODE_UNI_ALT to set
     *
    public void setH_CODE_UNI_ALT(String h_CODE_UNI_ALT) {
    this.h_CODE_UNI_ALT = h_CODE_UNI_ALT;
    }

    /**
     * @param h_uni_alt the h_uni_alt to set
     *
    public void setH_uni_alt(String h_uni_alt) {
    this.h_uni_alt = h_uni_alt;
    }

    /**
     * @param h_t_1_cu_location the h_t_1_cu_location to set
     *
    public void setH_t_1_cu_location(String h_t_1_cu_location) {
    this.h_t_1_cu_location = h_t_1_cu_location;
    }

    /**
     * @param h_t_1_pf_locations the h_t_1_pf_locations to set
     *
    public void setH_t_1_pf_locations(String h_t_1_pf_locations) {
    this.h_t_1_pf_locations = h_t_1_pf_locations;
    }

    /**
     * @param h_pf_PLACE_ID the h_pf_PLACE_ID to set
     *
    public void setH_pf_PLACE_ID(String h_pf_PLACE_ID) {
    this.h_pf_PLACE_ID = h_pf_PLACE_ID;
    }

    /**
     * @param h_pf_TYPE the h_pf_TYPE to set
     *
    public void setH_pf_TYPE(String h_pf_TYPE) {
    this.h_pf_TYPE = h_pf_TYPE;
    }

    /**
     * @param h_t_1_run the h_t_1_run to set
     *
    public void setH_t_1_run(String h_t_1_run) {
    this.h_t_1_run = h_t_1_run;
    }*/

    /**
     * @param pf_PLACE_DESCR the pf_PLACE_DESCR to set
     */
    public void setPf_PLACE_DESCR(String pf_PLACE_DESCR) {
        this.pf_PLACE_DESCR = pf_PLACE_DESCR;
    }

    /**
     * @param pf_LOCATION the pf_LOCATION to set
     */
    public void setPf_LOCATION(String pf_LOCATION) {
        this.pf_LOCATION = pf_LOCATION;
    }

    /**
     * @param pf_LOCATION_ROMAN the pf_LOCATION_ROMAN to set
     */
    public void setPf_LOCATION_ROMAN(String pf_LOCATION_ROMAN) {
        this.pf_LOCATION_ROMAN = pf_LOCATION_ROMAN;
    }

    /**
     * @param pf_ZIP the pf_ZIP to set
     */
    public void setPf_ZIP(String pf_ZIP) {
        this.pf_ZIP = pf_ZIP;
    }

    /**
     * @param pf_CODE_UNI the pf_CODE_UNI to set
     *
    public void setPf_CODE_UNI(String pf_CODE_UNI) {
    this.pf_CODE_UNI = pf_CODE_UNI;
    }

    /**
     * @param pf_CATEGORY the pf_CATEGORY to set
     *
    public void setPf_CATEGORY(String pf_CATEGORY) {
    this.pf_CATEGORY = pf_CATEGORY;
    }*/

    /**
     * @param pf_URL the pf_URL to set
     */
    public void setPf_URL(String pf_URL) {
        this.pf_URL = pf_URL;
    }

    /**
     * @param pf_USE_STATION the pf_USE_STATION to set
     *
    public void setPf_USE_STATION(String pf_USE_STATION) {
    this.pf_USE_STATION = pf_USE_STATION;
    }*/

    /**
     * @param pf_LOCATION_SEARCH_V1 the pf_LOCATION_SEARCH_V1 to set
     */
    public void setPf_LOCATION_SEARCH_V1(String pf_LOCATION_SEARCH_V1) {
        this.pf_LOCATION_SEARCH_V1 = pf_LOCATION_SEARCH_V1;
    }

    /**
     * @param pf_mCode the pf_mCode to set
     */
    public void setPf_mCode(String pf_mCode) {
        this.pf_mCode = pf_mCode;
    }

    /**
     * @param pf_LAT the pf_LAT to set
     */
    public void setPf_LAT(String pf_LAT) {
        this.pf_LAT = pf_LAT;
    }

    /**
     * @param pf_LON the pf_LON to set
     */
    public void setPf_LON(String pf_LON) {
        this.pf_LON = pf_LON;
    }

}
