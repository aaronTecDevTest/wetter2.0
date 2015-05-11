package de.telekom.pni.rmstest.middleware.properties;

import java.util.Properties;

import org.apache.log4j.Logger;


import de.telekom.pni.rmstest.backend.manager.PropertiesManager_New;

/**
 * PropertiesFacade.java ist für das Abrufen von Properties-Dateien aus dem Backend zuständig.
 * Sie stellt Methoden zur Verfügung, die es dem Frontend ermöglichen auf Properties-Dateien zuzugreifen.
 *
 * @author M.Forster
 */
public class PropertiesFacade {

    private static final Logger log = Logger.getLogger(PropertiesFacade.class);

    public Properties getProperties(String filename) {
        log.debug("Getting Properties " + filename);
        if (!filename.isEmpty()) {
            log.debug("Success getting Properties");
            PropertiesManager_New test = PropertiesManager_New.getInstance();
            Properties temp = test.getProperties(filename);
            return temp;
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

