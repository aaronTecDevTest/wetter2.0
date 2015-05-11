package de.telekom.pni.rmstest.backend.manager;

//import java.awt.Toolkit;

import java.io.File;
import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
//import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import de.telekom.pni.rmstest.backend.config.RunningConfiguration_New;

/**
 * WebDriverManager ist für die Verwaltung und Konfiguration des WebDrivers zuständig.
 * Sie kann für die unterstützten Browser den jedweis benötigten WebDriver erzeugen und
 * bietet die Möglichkeit diesen weiter zu konfigurieren.
 * Unterstützt werden im Monent Internet Explorer und Firefox.
 *
 * @author M.Forster
 */
public class WebDriverManager_New {

    private static final Logger log = Logger.getLogger(WebDriverManager_New.class);

    /**
     * Diese Methode prüft anhand der RunningConfiguration des Tests, welcher
     * WebDriver benötigt wird, erzeugt diesen und gibt ihn zurück.
     *
     * @param runningConfiguration_New - RunningConfiguration des Tests
     * @return - driver - Der erzeugte Webdriver für den jeweiligen Browser
     */
    public WebDriver setUpDriver(RunningConfiguration_New runningConfiguration_New) {
        log.debug("Setting up Webdriver");
        WebDriver driver;
        //Toolkit toolkit = Toolkit.getDefaultToolkit();
        /*Dimension screenResolution = new Dimension((int)
		toolkit.getScreenSize().getWidth(), (int)
		toolkit.getScreenSize().getHeight());*/
        switch (runningConfiguration_New.getBrowser()) {
            case "IE":
                try {
                    log.debug(new File(".").getCanonicalPath());
                } catch (IOException e) {
                    log.error("Error while initializing IEDriver");
                }
                System.setProperty("webdriver.ie.driver", "wetter2.0_backend" +File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "IEDriverServer.exe");
                driver = new InternetExplorerDriver(this.setUpIEDriver());
                System.out.println("test");
                break;

            case "CH":
                try {
                    log.debug(new File(".").getCanonicalPath());
                } catch (IOException e) {
                    log.error("Error while initializing ChromeDriver");
                }
                System.setProperty("webdriver.chrome.driver", "wetter2.0_backend" +File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "chromedriver.exe");
                driver = new ChromeDriver(this.setUpChromeDriver());
                break;

            case "SA":
                try {
                    log.debug(new File(".").getCanonicalPath());
                } catch (IOException e) {
                    log.error("Error while initializing SafariDriver");
                }
                System.setProperty("webdriver.chrome.driver", "wetter2.0_backend" +File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "safariDriver.exe");
                driver = new ChromeDriver();
                break;

            case "OP":
                try {
                    log.debug(new File(".").getCanonicalPath());
                } catch (IOException e) {
                    log.error("Error while initializing ChromeDriver");
                }
                System.setProperty("webdriver.chrome.driver", "wetter2.0_backend" +File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "chromedriver.exe");
                driver = new ChromeDriver();
                break;

            default:
                driver = new FirefoxDriver(this.setUpFirefox());
                break;
        }
        //driver.manage().window().setSize(screenResolution);
        //driver.get(runningConfiguration_New.getMachine());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        log.debug("WebDriver initialized");
        return driver;
    }

    /**
     * Diese Private Methode erzeugt ein neues Firefox Profil, nimmt verschiedene Einstellungen vor
     * und gibt das Profil dann zur weiteren Verwendung zurück. Das Profil kann verwendet werden,
     * um einen FirefoxDriver weiter zu konfigurieren.
     *
     * @return profile - Das angepasste Firefox Profile für einen FirefoxDriver.
     */
    private FirefoxProfile setUpFirefox() {
        log.debug("Configuring FirefoxDriver");
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("app.update.enabled", false);
        profile.setPreference("accessibility.blockautorefresh", true);
        profile.setPreference("browser.download.manager.retention", 0);
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("browser.formfill.enable", false);
        profile.setPreference("browser.history_expire_days", 0);
        profile.setPreference("browser.history_expire_days.mirror", 180);
        profile.setPreference("browser.migration.version", 1);
        profile.setPreference("browser.places.importBookmarksHTML", true);
        profile.setPreference("browser.places.importDefaults", false);
        profile.setPreference("browser.places.leftPaneFolderId", -1);
        profile.setPreference("browser.places.migratePostDataAnnotations", false);
        profile.setPreference("browser.places.smartBookmarksVersion", 1);
        profile.setPreference("browser.places.updateRecentTagsUri", false);
        profile.setPreference("browser.preferences.advanced.selectedTabIndex", 2);
        profile.setPreference("Browser.windows.loadOnNewWindow", 0);
        profile.setPreference("browser.safebrowsing.enabled", false);
        profile.setPreference("browser.safebrowsing.malware.enabled", false);
        profile.setPreference("browser.search.update", false);
        profile.setPreference("browser.shell.checkDefaultBrowser", false);
        profile.setPreference("browser.startup.homepage", "about:blank");
        profile.setPreference("browser.tabs.forceHide", false);
        profile.setPreference("browser.tabs.warnOnClose", false);
        profile.setPreference("browser.tabs.warnOnOpen", false);
        profile.setPreference("extensions.checkCompatibility", false);
        profile.setPreference("extensions.checkUpdateSecurity", false);
        profile.setPreference("extensions.newAddons", false);
        profile.setPreference("extensions.update.enabled", false);
        profile.setPreference("extensions.update.notifyUser", false);
        profile.setPreference("security.warn_entering_weak", false);
        profile.setPreference("security.warn_viewing_mixed", false);
        profile.setPreference("signon.rememberSignons", false);
        profile.setPreference("general.appversion.override", "");
        profile.setPreference("general.description.override", "");
        profile.setPreference("general.platform.override", "");
        log.debug("FirefoxDriver configured successfully");
        return profile;
    }

    /**
     * Diese Private Methode erzeugt ein DesiredCapabilities Objekt, über das Einstellungen an einem
     * InternetExplorerDriver vorgenommen werden können. Das angepasste Objekt wird zur weiteren Verwendung
     * zurückgegeben.
     *
     * @return ieCapabilities - Das angepasste DesiredCapabilities Objekt
     */
    public DesiredCapabilities setUpIEDriver() {
        log.debug("Configuring IEDriver");
        DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
        ieCapabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
        ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        ieCapabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
        ieCapabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
        ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        ieCapabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, true);
//		ieCapabilities.setCapability(InternetExplorerDriver.ELEMENT_SCROLL_BEHAVIOR, "InternetExplorerElementScrollBehavior.Top");
//		ieCapabilities.setCapability(InternetExplorerDriver.BROWSER_ATTACH_TIMEOUT, "TimeSpan.FromSeconds(timeOut");
//		ieCapabilities.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, "InternetExplorerUnexpectedAlertBehavior.Ignore");
        ieCapabilities.setVersion("8");
        log.debug("IeDriver configured successfully");
        return ieCapabilities;
    }

    /**
     * Diese Private Methode erzeugt ein DesiredCapabilities Objekt, über das Einstellungen an einem
     * ChromeDriver vorgenommen werden können. Das angepasste Objekt wird zur weiteren Verwendung
     * zurückgegeben.
     *
     * @return chOptions - Das angepasste ChromeOptions Objekt
     */
    public ChromeOptions setUpChromeDriver() {
        log.debug("Configuring ChromeDriver");
        ChromeOptions chOptions = new ChromeOptions();
        //List <String> arguments = new ArrayList<String>();

        //TODO bitte hier noch sinnvolle optionnen eintrgagen

        //arguments.add("--silenc");
        //arguments.add("--use-mobile-user-agent = Mozilla/5.0 (Linux; U; Android 4.3; en-us; SM-N900T Build/JSS15J) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30");

        //chOptions.addArguments(arguments);
        log.debug("ChromeDriver configured successfully");
        return chOptions;
    }
}
