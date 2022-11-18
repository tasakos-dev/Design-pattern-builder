package dpb.io;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;


import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class FileParser implements IFileParser {
	private Document patternsDoc;
	
	

	
	public FileParser() {
		File patternsFIle = null;
		Bundle bundle = Platform.getBundle("DPB");
		URL filUrl = bundle.getEntry("src/main/resources/patterns.xml");
		
		try {
			patternsFIle = new File(FileLocator.resolve(filUrl).toURI());
		} catch (URISyntaxException | IOException e1) {
			e1.printStackTrace();
		}

		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		
		try {
			documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			patternsDoc = documentBuilder.parse(patternsFIle);
			patternsDoc.getDocumentElement().normalize();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	
	}

	@Override
	public String[] getPatternCategories() {
		NodeList categoriesNodeList = (NodeList) patternsDoc.getElementsByTagName("category");
		int length = categoriesNodeList.getLength();
		String[] categories = new String[length];
		String categoryString;
		
		for (int i = 0;i < length;i++) {
			Element element = (Element) categoriesNodeList.item(i);
			categoryString = element.getAttribute("id");
			categories[i] = categoryString;
			
		}
		
		
		return categories;
	}

	@Override
	public String[] getPatternsOfCategory(String category) {
//		System.err.println(patternsDoc.getElementsByTagName("category"));
		NodeList patternList = (NodeList) getElementByTagAndId("category", category).getElementsByTagName("pattern");
		int length = patternList.getLength();
		String[] patterns = new String[length];
		String patternString;
		
		for (int i = 0;i < length;i++) {
			Element element = (Element) patternList.item(i);
			patternString = element.getAttribute("id");
			patterns[i] = patternString;
			
		}
		

		
		return patterns;
	}
	
	private Element getElementByTagAndId(String tag, String id) {
		Element retElement = null;
		NodeList categoryElements = patternsDoc.getElementsByTagName(tag);
		int length = categoryElements.getLength();
		
		for (int i = 0;i < length; i++) {
			Element element = (Element) categoryElements.item(i);
			if (element.hasAttribute("id") && element.getAttribute("id").equals(id)) {
				retElement = element;
				break;
			}
			
		}
		
		return retElement ; 
		
	}

	// TODO refactor all methods below duplication!
	@Override
	public String[] getClasses(String pattern) {
		
		NodeList patternList = (NodeList) getElementByTagAndId("pattern", pattern).getElementsByTagName("class");
		int length = patternList.getLength();
		String[] patterns = new String[length];
		String patternString;
		
		for (int i = 0;i < length;i++) {
			Element element = (Element) patternList.item(i);
			patternString = element.getAttribute("id");
			patterns[i] = patternString;
			
		}
		

		
		return patterns;
	}

	@Override
	public String[] getInterfaces(String pattern) {
		NodeList patternList = (NodeList) getElementByTagAndId("pattern", pattern).getElementsByTagName("interface");
		int length = patternList.getLength();
		String[] patterns = new String[length];
		String patternString;
		
		for (int i = 0;i < length;i++) {
			Element element = (Element) patternList.item(i);
			patternString = element.getAttribute("id");
			patterns[i] = patternString;
			
		}
		

		
		return patterns;
	}

	@Override
	public String[][] getClassMethods(String className) {
		NodeList classesList = (NodeList) getElementByTagAndId("class", className).getElementsByTagName("method");
		int length = classesList.getLength();
		String[][] classMethods = new String[length][2];
		
		for (int i = 0; i < length; i++) {
			Element element = (Element) classesList.item(i);
			String name = element.getAttribute("id");
			String type = element.getElementsByTagName("type").item(0).getTextContent();
			classMethods[i] = new String[] {type, name};
		}
		
		return classMethods;
	}

	@Override
	public String[][] getInterfaceMethods(String interfaceName) {
		
		NodeList interfaceList = (NodeList) getElementByTagAndId("interface", interfaceName).getElementsByTagName("method");
		int length = interfaceList.getLength();
		String[][] interfaceMethods = new String[length][2];
		
		for (int i = 0; i < length; i++) {
			Element element = (Element) interfaceList.item(i);
			String name = element.getAttribute("id");
			String type = element.getElementsByTagName("type").item(0).getTextContent();
			interfaceMethods[i] = new String[] {type, name};
		}
		
		return interfaceMethods;
	}

	@Override
	public String[][] getClassFields(String className) {
		
		NodeList classesList = (NodeList) getElementByTagAndId("class", className).getElementsByTagName("field");
		int length = classesList.getLength();
		String[][] classFields = new String[length][2];
		
		for (int i = 0; i < length; i++) {
			Element element = (Element) classesList.item(i);
			String name = element.getAttribute("id");
			String type = element.getElementsByTagName("type").item(0).getTextContent();
			classFields[i] = new String[] {type, name};
		}
		
		return classFields;
	}
	
	

}
