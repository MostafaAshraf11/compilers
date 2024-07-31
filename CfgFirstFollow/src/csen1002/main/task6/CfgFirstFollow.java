package csen1002.main.task6;

import java.util.*;

/**
 * Write your info here
 * 
 * @name Mostafa Ashraf 
 * @id 49-3737
 * @labNumber 06
 */

public class CfgFirstFollow {

	HashMap<Character, HashSet<String>> rules;
	HashMap<Character, HashSet<String>> firstRules;
	String[] varString;
	HashMap<Character, HashSet<String>> newFollowRules;
	HashMap<Character, HashSet<String>> followRules;
	/**
	 * Constructs a Context Free Grammar
	 * 
	 * @param cfg A formatted string representation of the CFG. The string
	 *            representation follows the one in the task description
	 */
	public CfgFirstFollow(String cfg) {
		// TODO Auto-generated constructor stub
		rules = new HashMap<Character, HashSet<String>>();
		String StringInput = cfg;
			
        String[] cfgParts = cfg.split("#");
        varString = cfgParts[0].split(";");
        String[] ruleString = cfgParts[2].split(";");
        firstRules = new HashMap<Character, HashSet<String>>();
        followRules = new HashMap<Character, HashSet<String>>();
        newFollowRules = new HashMap<Character, HashSet<String>>();
        
        for (int i = 0; i < ruleString.length; i++) {
            String[] rulesStringParts = ruleString[i].split("/");

            char rulesKey = rulesStringParts[0].charAt(0);

            HashSet<String> rulesValues = new HashSet<>();
            String[] valueArray = rulesStringParts[1].split(",");
            for (String value : valueArray) {
            	rulesValues.add(value);
            }

            HashSet<String> firstRulevalues = new HashSet<>();
            HashSet<String> followRulevalues = new HashSet<>();
            
            if(i==0) {
            	followRulevalues.add("$");
                rules.put(rulesKey, rulesValues);
                firstRules.put(rulesKey,firstRulevalues);
            	followRules.put(rulesKey,followRulevalues);
            	newFollowRules.put(rulesKey,followRulevalues);
            }
            else {
            	followRules.put(rulesKey,followRulevalues);
            	newFollowRules.put(rulesKey,followRulevalues);
                rules.put(rulesKey, rulesValues);
                firstRules.put(rulesKey,firstRulevalues);
            }
        }
        
        String[] terminalString = cfgParts[1].split(";");
        
        for (String terminal : terminalString) {
            HashSet<String> firstSet = new HashSet<>();
            firstSet.add(terminal);
            char c = terminal.charAt(0);
            firstRules.put(c, firstSet);
            followRules.put(c, firstSet);
            newFollowRules.put(c, firstSet);
        }
        HashSet<String> eSet = new HashSet<>();
        eSet.add("e");
        firstRules.put('e',eSet );

        followRules.put('e',eSet );
        newFollowRules.put('e',eSet);
        
        


	}

	/**
	 * Calculates the First Set of each variable in the CFG.
	 * 
	 * @return A string representation of the First of each variable in the CFG,
	 *         formatted as specified in the task description.
	 */
	public String first() {
		// TODO Auto-generated method stub
        boolean change = true;

        HashMap<Character, HashSet<String>> newFirstRules = new HashMap<Character, HashSet<String>>();
        while(change) {
            change = false;
            
            //HashMap<Character, HashSet<String>> newFirstRules = new HashMap<Character, HashSet<String>>();
            for (char variable : rules.keySet()) {
            	HashSet<String> firstSetVariable = new HashSet<>();
            	firstSetVariable.addAll(firstRules.get(variable));
                for (String production : rules.get(variable)) {
                    boolean containsEpsilon = true;
                    
                	if(production.charAt(0)=='e') {
                		containsEpsilon = true;
                	}
                	else {
	                	for (int i = 0; i < production.length(); i++) {	
	                        HashSet<String> firstOfProduction = firstRules.getOrDefault(production.charAt(i), new HashSet<>());
	                        if (!firstOfProduction.contains("e")) {
	                            containsEpsilon = false;
	                            break;
	                        	}         
	                		}	
	                	}
                	
                    if (containsEpsilon && !firstSetVariable.contains("e")) {
                        
                    	firstSetVariable.add("e");

                        change = true;
                    }
                    
                	for (int i = 0; i < production.length(); i++) {
                		boolean epsilonIntersect = true;
                		for (int j = 0; j <= i-1 ; j++) {
                			
	                        HashSet<String> firstOfProduction = firstRules.getOrDefault(production.charAt(j), new HashSet<>());
	                        if (!firstOfProduction.contains("e")) {
	                        	epsilonIntersect = false;
	                            break;    
	                        }   
                		}
                        if (epsilonIntersect) {
                        	HashSet<String> firstOfProduction = new HashSet<>(firstRules.getOrDefault(production.charAt(i), new HashSet<>()));
                        	if(firstOfProduction.contains("e") && production.charAt(i)!='e') {
                        		firstOfProduction.remove("e");
                        	}
                        	if(!firstSetVariable.containsAll(firstOfProduction)) {
                        		

                        		change = true;
                        		firstSetVariable.addAll(firstOfProduction);
                        	}
                            
                        }
                		
                		
                	}
                }
                if (newFirstRules.containsKey(variable)) {
                	newFirstRules.get(variable).addAll(firstSetVariable);
                }
                else {
                	newFirstRules.put(variable, firstSetVariable);
                }
            }
            for (char key : firstRules.keySet()) {
                if (newFirstRules.containsKey(key)) {
                	firstRules.get(key).addAll(newFirstRules.get(key));
                }
            }
        }
        
        StringBuilder firstResult = new StringBuilder();

        for (String variable : varString) {
            char key = variable.charAt(0); 
            firstResult.append(key).append('/');

            HashSet<String> values = firstRules.get(key);
            ArrayList<String> sortedValues = new ArrayList<>(values);
            Collections.sort(sortedValues); 

            for (String value : sortedValues) {
            	firstResult.append(value);
            }

            firstResult.append(';');
        }

        
        if (firstResult.length() > 0) {
        	firstResult.setLength(firstResult.length() - 1);
        }

        String firstResults = firstResult.toString();

        
        
		return firstResults;
	}

	/**
	 * Calculates the Follow Set of each variable in the CFG.
	 * 
	 * @return A string representation of the Follow of each variable in the CFG,
	 *         formatted as specified in the task description.
	 */
	public String follow() {
		// TODO Auto-generated method stub
		first();
        boolean change = true;
        while(change) {
            change = false;
            for (char variable : rules.keySet()) {
            
            	for (String production : rules.get(variable)) {
            		for(int i=0;i<production.length();i++) {
            			if(!Character.isLowerCase(production.charAt(i))){
            				if(i+1==production.length() && Character.isUpperCase(production.charAt(i))) {
            					
            					if(!followRules.get(production.charAt(i)).containsAll(followRules.get(variable))) {
                    				newFollowRules.get(production.charAt(i)).addAll(followRules.get(variable));
                    				change = true;
                    			}
            				}
            				else {
		            			String productionSubString = production.substring(i+1);
		                		HashSet<String> betaFirst = new HashSet<>();
		                		betaFirst = getBetaFirst(productionSubString,firstRules);
		                		HashSet<String> betaFirsteElem = new HashSet<>();
		                		betaFirsteElem.addAll(betaFirst);
		                		if(betaFirsteElem.contains("e")) {
		                			betaFirsteElem.remove("e");
		                		}
		                		if(!betaFirsteElem.isEmpty()) {
		                			if(!newFollowRules.get(production.charAt(i)).containsAll(betaFirsteElem)){
		                				newFollowRules.get(production.charAt(i)).addAll(betaFirsteElem);
		                				change = true;
		                			}
		                		}
		                		
		                		if(betaFirst.contains("e")) {
		                			if(!newFollowRules.get(production.charAt(i)).containsAll(followRules.get(variable))) {
		                				newFollowRules.get(production.charAt(i)).addAll(followRules.get(variable));
		                				change = true;
		                			}
		                		}
		            		}
            			}
            		}
        		
            	}
            }
            for (char key : followRules.keySet()) {
                if (newFollowRules.containsKey(key)) {
                	followRules.get(key).addAll(newFollowRules.get(key));
                }
            }
        }

        
        StringBuilder followResult = new StringBuilder();
        for (String variable : varString) {
            char key = variable.charAt(0);
            followResult.append(key).append('/');

            
            HashSet<String> values = followRules.get(key);
            ArrayList<String> sortedValues = new ArrayList<>(values);
            Collections.sort(sortedValues); 

            for (String value : sortedValues) {
            	followResult.append(value);
            }

            followResult.append(';');
        }

        if (followResult.length() > 0) {
        	followResult.setLength(followResult.length() - 1);
        }

        String followResults = followResult.toString();
        
		return followResults;
	}
	
	
    public static HashSet<String> getBetaFirst(String input,HashMap<Character, HashSet<String>> firstRules) {
        HashSet<String> betaFirst = new HashSet<>();
        
        // Iterate over each character of the input string
        for (int i = 0; i < input.length(); i++) {
        	HashSet<String> newbetaFirst = new HashSet<>();
        	newbetaFirst.addAll(firstRules.get(input.charAt(i)));
        	if(!newbetaFirst.contains("e")) {
        		betaFirst.addAll(newbetaFirst);        		
        		return betaFirst;
        	}
        	else {
        		betaFirst.addAll(newbetaFirst);
        		if(i!=input.length()-1) {
        			betaFirst.remove("e");
        		}
        		
        	}
        }
        
        return betaFirst;
    }
	
}


        
