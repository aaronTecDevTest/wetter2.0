package de.telekom.pni.rmstest.backend.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ListResourceBundle;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

/**
 * PropertiesManager ist für die Verwaltung der Property-Files zuständig. Die Klasse ist als Singleton
 * implementiert, damit sie nicht mehrfach in der Anwendung erzeugt wird. Sobald die Klasse erzeugt
 * wird, liest sie die Property-Files aus und lädt die Daten in eine Properties Klasse. Diese Daten
 * kann dann jeder Zeit mit den passenden Methode abgerufen werden.
 *
 * @author M.Forster
 */
public class PropertiesManager_New {
    private static final Logger log = Logger.getLogger(PropertiesManager_New.class);
    private static PropertiesManager_New instance; //= new PropertiesManager_New();
    private static Properties pageProperties;
    private static Properties guiProperties;
    private static Properties rallyProperties;
    private static Properties autorunProperties;
    private static Properties testrunProperties;
    private static Properties dbProperties;
    private static Properties searchProperties;
    private static Properties urlProperties;
    private static Properties appProperties;


    private static final String PAGE_FILE = "properties/page.properties";
    private static final String GUI_FILE = "properties/gui.properties";
    private static final String RALLY_FILE = "properties/rally.properties";
    private static final String AUTORUN_FILE = "properties/autorun.properties";
    private static final String TESTRUN_FILE = "properties/testrun.properties";
    private static final String DB_FILE = "properties/db.properties";
    private static final String SEARCH_FILE = "properties/search.properties";
    private static final String URL_FILE = "properties/url.properties";
    private static final String APP_FILE = "properties/app.properties";

    private InputStream streamPage;
    private InputStream streamGui;
    private InputStream streamRally;
    private InputStream streamAutorun;
    private InputStream streamTestrun;
    private InputStream streamDb;
    private InputStream streamSearch;
    private InputStream streamUrl;
    private InputStream streamApp;

    /**
     * Konstrultor der Klasse, der die privaten Variablen initialisiert.
     * Da es sich um einen Singleton handelt, ist der Konstruktor private
     * und kann nur innerhalb der Klasse aufgerufen werden.
     */
    private PropertiesManager_New() {
        log.debug("Starting PropertiesManager");
        pageProperties = new Properties();
        guiProperties = new Properties();
        rallyProperties = new Properties();
        autorunProperties = new Properties();
        testrunProperties = new Properties();
        dbProperties = new Properties();
        searchProperties = new Properties();
        urlProperties = new Properties();
        appProperties = new Properties();
        try {
            /**
             * Alternative von  //ClassLoader.getSystemClassLoader();
             *  //ClassLoader.getSystemClassLoader();
             *  // ResourceBundle rb = ResourceBundle.getBundle("button.infolayer", Locale.ENGLISH,ClassLoader.getSystemClassLoader());
             */
            ClassLoader cl = getClass().getClassLoader();


            if (cl != null) {
                streamPage = cl.getResource(PAGE_FILE).openStream();
                pageProperties.load(streamPage);
                //log.info("Path for resources: " + cl.getResource(PAGE_FILE).getPath());

                streamGui = cl.getResource(GUI_FILE).openStream();
                guiProperties.load(streamGui);

                streamRally = cl.getResource(RALLY_FILE).openStream();
                rallyProperties.load(streamRally);

                streamAutorun = cl.getResource(AUTORUN_FILE).openStream();
                autorunProperties.load(streamAutorun);

                streamTestrun = cl.getResource(TESTRUN_FILE).openStream();
                testrunProperties.load(streamTestrun);

                streamDb = cl.getResource(DB_FILE).openStream();
                dbProperties.load(streamDb);

                streamSearch = cl.getResource(SEARCH_FILE).openStream();
                searchProperties.load(streamSearch);

                streamUrl = cl.getResource(URL_FILE).openStream();
                urlProperties.load(streamUrl);

                streamApp = cl.getResource(APP_FILE).openStream();
                appProperties.load(streamApp);
            }

            log.debug("PropertiesManager started and initialised");
        } catch (FileNotFoundException e) {
            log.error("FileNotFoundException: Error while loading properties." + e.getMessage());
        } catch (IOException e) {
            log.error("IOException: Error while loading properties." + e.getMessage());
        } catch (Exception e) {
            //log.error("Exception: Error while loading properties." + e.getMessage());
            System.out.println(e);
        } finally {
            try {
                if (streamPage != null) {
                    streamPage.close();
                }
                if (streamGui != null) {
                    streamGui.close();
                }
                if (streamRally != null) {
                    streamRally.close();
                }
                if (streamAutorun != null) {
                    streamAutorun.close();
                }
                if (streamTestrun != null) {
                    streamTestrun.close();
                }
                if (streamDb != null) {
                    streamDb.close();
                }
                if (streamSearch != null) {
                    streamSearch.close();
                }
                if (streamUrl != null) {
                    streamUrl.close();
                }
                if (streamApp != null) {
                    streamApp.close();
                }
            } catch (IOException e) {
                log.error("Error while closing a file" + e.getMessage());
            }
        }
    }

    /**
     * Diese Methode stellt eine Instanz der Klasse zur Verfügung.
     * Wird die Methode zum ersten Mal aufgerufen, wird der Konstruktor
     * ausgeführt und eine neue Instanz der Klasse ertzeugt.
     * Bei jedem weiteren Aufruf, wird immer wieder die gleiche Instanz
     * zurückgegeben
     *
     * @return instance - Instanz der Klasse PropertiesManager
     */
    public static PropertiesManager_New getInstance() {
        log.debug("Getting PropertiesManager Instance");
        if (instance == null) {
            instance = new PropertiesManager_New();
        }
        log.debug("PropertiesManager Instance received");
        return instance;
    }

    /**
     * Mit dieser Methode können die einzelnen Properties Objekte abgerufen werden.
     *
     * @param type - Name des Properties Objekts (Dateiname)
     * @return properties - Properties Objekt mit den Daten aus dem Property-File
     */
    public Properties getProperties(String type) {
        log.debug("Getting " + type + ".properties");
        if (!(type == null)) {
            Properties properties;
            switch (type) {
                case "page":
                    properties = pageProperties;
                    break;
                case "gui":
                    properties = guiProperties;
                    break;
                case "rally":
                    properties = rallyProperties;
                    break;
                case "autorun":
                    properties = autorunProperties;
                    break;
                case "testrun":
                    properties = testrunProperties;
                    break;
                case "db":
                    properties = dbProperties;
                    break;
                case "search":
                    properties = searchProperties;
                    break;
                case "url":
                    properties = urlProperties;
                    break;
                case "app":
                    properties = appProperties;
                    break;
                default:
                    properties = null;
                    break;
            }
            log.debug(type + ".properties received");
            return properties;
        } else {
            return new Properties();
        }
    }

    /**
     * Diese Methode ermöglicht es Properties zu ändern. Anhand
     *
     * @param type
     * @param property
     * @param value
     */
    public void setProperty(String type, String property, String value) {
        log.debug("Saving property");
        try {
            if (type != null) {
                ClassLoader cl = ClassLoader.getSystemClassLoader();
                if (cl != null) {
                    //String path = ".." + File.separator + "rmstest_backend" + File.separator + "src" + File.separator + "main" + File.separator + "resources";
                    Properties properties;
                    File f = null;
                    switch (type) {
                        case "page":
                            properties = pageProperties;
                            f = new File(cl.getResource(PAGE_FILE).getPath());
                            break;
                        case "gui":
                            properties = guiProperties;
                            f = new File(cl.getResource(GUI_FILE).getPath());
                            break;
                        case "rally":
                            properties = rallyProperties;
                            f = new File(cl.getResource(RALLY_FILE).getPath());
                            break;
                        case "autorun":
                            properties = autorunProperties;
                            f = new File(cl.getResource(AUTORUN_FILE).getPath());
                            break;
                        case "testrun":
                            properties = testrunProperties;
                            f = new File(cl.getResource(TESTRUN_FILE).getPath());
                            break;
                        case "db":
                            properties = dbProperties;
                            f = new File(cl.getResource(DB_FILE).getPath());
                            break;
                        case "search":
                            properties = searchProperties;
                            f = new File(cl.getResource(SEARCH_FILE).getPath());
                            break;
                        case "app":
                            properties = searchProperties;
                            f = new File(cl.getResource(APP_FILE).getPath());
                            break;
                        default:
                            properties = new Properties();
                            f = new File(cl.getResource(type + ".properties").getPath());
                            break;
                    }
                    properties.setProperty(property, value);
                    OutputStream out = new FileOutputStream(f);
                    properties.store(out, "Property changes");
                    log.debug("Property " + property + "=" + value + " saved to " + type + ".properties");
                }
            } else {
                log.error("No property saved because type was null");
            }
        } catch (Exception e) {
            log.error("Error while saving a property: " + e.getMessage());
        }
    }
}