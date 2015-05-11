package de.telekom.pni.rmstest.frontend.view;

import java.util.Properties;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.MessageBox;
import swing2swt.layout.BorderLayout;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Projet Packete (Extern)
 */
import de.telekom.pni.rmstest.backend.config.LoginDataRally;
import de.telekom.pni.rmstest.frontend.controller.ApplicationController_New;
import de.telekom.pni.rmstest.middleware.properties.PropertiesFacade;


/**
 * @author a.kutekidila
 */
public class RALLYLoginDataForm_New extends Shell {

    private Text txtUsername;
    private Text txtPassword;
    private Text txtWorkspace;
    private Text txtProject;
    private Text txtIP;

    private static ApplicationController_New appController = null;
    private PropertiesFacade propFacade = new PropertiesFacade();
    private Properties DB = propFacade.getProperties("db");
    private String databaseIP = DB.getProperty("general.ip");
    private String databaseName = DB.getProperty("general.DBname");
    private static RALLYLoginDataForm_New shell = null;
    private Text txtWetterdatennext;

    private Pattern pattern;
    private Matcher matcher;

    private static final String IPADDRESS_PATTERN =
            "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";


    /**
     * Launch the application.
     *
     * @param args
     */
    public static void main(String args[]) {
        try {
            Display display = Display.getDefault();
            ApplicationController_New cont = new ApplicationController_New();
            shell = new RALLYLoginDataForm_New(display, cont);

            shell.open();
            shell.layout();

            while (!shell.isDisposed()) {
                if (!display.readAndDispatch()) {
                    display.sleep();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Create the shell.
     *
     * @param windows
     */
    public RALLYLoginDataForm_New(final Display windows, ApplicationController_New controller) {
        super(windows, SWT.CLOSE | SWT.MIN | SWT.MAX | SWT.TITLE);
        RALLYLoginDataForm_New.appController = controller;

        setLayout(new BorderLayout(0, 0));

        Composite cmp_Center = new Composite(this, SWT.NONE);
        cmp_Center.setLayoutData(BorderLayout.CENTER);
        cmp_Center.setLayout(null);

        Label lblUsername = new Label(cmp_Center, SWT.NONE);
        lblUsername.setAlignment(SWT.RIGHT);
        lblUsername.setBounds(10, 31, 89, 15);
        lblUsername.setText("Username:");

        Label lblPassword = new Label(cmp_Center, SWT.NONE);
        lblPassword.setAlignment(SWT.RIGHT);
        lblPassword.setText("Password:");
        lblPassword.setBounds(10, 71, 89, 15);

        Label lblWorkspace = new Label(cmp_Center, SWT.NONE);
        lblWorkspace.setAlignment(SWT.RIGHT);
        lblWorkspace.setText("Workspace:");
        lblWorkspace.setBounds(10, 205, 89, 15);

        Label lblProject = new Label(cmp_Center, SWT.NONE);
        lblProject.setAlignment(SWT.RIGHT);
        lblProject.setText("Project:");
        lblProject.setBounds(10, 237, 89, 15);

        txtUsername = new Text(cmp_Center, SWT.BORDER);
        txtUsername.setBounds(112, 31, 360, 21);
        txtUsername.setText("");

        txtPassword = new Text(cmp_Center, SWT.BORDER);
        txtPassword.setText("");
        txtPassword.setBounds(112, 68, 360, 21);
        txtPassword.setEchoChar('*');

        txtWorkspace = new Text(cmp_Center, SWT.BORDER);
        txtWorkspace.setEnabled(false);
        txtWorkspace.setBounds(112, 202, 279, 21);
        //	txtWorkspace.setText(ResourceBundle.getBundle("properties/rally.properties").getString("workspace"));//appController.getDefaultWorkspace());
        txtWorkspace.setText(appController.getDefaultWorkspace());

        txtProject = new Text(cmp_Center, SWT.BORDER);
        txtProject.setEnabled(false);
        txtProject.setBounds(112, 237, 279, 21);
        txtProject.setText(appController.getDefaultProject());

        final Button btnEditWorkspace = new Button(cmp_Center, SWT.NONE);
        btnEditWorkspace.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                if (!txtWorkspace.getEnabled()) {
                    txtWorkspace.setEnabled(!txtWorkspace.getEnabled());
                    btnEditWorkspace.setText("OK");
                } else {
                    txtWorkspace.setEnabled(!txtWorkspace.getEnabled());
                    btnEditWorkspace.setText("Edit");
                }

            }
        });
        btnEditWorkspace.setBounds(397, 195, 75, 25);
        btnEditWorkspace.setText("Edit");

        final Button btnEditProject = new Button(cmp_Center, SWT.NONE);
        btnEditProject.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                if (!txtProject.getEnabled()) {
                    txtProject.setEnabled(!txtProject.getEnabled());
                    btnEditProject.setText("OK");
                } else {
                    txtProject.setEnabled(!txtProject.getEnabled());
                    btnEditProject.setText("Edit");
                }
            }
        });
        btnEditProject.setText("Edit");
        btnEditProject.setBounds(397, 237, 75, 25);

        final Button btnCheckButton = new Button(cmp_Center, SWT.CHECK);
        btnCheckButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                if (btnCheckButton.getSelection()) {
                    txtIP.setEnabled(!txtIP.getEnabled());
                    txtWetterdatennext.setEnabled(!txtWetterdatennext.getEnabled());
                    appController.setDbStatus(true);
                } else {
                    txtIP.setEnabled(!txtIP.getEnabled());
                    txtWetterdatennext.setEnabled(!txtWetterdatennext.getEnabled());
                    appController.setDbStatus(false);
                }
            }
        });
        btnCheckButton.setBounds(112, 115, 124, 16);
        btnCheckButton.setText("Enable Database");

        Label lblNewLabel_1 = new Label(cmp_Center, SWT.NONE);
        lblNewLabel_1.setAlignment(SWT.RIGHT);
        lblNewLabel_1.setBounds(261, 115, 55, 15);
        lblNewLabel_1.setText("IP:");

        txtIP = new Text(cmp_Center, SWT.BORDER | SWT.CENTER);
        txtIP.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent arg0) {
                if (!validate(txtIP.getText())) {
                    MessageBox messagebox = new MessageBox(shell, SWT.ICON_WARNING);
                    messagebox.setText("IP-Adresse");
                    messagebox.setMessage("Bitte geben Sie ein IP-Adresse \n in den Format ddd.ddd.ddd. ein!");
                    messagebox.open();
                    txtIP.setText("");
                    txtIP.setFocus();
                }
            }
        });
        txtIP.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseDoubleClick(MouseEvent arg0) {
                txtIP.setText("");
            }
        });
        txtIP.setText("127.255.90.90");
        txtIP.setBounds(322, 115, 150, 21);
        txtIP.setText(databaseIP.replaceAll("//", ""));

        txtWetterdatennext = new Text(cmp_Center, SWT.BORDER | SWT.CENTER);
        txtWetterdatennext.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseDoubleClick(MouseEvent e) {
                txtWetterdatennext.setText("");
            }
        });
        txtWetterdatennext.setText("wetterdaten_next");
        txtWetterdatennext.setBounds(322, 152, 150, 21);
        txtWetterdatennext.setText(databaseName.replaceAll("/", "/"));

        Label lblDatenbank = new Label(cmp_Center, SWT.NONE);
        lblDatenbank.setText("Datenbank:");
        lblDatenbank.setAlignment(SWT.RIGHT);
        lblDatenbank.setBounds(246, 158, 70, 15);

        Composite cmp_South = new Composite(this, SWT.NONE);
        cmp_South.setLayoutData(BorderLayout.SOUTH);
        cmp_South.setLayout(null);

        Button btnOk = new Button(cmp_South, SWT.NONE);
        btnOk.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {

                //Login prüfen
                /*
				boolean wrongData = false;
				if (txtUsername.getText() == null|| txtUsername.getText().length() == 0) {
					wrongData = true;
				}
				if (txtPassword.getText() == null|| txtPassword.getText().length() == 0) {
					wrongData = true;
				}
				if (txtWorkspace.getText() == null|| txtWorkspace.getText().length() == 0) {
					wrongData = true;
				}
				if (txtProject.getText() == null || txtProject.getText().length() == 0) {
					wrongData = true;
				}
				
				if (wrongData)
					return; // try again*/

                LoginDataRally ld = new LoginDataRally(txtUsername.getText(),
                        txtPassword.getText(),
                        txtWorkspace.getText(),
                        txtProject.getText());

                if (btnCheckButton.getSelection()) {
                    ld.setDbActive(true);
                } else {
                    ld.setDbActive(false);
                }

                if (appController.connectExternalReporting(ld)) {
                    ApplicationWindows_New window_App = new ApplicationWindows_New(appController);
                    appController.setViewController(window_App);
                    appController.setLoginData(ld);
                    propFacade.setProperty("db", "general.ip", txtIP.getText().replaceAll("//", ""));
                    shell.dispose();
                    appController.runApplication();
                    //controller.startApplication();
                } else { // could not log in
                    //txtUsername.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
                    //txtPassword.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
                    //txtWorkspace.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
                    //txtProject.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
                    MessageBox messagebox = new MessageBox(shell, SWT.ICON_ERROR);
                    messagebox.setMessage("Username or Password is wrong!");
                    messagebox.open();
                    //wrongLoginDataLabel.setVisible(true);
                }

            }
        });
        btnOk.setBounds(175, 0, 70, 25);
        btnOk.setText("OK");
        this.setDefaultButton(btnOk);


        Button btnCancel = new Button(cmp_South, SWT.NONE);
        btnCancel.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                dispose();
            }
        });
        btnCancel.setBounds(265, 0, 70, 25);
        btnCancel.setText("Cancel");
        createContents();
    }

    /**
     * Validate ip address with regular expression
     *
     * @param ip ip address for validation
     * @return true valid ip address, false invalid ip address
     */
    public boolean validate(final String ip) {
        pattern = Pattern.compile(IPADDRESS_PATTERN);
        matcher = pattern.matcher(ip);
        return matcher.matches();
    }


    /**
     * Create contents of the shell.
     */
    protected void createContents() {
        setText("Rally Login Data");
        setSize(510, 325);
    }

    @Override
    protected void checkSubclass() {
        // Disable the check that prevents subclassing of SWT components
    }
}
