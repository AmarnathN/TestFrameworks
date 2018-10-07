package com.fileUtilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.internal.Nullable;

import com.frameworkUtilities.EnvironmentVariables;
import com.frameworkUtilities.FrameWorkConstants;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JSONUtilities {
    private Map<String,String> fieldDefinitions = null;
    private Map<String,String> fieldLocationStrategy = null;

    public Map readFieldDefinitions() {

        fieldDefinitions = new LinkedHashMap<String,String>();
        String locatorsFile  = new EnvironmentVariables().getFieldDefinitionsFolderpath() + File.separator+ FrameWorkConstants.FIELD_DEFINITIONS_FILENAME + ".json" ;
        try {

            File file = new File(locatorsFile);
            checkArgument(file.exists(), "Unable to locate " + locatorsFile);
            JsonArray array = null;

            array = new JsonParser().parse(new FileReader(file)).getAsJsonArray();
            Iterator<JsonElement> iterator = array.iterator();
            JsonObject foundObject = null;
                    while (iterator.hasNext()) {
                    JsonObject object = iterator.next().getAsJsonObject();
                    if (!object.get("pageName").isJsonNull() && !object.get("name").isJsonNull() &&  !object.get("locateUsing").isJsonNull() && !object.get("locator").isJsonNull()) {
                         fieldDefinitions.put(object.get("name").getAsString(), object.get("locator").getAsString());
                    }else{
                        System.out.println("Invalid Field Defined in FieldDefinitions.json ");
                        System.out.println("pageName :" + object.get("pageName"));
                        System.out.println("name :" + object.get("name"));
                        System.out.println("locateUsing :" + object.get("locateUsing"));
                        System.out.println("locator :" + object.get("locator"));

                        System.exit(1);
                    }
                }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
       return fieldDefinitions ;
    }
    public Map readFieldLocationStrategy() {

        fieldLocationStrategy = new LinkedHashMap<String,String>();
        String locatorsFile  = new EnvironmentVariables().getFieldDefinitionsFolderpath() + File.separator+ FrameWorkConstants.FIELD_DEFINITIONS_FILENAME + ".json" ;
        try {
            File file = new File(locatorsFile);
            checkArgument(file.exists(), "Unable to locate " + locatorsFile);
            JsonArray array = null;

            array = new JsonParser().parse(new FileReader(file)).getAsJsonArray();
            Iterator<JsonElement> iterator = array.iterator();
            JsonObject foundObject = null;
            while (iterator.hasNext()) {
                JsonObject object = iterator.next().getAsJsonObject();
                if (!object.get("pageName").isJsonNull() && !object.get("name").isJsonNull() &&  !object.get("locateUsing").isJsonNull() && !object.get("locator").isJsonNull()) {
                    fieldLocationStrategy.put(object.get("name").getAsString(), object.get("locateUsing").getAsString());
                }else{
                    System.out.println("Invalid Field Defined in FieldDefinitions.json ");
                    System.out.println("pageName :" + object.get("pageName"));
                    System.out.println("name :" + object.get("name"));
                    System.out.println("locateUsing :" + object.get("locateUsing"));
                    System.out.println("locator :" + object.get("locator"));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fieldLocationStrategy ;
    }

    /**
     * Ensures the truth of an expression involving one or more parameters to the calling method.
     *
     * @param expression a boolean expression
     * @param errorMessage the exception message to use if the check fails; will be converted to a
     *     string using {@link String#valueOf(Object)}
     * @throws IllegalArgumentException if {@code expression} is false
     */

    public void checkArgument(boolean expression, @Nullable Object errorMessage) {
        if (!expression) {
            throw new IllegalArgumentException(String.valueOf(errorMessage));
        }
    }

}
