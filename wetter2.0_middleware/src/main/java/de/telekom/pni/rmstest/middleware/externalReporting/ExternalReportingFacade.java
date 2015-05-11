package de.telekom.pni.rmstest.middleware.externalReporting;

import java.util.List;

import javax.swing.JTree;

import org.apache.log4j.Logger;

import de.telekom.pni.rmstest.backend.config.LoginDataRally;
import de.telekom.pni.rmstest.backend.externalReporting.RallyExternalReportingImpl;
import de.telekom.pni.rmstest.backend.externalReporting.interfaces.ExternalReporting;
import de.telekom.pni.rmstest.backend.reporting.Report;
import de.telekom.pni.rmstest.backend.testentities.interfaces.TestCase;
import de.telekom.pni.rmstest.backend.testentities.interfaces.TestSet;

/**
 * ExternalReportingFacade.java ist für den Zugriff auf die Externe-Reporting-Schnittstelle von der
 * Benutzeroberfläche zustädig. Sie stellt Methoden zur Verfügung, die das Abrufen von Testdaten
 * und das Speichern von Testergebnissen ermöglicht.
 *
 * @author M.Forster
 */
public class ExternalReportingFacade {

    private static final Logger log = Logger.getLogger(ExternalReportingFacade.class);
    private ExternalReporting externalReport = new RallyExternalReportingImpl();

    /**
     * Diese Methode ermöglicht die Anmeldung am System für das externe Reporting.
     *
     * @param loginData - Die Zugangsdaten für die Anmeldung
     * @return TRUE/FALSE - Ergebnis der Anmeldung
     */
    public boolean connect(LoginDataRally loginData) {
        log.debug("Entering connect Method");
        log.debug("Leaving connect Method");
        return externalReport.connect(loginData);
    }

    /**
     * Diese Methode ermöglicht die Abmeldung am System für das externe Reporting.
     *
     * @return TRUE/FALSE - Ergebnis der Abmeldung
     */
    public boolean disconnect() {
        log.debug("Entering disconnect Method");
        log.debug("Leaving disconnect Method");
        return externalReport.disconnect();
    }

    /**
     * Diese Methode ermöglicht das Abspeichern eines Testergebnisses.
     *
     * @param test   - Der zu speichernde Tests als TestCase Objekt
     * @param result - Das zu speichernde Testergebnis als Report Objekt
     * @return TRUE/FALSE - Ergebnis der Speicherung
     */
    public boolean writeTestResult(TestCase test, Report result) {
        log.debug("Entering writeTestResult Method");
        log.debug("Leaving writeTestResult Method");
        return externalReport.writeTestResult(test, result);
    }

    /**
     * Diese Methode ermöglicht es ein TestSet aus dem externen Reporting abzurufen.
     *
     * @param testSetID - Die ID des abzurufenden TestSets
     * @return
     */
    public TestSet getTestSet(String testSetID) //String filter)
    {
        log.debug("Entering getTestSet Method");
        log.debug("Leaving getTestSet Method");
        return externalReport.getTestSet(testSetID);//, filter);
    }

    /**
     * Diese Methode ermöglicht es alle TestSets als JTree Objekt abzurufen.
     *
     * @return jTree - JTree Objekt mit allen verfügbaren TestSets
     */
    public JTree getTestlabStructure() {
        log.debug("Entering getTestlabStructure Method");
        log.debug("Leaving getTestlabStructure Method");
        return externalReport.getTestlabStructure();
    }

    /**
     * Diese Methode ermöglicht es alle TestSets als JTree Objekt abzurufen.
     *
     * @return JasonArray - JasonArray Objekt mit allen verfügbaren TestSets
     */
    public List<String> getTestSetList() {
        log.debug("Entering getTestlabStructure Method");
        log.debug("Leaving getTestlabStructure Method");
        return externalReport.getTestSetList();

    }

}
