package com.frameworkUtilities;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fileUtilities.CSVUtilities;

public class TestData {
	private static final Logger LOGGER = Logger.getLogger(TestData.class.getName());

    public Map<String, Object> runtimeData = null;
    
	private  TestData() {
		
	}
	
	private static final ThreadLocal<TestData> _localStorage = new ThreadLocal<TestData>() {
		protected TestData initialValue() {
			return new TestData();
		}
	};
		  
	public static TestData getInstance() {
		return _localStorage.get();
	}
    
     
    public int addRuntimeData(String runtimeVariableName, Object runtimeVariableValue){
        
    	int exitcode = ERRORCODES.DEFAULT_CODE ;
                try {
                if (runtimeData == null) {
                    runtimeData = new LinkedHashMap<String, Object>();
                    runtimeData.put(runtimeVariableName.toUpperCase(), runtimeVariableValue);

                }else{
                    runtimeData.put(runtimeVariableName.toUpperCase(), runtimeVariableValue);

                }
                exitcode = ERRORCODES.PASS ;
            }catch(Exception e){
                System.out.println("Issue in Adding the Runtime Data ");
                exitcode = ERRORCODES.OPERATION_FAILURE;
            }
                return exitcode;
    }


    public Object getRuntimeData(String runtimeVariableName){
        Object runtimeVariableValue = null;
            try {
                    if (runtimeData != null || runtimeData.get(runtimeVariableName.toUpperCase()) != null) {
                        runtimeVariableValue = runtimeData.get(runtimeVariableName.toUpperCase());
                    }
                    else{
                        System.out.println("No runtime data present with given variable " + runtimeVariableName );
                    }
            }catch(Exception e){
                System.out.println("Issue in getting the Runtime Data ");
            }
        return runtimeVariableValue;
    }


    public int clearRuntimeData(){
        int exitcode = ERRORCODES.DEFAULT_CODE ;
            try {
                if (runtimeData != null) {
                    runtimeData.clear();
                }
                exitcode = ERRORCODES.PASS ;
            }catch(Exception e){
                System.out.println("Issue in Clearing the Runtime Data ");
                exitcode = ERRORCODES.OPERATION_FAILURE;
            }

        return exitcode;
    }
    
    public String getTestData(String testdataVariable){
    	String testdataValue = null;
            try {
            	String testdataVariableType = null;
            	
            	
            	if(testdataVariable.contains("RT_")) {
            		testdataVariableType =  FrameWorkConstants.TestdataType.RUNTIMEVARIABLE.toString();
            	}else if(testdataVariable.contains("GV_")) {
            		testdataVariableType =  FrameWorkConstants.TestdataType.GLOBALVARIABLE.toString();
            	}else if(testdataVariable.contains("TD_")) {
            		testdataVariableType =  FrameWorkConstants.TestdataType.TDPARAMETER.toString();
            	}else {
            		testdataVariableType = testdataVariable ;
            	}

           
            
            	
            	switch(FrameWorkConstants.TestdataType.valueOf(testdataVariableType.toUpperCase())) {
            	
            	case RUNTIMEVARIABLE :
            		testdataValue = getRuntimeData(testdataVariable.toUpperCase()).toString();
            		break;
            		
            	case GLOBALVARIABLE:
            		try {
            			Map<String,String> globalTestdataMap = new CSVUtilities().readEnvironmentGlobalVariables();
            			System.out.println(globalTestdataMap + "     " + testdataVariable.substring(3));
		            		if(globalTestdataMap!= null && globalTestdataMap.containsKey(testdataVariable.substring(3).toUpperCase())){
		            		
		            			testdataValue = globalTestdataMap.get(testdataVariable.substring(3).toUpperCase());
		                	}else {
		                		LOGGER.error("Invalid Global Test Data Parameter referred ");
		                	}
		            		
		            }catch(Exception e) {
            			LOGGER.error(e + "Exception in reading Global Test Data Parameter");
            		}
            		break;
            		
            	
            	case TDPARAMETER :
            		try {
            			Map<String,String> inputTestdataMap = (Map<String, String>) getRuntimeData("TestDataMap".toUpperCase());
            			
		            		if(inputTestdataMap!=null && inputTestdataMap.containsKey(testdataVariable.substring(3).toUpperCase())){
		            			testdataValue = inputTestdataMap.get(testdataVariable.substring(3).toUpperCase());
		                	}else {
		                		LOGGER.error("Invalid Input Test Data Parameter referred ");
		                	}
		            		
		            }catch(Exception e) {
            			LOGGER.error(e + "Exception in reading Input Test Data Parameter");
            		}
            		break;
            	default :
            		break;
            		
            	}
            }catch(Exception e){
                LOGGER.error("Issue in reading Test Data ");
            }

        return testdataValue ;
    }

}
