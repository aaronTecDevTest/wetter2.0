package de.telekom.pni.rmstest.backend.global;

import java.util.ArrayList;


public class GlobalVarWetterInfo_Preview extends GlobalVar {

    public GlobalVarWetterInfo_Preview() {

        super.testCaseShortName = "wetter";

        //Suchfeld
        super._INPUT_BOX = ".//*/input[contains(@id,'searchPattern')]";              //".//*[@id='searchPattern']";
        super._SEARCH_BUTTON = ".//*[@id='wisuche']/a";


        //Liste des Autosuggest
        super._AUTOSUGGEST = ".//*[@id='searchPattern_ac']";
        super._AUTOSUGGEST_LIST = ".//*[@id='searchPattern_ac']/ul/li[*]/a";                //".//*[@id='searchPattern_ac']/ul/li";
        super._WETTER_ORT_H1 = ".//*[@id='Tcontbox']/div[2]/div[3]/div[2]/div[1]/div/a"; //(Recht Oben unter Info Zeichen)


        //Sucheergenisseite
        super._SUCHERGEBNISSEITE_LIST_TITEL = ".//*[@id='Twieser']/h4[*]";
        super._SUCHERGEBNISSEITE_LIST_ORTE3 = ".//*[@id='Twieser']/div[3]";
        super._SUCHERGEBNISSEITE_TOP_ORT = ".//*[@id='Twieser']/div[1]";
        super._SUCHERGEBNISSEITE_TOP_ORT_P = ".//*[@id='Twieser']/div[1]/p[1]/a";
        super._SUCHERGEBNISSEITE_ALL_ORT_OHNE_TOP = ".//*[@id='Twieser']/div[*]/div[*]";
        super._SUCHERGEBNISSEITE_ALL_ORT_MIT_TOP = ".//*[@id='Twieser']/div[*]/p[*]";
        super._SUCHERGEBNISSEITE_AUSKLAPP_BUTTONS = ".//*[@id='Twieser']/h4[*]/span";
        super._SUCHERGEBNISSEITE_LIST_DE = ".//*[@id='Twieser']/div[1]/div[*]";
        super._SUCHERGEBNISSEITE_Filter_AnzahlOrt = ".//*[@id='TfltLst']/li[*]/label/b";


        //xPath für Wetter.info
        super._BOX_WETTER__LOGO = ".//*[@id='Tlogo2']/img";
        super._BOX_WETTER__LOGO_POWERED_BY = ".//*[@id='Thead2']/a[2]/img";
        super._BOX_WETTER__MENÜLEISTE = ".//*[@id='Twnav']/ul[1]/li[*]";
        super._BOX_WETTER__THERE_BIG = null;
        super._BOX_WETTER__EINSTELLUNG_BUTTON = ".//*[@id='Tcontbox']/div[2]/div[3]/div[2]/div[1]/p";
        super._BOX_WETTER__BREADCRUMB = ".//*[@id='Tbcrumbinner']/div";
        super._BOX_WETTER__BREADCRUMB_aLink = ".//*[@id='Tbcrumbinner']/div/a[*]";
        super._BOX_WETTER_h1 = ".//*[@id='Tbcrumbinner']/div/h1";
        super._BOX_WETTER_h2 = ".//*[@id='Tcontbox']/div[2]/div[3]/div[2]/div[1]/h2";
        super._BOX_WETTER_h3 = ".//*[@id='Tcontboxi']/div/div[12]";

        super._BOX_WETTER__3TAGE_ANSICHT = null;
        super._BOX_WETTER__WETER_KARTE = ".//*[@id='pwic_card']/div[20]/img";

        super._BOX_WETTER__TWITER_LINK = ".//*[@id='T-63329502']";
        super._BOX_WETTER__FACEBOOK_LINK = ".//*[@id='T-63329326']";

        super._BOX_WETTER__WETTERBERICHT = ".//*[@id='T-63480794']";
        super._BOX_WETTE3__URLAUBSZIELE = ".//*[@id='T-63286028']";
        super._BOX_WETTER__WETTER_IM_BLICK = ".//*[@id='T-62927456']";
        super._BOX_WETTER__VIDEO = ".//*[@id='T-52223102']";

        super._BOX_WETTER__WETTER_SEO_LINKS_Kreis = ".//*[@id='T-52310918']/div[2]/div/ul[*]/li[*]/a";
        super._BOX_WETTER__WETTER_SEO_LINKS_Regional = ".//*[@id='T-52310918']/div[2]/div/ul[*]/li[*]/a";
        super._BOX_WETTER__WETTER_SEO_LINK_SPALTEN = ".//*[@id='T-52310918']/div[2]/div/ul[*]";
        super._BOX_WETTER__WETTER_KARTE_PROFI = null;
        super._BOX_WETTER__WETTER_ZUHAUSE = null;
        super._BOX_WETTER__WETTER_SEO_TEXT = null;
        super._BOX_WETTER__PARTNER_LINK = ".//*[@id='Tfootblock']";
        super._BOX_WETTER__WETTER_CopyRight = ".//*[@id='Tcopyr']";

        super._BOX_LINKS__WETTERVORHERSAGE1 = null;
        super._BOX_LINKS__WETTERVORHERSAGE2 = null;
        super._BOX_LINKS__FACEBOOK_PLUG_IN = null;
        super._BOX_LINKS__WETTER_BILDERN = null;
        super._BOX_LINKS__REGION_NACHRICHT = ".//*[@id='T-44091352']/h6/span";


        //Werbung
        super._Werbung_0 = ".//*[@id='vmsky']";  //<div id="vmsky">
        super._Werbung_1 = ".//*[@id='vmpop']";  //<div id="vmpop">
        super._Werbung_2 = ".//*[@id='vmsb']";  //<div id="vmsb">
        super._Werbung_3 = ".//*[@id='vmcadsl']";  //<div id="vmcadsl">
        super._Werbung_4 = ".//*[@id='med_anz']";  //<div id="med_anz">


        //xPath für Wetter.info AD-Boxs
        super._AD_BOX_LINKS__1 = null;
        super._AD_BOX_LINKS__2 = null;
        super._AD_BOX_LINKS__3 = null;
        super._AD_BOX_LINKS__4 = null;
        super._AD_BOX_LINKS__5 = null;

        //CSS-Classen
        super._CSS_INPUT_BOX_ = ".suin";
        super._CSS_AUTOSUGGEST_ = ".autoComplete";
        super.stringSearch = null;
        super.stringResult = null;

        super.searchStringList = new ArrayList<>();
        super.resultStringList = new ArrayList<>();
        super.linkTab = new ArrayList<>();

        //Testlink
        super.__WEBSIDE__ = "www.wetter.info";
        super.__AutoSuggest__ = "http://portal.wetter.info/winfo/wetter-deutschland/hessen/kreisfreie-stadt-darmstadt/wetter-darmstadt/K06411000?preview=1";
        super.__Suchergebnisseite = "http://cm7prev.ada.t-online.de/toiPortal/servlet/suche/sp_frank/62174516";
        super.__StartSeiteFavoriten__ = "http://cm7prev.ada.t-online.de/toiPortal/servlet/-/sp_moskau/17568854";
        //"http://cm7prev.ada.t-online.de/toiPortal/servlet/-/sp_Rom/17568854"; //Toi: http://cm7prev.ada.t-online.de/toiPortal/servlet/wetter/sp_moskau/12347708
        super.__SUCHWEBSIDE_3TageAnsicht__ = "http://portal.wetter.info/winfo/wetter-deutschland/hessen/kreisfreie-stadt-darmstadt/wetter-darmstadt/K06411000?preview=1";
        super.__SUCHWEBSIDE_3TageAnsicht_Preview__ = "http://portal.wetter.info/winfo/wetter-deutschland/hessen/kreisfreie-stadt-darmstadt/wetter-darmstadt/K06411000?preview=1";
        super.__SUCHESTATION_ID__ = "http://portal.wetter.info/winfo/wetter-deutschland/hessen/kreisfreie-stadt-darmstadt/wetter-darmstadt/";
        super.__SUCHWEBSIDE_3TageAnsicht_Preview__Ausland = "http://portal.wetter.info/winfo/ausland/wetter-frankreich/ile-de-france/wetter-paris/N-2085971?preview=1";

        //Favoritenmodul
        super.__FAVORITEN_MODUL__ = "http://cm7prev.ada.t-online.de/toiPortal/servlet/-/sp_moskau/17568854";
        super.__FAVORETEN_MODUL__Zahnrad__ = "//*[@id=\"Tcontbox\"]/div[2]/div[3]/div[2]/div[1]/p";

        super.__FAVORETEN_MODUL_MeinStadt__ = "//*[@id=\"Tcontbox\"]/div[2]/div[3]/div[2]/div[6]/div[2]/div/div/ul/li[1]/a";
        super.__FAVORETEN_MODUL_MeinStadt_Suchfeld___ = "//*[@id=\"Tsetfav1\"]";
        super.__FAVORETEN_MODUL_MeinStadt_Speichern___ = "//*[@id=\"Tcontbox\"]/div[2]/div[3]/div[2]/div[6]/div[2]/div/div/form/div[2]/button";
        super.__FAVORETEN_MODUL_MeinStadt_AutoSuggest___ = "//*[@id=\"Tsetfav1_ac\"]";

        super.__FAVORETEN_MODUL_MeineFavoriten__ = "//*[@id=\"Tcontbox\"]/div[2]/div[3]/div[2]/div[6]/div[2]/div/div/ul/li[2]/a";
        super.__FAVORETEN_MODUL_MeineFavoriten_Suchfeld1__ = "//*[@id=\"Tsetfav2\"]";
        super.__FAVORETEN_MODUL_MeineFavoriten_Suchfeld2__ = "//*[@id=\"Tsetfav3\"]";
        super.__FAVORETEN_MODUL_MeineFavoriten_Suchfeld3__ = "//*[@id=\"Tsetfav4\"]";
        super.__FAVORETEN_MODUL_MeineFavoriten_Speicher1__ = "//*[@id=\"Tcontbox\"]/div[2]/div[3]/div[2]/div[6]/div[2]/div/div/form/div[1]/button";
        super.__FAVORETEN_MODUL_MeineFavoriten_Speicher2__ = "//*[@id=\"Tcontbox\"]/div[2]/div[3]/div[2]/div[6]/div[2]/div/div/form/div[2]/button";
        super.__FAVORETEN_MODUL_MeineFavoriten_Speicher3__ = "//*[@id=\"Tcontbox\"]/div[2]/div[3]/div[2]/div[6]/div[2]/div/div/form/div[3]/button";
        super.__FAVORETEN_MODUL_MeineFavoriten_AutoSuggest1__ = "//*[@id=\"Tsetfav2_ac\"]";
        super.__FAVORETEN_MODUL_MeineFavoriten_AutoSuggest2__ = "//*[@id=\"Tsetfav3_ac\"]";
        super.__FAVORETEN_MODUL_MeineFavoriten_AutoSuggest3__ = "//*[@id=\"Tsetfav4_ac\"]";

        //Meta Info
        super.__META_CONONICAL_TAG__ = "/html/head/link[13]";
        super.__META_URL_Tag__ = "/html/head/meta[7]";

        //Breadcrumb
        super.__BREADCRUMB_ALL__ = "//*[@id=\"Tbcrumbinner\"]/div";
        super.__BREADCRUMB_LINK1__ = "//*[@id=\"Tbcrumbinner\"]/div/a[1]";
        super.__BREADCRUMB_LINK2__ = "//*[@id=\"Tbcrumbinner\"]/div/a[2]";
        super.__BREADCRUMB_LINK3__ = "//*[@id=\"Tbcrumbinner\"]/div/a[3]";
        super.__BREADCRUMB_LINK4__ = "//*[@id=\"Tbcrumbinner\"]/div/a[4]";
        super.__BREADCRUMB_LINK3_Temp__ = "//*[@id=\"Tbcrumbinner\"]/div/h1/a[1]";
        super.__BREADCRUMB_LINK4_Temp__ = "//*[@id=\"Tbcrumbinner\"]/div/h1/a[2]";


        //Schnittstelle
        super.__Schnittstelle_Wirk__ = "http://suche.wetter.info/ariadne/wsuggestion?location=new&limitNumber=10&limitCountries=??&countryPriority=?";
        super.__Schnittstelle_Test__ = "http://livetest.wetter.info/suchewetter/ariadne/wsuggestion?location=new%20york&limitNumber=10&limitCountries=??&countryPriority=?";
        super.__Schnitstelle_Wetterdaten_Showall__ = "http://rest.wetter.info/webservice/v1_0/rest/place&t=td39ef23d36bbcf35c7f6607a3ab239be&id=K90033549&attributes=showall";
        super.__All_Links__ = ".//a";


        linkTab.add(".//*[@id='T-self.parsedId-1']/div[2]/span[6]");                                    //3-Tage Wetterseite (Übersicht)
        linkTab.add(".//*[@id='Treitsci']/ul/li[1]/a/");                                                //1.Tage Wetterseite
        linkTab.add(".//*[@id='Treitsci']/ul/li[2]/a/");                                                //2.Tage Wetterseite
        linkTab.add(".//*[@id='Treitsci']/ul/li[3]/a/");                                                //3.Tage Wetterseite
        linkTab.add(".//*[@id='Treitsci']/ul/li[4]/a/");                                                //4.Tage Wetterseite
        linkTab.add(".//*[@id='Treitsci']/ul/li[5]/a/");                                                //5.Tage Wetterseite
        linkTab.add(".//*[@id='Treitsci']/ul/li[6]/a/");                                                //6.Tage Wetterseite
        linkTab.add(".//*[@id='Treitsci']/ul/li[7]/a/");                                                //7.Tage Wetterseite
        linkTab.add(".//*[@id='Treitsci']/ul/li[8]/a/");                                                //8.Tage Wetterseite
        linkTab.add(".//*[@id='Treitsci']/ul/li[9]/a/");                                                //9.Tage Wetterseite
        linkTab.add(".//*[@id='Treitsci']/ul/li[10]/a/");                                                //10.Tage Wetterseite
        linkTab.add(".//*[@id='Tcontbox']/div[2]/div[3]/div[2]/div[3]/div[2]/div[3]/div[1]/div/a[2]");    //Für 10-Tage Ansicht
    }
}
