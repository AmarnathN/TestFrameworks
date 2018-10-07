package com.ofss.helpers;

import com.ofss.fileUtilities.CSVUtilities;

import java.util.Map;

public class FieldDefinitions {

    private static FieldDefinitions instance;
    public Map<String,String> fieldDefinitions = null;
    public Map<String,String> fieldLocationStrategy = null;


    public FieldDefinitions () {
        // setFieldDefinitions(new JSONUtilities().readFieldDefinitions());
        // setFieldLocationStrategy(new JSONUtilities().readFieldLocationStrategy());
        setFieldDefinitions(new CSVUtilities().readLocators("Locator"));
        setFieldLocationStrategy(new CSVUtilities().readLocators("LocateUsing"));
    }


    /**
     * Gets the single instance of ExecutionSettings.
     *
     * @return single instance of ExecutionSettings
     */
    public static synchronized FieldDefinitions getInstance() {
        if (instance == null) {
            instance = new FieldDefinitions();
        }
        return instance;
    }



    public Map<String, String> getFieldDefinitions() {
        return fieldDefinitions;
    }

    public void setFieldDefinitions(Map<String, String> fieldDefinitions) {
        this.fieldDefinitions = fieldDefinitions;
    }

    public Map<String, String> getFieldLocationStrategy() {
        return fieldLocationStrategy;
    }

    public void setFieldLocationStrategy(Map<String, String> fieldLocationStrategy) {
        this.fieldLocationStrategy = fieldLocationStrategy;
    }






}
