package dpb.patternManagerTests;
import org.junit.*;
import org.xml.sax.SAXException;

import dpb.controller.PatternManager;
import dpb.exceptions.NoPropertiesException;
import dpb.model.Field;
import dpb.model.Method;
import dpb.model.PatternClass;
import dpb.model.PatternElement;
import dpb.model.PatternInterface;
import dpb.model.Property;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

import javax.xml.parsers.ParserConfigurationException;

public class PatternManagerTest {
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
  public void testGetPatternCategories() {
    String[] expectedCategories = { "Creational", "Structural", "Behavioral"};
    assertArrayEquals(expectedCategories, patternManager.getPatternCategories());
  }

  @Test
  public void testGetPatternsOfCategory() {
    String[] expectedPatterns = {"Abstract Factory", "Builder","Factory Method", "Prototype", "Singleton"};
    assertArrayEquals(expectedPatterns, patternManager.getPatternsOfCategory("Creational"));
    String[] structuralPatterns = {"Object Adapter", "Class Adapter", "Bridge", "Composite", "Decorator", "Facade", "Flyweight", "Proxy"};
    assertArrayEquals(structuralPatterns, patternManager.getPatternsOfCategory("Structural"));
    String[] behavioralPatterns = {"Chain of Responsibility", "Command", "Interpreter", "Iterator", "Mediator", "Memento", "Observer", "State", "Strategy", "Template Method", "Visitor"};
    assertArrayEquals(behavioralPatterns, patternManager.getPatternsOfCategory("Behavioral"));
  }
  

  @Test
  public void testGetInterfaces() {
	patternManager.getClasses("Creational", "Builder");
    List<PatternInterface> interfaces = patternManager.getInterfaces();
    assertEquals(1, interfaces.size());
  }

  @Test
  public void testUpdatePatternElementName() {
    PatternElement element = new PatternElement("Factory", "product");
    patternManager.updatePatternElementName("newProduct", element);
    assertEquals("newProduct", element.getName());
  }

  @Test
  public void testUpdateFieldName() {
    PatternClass patternClass = new PatternClass("Factory", "public");
    Field field = new Field("product", "private", "", true);
    ArrayList<Field> fields = new ArrayList<>();
    fields.add(field);
    patternClass.setFields(fields);;

    patternManager.updateFieldName("newProduct", field, patternClass);
    assertEquals("newProduct", field.getName());
  }

  @Test
  public void testUpdateMethodName() {
    Method method = new Method("create", "public", "", false, false, null, false);
    patternManager.updateMethodName("newCreate", method);
    assertEquals("newCreate", method.getName());
  }
  
  
  @Test
  public void testGetProperties() throws NoPropertiesException {
    String pattern = "Prototype";
    Property[] properties = patternManager.getProperties(pattern);
    assertEquals(1, properties.length);

    Property property1 = properties[0];
    assertEquals("Prototype", property1.getImplement());
    assertEquals("ConcretePrototypeAnnotation", property1.getAnnotation());

  }

  @Test(expected = NoPropertiesException.class)
  public void testGetPropertiesWithNoPropertiesException() throws NoPropertiesException {
    String pattern = "Singleton";
 	patternManager.getProperties(pattern);
	
  }
}
