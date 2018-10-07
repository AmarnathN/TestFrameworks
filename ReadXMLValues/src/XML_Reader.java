import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XML_Reader {
	public static Map<String, ArrayList<String>> pairs = null;
	public static ArrayList<Map<String, ArrayList<String>>> values = null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PrintWriter pw = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			  String myDirectoryPath = args[0];
			  File dir = new File(myDirectoryPath);
			  File[] directoryListing = dir.listFiles();
			  try {
					if(pw==null) {
						 pw = new PrintWriter(new File("ReponsePartyData.csv"));
					}
				   
				} catch (FileNotFoundException e) {
				    e.printStackTrace();
				}
				StringBuilder strngBuilder = new StringBuilder();
				String ColumnNamesList = "ciskey,partyUid,familyName,givenName,givenName2,givenName3,customerNum,birthDate";
				// No need give the headers Like: id, Name on builder.append
				strngBuilder.append(ColumnNamesList +"\n");
			  if (directoryListing != null) {
			    for (File child : directoryListing) {
			      // Do something with child
			    	System.out.println(child.getCanonicalPath());
			    	
			    	
			    	DocumentBuilder builder = factory.newDocumentBuilder();
					Document doc = builder.parse(child.getCanonicalPath());
				       
					NodeList nodesOfGivenTagName = doc.getElementsByTagName("Individual");
					
					values = new ArrayList<Map<String, ArrayList<String>>>();
					
			//		System.out.println(nodesOfGivenTagName.getLength());
					
					for (int i = 0; i < nodesOfGivenTagName.getLength(); i++) {
						pairs = new  LinkedHashMap<String, ArrayList<String>> ();
						
						Node childNodeOfGivenTagName = nodesOfGivenTagName.item(i);
						if(childNodeOfGivenTagName.hasAttributes()) {
							NamedNodeMap attribs = childNodeOfGivenTagName.getAttributes();
							for( int attribIndex =0 ; attribIndex<attribs.getLength();attribIndex++) {
								String atribName = attribs.item(attribIndex).getNodeName();
								String atribValue = attribs.item(attribIndex).getTextContent();
								ArrayList<String> valueList = new ArrayList<String>();
								if(pairs.get(atribName) == null) {
									valueList.add(atribValue);
									pairs.put(atribName, valueList);
			
								}
								else{
									// key is already present hence adding the value to list of values for the key  
									valueList = pairs.get(atribName);
									// Verifying for the Given Key the value is already read or not
									if(!valueList.contains(atribValue)) {
											valueList.add(atribValue);
											pairs.put(atribName, valueList);
											}
									
								}
								
					//			System.out.println(attribs.item(attribIndex).getNodeName());
							}
							
						}
						
					
						//Sending the Parent Node to read the tags under the node
				//		System.out.println("Head Parent Node " + childNodeOfGivenTagName.getNodeName());
						if (childNodeOfGivenTagName.getNodeType() == Node.ELEMENT_NODE) {
							
								values.add(readTags(childNodeOfGivenTagName));
								values.add(readTags(childNodeOfGivenTagName));	
							}
						}
				
				System.out.println(values);
				if(values.get(0).containsKey("indIntegrationId")) {
					String givenName2="",givenName3 = "";
					
					String ciskey = values.get(0).get("indIntegrationId").get(0);
					String partyUid  = values.get(0).get("partyUid").get(0);
					String familyName  = values.get(0).get("familyName").get(0);
					String givenName  = values.get(0).get("givenName").get(0);
					String customerNum  = values.get(0).get("customerNum").get(0);
					String birthDate  = values.get(0).get("birthDate").get(0);
					
					if(values.get(0).containsKey("givenName2")) {
						givenName2 = values.get(0).get("givenName2").get(0);
					}
					if(values.get(0).containsKey("givenName3")) {
						givenName3 = values.get(0).get("givenName3").get(0) ;
					}
					
			
					
					strngBuilder.append(ciskey+",");
					strngBuilder.append(partyUid+",");
					strngBuilder.append(familyName+",");
					strngBuilder.append(givenName+",");
					strngBuilder.append(givenName2+",");
					strngBuilder.append(givenName3+",");
					strngBuilder.append(customerNum+",");
					strngBuilder.append(birthDate);
					strngBuilder.append('\n');
				
					System.out.println("done!");
				}else {
					
					System.out.println(" Failed to get valid Response ");
				}
			
				
				
			    }
			  } else {
			    // Handle the case where dir is not really a directory.
			    // Checking dir.isDirectory() above would not be sufficient
			    // to avoid race conditions with another process that deletes
			    // directories.
			  }
			
				pw.write(strngBuilder.toString());
				pw.close();
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} /*catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

	}
	
	


public static  Map<String, ArrayList<String>> readTags(Node node) {
	String key = "";
	String value = "";
	NodeList childNodesOfGivenNode = node.getChildNodes();

			for (int i = 0; i < (childNodesOfGivenNode.getLength() ); i++) {
						
					Node childNodeOfGivenChildNode = childNodesOfGivenNode.item(i);
				//	System.out.println(childNodeOfGivenChildNode);
						if (childNodeOfGivenChildNode.getNodeType() == Node.ELEMENT_NODE) {
					
							if(childNodeOfGivenChildNode.getChildNodes().getLength()>1){
										System.out.println("Child Parent " + childNodeOfGivenChildNode.getNodeName());
																	
										//Sending the Node to read the tags of the node
										//this step is recursive until we get the tag for a node
										readTags(childNodeOfGivenChildNode);
									}
							else if (childNodeOfGivenChildNode.getNodeType() == Node.ELEMENT_NODE) {
										
										Element name = (Element) childNodeOfGivenChildNode;
										value = name.getTextContent();
									
										// Splitting the Attribute name by ":" 
										String[]  TagValue = name.getNodeName().split(":");
										if (TagValue.length > 1 ) {
											key = TagValue[1];
										}else{
											key = TagValue[0];
										}
									 
										
												if (!key.isEmpty() && !value.isEmpty()) {
													
													ArrayList<String> valueList = new ArrayList<String>();
													if(pairs.get(key) == null) {
														valueList.add(value);
														pairs.put(key, valueList);
								
													}
													else{
														// key is already present hence adding the value to list of values for the key  
														valueList = pairs.get(key);
														// Verifying for the Given Key the value is already read or not
													//	if(!valueList.contains(value)) {
																valueList.add(value);
																pairs.put(key, valueList);
												//					}
														
													}
												
													
												}
										}
							}
			}
			return pairs;
}

}
