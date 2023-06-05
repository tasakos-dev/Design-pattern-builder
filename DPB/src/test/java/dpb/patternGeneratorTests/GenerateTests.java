package dpb.patternGeneratorTests;


import static org.junit.Assert.assertEquals;
import org.eclipse.jdt.core.IPackageFragment;

import org.junit.Test;

import dpb.controller.PatternGenerator;
import dpb.controller.PatternManager;


public class GenerateTests {
    @Test
    public void testGenerate() throws Exception {
    	String SINGLETON_CODE = "package test;\n"
    			+ "\n" 
    			+ "import creational.singleton.SingletonAnnotation;\n"
        		+ "\n"
        		+ "@SingletonAnnotation\n"
        		+ "public class Singleton{\n"
        		+ "\n"
        		+ "	private static Singleton instance;\n"
        		+ "\n"
        		+ "\n"
        		+ "	private  Singleton(){\n"
        		+ "		// TODO Auto-generated block\n"
        		+ "\n"
        		+ "	}\n"
        		+ "	public  static Object getInstance(){\n"
        		+ "		// TODO Auto-generated block\n"
        		+ "		if (instance == null) {\n"
        		+ "			instance = new Singleton();\n"
        		+ "		}\n"
        		+ "		return instance;\n"
        		+ "	}\n"
        		+ "}";

    	IPackageFragment packageFragment = new MockPackageFragment("test");
    	PatternGenerator generator = new MockClassGenerator();
    	generator.setSelectedPackage(packageFragment);
    	
    	generator.generate(PatternManager.getInstance().getClasses("Creational", "Singleton").get(0));
 
    	assertEquals("Singleton.java", ((MockPackageFragment) packageFragment).getArg0());
        assertEquals(SINGLETON_CODE, ((MockPackageFragment) packageFragment).getArg1());

    }


}
