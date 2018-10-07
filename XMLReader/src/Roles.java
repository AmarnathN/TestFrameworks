

import java.util.HashMap;
import java.util.Map;

public class Roles {
	
private static Map<String, String> roles = createMap();
	
	private static Map<String, String> createMap(){
		Map<String,String> myMap = new HashMap<String, String>();
        myMap.put("OBP - Configurator", "testuser1");
        myMap.put("OBP - Credit Approver (H)", "testuser2");
        myMap.put("OBP - Credit Approver (L)", "testuser4");
        myMap.put("OBP - Lender (Branch)", "testuser3");
        myMap.put("OBP - Operations Team Lead", "testuser6");
        myMap.put("OBP - Pricing Officer", "testuser7");
        myMap.put("OBP - Product Manager", "testuser8");
        myMap.put("OBP - Tech Support", "testuser9");
        myMap.put("OBP - Operations Member", "testuser5");
        myMap.put("OBP - Workflow Coordinator", "testuser10");
        myMap.put("OBP - Access Manager", "testuser11");
        return myMap;
	}
	
	private Roles(){}
	
	public static Map<String, String> getRoles() {
		return roles;
	}
}
