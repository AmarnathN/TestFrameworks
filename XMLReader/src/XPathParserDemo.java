
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.SAXException;

public class XPathParserDemo {
	public static Map<String, ArrayList<String>> pairs = null;
	public static ArrayList<Map<String, ArrayList<String>>> values = null;
	
	
   public static void main(String[] args) {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {

//			
//			DocumentBuilder builder = factory.newDocumentBuilder();
//			Document doc = builder.parse("D://BankWOW_Request.xml");
//			System.out.println(doc.getChildNodes());
//			
		//	 File file = new File("D://BankWow2.xml");
			 File file = new File("C:\\Users\\namarnat\\Documents\\SubmissionInputDTO.xml");
			 
		        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		        Enumeration<InputStream> streams = Collections.enumeration(
		                Arrays.asList(new InputStream[] {
		                    new ByteArrayInputStream("<ROOT>".getBytes()),
		                    new FileInputStream(file),
		                    new ByteArrayInputStream("</ROOT>".getBytes()),
		                }));

		        SequenceInputStream sequenceStream = new SequenceInputStream(streams);
		
		        Document doc = db.parse(sequenceStream);
		        doc.getDocumentElement().normalize();
		    
		  //  String parentNode = "//modifyDemandDepositArrangementRequest/arrangement/hasCondition";	
		    String parentNode = "hasCondition" ;
			
			  NodeList nodesOfGivenTagName ;
		         if(parentNode.contains("/")){
		             	doc.getDocumentElement().normalize();
		 		    	XPath xPath =  XPathFactory.newInstance().newXPath();
		 		    	String xpathExpression = parentNode;	
		 		    	nodesOfGivenTagName = (NodeList) xPath.compile(xpathExpression).evaluate(doc, XPathConstants.NODESET);
		         }else{
		        	 	nodesOfGivenTagName = doc.getElementsByTagName(parentNode);
				        
		         }
			values = new ArrayList<Map<String, ArrayList<String>>>();
			
			System.out.println(nodesOfGivenTagName.getLength());
			
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
						
						System.out.println(attribs.item(attribIndex).getNodeName());
					}
					
				}
				
			
				//Sending the Parent Node to read the tags under the node
				System.out.println("Head Parent Node " + childNodeOfGivenTagName.getNodeName());
				if (childNodeOfGivenTagName.getNodeType() == Node.ELEMENT_NODE) {
					
						values.add(readTags(childNodeOfGivenTagName));
							
					}
				}
			
		System.out.println(values);
		//	System.out.println(values);
		//	pairs=values.get(0);
		//	pairs =values.get(1);
		
		//	System.out.println(verifyValueList);
			
			//System.out.println(verifyValueList.get(0).equalsIgnoreCase("12345678902"));
			
			//System.out.println(verifyValueList.get(1).equalsIgnoreCase("12345678902"));
			
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
		}*/ catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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



