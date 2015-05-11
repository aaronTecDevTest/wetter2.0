package de.telekom.pni.rmstest.backend.properties;

import java.util.Properties;

import de.telekom.pni.rmstest.backend.manager.PropertiesManager_New;
import org.apache.log4j.Logger;


/**
 * PropertiesFacade.java ist für das Abrufen von Properties-Dateien aus dem Backend zuständig.
 * Sie stellt Methoden zur Verfügung, die es dem Frontend ermöglichen auf Properties-Dateien zuzugreifen.
 *
 * @author M.Forster
 */
public class PropertiesFacade {

    private static final Logger log = Logger.getLogger(PropertiesFacade.class);

    /**
     * Diese Methode ist für die Ausführung von externen Testfällen zuständig.
     *
     * @param filename - Name des auszuführenden Testfalls als String
     * @param -        RunningConfiguration des Testfalls
     * @return - report - Testergebniss als Report Objekt
     */
    public Properties getProperties(String filename) {
        log.debug("Getting Properties " + filename);
        if (!filename.isEmpty()) {
            log.debug("Success getting Properties");
            return PropertiesManager_New.getInstance().getProperties(filename);
        } else {
            log.debug("Failure getting Properties");
            return null;
        }
    }

    public void setProperty(String type, String property, String value) {
        log.debug("Setting Property " + property);
        PropertiesManager_New.getInstance().setProperty(type, property, value);
        log.debug("Finished setting Property");
    }
}

