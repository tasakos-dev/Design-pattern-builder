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

public class TestGetInterfacesBehavioral {
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
	      List<PatternInterface> interfaces = patternManager.getInterfaces();
	      assertEquals(0, interfaces.size());

	  }

	  
	  @Test
	  public void testGetClassesCommand() {
	      List<PatternClass> classes = patternManager.getClasses("Behavioral", "Command");
	      List<PatternInterface> interfaces = patternManager.getInterfaces();
	      assertEquals(1, interfaces.size());

	      PatternInterface command = interfaces.get(0);
	      assertEquals("Command", command.getName());

	      List<Method> methods2 = command.getMethods();
	      assertEquals(1, methods2.size());
	      assertEquals("execute", methods2.get(0).getName());

	  }
	  
	  @Test
	  public void testGetClassesInterpreterPattern() {
	      List<PatternClass> classes = patternManager.getClasses("Behavioral", "Interpreter");
	      List<PatternInterface> interfaces = patternManager.getInterfaces();
	      assertEquals(1, interfaces.size());

	      PatternInterface AbstractExpression = interfaces.get(0);
	      assertEquals("AbstractExpression", AbstractExpression.getName());

	
	      List<Method> methods = AbstractExpression.getMethods();
	      assertEquals(1, methods.size());
	      assertEquals("interpret", methods.get(0).getName());
	      assertEquals("Context", methods.get(0).getParameters().get(0)[0]);
	      assertEquals("context", methods.get(0).getParameters().get(0)[1]);
	      
	  }
	  
	  @Test
	  public void testGetClassesIteratorPattern() {
	      List<PatternClass> classes = patternManager.getClasses("Behavioral", "Iterator");
	      List<PatternInterface> interfaces = patternManager.getInterfaces();
	      assertEquals(2, interfaces.size());

	      PatternInterface iterator = interfaces.get(0);
	      assertEquals("Iterator", iterator.getName());

	      List<Method> methods = iterator.getMethods();
	      assertEquals(2, methods.size());
	      assertEquals("getNext", methods.get(0).getName());
	      assertEquals("Object", methods.get(0).getType());
	      assertEquals("hasNext", methods.get(1).getName());
	      assertEquals("boolean", methods.get(1).getType());
	      
	      

	      PatternInterface collection = interfaces.get(1);

	      assertEquals("Collection", collection.getName());
	      
	      methods = collection.getMethods();
	      assertEquals(1, methods.size());
	      assertEquals("createIterator", methods.get(0).getName());
	      assertEquals("Iterator", methods.get(0).getType());
	  }
	  
	  @Test
	  public void testGetClassesMediatorPattern() {
	      List<PatternClass> classes = patternManager.getClasses("Behavioral", "Mediator");
	      List<PatternInterface> interfaces = patternManager.getInterfaces();
	      assertEquals(1, interfaces.size());

	      PatternInterface mediator = interfaces.get(0);
	      assertEquals("Mediator", mediator.getName());
	
	      
	      List<Method> methods = mediator.getMethods();
	      assertEquals(1, methods.size());
	      assertEquals("mediate", methods.get(0).getName());
	      assertEquals("Colleague", methods.get(0).getParameters().get(0)[0]);
	      assertEquals("colleague", methods.get(0).getParameters().get(0)[1]);
	      
	  }
	  
	  @Test
	  public void testGetClassesMementoPattern() {
	      List<PatternClass> classes = patternManager.getClasses("Behavioral", "Memento");
	      List<PatternInterface> interfaces = patternManager.getInterfaces();
	      assertEquals(0, interfaces.size());

	  }
	  
	  @Test
	  public void testGetClassesObserverPattern() {

		  List<PatternClass> classes = patternManager.getClasses("Behavioral", "Observer");
		  List<PatternInterface> interfaces = patternManager.getInterfaces();
	      assertEquals(1, interfaces.size());
	     

	      PatternInterface observer = interfaces.get(0);
	      assertEquals("Observer", observer.getName());
	      
	      assertEquals(1, observer.getMethods().size());
	      assertEquals("update", observer.getMethods().get(0).getName());

	  }
	  
	
	  
	  @Test
	  public void testGetClassesStatePattern() {
	      List<PatternClass> classes = patternManager.getClasses("Behavioral", "State");
	      List<PatternInterface> interfaces = patternManager.getInterfaces();
	      assertEquals(1, interfaces.size());


	      PatternInterface state = interfaces.get(0);
	      assertEquals("State", state.getName());


	      List<Method> methods2 = state.getMethods();
	      assertEquals(1, methods2.size());
	      assertEquals("handle", methods2.get(0).getName());

	  }
	  
	  @Test
	  public void testGetClassesForTemplateMethod() {
	      List<PatternClass> classes = patternManager.getClasses("Behavioral", "Template Method");
	      List<PatternInterface> interfaces = patternManager.getInterfaces();
	      
	      assertEquals(0, interfaces.size());
	  }

	  
	  @Test
	  public void testGetClassesForStrategy() {
	      List<PatternClass> classes = patternManager.getClasses("Behavioral", "Strategy");
	      List<PatternInterface> interfaces = patternManager.getInterfaces();
	      
	      assertEquals(1, interfaces.size());
	      
	      PatternInterface strategy = interfaces.get(0);
	      assertEquals("Strategy", strategy.getName());

	      List<Method> concreteStrategyAMethods = strategy.getMethods();
	      assertEquals(1, concreteStrategyAMethods.size());
	      assertEquals("algorithmInterface", concreteStrategyAMethods.get(0).getName());
	      
	  }


	  @Test
	  public void testGetClassesVisitor() {
	      List<PatternClass> classes = patternManager.getClasses("Behavioral", "Visitor");
	      List<PatternInterface> interfaces = patternManager.getInterfaces();
	      assertEquals(2, interfaces.size());

	      PatternInterface patternInterface = interfaces.get(0);
	      
	      assertEquals("Visitor", patternInterface.getName());


	      List<Method> methods = patternInterface.getMethods();
	      assertEquals(2, methods.size());
	      assertEquals("visitConcreteElementA", methods.get(0).getName());
	      assertEquals("ConcreteElementA", methods.get(0).getParameters().get(0)[0]);
	      assertEquals("concreteElementA", methods.get(0).getParameters().get(0)[1]);
	      assertEquals("visitConcreteElementB", methods.get(1).getName());
	      assertEquals("ConcreteElementB", methods.get(1).getParameters().get(0)[0]);
	      assertEquals("concreteElementB", methods.get(1).getParameters().get(0)[1]);

	      patternInterface = interfaces.get(1);
	   
	      assertEquals("Element", patternInterface.getName());

	      methods = patternInterface.getMethods();
	      assertEquals(1, methods.size());
	      assertEquals("accept", methods.get(0).getName());
	      assertEquals("Visitor", methods.get(0).getParameters().get(0)[0]);
	      assertEquals("visitor", methods.get(0).getParameters().get(0)[1]);    
	  }

	  
	  
	  

	

}
