package dpb.controller;

import java.util.List;

import dpb.model.Field;
import dpb.model.Method;
import dpb.model.PatternClass;
import dpb.model.PatternElement;
import dpb.model.PatternInterface;
import dpb.wizards.setupWizard.FieldsSetup;


public interface IPatternManager {
	public String[] getPatternCategories();
	public String[] getPatternsOfCategory(String category);
	
	public List<PatternClass> getClasses(String pattern);
	public List<PatternInterface> getInterfaces();
	public void updateClassName(String newName, PatternElement element);
	public void updateFieldName(String newName, Field field, PatternClass patternClass);
	public void updateMethodName(String newName, Method method);
}
