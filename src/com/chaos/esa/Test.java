package com.chaos.esa;

import java.util.ArrayList;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ESARuleSet esaRuleSet = new ESARuleSet();
		ESARuleSet.init();

		System.out.println(ESARuleSet.ratiocinate("has_hair", "give_milk",
				"eat_meat", "pointed_teeth", "claws", "forward_eyes", "black_stripes", "tawny_color"));
		System.out.println(ESARuleSet.ratiocinate("fly", "lay_eggs", "fly_well"));
		ArrayList<String> database = new ArrayList<String>(); 
		database.add("fly"); 
		database.add("lay_eggs"); 
		database.add("fly_well"); 
		System.out.println(ESARuleSet.ratiocinate(database));
	}

}
