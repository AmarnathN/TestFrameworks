import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Suman {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	public void MainTestDataReader() {
			
		int rowCount = 10;//Read Number of rows From XL";
		int columnCount=6;//Total number of columns I want to read;
		
		Map<String,ArrayList> TestDataMap = new HashMap<String,ArrayList>();
		ArrayList<Map<String,String>> testCaseDataList = null;
		for(int i=0;i<rowCount;i++) {
			
			String row = "Read Each Row in Sheet";
			String testCaseID = "TestCase Id From this particular row" ;
			if(!TestDataMap.containsKey(testCaseID)) {
				testCaseDataList = new  ArrayList<Map<String,String>>();
			}else {
				testCaseDataList = TestDataMap.get(testCaseID);
			}
			
			Map<String,String> TestIterationDataMap = null;
			for(int j=0;j<columnCount;j++) {
				TestIterationDataMap = new HashMap<String,String>();
				String Key = "ReadColumnHeader";
				String Value = "ColumnValue of particular Row";
				
				if(!TestIterationDataMap.containsKey(Key)) {
					TestIterationDataMap.put(Key, Value);
				}
				
			}
			
			if(!testCaseDataList.contains(TestIterationDataMap)) {
				testCaseDataList.add(TestIterationDataMap);
			}
			
			TestDataMap.put(testCaseID, testCaseDataList)
			
			
			
		}
		
			
	}

}
