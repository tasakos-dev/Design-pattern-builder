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

public class TestGetClassesCreational {
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
	      assertEquals(6, classes.size());

	      PatternClass concreteFactoryClass1 = classes.get(0);
	      assertEquals("ConcreteFactory1", concreteFactoryClass1.getName());
	      assertEquals("AbstractFactory", concreteFactoryClass1.getImplementedInterface().getName());

	      List<Field> fields = concreteFactoryClass1.getFields();
	      assertEquals(0, fields.size());

	      List<Method> methods = concreteFactoryClass1.getMethods();
	      assertEquals(2, methods.size());
	      assertEquals("createProductA", methods.get(0).getName());
	      assertEquals("createProductB", methods.get(1).getName());
	      
	      PatternClass concreteFactoryClass2 = classes.get(1);
	      assertEquals("ConcreteFactory2", concreteFactoryClass2.getName());
	      assertEquals("AbstractFactory", concreteFactoryClass2.getImplementedInterface().getName());

	      fields = concreteFactoryClass2.getFields();
	      assertEquals(0, fields.size());

	      methods = concreteFactoryClass2.getMethods();
	      assertEquals(2, methods.size());
	      assertEquals("createProductA", methods.get(0).getName());
	      assertEquals("createProductB", methods.get(1).getName());
	      
	      

	      PatternClass productA1 = classes.get(2);
	      assertEquals("ConcreteProductA1", productA1.getName());
	      assertEquals("ProductA", productA1.getImplementedInterface().getName());

	      fields = productA1.getFields();
	      assertEquals(0, fields.size());

	      methods = productA1.getMethods();
	      assertEquals(1, methods.size());
	      assertEquals("doStuff", methods.get(0).getName());
	      
	      PatternClass productA2 = classes.get(3);
	      assertEquals("ConcreteProductA2", productA2.getName());
	      assertEquals("ProductA", productA2.getImplementedInterface().getName());

	      fields = productA2.getFields();
	      assertEquals(0, fields.size());

	      methods = productA2.getMethods();
	      assertEquals(1, methods.size());
	      assertEquals("doStuff", methods.get(0).getName());
	      
	      PatternClass productB1 = classes.get(4);
	      assertEquals("ConcreteProductB1", productB1.getName());
	      assertEquals("ProductB", productB1.getImplementedInterface().getName());

	      fields = productB1.getFields();
	      assertEquals(0, fields.size());

	      methods = productB1.getMethods();
	      assertEquals(1, methods.size());
	      assertEquals("doStuff", methods.get(0).getName());
	      
	      PatternClass productB2 = classes.get(5);
	      assertEquals("ConcreteProductB2", productB2.getName());
	      assertEquals("ProductB", productB2.getImplementedInterface().getName());

	      fields = productB2.getFields();
	      assertEquals(0, fields.size());

	      methods = productB2.getMethods();
	      assertEquals(1, methods.size());
	      assertEquals("doStuff", methods.get(0).getName());
	      
	  }


	  @Test
	  public void testGetClassesBuilderPattern() {
	    List<PatternClass> classes = patternManager.getClasses("Creational", "Builder");
	    assertEquals(2, classes.size());
	    // Test ConcreteBuilder class
	    PatternClass concreteBuilder = classes.get(0);
	    assertEquals("ConcreteBuilder", concreteBuilder.getName());

	    // Test fields of ConcreteBuilder class
	    List<Field> concreteBuilderFields = concreteBuilder.getFields();
	    assertEquals(0, concreteBuilderFields.size());
	    

	    // Test methods of ConcreteBuilder class
	    List<Method> concreteBuilderMethods = concreteBuilder.getMethods();
	    assertEquals(1, concreteBuilderMethods.size());
	    Method buildPartMethod = concreteBuilderMethods.get(0);
	    assertEquals("buildPart", buildPartMethod.getName());
	    assertEquals("void", buildPartMethod.getType());

	    // Test Director class
	    PatternClass director = classes.get(1);
	    assertEquals("Director", director.getName());

	    // Test fields of Director class
	    List<Field> directorFields = director.getFields();
	    assertEquals(1, directorFields.size());
	    Field directorField = directorFields.get(0);
	    assertEquals("builder", directorField.getName());
	    assertEquals("Builder", directorField.getType());

	    // Test methods of Director class
	    List<Method> directorMethods = director.getMethods();
	    assertEquals(1, directorMethods.size());
	    Method constructMethod = directorMethods.get(0);
	    assertEquals("construct", constructMethod.getName());
	    assertEquals("void", constructMethod.getType());
	  }
	  
	  @Test
	  public void testGetClassesFactoryMethodPattern() {
		  List<PatternClass> classes = patternManager.getClasses("Creational", "Factory Method");
		    assertEquals(3, classes.size());

		    PatternClass patternClass = classes.get(0);
		    assertEquals("Creator", patternClass.getName());

		    List<Field> fields = patternClass.getFields();
		    assertEquals(0, fields.size());

		    List<Method> methods = patternClass.getMethods();
		    assertEquals(2, methods.size());
		    assertEquals("factoryMethodA", methods.get(0).getName());
		    assertEquals("anOperation", methods.get(1).getName());
		    
		    
		    patternClass = classes.get(1);
		    assertEquals("ConcreteCreator", patternClass.getName());

		    fields = patternClass.getFields();
		    assertEquals(0, fields.size());

		    methods = patternClass.getMethods();
		    assertEquals(1, methods.size());
		    assertEquals("factoryMethodA", methods.get(0).getName());
		    assertTrue(methods.get(0).isAbstract());
		    assertEquals("Product", methods.get(0).getType());
//		    assertEquals("anOperation", methods.get(1).getName());
		    
		    patternClass = classes.get(2);
		    assertEquals("ConcreteProductA", patternClass.getName());
		    assertEquals("Product", patternClass.getImplementedInterface().getName());

		    fields = patternClass.getFields();
		    assertEquals(0, fields.size());

		    methods = patternClass.getMethods();
		    assertEquals(1, methods.size());
		    assertEquals("doStuff", methods.get(0).getName());
		    
	  }
	  
	  @Test
	  public void testGetClassesSingletonPattern() {
		  String CODE = "if (instance == null) {\n"
		  		+ "							instance = new Singleton();\n"
		  		+ "						}\n"
		  		+ "						return instance;";
	      List<PatternClass> classes = patternManager.getClasses("Creational", "Singleton");
	      assertEquals(1, classes.size());

	      PatternClass patternClass = classes.get(0);
	      assertEquals("Singleton", patternClass.getName());

	      List<Field> fields = patternClass.getFields();
	      assertEquals(1, fields.size());
	      assertEquals("instance", fields.get(0).getName());

	      List<Method> methods = patternClass.getMethods();
	      assertEquals(2, methods.size());
	      assertEquals("private", methods.get(0).getModifier());
	      assertEquals("Singleton", methods.get(0).getName());
	      assertEquals("getInstance", methods.get(1).getName());
//	      assertEquals("Singleton", methods.get(1).getType());
	      assertTrue(methods.get(1).isStatic());
	      assertEquals(CODE, methods.get(1).getCode());
	  }

	  
	  @Test
	  public void testGetClassesPrototypePattern() {
		  String CODE_CLONE = "try {\n"
		  		+ "							return (Prototype) super.clone();\n"
		  		+ "						} catch (CloneNotSupportedException e) {\n"
		  		+ "							e.printStackTrace();\n"
		  		+ "						}\n"
		  		+ "						return null;";
		  String CODE_OPERATION = "Prototype p = null;\n"
		  		+ "						p = prototype.clone();";
	      List<PatternClass> classes = patternManager.getClasses("Creational", "Prototype");
	      assertEquals(3, classes.size());

	      
	      PatternClass concretePrototype1 = classes.get(0);
	      assertEquals("ConcretePrototype1", concretePrototype1.getName());
	      assertEquals("Prototype", concretePrototype1.getImplementedInterface().getName());
	      
	      PatternClass concretePrototype2 = classes.get(1);
	      assertEquals("ConcretePrototype2", concretePrototype2.getName());
	      assertEquals("Prototype", concretePrototype2.getImplementedInterface().getName());

	      List<Method> methods1 = concretePrototype1.getMethods();
	      assertEquals(1, methods1.size());
	      assertEquals("clone", methods1.get(0).getName());
	      assertEquals(CODE_CLONE, methods1.get(0).getCode());
	      
	      List<Method> methods2 = concretePrototype2.getMethods();
	      assertEquals(1, methods2.size());
	      assertEquals("clone", methods2.get(0).getName());
	      assertEquals(CODE_CLONE, methods2.get(0).getCode());
	      
	      PatternClass client = classes.get(2);
	      assertEquals("Client", client.getName());
	      
	      List<Field> fields = client.getFields();
	      assertEquals(1, fields.size());
	      assertEquals("prototype", fields.get(0).getName());
	      assertEquals("Prototype", fields.get(0).getType());
	      assertEquals("private", fields.get(0).getModifier());
	    
	      
	      List<Method> methods3 = client.getMethods();
	      assertEquals(1, methods3.size());
	      assertEquals("operation", methods3.get(0).getName());
	      assertEquals(CODE_OPERATION, methods3.get(0).getCode());
	      
	  }



}
