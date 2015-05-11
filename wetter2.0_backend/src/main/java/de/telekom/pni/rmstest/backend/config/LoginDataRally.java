package de.telekom.pni.rmstest.backend.config;

/**
 * Die Klasse LoginDataRally.java ist ein Container, in dem ugangsdaten für Rally angelegt werden können.
 * Die Klasse wird für den Transport der Zugangsdaten für die Anmeldung an Rally verwendet. Die Klasse
 * wurde vom ursprünglichen Entwickler des Frameworks entwicklet und wird ohne Änderungen weiter verwendet.
 *
 * @author A.Roth
 */
public class LoginDataRally {

    private String username;
    private String password;
    private String workspace;
    private String project;
    private boolean dbActive;

    public static final String WORKSPACE_DEFAULT = "Playground";
    public static final String PROJECT_DEFAULT = "CAE";

    public LoginDataRally(String usern, String passw, String workspace, String project) {
        this.username = usern;
        this.password = passw;
        this.workspace = workspace;
        this.project = project;
        this.dbActive = true;
    }

    /**
     * Konstruktor der Klasse
     */
    public LoginDataRally() {
    }

    /**
     * Getter Methode, die den Inhalt der Variable username zurückgibt
     *
     * @return username - Benutzername für die Rally Anmeldung
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter Methode zum festlegen des username
     *
     * @param project - Project für die Rally Anmeldung
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter Methode, die den Inhalt der Variable passwort zurückgibt
     *
     * @return password - Das Passwort für die Anmeldung an Rally
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter Methode zum festlegen des password
     *
     * @param project - Project für die Rally Anmeldung
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter Methode, die den Inhalt der Variable workspace zurückgibt
     *
     * @return workspace - Workspace für die Rally Anmeldung
     */
    public String getWorkspace() {
        return workspace;
    }

    /**
     * Setter Methode zum festlegen des workspace
     *
     * @param project - Project für die Rally Anmeldung
     */
    public void setDomain(String workspace) {
        this.workspace = workspace;
    }

    /**
     * Getter Methode, die den Inhalt der Variable project zurückgibt
     *
     * @return project - Project für die Rally Anmeldung
     */
    public String getProject() {
        return project;
    }

    /**
     * Setter Methode zum festlegen des project
     *
     * @param project - Project für die Rally Anmeldung
     */
    public void setProject(String project) {
        this.project = project;
    }

    /**
     * Getter Methode, die den Inhalt der Variable dbActive zurückgibt
     *
     * @return dbActive - Status ob MySQL Datenbank aktiv ist oder nicht
     */
    public boolean isDbActive() {
        return dbActive;
    }

    /**
     * Setter Methode zum festlegen des dbActive
     *
     * @param dbActive - Status ob MySQL Datenbank aktiv ist oder nicht
     */
    public void setDbActive(boolean dbActive) {
        this.dbActive = dbActive;
    }


}
