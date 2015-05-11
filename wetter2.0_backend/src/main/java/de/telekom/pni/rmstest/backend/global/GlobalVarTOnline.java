package de.telekom.pni.rmstest.backend.global;

import java.util.ArrayList;

public class GlobalVarTOnline extends GlobalVar {

    public GlobalVarTOnline() {

        super.testCaseShortName = "wetter";
        super._INPUT_BOX = ".//*/input[contains(@id,'searchPattern')]";//".//*[@id='searchPattern']";
        super._SEARCH_BUTTON = ".//*[@id='wisuche']/a";

        super._AUTOSUGGEST = ".//*[@id='searchPattern_ac']";
        super._AUTOSUGGEST_LIST = ".//*[@id='searchPattern_ac']/ul/li[*]/a"; //".//*[@id='searchPattern_ac']/ul/li";
        super._WETTER_ORT_H1 = ".//*[@id='Tcontbox']/div[2]/div[3]/div[2]/div[1]/div/a"; //(Recht Oben unter Info Zeichen)

        //Sucheergenisseite
        super._SUCHERGEBNISSEITE_LIST_TITEL = ".//*[@id='Twieser']/h4[*]";
        super._SUCHERGEBNISSEITE_TOP_ORT = ".//*[@id='Twieser']/div[1]";
        super._SUCHERGEBNISSEITE_TOP_ORT_P = ".//*[@id='Twieser']/div[1]/p[1]/a";
        super._SUCHERGEBNISSEITE_ALL_ORT_OHNE_TOP = ".//*[@id='Twieser']/div[*]/div[*]";
        super._SUCHERGEBNISSEITE_ALL_ORT_MIT_TOP = ".//*[@id='Twieser']/div[*]";
        super._SUCHERGEBNISSEITE_AUSKLAPP_BUTTONS = ".//*[@id='Twieser']/h4[*]/span";
        super._SUCHERGEBNISSEITE_LIST_ORTE3 = ".//*[@id='Twieser']/div[3]";
        super._SUCHERGEBNISSEITE_LIST_DE = ".//*[@id='Twieser']/div[1]/div[*]";
        super._SUCHERGEBNISSEITE_Filter_AnzahlOrt = ".//*[@id='TfltLst']/li[*]/label/b";

        //xPath t-online.de/wetter
        super._BOX_WETTER__LOGO = null;
        super._BOX_WETTER__LOGO_POWERED_BY = null;
        super._BOX_WETTER__MENÜLEISTE = null;
        super._BOX_WETTER__THERE_BIG = null;
        super._BOX_WETTER__EINSTELLUNG_BUTTON = null;
        super._BOX_WETTER__BREADCRUMB = ".//*[@id='Tbcrumbinner']/div";
        super._BOX_WETTER__BREADCRUMB_aLink = ".//*[@id='Tbcrumbinner']/div/a[*]";
        super._BOX_WETTER_h1 = ".//*[@id='Tbcrumbinner']/div/h1";
        super._BOX_WETTER_h2 = ".//*[@id='Tcontbox']/div[2]/div[3]/div[2]/div[1]/h2";
        super._BOX_WETTER_h3 = ".//*[@id='T-52310918']/div[2]/div/h6/span";

        super._BOX_WETTER__3TAGE_ANSICHT = null;
        super._BOX_WETTER__WETER_KARTE = null;
        super._BOX_WETTER__TWITER_LINK = null;
        super._BOX_WETTER__FACEBOOK_LINK = null;
        super._BOX_WETTER__WETTERBERICHT = null;
        super._BOX_WETTE3__URLAUBSZIELE = null;
        super._BOX_WETTER__WETTER_IM_BLICK = null;
        super._BOX_WETTER__VIDEO = null;
        super._BOX_WETTER__WETTER_SEO_LINKS_Kreis = null;
        super._BOX_WETTER__WETTER_SEO_LINKS_Regional = null;
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
        super._BOX_LINKS__REGION_NACHRICHT = null;


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
        super.__WEBSIDE__ = "www.t-online.de/wetter";
        super.__AutoSuggest__ = "www.t-online.de/wetter";
        super.__Suchergebnisseite = "www.t-online.de/wetter";
        super.__StartSeiteFavoriten__ = "www.t-online.de/wetter";
        super.__SUCHWEBSIDE_3TageAnsicht__ = "www.t-online.de/wetter";
        super.__SUCHWEBSIDE_3TageAnsicht_Preview__ = "www.t-online.de/wetter";
        super.__SUCHESTATION_ID__ = "www.t-online.de/wetter";

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
