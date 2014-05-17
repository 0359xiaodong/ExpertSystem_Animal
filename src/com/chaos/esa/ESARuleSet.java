/**
 * 
 */
package com.chaos.esa;

import java.util.ArrayList;

/**
 * @author chaos 规则集
 */
public class ESARuleSet {

	public static ArrayList<ESARule> ESARuleList = new ArrayList<ESARule>();
	public static ArrayList<String> database = null;
	public static WordPair[] basicWordPairs = {
			new WordPair("has_hair", "有毛发"), new WordPair("give_milk", "有奶"),
			new WordPair("feathers", "有羽毛"), new WordPair("fly", "会飞"),
			new WordPair("lay_eggs", "会下蛋"), new WordPair("eat_meat", "吃肉"),
			new WordPair("pointed_teeth", "有犬牙"), new WordPair("claws", "有爪子"),
			new WordPair("forward_eyes", "眼盯前方"),
			new WordPair("ungulate", "有蹄"), new WordPair("chew_cud", "反刍"),
			new WordPair("tawny_color", "黄褐色"),
			new WordPair("dark_spots", "暗斑点"),
			new WordPair("black_stripes", "黑色条纹"),
			new WordPair("long_neck", "长脖子"), new WordPair("wingless", "不会飞"),
			new WordPair("long_legs", "长腿"),
			new WordPair("black&white_color", "黑白两色"),
			new WordPair("can_swim", "会游泳"), new WordPair("fly_well", "善飞") };
	public static WordPair[] conclusionWordPairs = {
			new WordPair("cheetah", "猎豹"), new WordPair("tiger", "老虎"),
			new WordPair("giraffe", "长颈鹿"), new WordPair("zebra", "斑马"),
			new WordPair("ostrich", "鸵鸟"), new WordPair("penguin", "企鹅"),
			new WordPair("albatross", "信天翁") };

	public static void init() {
		ESARuleList.add(new ESARule("mammal", "has_hair"));
		ESARuleList.add(new ESARule("mammal", "give_milk"));
		ESARuleList.add(new ESARule("bird", "feathers"));
		ESARuleList.add(new ESARule("bird", "fly", "lay_eggs"));
		ESARuleList.add(new ESARule("carnivore", "eat_meat"));
		ESARuleList.add(new ESARule("carnivore", "pointed_teeth", "claws",
				"forward_eyes"));
		ESARuleList.add(new ESARule("hooves", "mammal", "ungulate"));
		ESARuleList.add(new ESARule("hooves", "mammal", "chew_cud"));
		ESARuleList.add(new ESARule("cheetah", "mammal", "carnivore",
				"tawny_color", "dark_spots"));
		ESARuleList.add(new ESARule("tiger", "mammal", "carnivore",
				"tawny_color", "black_stripes"));
		ESARuleList.add(new ESARule("giraffe", "hooves", "long_neck",
				"long_legs", "dark_spots"));
		ESARuleList.add(new ESARule("zebra", "hooves", "black_stripes"));
		ESARuleList.add(new ESARule("ostrich", "bird", "wingless", "long_neck",
				"long_legs", "black&white_color"));
		ESARuleList.add(new ESARule("penguin", "bird", "can_swim", "wingless",
				"black&white_color"));
		ESARuleList.add(new ESARule("albatross", "bird", "fly_well"));
	}

	public static String ratiocinate(String... conditionStrings) {
		database = new ArrayList<String>();
		for (String conditionString : conditionStrings)
			database.add(conditionString);

		return ratiocinate(database); 
	}
	
	public static String ratiocinate(ArrayList<String> database) {
		int databaseSize;
		do {
			databaseSize = database.size();

			for (int i = 0; i < ESARuleList.size(); i++) {
				if (ESARuleList.get(i).isMatch(database)) {
					// database.add(ESARuleList.get(i).conclusion);
					addData(database, ESARuleList.get(i).conclusion);
					System.out.println(ESARuleList.get(i).conclusion);
				}
			}

		} while (database.size() > databaseSize);

//		return (isRatiocinated(database.get(database.size() - 1))) ? database.get(database.size() - 1) : "没有这样的动物";
		return getConclusion(database.get(database.size() - 1));

		// return "Not Found";
	}

	private static void addData(ArrayList<String> list, String data) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equalsIgnoreCase(data))
				return;
		}
		list.add(data);
	}

	@SuppressWarnings("unused")
	private static Boolean isRatiocinated(String conclusion) {
		for (int i = 0; i < conclusionWordPairs.length; i++) {
			if (conclusion.equalsIgnoreCase(conclusionWordPairs[i].key)) {
				return true;
			}
		}
		return false;
	}
	
	private static String getConclusion(String conclusion) {
		for (int i = 0; i < conclusionWordPairs.length; i++) {
			if (conclusion.equalsIgnoreCase(conclusionWordPairs[i].key)) {
				return conclusionWordPairs[i].word;
			}
		}
		return "没有这样的动物";
	}
	
	private static String key2Word(String key) {
		for (int i = 0; i < basicWordPairs.length; i++) {
			if (key.equalsIgnoreCase(basicWordPairs[i].key)) 
				return basicWordPairs[i].word; 
		}
		return ""; 
	}
	
	public static String database2String(ArrayList<String> database) {
		String string = ""; 
		for (int i = 0; i < database.size(); i++) {
			string += key2Word(database.get(i)) + "\t";
		}
		return string; 
	}
}
