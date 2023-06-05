package dpb;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import dpb.patternGeneratorTests.GenerateTests;
import dpb.patternManagerTests.PatternManagerTests;
import dpb.patternManagerTests.getClassesTests.GetClassTests;
import dpb.patternManagerTests.getInterfacesTest.GetInterfacesTests;

@RunWith(Suite.class)
@SuiteClasses({PatternManagerTests.class, GetInterfacesTests.class, GetClassTests.class, GenerateTests.class})
public class AllTests {

}
