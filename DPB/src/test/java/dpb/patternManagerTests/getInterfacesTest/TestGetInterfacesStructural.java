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
import dpb.model.Method;
import dpb.model.PatternClass;
import dpb.model.PatternInterface;

public class TestGetInterfacesStructural {
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
	  public void testGetObjectAdapterClasses() {
	      List<PatternClass> classes = patternManager.getClasses("Structural", "Object Adapter");
	      List<PatternInterface> interfaces = patternManager.getInterfaces();
	      assertEquals(1, interfaces.size());

	      PatternInterface adapter = interfaces.get(0);
	      assertEquals("Target", adapter.getName());


	      List<Method> methods = adapter.getMethods();
	      assertEquals(1, methods.size());
	      assertEquals("request", methods.get(0).getName());	      
	  }
	  
	  @Test
	  public void testGetClassAdapterClasses() {
	      List<PatternClass> classes = patternManager.getClasses("Structural", "Class Adapter");
	      List<PatternInterface> interfaces = patternManager.getInterfaces();
	      assertEquals(1, interfaces.size());

	      PatternInterface adapter = interfaces.get(0);
	      assertEquals("Target", adapter.getName());


	      List<Method> methods = adapter.getMethods();
	      assertEquals(1, methods.size());
	      assertEquals("request", methods.get(0).getName());	  
	  }
	  
	  @Test
	  public void testGetClassesBridgePattern() {
	      List<PatternClass> classes = patternManager.getClasses("Structural", "Bridge");
	      List<PatternInterface> interfaces = patternManager.getInterfaces();
	      assertEquals(1, interfaces.size());
	      
	      PatternInterface concreteImplementorA = interfaces.get(0);
	      assertEquals("Implementor", concreteImplementorA.getName());


	      List<Method> methods = concreteImplementorA.getMethods();
	      assertEquals(1, methods.size());
	      assertEquals("operationImp", methods.get(0).getName());

	  }

	  @Test
	  public void testGetClassesCompositePattern() {
		  List<PatternClass> classes = patternManager.getClasses("Structural", "Composite");
		  List<PatternInterface> interfaces = patternManager.getInterfaces();
		  assertEquals(1, interfaces.size());

		  PatternInterface composite = interfaces.get(0);

		  assertEquals("Component", composite.getName());

		  List<Method> methods = composite.getMethods();
		  assertEquals(1, methods.size());
		  assertEquals("operation", methods.get(0).getName());

	  }
	  
	  @Test
	  public void testGetClassesDecoratorPattern() {
	      List<PatternClass> classes = patternManager.getClasses("Structural", "Decorator");
	      List<PatternInterface> interfaces = patternManager.getInterfaces();
	      assertEquals(1, interfaces.size());


	      PatternInterface concreteComponent = interfaces.get(0);
	      assertEquals("Component", concreteComponent.getName());
	   
	      
	      assertEquals(1, concreteComponent.getMethods().size());
	      assertEquals("operation", concreteComponent.getMethods().get(0).getName());
	  }
	  
	  @Test
	  public void testGetClassesFacadePattern() {
	      List<PatternClass> classes = patternManager.getClasses("Structural", "Facade");
	      List<PatternInterface> interfaces = patternManager.getInterfaces();
	      assertEquals(0, interfaces.size());


	  }

	  @Test
	  public void testGetClassesFlyweightPattern() {
	      List<PatternClass> classes = patternManager.getClasses("Structural", "Flyweight");
	      List<PatternInterface> interfaces = patternManager.getInterfaces();
	      assertEquals(0, interfaces.size());

	  }
	  
	  @Test
	  public void testGetClassesProxyPattern() {
	      List<PatternClass> classes = patternManager.getClasses("Structural", "Proxy");
	      List<PatternInterface> interfaces = patternManager.getInterfaces();
	      assertEquals(1, interfaces.size());


	      PatternInterface realSubjectClass = interfaces.get(0);
	      assertEquals("Subject", realSubjectClass.getName());


	      List<Method> methods = realSubjectClass.getMethods();
	      assertEquals(1, methods.size());
	      assertEquals("request", methods.get(0).getName());

	  }


}
