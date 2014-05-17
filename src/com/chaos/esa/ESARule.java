package com.chaos.esa;

import java.util.ArrayList;

public class ESARule {

	public String[] conditions = null; 
	public String conclusion = null; 
	private Boolean isExist; 
	
	public ESARule(String conclusionString, String... conditionStrings){
		conditions = conditionStrings;
		conclusion = conclusionString;
		print();
	}
	
	/**
	 * 输入一组条件，判断该规则内的所有条件都满足
	 * @param conditionStrings
	 * @return
	 */
	public Boolean isMatch(String... conditionStrings) {
		for (String myCondition : conditions) {
			isExist = false;
			for (String inputCondition : conditionStrings) {
				if (myCondition.equalsIgnoreCase(inputCondition)) {
					isExist = true;
					break;
				}
			}
			if (!isExist) {
				return false;
			}
		}
		return true;
	}
	
	public Boolean isMatch(ArrayList<String> list) {
		int size = list.size();
		for (String myCondition : conditions) {
			isExist = false;
			for (int i = 0; i < size; i++) {
				if (myCondition.equalsIgnoreCase(list.get(i))) {
					isExist = true;
					break;
				}
			}
			if (!isExist) {
				return false;
			}
		}
		return true;
	}
	
	public void print() {
		System.out.println("Condition: ");
		for (String condition : conditions) 
			System.out.print("\t" + condition);
		System.out.println("\nConclusion: " + conclusion + "\n");
	}
}
