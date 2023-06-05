package dpb.patternManagerTests.getInterfacesTest;

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
import dpb.model.PatternInterface;

public class TestGetInterfacesCreational {

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
	  public void testGetClassesAbstractFactoryPattern() {
	      List<PatternClass> classes = patternManager.getClasses("Creational", "Abstract Factory");
	      List<PatternInterface> interfaces = patternManager.getInterfaces();
	      assertEquals(3, interfaces.size());

	      PatternInterface factory = interfaces.get(0);
	      assertEquals("AbstractFactory", factory.getName());

	      
	      List<Method> methods = factory.getMethods();
	      assertEquals(2, methods.size());
	      assertEquals("createProductA", methods.get(0).getName());
	      assertEquals("createProductB", methods.get(1).getName());
	
	      PatternInterface productA = interfaces.get(1); 
	      assertEquals("ProductA", productA.getName());

	   
	      methods = productA.getMethods();
	      assertEquals(1, methods.size());
	      assertEquals("doStuff", methods.get(0).getName());
	      
	      
	      PatternInterface productB = interfaces.get(2);
	      assertEquals("ProductB", productB.getName());

	      methods = productB.getMethods();
	      assertEquals(1, methods.size());
	      assertEquals("doStuff", methods.get(0).getName());
	      
	  }


	  @Test
	  public void testGetClassesBuilderPattern() {
	    List<PatternClass> classes = patternManager.getClasses("Creational", "Builder");
	    List<PatternInterface> interfaces = patternManager.getInterfaces();
	    assertEquals(1, interfaces.size());

	    PatternInterface concreteBuilder = interfaces.get(0);
	    assertEquals("Builder", concreteBuilder.getName());

	    List<Method> concreteBuilderMethods = concreteBuilder.getMethods();
	    assertEquals(1, concreteBuilderMethods.size());
	    Method buildPartMethod = concreteBuilderMethods.get(0);
	    assertEquals("buildPart", buildPartMethod.getName());
	    assertEquals("void", buildPartMethod.getType());
	  }
	  
	  @Test
	  public void testGetClassesFactoryMethodPattern() {
		  List<PatternClass> classes = patternManager.getClasses("Creational", "Factory Method");
		  List<PatternInterface> interfaces = patternManager.getInterfaces();
		    assertEquals(1, interfaces.size());

		    PatternInterface product = interfaces.get(0);
		    assertEquals("Product", product.getName());

		    List<Method> methods = product.getMethods();
		    assertEquals(1, methods.size());
		    assertEquals("doStuff", methods.get(0).getName());
		    
	  }
	  
	  @Test
	  public void testGetClassesSingletonPattern() {
	      List<PatternClass> classes = patternManager.getClasses("Creational", "Singleton");
	      List<PatternInterface> interfaces = patternManager.getInterfaces();
	      assertEquals(0, interfaces.size());
	  }

	  
	  @Test
	  public void testGetClassesPrototypePattern() {
	      List<PatternClass> classes = patternManager.getClasses("Creational", "Prototype");
	      List<PatternInterface> interfaces = patternManager.getInterfaces();
	      assertEquals(1, interfaces.size());

	      
	      PatternInterface prototype = interfaces.get(0);
	      assertEquals("Prototype", prototype.getName());
	      
	      List<Method> methods1 = prototype.getMethods();
	      assertEquals(1, methods1.size());
	      assertEquals("clone", methods1.get(0).getName());
     
	  }


}
