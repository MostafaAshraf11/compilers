package csen1002.main.task4;

import java.util.*;

/**
 * Write your info here
 * 
 * @name Mostafa Ashraf
 * @id 49-3737
 * @labNumber 06
 */

public class CfgEpsUnitElim {

	public static String StringInput;
	public static HashMap<Character, HashSet<String>> rules;
	public static String[] varString;
	/**
	 * Constructs a Context Free Grammar
	 * 
	 * @param cfg A formatted string representation of the CFG. The string
	 *             representation follows the one in the task description
	 */
	public CfgEpsUnitElim(String cfg) {
		// TODO Auto-generated constructor stub
		rules = new HashMap<>();
		StringInput = cfg;
			
        String[] cfgParts = cfg.split("#");
        varString = cfgParts[0].split(";");
        String[] ruleString = cfgParts[2].split(";");
        
        for (int i = 0; i < ruleString.length; i++) {
            String[] rulesStringParts = ruleString[i].split("/");

            char rulesKey = rulesStringParts[0].charAt(0);

            HashSet<String> rulesValues = new HashSet<>();
            String[] valueArray = rulesStringParts[1].split(",");
            for (String value : valueArray) {
            	rulesValues.add(value);
            }

            rules.put(rulesKey, rulesValues);
        }
        
        
	}

	/**
	 * @return Returns a formatted string representation of the CFG. The string
	 *         representation follows the one in the task description
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub

		String result = "";
		for (String var : varString) {
		    result += var + "/";
		    HashSet<String> finalRules = rules.get(var.charAt(0));
		    if (finalRules != null) {
		        List<String> finalSortedRules = new ArrayList<>(finalRules);
		        Collections.sort(finalSortedRules);
		        int count = finalSortedRules.size();
		        for (String rule : finalSortedRules) {
		            result += rule;
		            if (count > 1) {
		                result += ",";
		            }
		            count--;
		        }
		    }
		    result += ";";
		}
		if (result.length() > 0 && result.charAt(result.length() - 1) == ';') {
		    result = result.substring(0, result.length() - 1);
		}

        String[] cfgParts = StringInput.split("#");
        String varElem = cfgParts[0];
        String termString = cfgParts[1];
        
		return varElem+"#" +termString +"#" + result;
	}

	/**
	 * Eliminates Epsilon Rules from the grammar
	 */
	public void eliminateEpsilonRules() {
		// TODO Auto-generated method stub
		 boolean epsilonExists = epsilonExists(rules);
	       HashSet<Character> ancestors = new HashSet<>();
	        while(epsilonExists == true) {
	        	char variable = variableWEpsilon(rules);
	            removeEpsilon(rules,variable,ancestors);
	            epsilonExists = epsilonExists(rules);    
	        }
	}

	/**
	 * Eliminates Unit Rules from the grammar
	 */
	public void eliminateUnitRules() {
		// TODO Auto-generated method stub
		 boolean unitExists = unitsExists(rules,varString);
		 HashMap<Character, HashSet<String>> ancestor = new HashMap<>();
		 
	        for (Character key : rules.keySet()) {
	            // Add the key to ancestor HashMap with an empty HashSet<String>
	            ancestor.put(key, new HashSet<>());
	        }
	        
	        while(unitExists == true) {
	        	unitsRemoval(rules,varString,ancestor);
	        	unitExists = unitsExists(rules,varString);  
	        }
	}
	
	public static char variableWEpsilon(HashMap<Character, HashSet<String>> rules) {
		for (Map.Entry<Character, HashSet<String>> entry : rules.entrySet()) {
			 HashSet<String> values = entry.getValue();
			 for (String value : values) {
				 if (value.contains("e")) {
					 char key = entry.getKey();
					 rules.get(key).remove(value);
					 return key;
				 }
			 }
		}
		return 'n';
	}
	
	public static boolean epsilonExists(HashMap<Character, HashSet<String>> rules) {
		for (Map.Entry<Character, HashSet<String>> entry : rules.entrySet()) {
			 HashSet<String> values = entry.getValue();
			 for (String value : values) {
				 if (value.contains("e")) {
					 return true;
				 }
			 }
		}
		return false;
		
	}
	
	public static void unitsRemoval(HashMap<Character, HashSet<String>> rules,String[] varString,HashMap<Character, HashSet<String>> ancestors) {
		HashMap<Character, HashSet<String>> newRules = new HashMap<>();
		HashMap<Character, HashSet<String>> oldRulesRemove = new HashMap<>();
		
		for (Map.Entry<Character, HashSet<String>> entry : rules.entrySet()) {
	        HashSet<String> updatedValues = new HashSet<>();
	        HashSet<String> oldValuesRemove = new HashSet<>();
			HashSet<String> values = entry.getValue();
			 for (String value : values) {
				 if(value.length()==1) {
					 char firstChar = value.charAt(0);
					 if (Character.isUpperCase(firstChar)) {
						 for(int i=0;i<varString.length;i++) {
							 if(value.equals(varString[i])) { 
								 updatedValues.addAll(rules.get(firstChar));
								 oldValuesRemove.add(value);
								 ancestors.get(entry.getKey()).add(value);
							 }
						 }
					 }
				 }
			 }
			 //ancesctors.put(entry.getKey(), values);
			 newRules.put(entry.getKey(), updatedValues);
			 oldRulesRemove.put(entry.getKey(), oldValuesRemove);
		}
		
        for (Map.Entry<Character, HashSet<String>> entry : oldRulesRemove.entrySet()) {
            char oldRulesKey = entry.getKey();
            HashSet<String> oldValues = entry.getValue();
            if (rules.containsKey(oldRulesKey)) {
                HashSet<String> originalValues = rules.get(oldRulesKey);
                originalValues.removeAll(oldValues); 
            }
        }      
		
        for (Map.Entry<Character, HashSet<String>> entry : newRules.entrySet()) {
            char newRulesKey = entry.getKey();
            HashSet<String> newValues = entry.getValue();
            if (rules.containsKey(newRulesKey)) {
                HashSet<String> originalValues = rules.get(newRulesKey);
                originalValues.addAll(newValues); 
            }
        }
        
        for (Map.Entry<Character, HashSet<String>> entry : ancestors.entrySet()) {
            char ancestorkey = entry.getKey();
            HashSet<String> oldValues = entry.getValue();
            if (rules.containsKey(ancestorkey)) {
                HashSet<String> originalValues = rules.get(ancestorkey);
                originalValues.removeAll(oldValues); 
            }
        } 
        
        

	}
	
	public static Boolean unitsExists(HashMap<Character, HashSet<String>> rules,String[] varString) {		
		for (Map.Entry<Character, HashSet<String>> entry : rules.entrySet()) {   
			HashSet<String> values = entry.getValue();
			 for (String value : values) {
				 if(value.length()==1) {
					 char firstChar = value.charAt(0);
					 if (Character.isUpperCase(firstChar)) {
						 for(int i=0;i<varString.length;i++) {
							 if(value.equals(varString[i])) { 
								return true;
							 }
						 }
					 }

				 }
			 }

		}
		return false;	
	}
	
	
	
	public static void removeEpsilon(HashMap<Character, HashSet<String>> rules, char key,HashSet<Character> ancestors) {
		String keyString= String.valueOf(key);
		HashMap<Character, HashSet<String>> newRules = new HashMap<>();

		for (Map.Entry<Character, HashSet<String>> entry : rules.entrySet()) {
	        HashSet<String> values = entry.getValue();
	        HashSet<String> updatedValues = new HashSet<>();
			 char entryKey = entry.getKey();
			 for (String value : values) {
				 if (value.contains(keyString)) {
					 if(value.equals(keyString)){
						 if(!ancestors.contains(key)) {
							 updatedValues.add("e");
						 }
					 }
					 else {
						 
						 HashSet<String> result = new HashSet<String>();
						 int countOccurrence = 0;
						 for (char c : value.toCharArray()) {
					            if (c == key) {
					            	countOccurrence++;
					            }
					        }
						 int substringsCount = (int) Math.pow(2, countOccurrence) - 1;
					        for (int i = 0; i < substringsCount; i++) {
					            String removedEpsVar = Integer.toBinaryString(i); 
					            while (removedEpsVar.length() < countOccurrence) {
					            	removedEpsVar = "0" + removedEpsVar;
					            }
					            
					            String substring = "";
					            int j = 0;
					            for (char c : value.toCharArray()) {
					                if (c == key) {
					                    if (removedEpsVar.charAt(j) == '1') {
					                    	substring += c;

					                    }
					                    j++;
					                }
					                else {
					                	substring += c;
					                }
					            }
					            if(substring.toString().equals("")) {
									 if(!ancestors.contains(key)) {
										 result.add("e");
									 }
					            	  
					            }else {
					            	  result.add(substring.toString());
					            }
					        }
					        updatedValues.addAll(result);
					 }		 
				 }
			 }
			 newRules.put(entryKey, updatedValues);
		}
		ancestors.add(key);
        for (Map.Entry<Character, HashSet<String>> entry : newRules.entrySet()) {
            char newRulesKey = entry.getKey();
            HashSet<String> newValues = entry.getValue();
            if (rules.containsKey(newRulesKey)) {
                HashSet<String> originalValues = rules.get(newRulesKey);
                originalValues.addAll(newValues); 
            }
        }
	
	}

}
