package de.telekom.pni.rmstest.frontend.view;


import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.ProgressBar;

import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import org.apache.log4j.Logger;
import swing2swt.layout.BorderLayout;
import de.telekom.pni.rmstest.backend.config.TestrunConfiguration_New;
import de.telekom.pni.rmstest.backend.testentities.interfaces.TestCase;
import de.telekom.pni.rmstest.backend.testentities.interfaces.TestSet;
import de.telekom.pni.rmstest.frontend.controller.ApplicationController_New;
import de.telekom.pni.rmstest.frontend.view.TestingView_New;

import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
//End


public class ApplicationWindows_New implements TestingView_New {

    private final static Logger log = Logger.getLogger(ApplicationWindows_New.class);
    protected static Shell shlGuiAutomated = new Shell();

    final Display display = Display.getDefault();
    final static Composite compositeSouth = new Composite(shlGuiAutomated, SWT.NONE);
    final static ProgressBar progressBar = new ProgressBar(compositeSouth, SWT.NONE);
    static Label lblDatabaseStatus;
    static Label lblStatusTestcase;


    //private Table table;
    private Combo comboTestSet;
    private Combo comboFilter;
    private static Combo comboBrowser;
    private static Combo comboServer;

    private Button btnStopTest;
    private Button btnRunTest;
    private Button btnUpdateTable;

    //Nicht von mir
    private ApplicationController_New controller;
    private List<String> selectedTestCase = null;
    private TestSet displayedTestSet;

    private TableViewer tableViewer;
    private Table table;
    private String testSetPreSelect;


    //Ende

    /**
     * Launch the application.
     * @param args
     *
    /public static void main(String[] args) {
    try {
    ApplicationWindows_New window = new ApplicationWindows_New();
    window.open();
    } catch (Exception e) {
    e.printStackTrace();
    }
    }*/

    /**
     * @wbp.parser.entryPoint
     */
    public ApplicationWindows_New() {
        super();
        open();

    }

    /**
     * @wbp.parser.entryPoint
     */
    public ApplicationWindows_New(ApplicationController_New cont) {

        log.debug("Entering Mainwindow");
        controller = cont;
        //testrunUtil = new TestrunUtil_New(this, controller);
        open();
        log.debug("Leaveing Mainwindow");
    }


    /**
     * Open the window.
     */
    public void open() {
        //setDisplay(Display.getDefault())
        createContents();
        controller.setApp(this);
        shlGuiAutomated.open();
        shlGuiAutomated.layout();
        while (!shlGuiAutomated.isDisposed()) {
            if (!getDisplay().readAndDispatch()) {
                getDisplay().sleep();
            }
        }
    }

    /**
     * Create contents of the window.
     */
    protected void createContents() {
        //shlGuiAutomated = new Shell();
        shlGuiAutomated.setSize(1120, 600);
        shlGuiAutomated.setText("GUI Automated");
        shlGuiAutomated.setLayout(new BorderLayout(0, 0));

        Menu menu = new Menu(shlGuiAutomated, SWT.BAR);
        shlGuiAutomated.setMenuBar(menu);

        MenuItem mntmDatei_1 = new MenuItem(menu, SWT.CASCADE);
        mntmDatei_1.setText("Datei");

        Menu menu_1 = new Menu(mntmDatei_1);
        mntmDatei_1.setMenu(menu_1);

        MenuItem mntmOeffnen = new MenuItem(menu_1, SWT.NONE);
        mntmOeffnen.setText("Öffnen");

        MenuItem mntmSpeichern = new MenuItem(menu_1, SWT.NONE);
        mntmSpeichern.setText("Speichern");

        new MenuItem(menu_1, SWT.SEPARATOR);

        MenuItem mntmTestsetLaden = new MenuItem(menu_1, SWT.NONE);
        mntmTestsetLaden.setText("Testset laden...");

        MenuItem mntmBearbeiten = new MenuItem(menu, SWT.CASCADE);
        mntmBearbeiten.setText("Bearbeiten");

        Menu menu_2 = new Menu(mntmBearbeiten);
        mntmBearbeiten.setMenu(menu_2);

        MenuItem mntmKopieren = new MenuItem(menu_2, SWT.NONE);
        mntmKopieren.setText("Kopieren");

        MenuItem mntmEinfuegen = new MenuItem(menu_2, SWT.NONE);
        mntmEinfuegen.setText("Einfügen");

        MenuItem mntmOptions = new MenuItem(menu, SWT.CASCADE);
        mntmOptions.setText("Options");

        Menu menu_3 = new Menu(mntmOptions);
        mntmOptions.setMenu(menu_3);

        MenuItem mntmDatenbankEinstellung = new MenuItem(menu_3, SWT.NONE);
        mntmDatenbankEinstellung.setText("Datenbank Einstellung");

        MenuItem mntmEinstellung = new MenuItem(menu_3, SWT.NONE);
        mntmEinstellung.setText("Einstellung");

        MenuItem menuItem = new MenuItem(menu, SWT.CASCADE);
        menuItem.setText("?");

        Menu menu_4 = new Menu(menuItem);
        menuItem.setMenu(menu_4);

        MenuItem mntmHilfe = new MenuItem(menu_4, SWT.NONE);
        mntmHilfe.setText("Hilfe");

        MenuItem mntmUeber = new MenuItem(menu_4, SWT.NONE);
        mntmUeber.setText("Über");

        Composite compositeCenter = new Composite(shlGuiAutomated, SWT.BORDER);
        compositeCenter.setLayoutData(BorderLayout.CENTER);
        compositeCenter.setLayout(new BorderLayout(0, 10));

        Composite composite_CenterSouth = new Composite(compositeCenter, SWT.NONE);
        composite_CenterSouth.setLayoutData(BorderLayout.SOUTH);
        composite_CenterSouth.setLayout(new GridLayout(4, false));

        btnStopTest = new Button(composite_CenterSouth, SWT.NONE);
        GridData gd_btnStopTest = new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1);
        gd_btnStopTest.widthHint = 73;
        btnStopTest.setLayoutData(gd_btnStopTest);
        btnStopTest.setSize(61, 25);
        btnStopTest.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                try {
                    System.err.println("Test run will stop shortly...");
                    controller.stopTestRun(null);
                    progressBar.setVisible(false);
                    progressBar.setSelection(0);
                    table.setFocus();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
        });
        btnStopTest.setText("Stop Test");
        new Label(composite_CenterSouth, SWT.NONE);

        btnRunTest = new Button(composite_CenterSouth, SWT.NONE);
        GridData gd_btnRunTest = new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1);
        gd_btnRunTest.widthHint = 76;
        btnRunTest.setLayoutData(gd_btnRunTest);
        btnRunTest.setSize(58, 25);
        btnRunTest.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {

                try {
                    log.debug("Run Test Button clicked");
                    String browser = comboBrowser.getText();
                    String server = comboServer.getText();

                    if (browser.contains("<Select>") && !server.contains("<Select>")) {
                        MessageBox messagebox = new MessageBox(shlGuiAutomated, SWT.ICON_WARNING);
                        messagebox.setText("Browser-Filter Warning");
                        messagebox.setMessage("Die Filter 'Browser' dürfen \n den Wert <Select> nicht haben. \n Bitte ein Wert auswählen.");
                        messagebox.open();

                    } else if (!browser.contains("<Select>") && server.contains("<Select>")) {
                        MessageBox messagebox = new MessageBox(shlGuiAutomated, SWT.ICON_WARNING);
                        messagebox.setText("Server-Filter Warning");
                        messagebox.setMessage("Die Filter 'Server' dürfen \n den Wert <Select> nicht haben. \n Bitte ein Wert auswählen.");
                        messagebox.open();
                    } else {
                        getDisplayedTestSet().setConfig(new TestrunConfiguration_New(1));
                        getDisplayedTestSet().getConfig().setTestSet(displayedTestSet);

                        getDisplayedTestSet().getConfig().setBrowser(browser);
                        getDisplayedTestSet().getConfig().setMachine(server);
                        getDisplayedTestSet().getConfig().setName(server);

                        progressBar.setSelection(0);
                        progressBar.setVisible(true);

                        if (selectedTestCase == null) {

                            controller.runTestSet(getDisplayedTestSet(), null);//, -1); // null = whole test set; no // selection made
                        } else {
                            controller.runTestSet(getDisplayedTestSet(), selectedTestCase);//, -1); // null = whole test set; no // selection made
                        }
                    }

                } catch (Exception e) {
                    System.err.println(e);
                }
            }
        });
        btnRunTest.setText("Run Test");

        btnUpdateTable = new Button(composite_CenterSouth, SWT.NONE);
        btnUpdateTable.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                try {
                    upadateTable();
                    table.setFocus();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
        });
        btnUpdateTable.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));
        btnUpdateTable.setText("Update Table");

        tableViewer = new TableViewer(compositeCenter, SWT.BORDER | SWT.CHECK | SWT.FULL_SELECTION | SWT.HIDE_SELECTION | SWT.MULTI);
        table = tableViewer.getTable();
        table.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                if (event.detail == SWT.CHECK) {
                    boolean checktItems;
                    for (int i = 0; i < table.getItemCount(); i++) {
                        checktItems = table.getItems()[i].getChecked();
                        if (checktItems) {
                            table.setSelection(i);
                        } else {
                            table.deselect(i);
                        }
                    }
                    table.redraw();
                }
            }
        });

        table.addListener(SWT.KeyDown, new Listener() {
            public void handleEvent(Event event) {
                if ((event.stateMask == SWT.CTRL) && (event.keyCode == 'a')) {
                    for (int i = 0; i < table.getItemCount(); i++) {
                        table.getItems()[i].setChecked(true);
                    }
                    table.selectAll();
                    comboFilter.select(1);
                }
            }
        });

        table.addListener(SWT.MouseDown, new Listener() {
            public void handleEvent(Event event) {
                if ((event.stateMask == SWT.CTRL)) {

                    if (selectedTestCase != null)
                        selectedTestCase = null;

                    selectedTestCase = new ArrayList<String>();
                    int selectionIndices[] = table.getSelectionIndices();

                    for (int i = 0; i < selectionIndices.length; i++) {
                        table.getItem(selectionIndices[i]).setChecked(true);
                        selectedTestCase.add(table.getSelection()[i].getText(2));
                    }
                } else {
                    for (int i = 0; i < table.getItemCount(); i++) {
                        table.getItems()[i].setChecked(false);
                    }
                    table.deselectAll();
                    comboFilter.select(0);
                }
            }
        });

        table.setVisible(true);
        table.setLinesVisible(true);
        table.setHeaderVisible(true);

        TableViewerColumn tvCheck = new TableViewerColumn(tableViewer, SWT.NONE);
        TableColumn tblclmnCheck = tvCheck.getColumn();
        tblclmnCheck.setResizable(false);
        tblclmnCheck.setWidth(41);

        TableViewerColumn tvcTCID = new TableViewerColumn(tableViewer, SWT.NONE);
        final TableColumn tblclmnTCID = tvcTCID.getColumn();
        tblclmnTCID.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                System.out.println("test");
            }
        });
        tblclmnTCID.setMoveable(true);
        tblclmnTCID.setWidth(60);
        tblclmnTCID.setText("TC - ID");

        TableViewerColumn tvcTCName = new TableViewerColumn(tableViewer, SWT.NONE);
        TableColumn tblclmnTCName = tvcTCName.getColumn();
        tblclmnTCName.setMoveable(true);
        tblclmnTCName.setWidth(229);
        tblclmnTCName.setText("TC - Name");

        TableViewerColumn tvcTCClassName = new TableViewerColumn(tableViewer, SWT.NONE);
        TableColumn tblclmnTCClass = tvcTCClassName.getColumn();
        tblclmnTCClass.setMoveable(true);
        tblclmnTCClass.setWidth(431);
        tblclmnTCClass.setText("TC - Class Name");

        TableViewerColumn tvcTCStatus = new TableViewerColumn(tableViewer, SWT.NONE);
        TableColumn tblclmnTCLastRunStatus = tvcTCStatus.getColumn();
        tblclmnTCLastRunStatus.setMoveable(true);
        tblclmnTCLastRunStatus.setWidth(84);
        tblclmnTCLastRunStatus.setText("TC - Status");

        TableViewerColumn tvTCLastRun = new TableViewerColumn(tableViewer, SWT.NONE);
        TableColumn tblclmnTCLastRun = tvTCLastRun.getColumn();
        tblclmnTCLastRun.setMoveable(true);
        tblclmnTCLastRun.setWidth(93);
        tblclmnTCLastRun.setText("TC - Last Run");

        TableViewerColumn tvTCAutomatable = new TableViewerColumn(tableViewer, SWT.NONE);
        TableColumn tblclmnTCAutomatable = tvTCAutomatable.getColumn();
        tblclmnTCAutomatable.setMoveable(true);
        tblclmnTCAutomatable.setWidth(119);
        tblclmnTCAutomatable.setText("TC - Automatable");

        Listener sortListener = new Listener() {

            public void handleEvent(Event e) {
                TableItem[] items = table.getItems();
                Collator collator = Collator.getInstance(Locale.getDefault());
                TableColumn column = (TableColumn) e.widget;
                int index = column == tblclmnTCID ? 0 : 1;
                for (int i = 1; i < items.length; i++) {
                    String value1 = items[i].getText(index);
                    for (int j = 0; j < i; j++) {
                        String value2 = items[j].getText(index);
                        if (collator.compare(value1, value2) < 0) {
                            String[] values = {items[i].getText(0),
                                    items[i].getText(1), items[i].getText(2),
                                    items[i].getText(3)};
                            items[i].dispose();
                            TableItem item = new TableItem(table, SWT.NONE, j);
                            item.setText(values);
                            items = table.getItems();
                            break;
                        }
                    }
                }
                table.setSortColumn(column);
                upadateTable();
            }
        };

        tblclmnTCID.addListener(SWT.SELECTED, sortListener);
        tblclmnTCName.addListener(SWT.SELECTED, sortListener);
        tblclmnTCClass.addListener(SWT.SELECTED, sortListener);
        tblclmnTCLastRunStatus.addListener(SWT.SELECTED, sortListener);
        tblclmnTCLastRun.addListener(SWT.SELECTED, sortListener);
        tblclmnTCAutomatable.addListener(SWT.SELECTED, sortListener);

        table.setSortColumn(tblclmnTCID);
        table.setSortDirection(SWT.UP);

        Composite compositeNorth = new Composite(shlGuiAutomated, SWT.NONE);
        compositeNorth.setLayoutData(BorderLayout.NORTH);
        compositeNorth.setLayout(new GridLayout(4, false));

        Label lblTestsets = new Label(compositeNorth, SWT.NONE);
        lblTestsets.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
        lblTestsets.setText("Test-Sets");

        Label lblNewLabel = new Label(compositeNorth, SWT.NONE);
        lblNewLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
        lblNewLabel.setText("Filter");

        Label lblServer = new Label(compositeNorth, SWT.NONE);
        lblServer.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
        lblServer.setText("Server");

        Label lblBrowser = new Label(compositeNorth, SWT.NONE);
        lblBrowser.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
        lblBrowser.setText("Browser");

        comboTestSet = new Combo(compositeNorth, SWT.READ_ONLY);
        comboTestSet.setItems(new String[]{"<Select>"});
        comboTestSet.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                try {
                    if (!comboTestSet.getText().equals("<Select>") &&
                            !testSetPreSelect.equals(comboTestSet.getText())) {
                        upadateTable();
                        table.setFocus();
                    } else {
                        table.setFocus();
                        return;
                    }
                } catch (Exception e2) {
                    System.err.println(e2);
                }
            }
        });
        comboTestSet.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseDown(MouseEvent e) {
                try {

                    int countTestSetinComboBox = comboTestSet.getItems().length;
                    int countTestSetInRally = loadTestSetFromRally().length;
                    testSetPreSelect = comboTestSet.getText();

                    if (countTestSetinComboBox != countTestSetInRally) {
                        comboTestSet.setItems(loadTestSetFromRally());
                    }

                } catch (Exception e2) {
                    System.err.println("MouseEvent Test-Sets" + e2.getStackTrace());
                }
            }
        });
        comboTestSet.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        comboTestSet.select(0);
        comboTestSet.setItems(loadTestSetFromRally());
        comboTestSet.select(0);

        comboFilter = new Combo(compositeNorth, SWT.READ_ONLY);
        comboFilter.setItems(controller.getComboFilterString());
        comboFilter.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {

                String selectedFilter = comboFilter.getText();

                switch (selectedFilter) {
                    case "All": {
                        for (int i = 0; i < table.getItemCount(); i++) {
                            table.getItems()[i].setChecked(true);
                        }
                        table.selectAll();
                    }
                    break;

                    case "Passed": {
                        selectTestCaseFromComboBox("Pass");
                    }
                    break;
                    case "Failed": {
                        selectTestCaseFromComboBox("Fail");
                    }
                    break;
                    case "Not Run": {
                        selectTestCaseFromComboBox("Not Run");
                    }
                    break;

                    case "Deselect": {
                        for (int i = 0; i < table.getItemCount(); i++) {
                            table.getItems()[i].setChecked(false);
                        }
                        table.deselectAll();
                        comboFilter.select(0);
                    }
                    break;

                    default: {
                        MessageBox messagebox = new MessageBox(shlGuiAutomated, SWT.ICON_WARNING);
                        messagebox.setText("Filter-Warning");
                        messagebox.setMessage("Please select a valid value on the filter!!");
                        messagebox.open();
                    }
                    break;
                }

                table.setFocus();
            }
        });
        comboFilter.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        comboFilter.setItems(controller.getComboFilterString());
        comboFilter.select(0);

        comboServer = new Combo(compositeNorth, SWT.READ_ONLY);
        comboServer.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                table.setFocus();
            }
        });
        comboServer.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        comboServer.setItems(controller.getComboServerString());
        comboServer.select(0);

        comboBrowser = new Combo(compositeNorth, SWT.READ_ONLY);
        comboBrowser.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                try {
                    table.setFocus();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
        });
        comboBrowser.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        comboBrowser.setItems(controller.getComboBrowserString());
        comboBrowser.select(0);

        compositeSouth.setLayoutData(BorderLayout.SOUTH);
        compositeSouth.setLayout(new GridLayout(20, false));
        new Label(compositeSouth, SWT.NONE);
        new Label(compositeSouth, SWT.NONE);
        new Label(compositeSouth, SWT.NONE);

        Label label_2 = new Label(compositeSouth, SWT.BORDER);
        label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
        label_2.setSize(2, 64);

        lblDatabaseStatus = new Label(compositeSouth, SWT.NONE);
        lblDatabaseStatus.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        lblDatabaseStatus.setSize(115, 15);
        lblDatabaseStatus.setText("Database: off");
        new Label(compositeSouth, SWT.NONE);
        new Label(compositeSouth, SWT.NONE);
        new Label(compositeSouth, SWT.NONE);
        new Label(compositeSouth, SWT.NONE);
        new Label(compositeSouth, SWT.NONE);
        new Label(compositeSouth, SWT.NONE);

        Label label_3 = new Label(compositeSouth, SWT.BORDER);
        label_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));

        lblStatusTestcase = new Label(compositeSouth, SWT.NONE);
        lblStatusTestcase.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        lblStatusTestcase.setSize(75, 15);
        lblStatusTestcase.setText("Testcase:");
        new Label(compositeSouth, SWT.NONE);
        new Label(compositeSouth, SWT.NONE);
        new Label(compositeSouth, SWT.NONE);
        new Label(compositeSouth, SWT.NONE);
        new Label(compositeSouth, SWT.NONE);

        Label label_1 = new Label(compositeSouth, SWT.BORDER);
        label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
        //final ProgressBar progressBar = new ProgressBar(compositeSouth, SWT.NONE);
        GridData gd_progressBar = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
        gd_progressBar.widthHint = 247;
        progressBar.setVisible(false);
        progressBar.setLayoutData(gd_progressBar);
        progressBar.setSize(180, 17);

        this.setDBstatusTOLabel();
    }

    private void selectTestCaseFromComboBox(String itemToSelect) {
        if (selectedTestCase == null)
            selectedTestCase = null;

        selectedTestCase = new ArrayList<String>();

        for (int i = 0; i < table.getItemCount(); i++) {
            table.getItems()[i].setChecked(false);
        }
        table.deselectAll();

        for (int i = 0; i < table.getItemCount(); i++) {

            String tmpStatus = table.getItem(i).getText(4);

            if (tmpStatus.contains(itemToSelect)) {
                table.select(i);
                table.getItems()[i].setChecked(true);
                //selectedTestCase.add(table.getSelection()[i].getText(2));
                selectedTestCase.add(table.getItems()[i].getText(2));
            }
        }
    }

    private void upadateTable() {
        if (table.getItems() != null) {
            table.removeAll();
        }
        this.loadTestCaseFromRally();
    }

    private String[] loadTestSetFromRally() {
        List<String> testSetList = this.controller.getTestLabList();
        String[] testSet = new String[]{"<Select>", "No Testset Found..."};

        if (!testSetList.isEmpty()) {
            testSet = new String[testSetList.size() + 1];
            for (int i = 0; i < testSetList.size() + 1; i++) {
                if (i == 0)
                    testSet[i] = "<Select>";
                else
                    testSet[i] = testSetList.get(i - 1).toString();
            }
            return testSet;
        }
        return testSet;
    }

    private int loadTestCaseFromRally() {
        //TODO patter verbessern
        String pattern = "testing.Relaunch_2014.Wetter20_"; //String pattern = "((ESEMOS)\\w*.*|(INTERN)\\w*.*)";
        String pattern2 = "[(TZ)]";
        String blank = "";
        String space = " ";
        String testSetString = comboTestSet.getText();
        String testSetID = testSetString.substring(testSetString.lastIndexOf("ID:") + 3);

        if (testSetString == null || testSetString == "<Select>") {
            return -1;
        }

        displayedTestSet = controller.getTestSetFromExternalReporting(testSetID); //comboFilter.getText());

        Vector<TestCase> testCases = displayedTestSet.getTestCases();

        for (TestCase testCase : testCases) {
            TableItem item1 = new TableItem(table, SWT.NONE);
            item1.setText(new String[]{"",
                            testCase.getTestCaseID().toString(),
                            testCase.getSimpleTestName(),
                            getClassName(testCase.getExecutableClass().replaceAll(pattern, blank)),
                            getStatus(testCase.getStatus()),
                            getLastRun(testCase.getLastRun().replaceAll(pattern2, space)),
                            testCase.getAutomatable()
                    }
            );
        }
        //Statusbar
        ApplicationWindows_New.getLabelStatusTestcase().setText("Testcase: " + table.getItemCount());
        return 1;
    }

    private String getStatus(String testStatus) {
        if (testStatus.contains("Pass") || testStatus.isEmpty())
            return "Pass";
        else if (testStatus.contains("Fail"))
            return "Fail";
        return "Not Run";
    }


    private String getLastRun(String lastRun) {
        if (lastRun.contains("null") || lastRun.isEmpty())
            return "yyyy-mm-dd hh:mm:ss";
        return lastRun;
    }

    private String getClassName(String className) {
        if (className.contains("null") || className.isEmpty())
            return "Not implemented";
        return className;
    }

    private void setDBstatusTOLabel() {
        if (controller.isDbStatus()) {
            lblDatabaseStatus.setText("Datenbank: on");
        }
    }


    /**
     * Diese Methode ist für die Aktualisierung Testliste zuständig.
     * Der Übergebeparameter muste auf TestSet umgestellt werden.
     *
     * @param its - TestSet mit den anzuzeigenden Testfällen als TestSet Objekt
     */
    public void updateTestList(TestSet its) {
        setDisplayedTestSet(its);

    }

    /**
     * Setter Methode zum Setzen des Werts von displayedTestSet. An dieser Methode
     * muste der Übergabeparameter auf TestSet angepasst werden.
     *
     * @param displayedTestSet - Das anzuzeigende TestsSet als TestSet Objekt
     */
    public void setDisplayedTestSet(TestSet displayedTestSet) {
        this.displayedTestSet = displayedTestSet;
    }


    @Override
    public Shell getFrame() {
        return shlGuiAutomated;
    }

    @Override
    public void notifyTestrunCompleted() {
        // TODO Auto-generated method stub

    }

    @Override
    public void notifyTestrunStarted() {
        // TODO Auto-generated method stub

    }

    @Override
    public void notifyDBOffline() {
        // TODO Auto-generated method stub

    }

    @Override
    public void notifyRunconfigurationAdded(TestrunConfiguration_New configuration, boolean edited) {
        // TODO Auto-generated method stub
    }

    @Override
    public TestSet getDisplayedTestSet() {
        return displayedTestSet;
    }

    @Override
    public void notifyTestrunStarted(int confID) {
        this.table.redraw();
    }

    @Override
    public void notifyTestrunCompleted(int confID) {
        this.table.redraw();
    }

    @Override
    public void setTestSetLabel(String string) {
        this.testSetPreSelect = string;
    }

    /**
     * @return the progressBar
     */
    public static ProgressBar getProgressBar() {
        return progressBar;
    }

    /**
     * @return the lblStatusTestcase
     */
    public static Label getLabelStatusTestcase() {
        return lblStatusTestcase;
    }

    /**
     * @return the display
     */
    public Display getDisplay() {
        return display;
    }
}




