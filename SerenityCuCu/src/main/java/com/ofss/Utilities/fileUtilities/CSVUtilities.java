package com.ofss.Utilities.fileUtilities;

import com.ofss.Utilities.frameworkUtilities.EnvironmentVariables;
import com.ofss.Utilities.frameworkUtilities.FrameWorkConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.relique.jdbc.csv.CsvDriver;

public class CSVUtilities {

    private static final Logger LOGGER = LoggerFactory.getLogger(CSVUtilities.class);

    /**
     * Read execution settings.
     *
     * @param settingsFilepath
     *            the settings filepath
     * @return the map
     */
    public Map<String, Map<String, String>> readExecutionSettings(String settingsFilepath) {
        Map<String, Map<String, String>> executionSettings = new HashMap<String, Map<String, String>>();
        Connection conn1 = null;
        Statement stmnt = null;
        ResultSet settingsTable = null;

        try {
            Class.forName("org.relique.jdbc.csv.CsvDriver");
            String essheetName = FrameWorkConstants.GLOBAL_SETTINGS_FILENAME;
            File f = new File(settingsFilepath + File.separator + essheetName + ".csv");

            if (f.exists()) {
                conn1 = DriverManager.getConnection("jdbc:relique:csv:" + settingsFilepath);
                stmnt = conn1.createStatement();
                String esQuery = "select * from " + essheetName + ";";
                settingsTable = stmnt.executeQuery(esQuery);

                while (settingsTable.next()) {
                    String execVariableType = settingsTable.getString(FrameWorkConstants.ExecutionSettingsColumnNames.EXECUTIONVARIABLETYPE.toString());
                    String execVariableName = settingsTable.getString(FrameWorkConstants.ExecutionSettingsColumnNames.EXECUTIONVARIABLENAME.toString());
                    String execVariableValue = settingsTable.getString(FrameWorkConstants.ExecutionSettingsColumnNames.EXECUTIONVARIABLEVALUE.toString());

                    if (executionSettings.get(execVariableType) == null) {
                        Map<String, String> execVariableSet = new HashMap<String, String>();
                        execVariableSet.put(execVariableName, execVariableValue);
                        executionSettings.put(execVariableType, execVariableSet);
                    } else {
                        executionSettings.get(execVariableType).put(execVariableName, execVariableValue);
                    }
                    //LOGGER.info("For execVariableType:: " + execVariableType + " new set added with Name: " + execVariableName + " & Value: " + execVariableValue);
                }
            } else {
               // LOGGER.error("Execution aborted as settings folder not found at: " + FrameWorkConstants.GLOBAL_SETTINGS_FOLDERNAME);
                System.out.println("Execution aborted as settings folder not found at: " + settingsFilepath);
                System.exit(0);
            }
        } catch (Exception e) {
           // LOGGER.error("Error while reading GloalSettings file: " + e.getMessage());
           // LOGGER.error(e);
            System.out.println("Error while reading GloalSettings file: " + e.getMessage());
            System.exit(0);
        } finally {
            try {
                if (settingsTable != null) {
                    settingsTable.close();
                }
                if (stmnt != null) {
                    stmnt.close();
                }
                if (conn1 != null) {
                    conn1.close();
                }
            } catch (SQLException e) {
            }
        }

        return executionSettings;
    }


    /**
     * Read locators.
     *
     * @param withDetail
     *            the loc app i ds
     * @return the map
     */
    public Map<String, String> readLocators(String withDetail) {
        Map<String,String> fieldDetails =  new HashMap<String, String>();;


        Connection conn1 = null;
        Statement stmnt = null;
        		
        		String locFolderPath = EnvironmentVariables.getInstance().getFieldDefinitionsFolderpath();

                String locfilePath = locFolderPath + File.separator+ FrameWorkConstants.FIELD_DEFINITIONS_FILENAME + ".csv" ;

                String locFileName = FrameWorkConstants.FIELD_DEFINITIONS_FILENAME;
                
               
                try {
                    File f = new File(locfilePath);

                    if (f.exists()) {
                        conn1 = DriverManager.getConnection("jdbc:relique:csv:" + locFolderPath);
                        // stmnt = conn1.createStatement();
                        stmnt = conn1.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        String locQuery = "select * from " + locFileName + ";";
                        ResultSet locTable = stmnt.executeQuery(locQuery);
                        List<Map<String, String>> locset = new ArrayList<Map<String, String>>();
                        checkEmptyCellInLocators(locTable);
                        locTable.beforeFirst();
                        //LOGGER.info("reading LOCATORS- adding locators records to a new applicationID: " + appID);
                        while (locTable.next()) {


                            switch (withDetail.toUpperCase()) {
                                case "LOCATOR":
                                    fieldDetails.put(locTable.getString(FrameWorkConstants.FieldDefinitionsColumnNames.FIELDNAME.toString()),
                                            locTable.getString(FrameWorkConstants.FieldDefinitionsColumnNames.FIELDDEFINITION.toString()));
                                    break;
                                case "LOCATEUSING":

                                    fieldDetails.put(locTable.getString(FrameWorkConstants.FieldDefinitionsColumnNames.FIELDNAME.toString()),
                                            locTable.getString(FrameWorkConstants.FieldDefinitionsColumnNames.LOCATORTYPE.toString()));

                                    break;
                                default:
                                    LOGGER.error("Inavlid fieldDetails Requested");
                                    System.exit(0);
                            }
                        }
                    } else {
                        LOGGER.error("Execution aborted Locators File not found:: " + locfilePath);
                        System.exit(0);
                    }
                } catch (Exception e) {
                    LOGGER.error("Exception in reading locators - stacktraceInfo:: ", e);
                    System.exit(-1);
                } finally {
                    try {
                        if (stmnt != null)
                            stmnt.close();
                    } catch (SQLException e) {
                    }
                    try {
                        if (conn1 != null)
                            conn1.close();
                    } catch (SQLException e) {
                    }
                }



        return fieldDetails;
    }
    
    /**
     * Read Global Environment Variables.
     *
     * @param withDetail
     *            the loc app i ds
     * @return the map
     */
    public Map<String, String> readEnvironmentGlobalVariables() {
        Map<String,String> EnvironmentGlobalVariables =  new HashMap<String, String>();;


        Connection conn1 = null;
        Statement stmnt = null;
        		
        		String locFolderPath = EnvironmentVariables.getInstance().getGlobalTestDataFolderpath();

                String locFileName = FrameWorkConstants.ENVIRONMENTGLOBALVARIABLES_FILENAME;
                
                String locfilePath = locFolderPath + File.separator+ locFileName + ".csv" ;

                
                try {
                    File f = new File(locfilePath);

                    if (f.exists()) {
                        conn1 = DriverManager.getConnection("jdbc:relique:csv:" + locFolderPath);
                        // stmnt = conn1.createStatement();
                        stmnt = conn1.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        String locQuery = "select * from " + locFileName + ";";
                        ResultSet locTable = stmnt.executeQuery(locQuery);
                        List<Map<String, String>> locset = new ArrayList<Map<String, String>>();
                 //       checkEmptyCellInLocators(locTable);
                        locTable.beforeFirst();
                        //LOGGER.info("reading LOCATORS- adding locators records to a new applicationID: " + appID);
                        while (locTable.next()) {
                        	EnvironmentGlobalVariables.put(locTable.getString(FrameWorkConstants.EnvironmentGlobalVariablesColumnNames.VARIABLENAME.toString()).toUpperCase(),
                                            locTable.getString(FrameWorkConstants.EnvironmentGlobalVariablesColumnNames.VARIABLEVALUE.toString()));
                            }
                    } else {
                        LOGGER.error("Execution aborted Locators File not found:: " + locfilePath);
                        System.exit(0);
                    }
                } catch (Exception e) {
                    LOGGER.error("Exception in reading locators - stacktraceInfo:: ", e);
                    System.exit(-1);
                } finally {
                    try {
                        if (stmnt != null)
                            stmnt.close();
                    } catch (SQLException e) {
                    }
                    try {
                        if (conn1 != null)
                            conn1.close();
                    } catch (SQLException e) {
                    }
                }



        return EnvironmentGlobalVariables;
    }


    /**
     * Check empty cell in locators.
     *
     * @param locRecord
     *            the loc record
     * @throws SQLException
     *             the SQL exception
     */
    private void checkEmptyCellInLocators(ResultSet locRecord) throws SQLException {
        int count = 1;

        try {
            while (locRecord.next()) {
                count++;

                if (locRecord.getString(FrameWorkConstants.FieldDefinitionsColumnNames.SCREENNAME.toString()).isEmpty()) {
                    LOGGER.error("ScreenName is empty at row: " + count + " in Field Locations Sheet");
                    System.exit(0);
                }

                if (locRecord.getString(FrameWorkConstants.FieldDefinitionsColumnNames.FIELDNAME.toString()).isEmpty()) {
                    LOGGER.error("FieldName is empty at row: " + count + " in Field Locations Sheet");
                    System.exit(0);
                }

                if (locRecord.getString(FrameWorkConstants.FieldDefinitionsColumnNames.FIELDDEFINITION.toString()).isEmpty()) {
                    LOGGER.error("FieldDefinition is empty at row: " + count + " in Field Locations Sheet");
                    System.exit(0);
                }
            }
        } catch (Exception ex) {
            LOGGER.error("Here " + ex.getMessage());
        }
    }
    
    


}
