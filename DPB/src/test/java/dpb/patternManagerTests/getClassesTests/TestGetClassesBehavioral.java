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
		  String SETTER_CODE = "this.successor = successor1;";
		  
	      List<PatternClass> classes = patternManager.getClasses("Behavioral", "Chain of Responsibility");
	      assertEquals(3, classes.size());

	      PatternClass patternClass1 = classes.get(0);
	      assertEquals("Handler", patternClass1.getName());
	      assertTrue(patternClass1.isAbstract());

	      List<Field> fields1 = patternClass1.getFields();
	      assertEquals(1, fields1.size());
	      assertEquals("successor", fields1.get(0).getName());
	      assertEquals("Handler", fields1.get(0).getType());

	      List<Method> methods1 = patternClass1.getMethods();
	      assertEquals(2, methods1.size());
	      assertEquals("setSuccessor", methods1.get(0).getName());
	      assertEquals(SETTER_CODE, methods1.get(0).getCode());
	      assertEquals("Handler", methods1.get(0).getParameters().get(0)[0]);
	      assertEquals("successor1", methods1.get(0).getParameters().get(0)[1]);
	      assertEquals("handleRequest", methods1.get(1).getName());
	      assertTrue(methods1.get(1).isAbstract());
	      
	      PatternClass concreteHandler1 = classes.get(1);
	      assertEquals("ConcreteHandler1", concreteHandler1.getName());
	      assertEquals("Handler", concreteHandler1.getExtendedClass().getName());

	      fields1 = concreteHandler1.getFields();
	      assertEquals(0, fields1.size());


	      methods1 = concreteHandler1.getMethods();
	      assertEquals(1, methods1.size());
	      assertEquals("handleRequest", methods1.get(0).getName());
	      
	      PatternClass concreteHandler2 = classes.get(2);
	      assertEquals("Handler", concreteHandler2.getExtendedClass().getName());
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
	      assertEquals("Command", patternClass2.getImplementedInterface().getName());

	      List<Field> fields2 = patternClass2.getFields();
	      assertEquals(2, fields2.size());
	      assertEquals("state", fields2.get(0).getName());
	      assertEquals("Object", fields2.get(0).getType());
	      assertEquals("receiver", fields2.get(1).getName());
	      assertEquals("Receiver", fields2.get(1).getType());
	      
	 

	      List<Method> methods2 = patternClass2.getMethods();
	      assertEquals(1, methods2.size());
	      assertEquals("execute", methods2.get(0).getName());
	      assertEquals("receiver.action();", methods2.get(0).getCode());

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
	      assertEquals("Command", fields3.get(0).getType());

	      List<Method> methods4 = patternClass3.getMethods();
	      assertEquals(2, methods4.size());
	      assertEquals("setCommand", methods4.get(0).getName());
	      assertEquals("Command", methods4.get(0).getParameters().get(0)[0]);
	      assertEquals("command", methods4.get(0).getParameters().get(0)[1]);
	      assertEquals("this.command = command;", methods4.get(0).getCode());
	      assertEquals("executeCommand", methods4.get(1).getName());
	      assertEquals("command.execute();", methods4.get(1).getCode());
	  }
	  
	  @Test
	  public void testGetClassesInterpreterPattern() {
	      List<PatternClass> classes = patternManager.getClasses("Behavioral", "Interpreter");
	      assertEquals(4, classes.size());

	      PatternClass terminalExpression = classes.get(0);
	      assertEquals("TerminalExpression", terminalExpression.getName());
	      assertEquals("AbstractExpression", terminalExpression.getImplementedInterface().getName());

	      List<Field> fields = terminalExpression.getFields();
	      assertEquals(0, fields.size());

	      List<Method> methods = terminalExpression.getMethods();
	      assertEquals(1, methods.size());
	      assertEquals("interpret", methods.get(0).getName());
	      assertEquals("Context", methods.get(0).getParameters().get(0)[0]);
	      assertEquals("context", methods.get(0).getParameters().get(0)[1]);

	      PatternClass nonTerminalExpression = classes.get(1);
	      assertEquals("NonTerminalExpression", nonTerminalExpression.getName());
	      assertEquals("AbstractExpression", nonTerminalExpression.getImplementedInterface().getName());

	      fields = nonTerminalExpression.getFields();
	      assertEquals(1, fields.size());
	      assertEquals("expression", fields.get(0).getName());
	      assertEquals("AbstractExpression", fields.get(0).getType());
	      

	      methods = nonTerminalExpression.getMethods();
	      assertEquals(1, methods.size());
	      assertEquals("interpret", methods.get(0).getName());
	      assertEquals("Context", methods.get(0).getParameters().get(0)[0]);
	      assertEquals("context", methods.get(0).getParameters().get(0)[1]);
	      PatternClass context = classes.get(2);
	      assertEquals("Context", context.getName());

	      fields = context.getFields();
	      assertEquals(0, fields.size());
	      

	      methods = context.getMethods();
	      assertEquals(0, methods.size());
	      
	      PatternClass client = classes.get(3);
	      assertEquals("Client", client.getName());

	      fields = client.getFields();
	      assertEquals(2, fields.size());
	      assertEquals("context", fields.get(0).getName());
	      assertEquals("Context", fields.get(0).getType());
	      assertEquals("expr", fields.get(1).getName());
	      assertEquals("AbstractExpression", fields.get(1).getType());
	      

	      methods = client.getMethods();
	      assertEquals(0, methods.size());
	      
	  }
	  
	  @Test
	  public void testGetClassesIteratorPattern() {
	      List<PatternClass> classes = patternManager.getClasses("Behavioral", "Iterator");
	      assertEquals(2, classes.size());

	      PatternClass iterator = classes.get(0);
	      assertEquals("ConcreteIterator", iterator.getName());
	      assertEquals("Iterator", iterator.getImplementedInterface().getName());

	      List<Field> fields = iterator.getFields();
	      assertEquals(2, fields.size());
	      assertEquals("collection", fields.get(0).getName());
	      assertEquals("ConcreteCollection", fields.get(0).getType());
	      assertEquals("iterationState", fields.get(1).getName());
	      assertEquals("Object", fields.get(1).getType());


	      List<Method> methods = iterator.getMethods();
	      assertEquals(3, methods.size());
	      assertEquals("ConcreteIterator", methods.get(0).getName());
	      assertEquals("ConcreteCollection", methods.get(0).getParameters().get(0)[0]);
	      assertEquals("c", methods.get(0).getParameters().get(0)[1]);
	      assertEquals("this.collection = c;", methods.get(0).getCode());
	      assertEquals("hasNext", methods.get(1).getName());
	      assertEquals("boolean", methods.get(1).getType());
	      assertEquals("return false;", methods.get(1).getCode());
	      assertEquals("getNext", methods.get(2).getName());
	      assertEquals("Object", methods.get(2).getType());
	      

	      PatternClass concreteCollection = classes.get(1);
	      assertEquals("ConcreteCollection", concreteCollection.getName());
	      assertEquals("Collection", concreteCollection.getImplementedInterface().getName());
	      
	      fields = concreteCollection.getFields();
	      assertEquals(0, fields.size());
	      

	      methods = concreteCollection.getMethods();
	      assertEquals(1, methods.size());
	      assertEquals("createIterator", methods.get(0).getName());
	      assertEquals("Iterator", methods.get(0).getType());
	  }
	  
	  @Test
	  public void testGetClassesMediatorPattern() {
	      List<PatternClass> classes = patternManager.getClasses("Behavioral", "Mediator");
	      assertEquals(4, classes.size());

	      PatternClass patternClass = classes.get(0);
	      assertEquals("ConcreteMediator", patternClass.getName());
	      assertEquals("Mediator", patternClass.getImplementedInterface().getName());
	      
	      List<Field> fields = patternClass.getFields();
	      assertEquals(2, fields.size());
	      assertEquals("concreteColleague1", fields.get(0).getName());
	      assertEquals("ConcreteColleague1", fields.get(0).getType());
	      assertEquals("concreteColleague2", fields.get(1).getName());
	      assertEquals("ConcreteColleague2", fields.get(1).getType());
	      
	      List<Method> methods = patternClass.getMethods();
	      assertEquals(1, methods.size());
	      assertEquals("mediate", methods.get(0).getName());
	      assertEquals("Colleague", methods.get(0).getParameters().get(0)[0]);
	      assertEquals("colleague", methods.get(0).getParameters().get(0)[1]);
	      
	      

	      patternClass = classes.get(1);
	      assertEquals("Colleague", patternClass.getName());

	      fields = patternClass.getFields();
	      assertEquals(1, fields.size());
	      assertEquals("mediator", fields.get(0).getName());
	      assertEquals("Mediator", fields.get(0).getType());

	      methods = patternClass.getMethods();
	      assertEquals(1, methods.size());
	      assertEquals("operation", methods.get(0).getName());
	      
	      patternClass = classes.get(2);
	      assertEquals("ConcreteColleague1", patternClass.getName());

	      fields = patternClass.getFields();
	      assertEquals(0, fields.size());

	      methods = patternClass.getMethods();
	      assertEquals(0, methods.size());
//	      assertEquals("operation", methods.get(0).getName());
	      
	      patternClass = classes.get(3);
	      assertEquals("ConcreteColleague2", patternClass.getName());

	      fields = patternClass.getFields();
	      assertEquals(0, fields.size());

	      methods = patternClass.getMethods();
	      assertEquals(0, methods.size());
//	      assertEquals("operation", methods.get(0).getName());
	      
	  }
	  
	  @Test
	  public void testGetClassesMementoPattern() {
	      List<PatternClass> classes = patternManager.getClasses("Behavioral", "Memento");
	      assertEquals(3, classes.size());

	      PatternClass patternClass = classes.get(1);
	      assertEquals("Memento", patternClass.getName());
	      

	      List<Field> fields = patternClass.getFields();
	      assertEquals(1, fields.size());
	      assertEquals("state", fields.get(0).getName());
	      assertEquals("Object", fields.get(0).getType());

	      List<Method> methods = patternClass.getMethods();
	      assertEquals(3, methods.size());
	      assertEquals("Memento", methods.get(0).getName());
	      assertEquals("Object", methods.get(0).getParameters().get(0)[0]);
	      assertEquals("state", methods.get(0).getParameters().get(0)[1]);
	      assertEquals("this.state = state;", methods.get(0).getCode());
	      assertEquals("getState", methods.get(1).getName());
	      assertEquals("Object", methods.get(1).getType());
	      assertEquals("return state;", methods.get(1).getCode());
	      assertEquals("setState", methods.get(2).getName());
	      assertEquals("Object", methods.get(2).getParameters().get(0)[0]);
	      assertEquals("state", methods.get(2).getParameters().get(0)[1]);
	      assertEquals("this.state = state;", methods.get(2).getCode());

	      patternClass = classes.get(0);
	      assertEquals("Originator", patternClass.getName());

	      fields = patternClass.getFields();
	      assertEquals(1, fields.size());
	      assertEquals("state", fields.get(0).getName());
	      assertEquals("Object", fields.get(0).getType());

	      methods = patternClass.getMethods();
	      assertEquals(2, methods.size());
	      assertEquals("setMemento", methods.get(0).getName());
	      assertEquals("Memento", methods.get(0).getParameters().get(0)[0]);
	      assertEquals("m", methods.get(0).getParameters().get(0)[1]);
	      assertEquals("state = m.getState();", methods.get(0).getCode());
	      assertEquals("createMemento", methods.get(1).getName());
	      assertEquals("Memento", methods.get(1).getType());
	      assertEquals("return new Memento(state);", methods.get(1).getCode());

	      patternClass = classes.get(2);
	      assertEquals("Caretaker", patternClass.getName());

	      fields = patternClass.getFields();
	      assertEquals(1, fields.size());
	      assertEquals("memento", fields.get(0).getName());
	      assertEquals("Memento", fields.get(0).getType());

	      methods = patternClass.getMethods();
	      assertEquals(0, methods.size());
	  }
	  
	  @Test
	  public void testGetClassesObserverPattern() {
	      String NOTIFY_CODE = "for (Observer o: observers) {\n"
	      		+ "							o.update();\n"
	      		+ "						}";
	      
	      String UPDATE_CODE = "observerState = subject.getState();";
		  List<PatternClass> classes = patternManager.getClasses("Behavioral", "Observer");
	      assertEquals(3, classes.size());
	     

	      PatternClass patternClass2 = classes.get(0);
	      assertEquals("Subject", patternClass2.getName());
	      assertEquals(1, patternClass2.getFields().size());
	      assertEquals("observers", patternClass2.getFields().get(0).getName());
	      assertEquals("Observer[]", patternClass2.getFields().get(0).getType());
	      assertEquals(3, patternClass2.getMethods().size());
	      assertEquals("attach", patternClass2.getMethods().get(0).getName());
	      assertEquals("Observer", patternClass2.getMethods().get(0).getParameters().get(0)[0]);
	      assertEquals("o", patternClass2.getMethods().get(0).getParameters().get(0)[1]);
	      assertEquals("detach", patternClass2.getMethods().get(1).getName());
	      assertEquals("Observer", patternClass2.getMethods().get(1).getParameters().get(0)[0]);
	      assertEquals("o", patternClass2.getMethods().get(1).getParameters().get(0)[1]);
	      assertEquals("notifyObservers", patternClass2.getMethods().get(2).getName());
	      assertEquals(NOTIFY_CODE, patternClass2.getMethods().get(2).getCode());

	      PatternClass patternClass3 = classes.get(1);
	      assertEquals("ConcreteSubject", patternClass3.getName());
	      assertEquals("Subject", patternClass3.getExtendedClass().getName());
	      assertEquals(1, patternClass3.getFields().size());
	      assertEquals("subjectState", patternClass3.getFields().get(0).getName());
	      assertEquals("Object", patternClass3.getFields().get(0).getType());
	      assertEquals(2, patternClass3.getMethods().size());
	      assertEquals("getState", patternClass3.getMethods().get(0).getName());
	      assertEquals("Object", patternClass3.getMethods().get(0).getType());
	      assertEquals("return subjectState;", patternClass3.getMethods().get(0).getCode());
	      assertEquals("setState", patternClass3.getMethods().get(1).getName());
	      assertEquals("Object", patternClass3.getMethods().get(1).getParameters().get(0)[0]);
	      assertEquals("subjectState", patternClass3.getMethods().get(1).getParameters().get(0)[1]);
	      assertEquals("this.subjectState = subjectState;", patternClass3.getMethods().get(1).getCode());
	      

	      PatternClass patternClass4 = classes.get(2);
	      assertEquals("ConcreteObserver", patternClass4.getName());
	      assertEquals("Observer", patternClass4.getImplementedInterface().getName());
	      assertEquals(2, patternClass4.getFields().size());
	      assertEquals("observerState", patternClass4.getFields().get(0).getName());
	      assertEquals("subject", patternClass4.getFields().get(1).getName());
	      assertEquals("ConcreteSubject", patternClass4.getFields().get(1).getType());
	      assertEquals(1, patternClass4.getMethods().size());
	      assertEquals("update", patternClass4.getMethods().get(0).getName());
	      assertEquals(UPDATE_CODE, patternClass4.getMethods().get(0).getCode());
	  }
	  
	
	  
	  @Test
	  public void testGetClassesStatePattern() {
	      List<PatternClass> classes = patternManager.getClasses("Behavioral", "State");
	      assertEquals(3, classes.size());

	      PatternClass patternClass1 = classes.get(0);
	      assertEquals("Context", patternClass1.getName());

	      List<Field> fields1 = patternClass1.getFields();
	      assertEquals(1, fields1.size());
	      assertEquals("state", fields1.get(0).getName());
	      assertEquals("State", fields1.get(0).getType());

	      List<Method> methods1 = patternClass1.getMethods();
	      assertEquals(1, methods1.size());
	      assertEquals("request", methods1.get(0).getName());
	      assertEquals("state.handle();", methods1.get(0).getCode());
	      

	      PatternClass patternClass2 = classes.get(1);
	      assertEquals("ConcreteStateA", patternClass2.getName());
	      assertEquals("State", patternClass2.getImplementedInterface().getName());

	      List<Field> fields2 = patternClass2.getFields();
	      assertEquals(0, fields2.size());

	      List<Method> methods2 = patternClass2.getMethods();
	      assertEquals(1, methods2.size());
	      assertEquals("handle", methods2.get(0).getName());

	      PatternClass patternClass3 = classes.get(2);
	      assertEquals("ConcreteStateB", patternClass3.getName());
	      assertEquals("State", patternClass3.getImplementedInterface().getName());

	      List<Field> fields3 = patternClass3.getFields();
	      assertEquals(0, fields3.size());

	      List<Method> methods3 = patternClass3.getMethods();
	      assertEquals(1, methods3.size());
	      assertEquals("handle", methods3.get(0).getName());
	  }
	  

	  
	  @Test
	  public void testGetClassesForStrategy() {
	      List<PatternClass> classes = patternManager.getClasses("Behavioral", "Strategy");
	      assertEquals(4, classes.size());
	      
	      PatternClass concreteStrategyA = classes.get(0);
	      assertEquals("ConcreteStrategyA", concreteStrategyA.getName());
	      assertEquals("Strategy", concreteStrategyA.getImplementedInterface().getName());

	      List<Field> concreteStrategyAFields = concreteStrategyA.getFields();
	      assertEquals(0, concreteStrategyAFields.size());

	      List<Method> concreteStrategyAMethods = concreteStrategyA.getMethods();
	      assertEquals(1, concreteStrategyAMethods.size());
	      assertEquals("algorithmInterface", concreteStrategyAMethods.get(0).getName());
	      
	      PatternClass concreteStrategyB = classes.get(1);
	      assertEquals("ConcreteStrategyB", concreteStrategyB.getName());
	      assertEquals("Strategy", concreteStrategyB.getImplementedInterface().getName());

	      List<Field> concreteStrategyBFields = concreteStrategyB.getFields();
	      assertEquals(0, concreteStrategyBFields.size());

	      List<Method> concreteStrategyBMethods = concreteStrategyB.getMethods();
	      assertEquals(1, concreteStrategyBMethods.size());
	      assertEquals("algorithmInterface", concreteStrategyBMethods.get(0).getName());
	      
	      PatternClass concreteStrategyC = classes.get(2);
	      assertEquals("ConcreteStrategyC", concreteStrategyC.getName());
	      assertEquals("Strategy", concreteStrategyC.getImplementedInterface().getName());

	      List<Field> concreteStrategyCFields = concreteStrategyC.getFields();
	      assertEquals(0, concreteStrategyCFields.size());

	      List<Method> concreteStrategyCMethods = concreteStrategyC.getMethods();
	      assertEquals(1, concreteStrategyCMethods.size());
	      assertEquals("algorithmInterface", concreteStrategyCMethods.get(0).getName());
	      
	      PatternClass context = classes.get(3);
	      assertEquals("Context", context.getName());

	      List<Field> contextFields = context.getFields();
	      assertEquals(1, contextFields.size());
	      assertEquals("strategy", contextFields.get(0).getName());
	      assertEquals("Strategy", contextFields.get(0).getType());

	      List<Method> contextMethods = context.getMethods();
	      assertEquals(1, contextMethods.size());
	      assertEquals("contextInterface", contextMethods.get(0).getName());


	  }


	  @Test
	  public void testGetClassesForTemplateMethod() {
		  String TEMPLATE_METHOD = "primitiveOperation1();\n"
		  		+ "						primitiveOperation2();\n"
		  		+ "						primitiveOperation3();";
	      List<PatternClass> classes = patternManager.getClasses("Behavioral", "Template Method");
	      assertEquals(3, classes.size());

	      PatternClass abstractClass = classes.get(0);
	      assertEquals("AbstractClass", abstractClass.getName());
	      assertTrue(abstractClass.isAbstract());

	      List<Field> abstractClassFields = abstractClass.getFields();
	      assertEquals(0, abstractClassFields.size());

	      List<Method> abstractClassMethods = abstractClass.getMethods();
	      assertEquals(4, abstractClassMethods.size());
	      assertEquals("templateMethod", abstractClassMethods.get(0).getName());
	      assertEquals(TEMPLATE_METHOD, abstractClassMethods.get(0).getCode());
	      assertEquals("primitiveOperation1", abstractClassMethods.get(1).getName());
	      assertEquals("primitiveOperation2", abstractClassMethods.get(2).getName());
	      assertEquals("primitiveOperation3", abstractClassMethods.get(3).getName());

	      PatternClass concreteClass1 = classes.get(1);
	      assertEquals("ConcreteClassA", concreteClass1.getName());
	      assertEquals("AbstractClass", concreteClass1.getExtendedClass().getName());

	      List<Field> concreteClass1Fields = concreteClass1.getFields();
	      assertEquals(0, concreteClass1Fields.size());

	      List<Method> concreteClass1Methods = concreteClass1.getMethods();
	      assertEquals(2, concreteClass1Methods.size());
	      assertEquals("primitiveOperation2", concreteClass1Methods.get(0).getName());
	      assertEquals("primitiveOperation3", concreteClass1Methods.get(1).getName());
//	      assertEquals("primitiveOperation3", concreteClass1Methods.get(2).getName());

	      PatternClass concreteClass2 = classes.get(2);
	      assertEquals("ConcreteClassB", concreteClass2.getName());
	      assertEquals("AbstractClass", concreteClass2.getExtendedClass().getName());

	      List<Field> concreteClass2Fields = concreteClass2.getFields();
	      assertEquals(0, concreteClass2Fields.size());

	      List<Method> concreteClass2Methods = concreteClass2.getMethods();
	      assertEquals(2, concreteClass2Methods.size());
	      assertEquals("primitiveOperation2", concreteClass2Methods.get(0).getName());
	      assertEquals("primitiveOperation3", concreteClass2Methods.get(1).getName());
//	      assertEquals("primitiveOperation2", concreteClass2Methods.get(2).getName());
	  }
	  
	  @Test
	  public void testGetClassesVisitor() {
	      List<PatternClass> classes = patternManager.getClasses("Behavioral", "Visitor");
	      assertEquals(6, classes.size());

	      PatternClass patternClass = classes.get(0);
	      assertEquals("ConcreteVisitor1", patternClass.getName());
	      assertEquals("Visitor", patternClass.getImplementedInterface().getName());

	      List<Field> fields = patternClass.getFields();
	      assertEquals(0, fields.size());

	      List<Method> methods = patternClass.getMethods();
	      assertEquals(2, methods.size());
	      assertEquals("visitConcreteElementA", methods.get(0).getName());
	      assertEquals("ConcreteElementA", methods.get(0).getParameters().get(0)[0]);
	      assertEquals("concreteElementA", methods.get(0).getParameters().get(0)[1]);
	      assertEquals("visitConcreteElementB", methods.get(1).getName());
	      assertEquals("ConcreteElementB", methods.get(1).getParameters().get(0)[0]);
	      assertEquals("concreteElementB", methods.get(1).getParameters().get(0)[1]);

	      patternClass = classes.get(1);
	      assertEquals("ConcreteVisitor2", patternClass.getName());
	      assertEquals("Visitor", patternClass.getImplementedInterface().getName());

	      fields = patternClass.getFields();
	      assertEquals(0, fields.size());

	      methods = patternClass.getMethods();
	      assertEquals(2, methods.size());
	      assertEquals("visitConcreteElementA", methods.get(0).getName());
	      assertEquals("ConcreteElementA", methods.get(0).getParameters().get(0)[0]);
	      assertEquals("concreteElementA", methods.get(0).getParameters().get(0)[1]);
	      assertEquals("visitConcreteElementB", methods.get(1).getName());
	      assertEquals("ConcreteElementB", methods.get(1).getParameters().get(0)[0]);
	      assertEquals("concreteElementB", methods.get(1).getParameters().get(0)[1]);
	      
	      patternClass = classes.get(2);
	      assertEquals("ConcreteElementA", patternClass.getName());
	      assertEquals("Element", patternClass.getImplementedInterface().getName());

	      fields = patternClass.getFields();
	      assertEquals(0, fields.size());

	      methods = patternClass.getMethods();
	      assertEquals(2, methods.size());
	      assertEquals("accept", methods.get(0).getName());
	      assertEquals("Visitor", methods.get(0).getParameters().get(0)[0]);
	      assertEquals("visitor", methods.get(0).getParameters().get(0)[1]);
	      assertEquals("operationA", methods.get(1).getName());
	      
	      patternClass = classes.get(3);
	      assertEquals("ConcreteElementB", patternClass.getName());

	      fields = patternClass.getFields();
	      assertEquals(0, fields.size());

	      methods = patternClass.getMethods();
	      assertEquals(2, methods.size());
	      assertEquals("accept", methods.get(0).getName());
	      assertEquals("Visitor", methods.get(0).getParameters().get(0)[0]);
	      assertEquals("visitor", methods.get(0).getParameters().get(0)[1]);
	      assertEquals("operationB", methods.get(1).getName());
	      
	      patternClass = classes.get(4);
	      assertEquals("ObjectStructure", patternClass.getName());

	      fields = patternClass.getFields();
	      assertEquals(1, fields.size());
	      assertEquals("element", fields.get(0).getName());
	      assertEquals("Element", fields.get(0).getType());
	      
	      methods = patternClass.getMethods();
	      assertEquals(0, methods.size());
	      
	      patternClass = classes.get(5);
	      assertEquals("Client", patternClass.getName());

	      fields = patternClass.getFields();
	      assertEquals(2, fields.size());
	      assertEquals("objectStructure", fields.get(0).getName());
	      assertEquals("ObjectStructure", fields.get(0).getType());
	      assertEquals("visitor", fields.get(1).getName());
	      assertEquals("Visitor", fields.get(1).getType());
	      
	      methods = patternClass.getMethods();
	      assertEquals(0, methods.size());
	      
	  }


}
