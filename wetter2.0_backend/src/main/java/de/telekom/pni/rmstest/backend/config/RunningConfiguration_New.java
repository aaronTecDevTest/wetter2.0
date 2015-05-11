package de.telekom.pni.rmstest.backend.config;

/**
 * Die Klasse RunningConfiguration.java wird für die Konfiguration der Testdurchläufe verwendet. Die Klasse wurde
 * vom ursprünglichen Entwickler des Frameworks entwicklet und wird ohne Änderungen weiter verwendet. Da die
 * Klasse nicht von mir verändert wurde, ist sie nicht weiter Dokumentiert.
 *
 * @author A.Roth
 */
public class RunningConfiguration_New {

    public static final String FIREFOX_BROWSER = "FF";
    public static final String INTERNET_EXPLORER_BROWSER = "IE";
    public static final String SAFARI_BROWSER = "SA";
    public static final String CHROME_BROWSER = "CH";
    public static final String OPERA_BROWSER = "OP";
    private String machine;
    private String browser;

    public RunningConfiguration_New() {
    }


    public RunningConfiguration_New(String browser, String machine) {
        this.browser = browser;
        this.machine = machine;
    }

    public String getBrowser() {
        return browser;
    }

    @Override
    public String toString() {
        return "RunningConfiguration [machine=" + machine + ", browser="
                + browser + "]";
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getMachine() {
        return machine;
    }

    public void setMachine(String machine) {
        this.machine = machine;
    }


    public static RunningConfiguration_New defaultFFOnSP() {
        return new RunningConfiguration_New("FF",
                "https://10.224.220.91");

    }

    public static RunningConfiguration_New defaultIEOnSP() {
        return new RunningConfiguration_New("IE",
                "https://10.224.220.91");
    }

    public static RunningConfiguration_New defaultCHOnSP() {
        return new RunningConfiguration_New("CH",
                "https://10.224.220.91");
    }

    public static RunningConfiguration_New defaultFFOnAB() {
        return new RunningConfiguration_New("FF",
                "https://10.224.220.92");
    }

    public static RunningConfiguration_New defaultIEOnAB() {
        return new RunningConfiguration_New("IE",
                "https://10.224.220.92");
    }

    public static RunningConfiguration_New defaultCHOnAB() {
        return new RunningConfiguration_New("CH",
                "https://10.224.220.92");
    }

    public static RunningConfiguration_New defaultFFOnWI() {
        return new RunningConfiguration_New("FF",
                "https://rms.intra.t-online.de");
    }

    public static RunningConfiguration_New defaultIEOnWI() {
        return new RunningConfiguration_New("IE",
                "https://rms.intra.t-online.de");
    }

    public static RunningConfiguration_New defaultCHOnWI() {
        return new RunningConfiguration_New("CH",
                "https://rms.intra.t-online.de");
    }


}
