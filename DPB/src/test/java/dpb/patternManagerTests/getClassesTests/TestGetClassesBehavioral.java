package dpb.patternManagerTests.getClassesTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import dpb.controller.PatternManager;
import dpb.model.Field;
import dpb.model.Method;
import dpb.model.PatternClass;

public class TestGetClassesBehavioral {
	private PatternManager patternManager;

	  @Before
	  public void setUp() {
	    try {
			patternManager = (PatternManager) PatternManager.getInstance();
		} catch (ParserConfigurationException | SAXException | IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Replace with your implementation of IPatternManager
	  }
	  
	  @Test
	  public void testGetClassesChainOfResponsibility() {
	      List<PatternClass> classes = patternManager.getClasses("Behavioral", "Chain of Responsibility");
	      assertEquals(3, classes.size());

	      PatternClass patternClass1 = classes.get(0);
	      assertEquals("Handler", patternClass1.getName());

	      List<Field> fields1 = patternClass1.getFields();
	      assertEquals(1, fields1.size());
	      assertEquals("successor", fields1.get(0).getName());
	      assertEquals("Handler", fields1.get(0).getType());

	      List<Method> methods1 = patternClass1.getMethods();
	      assertEquals(2, methods1.size());
	      assertEquals("setSuccessor", methods1.get(0).getName());
	      assertEquals("handleRequest", methods1.get(1).getName());
	      
	      PatternClass concreteHandler1 = classes.get(1);
	      assertEquals("ConcreteHandler1", concreteHandler1.getName());

	      fields1 = concreteHandler1.getFields();
	      assertEquals(0, fields1.size());


	      methods1 = concreteHandler1.getMethods();
	      assertEquals(1, methods1.size());
	      assertEquals("handleRequest", methods1.get(0).getName());
	      
	      PatternClass concreteHandler2 = classes.get(2);
	      assertEquals("ConcreteHandler2", concreteHandler2.getName());

	      fields1 = concreteHandler2.getFields();
	      assertEquals(0, fields1.size());

	      methods1 = concreteHandler2.getMethods();
	      assertEquals(1, methods1.size());
	      assertEquals("handleRequest", methods1.get(0).getName());
	      


	  }

	  
	  @Test
	  public void testGetClassesCommand() {
	      List<PatternClass> classes = patternManager.getClasses("Behavioral", "Command");
	      assertEquals(3, classes.size());

	      PatternClass patternClass2 = classes.get(0);
	      assertEquals("ConcreteCommand", patternClass2.getName());

	      List<Field> fields2 = patternClass2.getFields();
	      assertEquals(2, fields2.size());
	      assertEquals("state", fields2.get(0).getName());
	      assertEquals("receiver", fields2.get(1).getName());
	      
	 

	      List<Method> methods2 = patternClass2.getMethods();
	      assertEquals(1, methods2.size());
	      assertEquals("execute", methods2.get(0).getName());

	      PatternClass patternClass4 = classes.get(1);
	      assertEquals("Receiver", patternClass4.getName());
	      
	      List<Method> methods3 = patternClass4.getMethods();
	      assertEquals(1, methods3.size());
	      assertEquals("action", methods3.get(0).getName());
	      
	      PatternClass patternClass3 = classes.get(2);
	      assertEquals("Invoker", patternClass3.getName());

	      List<Field> fields3 = patternClass3.getFields();
	      assertEquals(1, fields3.size());
	      assertEquals("command", fields3.get(0).getName());

	      List<Method> methods4 = patternClass3.getMethods();
	      assertEquals(2, methods4.size());
	      assertEquals("setCommand", methods4.get(0).getName());
	      assertEquals("executeCommand", methods4.get(1).getName());

	  }




}
