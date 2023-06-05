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

public class TestGetClassesStructural {
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
		  String CODE = "adaptee.specificRequest();";
	      List<PatternClass> classes = patternManager.getClasses("Structural", "Object Adapter");
	      assertEquals(3, classes.size());

	      PatternClass adapterClass = classes.get(0);
	      assertEquals("Adapter", adapterClass.getName());
	      assertEquals("Target", adapterClass.getImplementedInterface().getName());

	      List<Field> fields = adapterClass.getFields();
	      assertEquals(1, fields.size());
	      assertEquals("adaptee", fields.get(0).getName());
	      assertEquals("Adaptee", fields.get(0).getType());

	      List<Method> methods = adapterClass.getMethods();
	      assertEquals(1, methods.size());
	      assertEquals("request", methods.get(0).getName());
	      assertEquals(CODE, methods.get(0).getCode());
	      

	      PatternClass adapteeClass = classes.get(1);
	      assertEquals("Adaptee", adapteeClass.getName());

	      fields = adapteeClass.getFields();
	      assertEquals(0, fields.size());

	      methods = adapteeClass.getMethods();
	      assertEquals(1, methods.size());
	      assertEquals("specificRequest", methods.get(0).getName());
	      
	      PatternClass client = classes.get(2);
	      assertEquals("Client", client.getName());

	      fields = client.getFields();
	      assertEquals(1, fields.size());
	      assertEquals("target", fields.get(0).getName());
	      assertEquals("Target", fields.get(0).getType());
	      

	      methods = client.getMethods();
	      assertEquals(0, methods.size());
	  }
	  
	  @Test
	  public void testGetClassAdapterClasses() {
		  String CODE = "super.specificRequest();";
	      List<PatternClass> classes = patternManager.getClasses("Structural", "Class Adapter");
	      assertEquals(3, classes.size());

	      PatternClass adapterClass = classes.get(0);
	      assertEquals("Adapter", adapterClass.getName());
	      assertEquals("Adaptee", adapterClass.getExtendedClass().getName());
	      assertEquals("Target", adapterClass.getImplementedInterface().getName());

	      List<Field> fields = adapterClass.getFields();
	      assertEquals(0, fields.size());
	      
	      

	      List<Method> methods = adapterClass.getMethods();
	      assertEquals(1, methods.size());
	      assertEquals("request", methods.get(0).getName());
	      assertEquals(CODE, methods.get(0).getCode());


	      PatternClass adapteeClass = classes.get(1);
	      assertEquals("Adaptee", adapteeClass.getName());

	      fields = adapteeClass.getFields();
	      assertEquals(0, fields.size());

	      methods = adapteeClass.getMethods();
	      assertEquals(1, methods.size());
	      assertEquals("specificRequest", methods.get(0).getName());
	      
	      PatternClass client = classes.get(2);
	      assertEquals("Client", client.getName());
	      
	      fields = client.getFields();
	      assertEquals(1, fields.size());
	      assertEquals("target", fields.get(0).getName());
	      assertEquals("Target", fields.get(0).getType());     

	      methods = client.getMethods();
	      assertEquals(0, methods.size());
	  }
	  
	  @Test
	  public void testGetClassesBridgePattern() {
		  String CODE = "imp.operationImp();";
	      List<PatternClass> classes = patternManager.getClasses("Structural", "Bridge");
	      assertEquals(4, classes.size());

	     
	      PatternClass abstraction = classes.get(0);
	      assertEquals("Abstraction", abstraction.getName());

	      List<Field> fields = abstraction.getFields();
	      assertEquals(1, fields.size());
	      assertEquals("imp", fields.get(0).getName());
	      assertEquals("Implementor", fields.get(0).getType());

	      
	      List<Method> methods = abstraction.getMethods();
	      assertEquals(1, methods.size());
	      assertEquals("operation", methods.get(0).getName());
	      assertEquals(CODE, methods.get(0).getCode());
	      

	      PatternClass refinedAbstraction = classes.get(1);
	      assertEquals("RefinedAbstraction", refinedAbstraction.getName());
	      assertEquals("Abstraction", refinedAbstraction.getExtendedClass().getName());

	      fields = refinedAbstraction.getFields();
	      assertEquals(0, fields.size());

	      methods = refinedAbstraction.getMethods();
	      assertEquals(0, methods.size());
	      
	      PatternClass concreteImplementorA = classes.get(2);
	      assertEquals("ConcreteImplementorA", concreteImplementorA.getName());
	      assertEquals("Implementor", concreteImplementorA.getImplementedInterface().getName());

	      fields = concreteImplementorA.getFields();
	      assertEquals(0, fields.size());

	      methods = concreteImplementorA.getMethods();
	      assertEquals(1, methods.size());
	      assertEquals("operationImp", methods.get(0).getName());
	      
	      PatternClass concreteImplementorB = classes.get(3);
	      assertEquals("ConcreteImplementorB", concreteImplementorB.getName());
	      assertEquals("Implementor", concreteImplementorB.getImplementedInterface().getName());

	      fields = concreteImplementorB.getFields();
	      assertEquals(0, fields.size());

	      methods = concreteImplementorB.getMethods();
	      assertEquals(1, methods.size());
	      assertEquals("operationImp", methods.get(0).getName());
	  }

	  @Test
	  public void testGetClassesCompositePattern() {
		  List<PatternClass> classes = patternManager.getClasses("Structural", "Composite");
		  assertEquals(2, classes.size());
  
		  PatternClass composite = classes.get(1);
 
		  assertEquals("Composite", composite.getName());
		  assertEquals("Component", composite.getImplementedInterface().getName());

  
		  List<Field> fields = composite.getFields();
		  assertEquals(1, fields.size());
		  assertEquals("children", fields.get(0).getName());
		  assertEquals("Component[]", fields.get(0).getType());
		
		  List<Method> methods = composite.getMethods();
		  assertEquals(4, methods.size());
		  assertEquals("add", methods.get(0).getName());
		  assertEquals("remove", methods.get(1).getName());
		  assertEquals("getChildren", methods.get(2).getName());
		  assertEquals("Component", methods.get(2).getType());
		  assertEquals("int", methods.get(2).getParameters().get(0)[0]);
		  assertEquals("id", methods.get(2).getParameters().get(0)[1]);
		  assertEquals("operation", methods.get(3).getName());
		
		
		  PatternClass leaf = classes.get(0);
		  assertEquals("Leaf", leaf.getName());
		  assertEquals("Component", leaf.getImplementedInterface().getName());
		
		  fields = leaf.getFields();
		  assertEquals(0, fields.size());
		
		  methods = leaf.getMethods();
		  assertEquals(1, methods.size());
		  assertEquals("operation", methods.get(0).getName());
	  }
	  
	  @Test
	  public void testGetClassesDecoratorPattern() {
		  String CODE_DECORATOR = "component.operation();";
		  String CODE_CONCRETE_DECORATORB = "super.operation();";
		  String CODE_CONCRETE_DECORATORA = "super.operation();\n"
		  		+ "						addedBehavior();";
	      List<PatternClass> classes = patternManager.getClasses("Structural", "Decorator");
	      assertEquals(4, classes.size());


	      PatternClass concreteComponent = classes.get(0);
	      assertEquals("ConcreteComponent", concreteComponent.getName());
	      assertEquals("Component", concreteComponent.getImplementedInterface().getName());
	      
	      
	      assertEquals(0, concreteComponent.getFields().size());
	      
	      assertEquals(1, concreteComponent.getMethods().size());
	      assertEquals("operation", concreteComponent.getMethods().get(0).getName());
	 

	      PatternClass decorator = classes.get(1);
	      assertEquals("Decorator", decorator.getName());
	      assertEquals("Component", decorator.getImplementedInterface().getName());
	      
	      assertEquals(1, decorator.getFields().size());
	      assertEquals("component", decorator.getFields().get(0).getName());
	      assertEquals("Component", decorator.getFields().get(0).getType());
	      assertEquals(1, decorator.getMethods().size());
	      assertEquals("operation", decorator.getMethods().get(0).getName());
	      assertEquals(CODE_DECORATOR, decorator.getMethods().get(0).getCode());
	      
	      PatternClass concreteDecoratorA = classes.get(2);
	      assertEquals("ConcreteDecoratorA", concreteDecoratorA.getName());
	      assertEquals("Decorator", concreteDecoratorA.getExtendedClass().getName());
	      
	      assertEquals(0, concreteDecoratorA.getFields().size());
	      assertEquals(2, concreteDecoratorA.getMethods().size());
	      assertEquals("addedBehavior", concreteDecoratorA.getMethods().get(0).getName());
	      assertEquals("operation", concreteDecoratorA.getMethods().get(1).getName());
	      assertEquals(CODE_CONCRETE_DECORATORA, concreteDecoratorA.getMethods().get(1).getCode());
	      PatternClass concreteDecoratorB = classes.get(3);
	      assertEquals("ConcreteDecoratorB", concreteDecoratorB.getName());
	      assertEquals("Decorator", concreteDecoratorB.getExtendedClass().getName());
	      
	      assertEquals(1, concreteDecoratorB.getFields().size());
	      assertEquals("addedState", concreteDecoratorB.getFields().get(0).getName());
	      assertEquals(1, concreteDecoratorB.getMethods().size());
	      assertEquals("operation", concreteDecoratorB.getMethods().get(0).getName());
	      assertEquals(CODE_CONCRETE_DECORATORB, concreteDecoratorB.getMethods().get(0).getCode());
	  }
	  
	  @Test
	  public void testGetClassesFacadePattern() {
		  String CODE = "subsystem.operation();";
	      List<PatternClass> classes = patternManager.getClasses("Structural", "Facade");
	      assertEquals(2, classes.size());

	      PatternClass subsystem1 = classes.get(0);
	      assertEquals("Subsystem1", subsystem1.getName());
	      assertEquals(0, subsystem1.getFields().size());
	      assertEquals(1, subsystem1.getMethods().size());
	      assertEquals("operation", subsystem1.getMethods().get(0).getName());
	      
	      PatternClass facade = classes.get(1);
	      assertEquals("Facade", facade.getName());
	      assertEquals(1, facade.getFields().size());
	      assertEquals("subsystem1", facade.getFields().get(0).getName());
	      assertEquals(1, facade.getMethods().size());
	      assertEquals("subsystemOperation", facade.getMethods().get(0).getName());
	      assertEquals(CODE, facade.getMethods().get(0).getCode());
	  }

	  @Test
	  public void testGetClassesFlyweightPattern() {
		  String CODE = "if (flyweights[key] == null) {\n"
		  		+ "							flyweights[key] = new ConcreteFlyweight();\n"
		  		+ "						}\n"
		  		+ "						return flyweights[key];";
	      List<PatternClass> classes = patternManager.getClasses("Structural", "Flyweight");
	      assertEquals(4, classes.size());

	      PatternClass flyweightClass = classes.get(0);
	      assertEquals("Flyweight", flyweightClass.getName());
	      List<Field> fields = flyweightClass.getFields();
	      assertEquals(1, fields.size());
	      assertEquals("repeatingState", fields.get(0).getName());

	      List<Method> methods = flyweightClass.getMethods();
	      assertEquals(1, methods.size());
	      assertEquals("operation", methods.get(0).getName());
	      assertEquals(1, methods.get(0).getParameters().size());
	      assertEquals("Object", methods.get(0).getParameters().get(0)[0]);
	      assertEquals("extinsicState", methods.get(0).getParameters().get(0)[1]);
	      
	      PatternClass flyweightFactory = classes.get(1);
	      assertEquals("FlyweightFactory", flyweightFactory.getName());
	      fields = flyweightFactory.getFields();
	      assertEquals(1, fields.size());
	      assertEquals("flyweights", fields.get(0).getName());

	      methods = flyweightFactory.getMethods();
	      assertEquals(1, methods.size());
	      assertEquals("getFlyweight", methods.get(0).getName());
	      assertEquals(1, methods.get(0).getParameters().size());
	      assertEquals("int", methods.get(0).getParameters().get(0)[0]);
	      assertEquals("key", methods.get(0).getParameters().get(0)[1]);
	      assertEquals(CODE, methods.get(0).getCode());
	      
	      PatternClass concreteFlyweightClass = classes.get(2);
	      assertEquals("ConcreteFlyweight", concreteFlyweightClass.getName());
	      assertEquals("Flyweight", concreteFlyweightClass.getExtendedClass().getName());
	      fields = concreteFlyweightClass.getFields();
	      assertEquals(1, fields.size());
	      assertEquals("intrinsicState", fields.get(0).getName());

	      methods = concreteFlyweightClass.getMethods();
	      assertEquals(1, methods.size());
	      assertEquals("operation", methods.get(0).getName());
	      assertEquals(1, methods.get(0).getParameters().size());
	      assertEquals("Object", methods.get(0).getParameters().get(0)[0]);
	      assertEquals("extinsicState", methods.get(0).getParameters().get(0)[1]);
	      
	      PatternClass unsharedConcreteFlyweight = classes.get(3);
	      assertEquals("UnsharedConcreteFlyweight", unsharedConcreteFlyweight.getName());
	      assertEquals("Flyweight", unsharedConcreteFlyweight.getExtendedClass().getName());
	      
	      fields = unsharedConcreteFlyweight.getFields();
	      assertEquals(1, fields.size());
	      assertEquals("allState", fields.get(0).getName());

	      methods = unsharedConcreteFlyweight.getMethods();
	      assertEquals(1, methods.size());
	      assertEquals("operation", methods.get(0).getName());
	      assertEquals(1, methods.get(0).getParameters().size());
	      assertEquals("Object", methods.get(0).getParameters().get(0)[0]);
	      assertEquals("extinsicState", methods.get(0).getParameters().get(0)[1]);
	  }
	  
	  @Test
	  public void testGetClassesProxyPattern() {
		  String CODE = "if (checkAccess()){\n"
		  		+ "							realService.request();\n"
		  		+ "						}";
		  
	      List<PatternClass> classes = patternManager.getClasses("Structural", "Proxy");
	      assertEquals(2, classes.size());


	      PatternClass realSubjectClass = classes.get(1);
	      assertEquals("RealSubject", realSubjectClass.getName());
	      assertEquals("Subject", realSubjectClass.getImplementedInterface().getName());
	      
	      List<Field> fields = realSubjectClass.getFields();
	      assertEquals(0, fields.size());

	      List<Method> methods = realSubjectClass.getMethods();
	      assertEquals(1, methods.size());
	      assertEquals("request", methods.get(0).getName());

	      PatternClass proxyClass = classes.get(0);
	      assertEquals("Proxy", proxyClass.getName());
	      assertEquals("Subject", proxyClass.getImplementedInterface().getName());
	      
	      fields = proxyClass.getFields();
	      assertEquals(1, fields.size());
	      assertEquals("realSubject", fields.get(0).getName());

	      methods = proxyClass.getMethods();
	      assertEquals(2, methods.size());
	      assertEquals("checkAccess", methods.get(0).getName());
	      assertEquals("boolean", methods.get(0).getType());
	      assertEquals("return false;", methods.get(0).getCode());
	      assertEquals("request", methods.get(1).getName());
	      assertEquals(CODE, methods.get(1).getCode());
	  }

	

}
