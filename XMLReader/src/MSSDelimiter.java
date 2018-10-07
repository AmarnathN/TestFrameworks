
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class MSSDelimiter {
  public MSSDelimiter() {
    super();
  }

  public static void main(String[] args) {
    MSSDelimiter mSSDelimiter = new MSSDelimiter();
    FileReader reader;
  

    
//    ArrayList<Integer> Segment2 = new ArrayList<Integer>(Arrays.asList(4,10,2,4,35,35,29,2,3,9,2,4,1,7,7,9,2,3,7,1,4,12,20,1,9,11,11,1,20,20,12,1,2,2,7,1,7,7));
//    ArrayList<Integer> Segment3 = new ArrayList<Integer>(Arrays.asList(4,10,2,4,7,14,7,12,7,7,7,14,7,7,7,7,7,8));
//    ArrayList<Integer> Segment4 = new ArrayList<Integer>(Arrays.asList(4,10,2,4,2,1,4,15,7,1,1,2,1,1,2,2,3,3,1,1,1,1,1,5,5,10,3,3,3,3,4,1,1,1,1));
//    ArrayList<Integer> Segment5 = new ArrayList<Integer>(Arrays.asList(4,10,2,4,3,1,7,2,7,7,7,7,7,1,13,13,3,4,20,6,4,3,35,35,35,35,35,35,35,35,2,10,5,1,3,13));
//    ArrayList<Integer> Segment6 = new ArrayList<Integer>(Arrays.asList(4,10,2,4,3,7,7,12,1,1,1,12,3,3,1,1));
//    ArrayList<Integer> Segment11 = new ArrayList<Integer>(Arrays.asList(4,10,2,4,7,25,25,17,2,2,2,1,12,9,12,12,1,1));
//    ArrayList<Integer> Segment12 = new ArrayList<Integer>(Arrays.asList(4,10,2,4,3,35,35,9,2,2,1,10,2,7));
//    ArrayList<Integer> Segment1C = new ArrayList<Integer>(Arrays.asList(4,10,2,4,10,4,4,15,1,2,6,4,2,1,5,1,1,1,2,9,11,3,5,2,2,2,1,1,2,10,1,16));
//    ArrayList<Integer> Segment5G = new ArrayList<Integer>(Arrays.asList(4,10,2,4,10,1));
//    ArrayList<Integer> Segment28 = new ArrayList<Integer>(Arrays.asList(4,10,2,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1));
//    ArrayList<Integer> Segment3A = new ArrayList<Integer>(Arrays.asList(4,10,2,4,1,9,6,9,6,9,6,9,6,5,5,3,3,3,3,3));
//    ArrayList<Integer> Segment3B = new ArrayList<Integer>(Arrays.asList(4,10,2,4,7,4,7,6,10,7));
//    ArrayList<Integer> Segment40 = new ArrayList<Integer>(Arrays.asList(4,10,2,4,7,2,7,3,3,7,6,12,13,14,1,6,1,10,1,1,12));
//    ArrayList<Integer> Segment5D = new ArrayList<Integer>(Arrays.asList(4,10,2,4,7,6,7,6,3,6,6,10,14,7,1,3,6,3,2,3,7));
//    ArrayList<Integer> Segment5E = new ArrayList<Integer>(Arrays.asList(4,10,2,4,6,7,1,3,10,6,2,2,1,1));
//    ArrayList<Integer> Segment5F = new ArrayList<Integer>(Arrays.asList(4,10,2,4,7,7,6,15,7,4));
//    ArrayList<Integer> Segment94 = new ArrayList<Integer>(Arrays.asList(4,10,2,4,4,12,12,12,2,7,6,7,7,7,1));
//    ArrayList<Integer> Segment96 = new ArrayList<Integer>(Arrays.asList(4,10,2,4,3,30,5,7));
//    ArrayList<Integer> Segment98 = new ArrayList<Integer>(Arrays.asList(4,10,2,4,7));
//    ArrayList<Integer> Segment99 = new ArrayList<Integer>(Arrays.asList(4,10,2,4,7));
      HashMap<String,ArrayList<Integer>> segment1= new LinkedHashMap<String,ArrayList<Integer>>()
    {{
    	put("ACCOUNT_ID"		, new ArrayList<Integer> (Arrays.asList(4,10)));
    	put("PARTY_ID"  		,  new ArrayList<Integer> (Arrays.asList(20,9)));
    	put("PARTY_TYPE"  		, new ArrayList<Integer>( Arrays.asList(29,1)));
    	put("GENDER_TYPE"  		, new ArrayList<Integer>( Arrays.asList(30,1)));
    	put("MARITAL_STATUS"  	, new ArrayList<Integer>( Arrays.asList(31,1)));
    	
    }};
    
    HashMap<String,HashMap<String,ArrayList<Integer>> > segmentMap= new LinkedHashMap<String,HashMap<String,ArrayList<Integer>> > ();
    
    segmentMap.put("01", segment1);
//    segmentMap.put("02", Segment2);
//    segmentMap.put("03", Segment3);
//    segmentMap.put("04", Segment4);
//    segmentMap.put("05", Segment5);
//    segmentMap.put("06", Segment6);
//    segmentMap.put("11", Segment11);
//    segmentMap.put("12", Segment12);
//    segmentMap.put("1C", Segment1C);
//    segmentMap.put("5G", Segment5G);
//    segmentMap.put("28", Segment28);
//    segmentMap.put("3A", Segment3A);
//    segmentMap.put("3B", Segment3B);
//    segmentMap.put("40", Segment40);
//    segmentMap.put("5D", Segment5D);
//    segmentMap.put("5E", Segment5E);
//    segmentMap.put("5F", Segment5F);
//    segmentMap.put("94", Segment94);
//    segmentMap.put("96", Segment96);
//    segmentMap.put("98", Segment98);
//    segmentMap.put("99", Segment99);
//          
              
    
    String line = null;
    try {
      String AccountNumber = "037113000073".substring(4);
      sy
      reader = new FileReader("D:\\MSS.dat");

      BufferedReader bufferedReader = 
                    new BufferedReader(reader);
      
                while((line = bufferedReader.readLine()) != null) {
                  if(line.length()>16){
                    segmentLine(line,segmentMap.get(line.substring(14, 16)));
                  }
                }  
      bufferedReader.close(); 
    } catch (FileNotFoundException e) {
      }catch(IOException ie){
        
      }
  }
  
  private static String segmentLine(String line, HashMap<String, ArrayList<Integer>> hashMap){
    String delimit="";
   
    if(hashMap==null)
      return line;
      for(String key : hashMap.keySet()){
    	  			int initialIndex = hashMap.get(key).get(0);
    	  			int endIndex     = hashMap.get(key).get(0)+hashMap.get(key).get(1);
    	  			System.out.println("The actual value " + key + " is " + line.substring(initialIndex, endIndex));
             }
      
    return delimit;
  }
}
