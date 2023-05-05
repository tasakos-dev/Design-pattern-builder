package dpb.patternManagerTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import dpb.controller.PatternManager;

public class TestGetDescription {
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
	  public void testGetPatternDescriptionCreationalPatterns() {
		String[] creationalPatterns = {"Abstract Factory", "Builder","Factory Method", "Prototype", "Singleton"};
		 String expectedDescription = "";
	     String actualDescription = "";

	     // Test all GoF creational patterns and their intents
	     for (String pattern : creationalPatterns) {
	         switch (pattern) {
	             case "Abstract Factory":
	                 expectedDescription = "Provide an interface for creating families of related or dependent objects without specifying their concrete classes.";
	                 break;
	             case "Builder":
	                 expectedDescription = "Separate the construction of a complex object from its representation so that the same construction process can create different representations.";
	                 break;
	             case "Factory Method":
	                 expectedDescription = "Define an interface for creating an object, but let subclasses decide which class to instantiate. Factory Method lets a class defer instantiation to subclasses.";
	                 break;
	             case "Prototype":
	                 expectedDescription = "Specify the kinds of objects to create using a prototypical instance, and create new objects by copying this prototype.";
	                 break;
	             case "Singleton":
	                 expectedDescription = "Ensure a class only has one instance, and provide a global point of access to it.";
	                 break;
	         }

	         actualDescription = patternManager.getPatternDescription(pattern);
	         assertEquals(expectedDescription, actualDescription);
	     }
	 }

	  @Test
	  public void testGetPatternDescriptionStructuralPatterns() {
		String[] structuralPatterns = {"Object Adapter", "Class Adapter", "Bridge", "Composite", "Decorator", "Facade", "Flyweight", "Proxy"};
		String expectedDescription = "";
	    String actualDescription = "";

	    // Test all GoF structural patterns and their intents
	    for (String pattern : structuralPatterns) {
	        switch (pattern) {
	            case "Class Adapter":
	                expectedDescription = "Convert the interface of a class into another interface clients expect. Adapter lets classes work together that couldn't otherwise because of incompatible interfaces.";
	                break;
	            case "Object Adapter":
	                expectedDescription = "Convert the interface of a class into another interface clients expect. Adapter lets classes work together that couldn't otherwise because of incompatible interfaces.";
	                break;
	            case "Bridge":
	                expectedDescription = "Decouple an abstraction from its implementation so that the two can vary independently.";
	                break;
	            case "Composite":
	                expectedDescription = "Compose objects into tree structures to represent part-whole hierarchies. Composite lets clients treat individual objects and compositions of objects uniformly.";
	                break;
	            case "Decorator":
	                expectedDescription = "Attach additional responsibilities to an object dynamically. Decorators provide a flexible alternative to subclassing for extending functionality.";
	                break;
	            case "Facade":
	                expectedDescription = "Provide a unified interface to a set of interfaces in a subsystem. Facade defines a higher-level interface that makes the subsystem easier to use.";
	                break;
	            case "Flyweight":
	                expectedDescription = "Use sharing to support large numbers of fine-grained objects efficiently.";
	                break;
	            case "Proxy":
	                expectedDescription = "Provide a surrogate or placeholder for another object to control access to it.";
	                break;
	        }

	        actualDescription = patternManager.getPatternDescription(pattern);
	        assertEquals(expectedDescription, actualDescription);
	    }
	    
	  }
	  
	  @Test
	  public void testGetPatternDescriptionBehavioralPatterns() {
		  String[] behavioralPatterns = {"Chain of Responsibility", "Command", "Interpreter", "Iterator", "Mediator", "Memento", "Observer", "State", "Strategy", "Template Method", "Visitor"};
		  String expectedDescription = "";
	      String actualDescription = "";

	      // Test all GoF behavioral patterns and their intents
	      for (String pattern : behavioralPatterns) {
	          switch (pattern) {
	              case "Chain of Responsibility":
	                  expectedDescription = "Avoid coupling the sender of a request to its receiver by giving more than one object a chance to handle the request. Chain the receiving objects and pass the request along the chain until an object handles it.";
	                  break;
	              case "Command":
	                  expectedDescription = "Encapsulate a request as an object, thereby letting you parameterize clients with different requests, queue or log requests, and support undoable operations.";
	                  break;
	              case "Interpreter":
	                  expectedDescription = "Given a language, define a represention for its grammar along with an interpreter that uses the representation to interpret sentences in the language.";
	                  break;
	              case "Iterator":
	                  expectedDescription = "Provide a way to access the elements of an aggregate object sequentially without exposing its underlying representation.";
	                  break;
	              case "Mediator":
	                  expectedDescription = "Define an object that encapsulates how a set of objects interact. Mediator promotes loose coupling by keeping objects from referring to each other explicitly, and it lets you vary their interaction independently.";
	                  break;
	              case "Memento":
	                  expectedDescription = "Without violating encapsulation, capture and externalize an object's internal state so that the object can be restored to this state later.";
	                  break;
	              case "Observer":
	                  expectedDescription = "Define a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.";
	                  break;
	              case "State":
	                  expectedDescription = "Allow an object to alter its behavior when its internal state changes. The object will appear to change its class.";
	                  break;
	              case "Strategy":
	                  expectedDescription = "Define a family of algorithms, encapsulate each one, and make them interchangeable. Strategy lets the algorithm vary independently from clients that use it.";
	                  break;
	              case "Template Method":
	                  expectedDescription = "Define the skeleton of an algorithm in an operation, deferring some steps to subclasses. Template Method lets subclasses redefine certain steps of an algorithm without changing the algorithm's structure.";
	                  break;
	              case "Visitor":
	                  expectedDescription = "Represent an operation to be performed on the elements of an object structure. Visitor lets you define a new operation without changing the classes of the elements on which it operates.";
	                  break;
	          }

	          actualDescription = patternManager.getPatternDescription(pattern);
	          assertEquals(expectedDescription, actualDescription);
	      }
	  }
	
}
