package de.telekom.pni.rmstest.backend.utilities;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * Die Klasse TestingUtil.java wurde vom ursprünglichen Entwickler des Frameworks
 * entwickelt und wird ohne Anpassung weiterverwendet. Die Klasse wird für das
 * Ausführen von GenericTest.java Tests benötigt. Da sie bei meinen Anpassungen
 * keine Rolle spielt, wird sie von mir nicht weiter protokolliert.
 *
 * @author A.Roth
 */
public class TestingUtil {

    private final String DEFAULT_USERNAME = "corem";
    private final String DEFAULT_PASSWORD = "corem2view";

    /**
     * Parses the RMS-URL.xml file (under src folder) and generates an easy to
     * use Hashmap.
     *
     * @return Hashmap representing the name-value pairs in the xml file.
     * @throws SAXException
     */
    public static HashMap<String, String> parseRMSURL() throws SAXException {

        HashMap<String, String> values = new HashMap<String, String>();
        File xmlFile = new File(getRMSTestingSrcFolder() + File.separator
                + "RMS-URL.xml");

        // File xmlFile = new File("src" + File.separator + "CMS-URL.xml");

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document doc = null;
        try {
            doc = dBuilder.parse(xmlFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        doc.getDocumentElement().normalize();
        NodeList nameValuePairs = doc.getElementsByTagName("Variable");
        Element node = null;
        Node nameNode = null, valueNode = null;
        for (int i = 0; i < nameValuePairs.getLength(); i++) {
            node = (Element) nameValuePairs.item(i);
            nameNode = node.getElementsByTagName("Name").item(0);
            valueNode = node.getElementsByTagName("Value").item(0);

            //values.put(nameNode.get getTextContent(), valueNode.getTextContent());
            values.put(valueNode.getNodeValue(), valueNode.getNodeValue());

            // System.out.println("Added: " + nameNode.getTextContent() +
            // " <-> "
            // + valueNode.getTextContent());
        }
        return values;
    }

    /**
     * @return Testing root directory based on the environment variable
     * "CMSTesting" which must point to the projects parent folder (e.g.
     * c.\workspace)
     */
    public static String getTestingRoot() {
//		System.out.println(System.getenv().get("RMSTesting"));
        Map<String, String> env = System.getenv();

        String testingRoot = env.get("RMSTesting");
        if (testingRoot == null || testingRoot.length() == 0) {
            testingRoot = env.get("RMSTESTING");
        }
        return testingRoot;
    }

    public static String getRMSTestingSrcFolder() {
        return getRMSTestingFolder() + File.separator + "src";
    }

    public static String getRMSTestingFolder() {
        return getTestingRoot() + File.separator + "RMSTesting";
    }

	/*public WebDriver getBrowser(RunningConfiguration_New runningConfiguration)
            throws BowserNotYetSupportedException {
		if (runningConfiguration.getBrowser().equalsIgnoreCase(
				RunningConfiguration_New.FIREFOX_BROWSER)) {
			return new FirefoxDriver();
		} else if (runningConfiguration.getBrowser().equalsIgnoreCase(
				RunningConfiguration_New.INTERNET_EXPLORER_BROWSER)) {

			String ws = System.getenv("RMSTesting");
			String pathToIEExecutable = ws
					+ "\\RMSTesting\\iedriver\\IEDriverServer.exe";
			System.setProperty("webdriver.ie.driver", pathToIEExecutable);
			DesiredCapabilities ieCapabilities = DesiredCapabilities
					.internetExplorer();
			ieCapabilities
					.setCapability(
							InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
							true);
			return new InternetExplorerDriver(ieCapabilities);
		} else if (runningConfiguration.getBrowser().equalsIgnoreCase(
				RunningConfiguration_New.SAFARI_BROWSER)) {
			throw new BowserNotYetSupportedException();

		} else if (runningConfiguration.getBrowser().equalsIgnoreCase(
				RunningConfiguration_New.CHROME_BROWSER)) {
			String testingHome = System.getenv("RMSTesting");
			// C:\workspace\CMSTesting\%CMSTesting%\CMSTesting\chromedrive
			System.setProperty("webdriver.chrome.driver", testingHome
					+ "\\RMSTesting\\chromedrive\\chromedriver.exe");
			System.setProperty("webdriver.chrome.bin",
					"%USERPROFILE%\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe");
			return new ChromeDriver();

		}
		return null;
	}*/

    /**
     * @return The element if found before timeout, otherwise null
     */

    public String getDefaultUsername() {
        return this.DEFAULT_USERNAME;
    }

    public String getDefaultPassword() {
        return this.DEFAULT_PASSWORD;
    }

    public static void setProxy() {
        System.setProperty("http.proxyHost", "192.168.1.112");
        System.setProperty("http.proxyPort", "3128");
    }

    public static void clearProxy() {
        System.clearProperty("http.proxyHost");
        System.clearProperty("http.proxyPort");
    }
}
