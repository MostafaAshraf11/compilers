package csen1002.main.task8;

import java.util.*;
/**
 * Write your info here
 * 
 * @name Mostafa Ashraf
 * @id 49-3737
 * @labNumber 06
 */

public class CfgLl1Parser {

	/**
	 * Constructs a Context Free Grammar
	 * 
	 * @param cfg A formatted string representation of the CFG, the First sets of
	 *            each right-hand side, and the Follow sets of each variable. The
	 *            string representation follows the one in the task description
	 */
	HashMap<String, HashMap<String, String>> ll ;
	
	public CfgLl1Parser(String input) {
		// TODO Auto-generated constructor stub
		//input = "S;A;B# a;b;c;d#S/AaS,d;A/BbBaSc,e;B/e# S/ab,d;A/b,e;B/e#\r\nS/$c;A/a;B/ab";
		String[] sections = input.split("#");
		
		String[] terminaslString = sections[0].split(";");
		String[] ruleStrings = sections[2].split(";");
        String[] firstSetStrings = sections[3].split(";");
        String[] followSetStrings = sections[4].split(";");
        
        Table t = new Table();
        
        ll = new HashMap<>();
        
//        for (String terminal : terminaslString) {
//            // Initialize the outer map with empty inner maps
//        	ll.put(terminal, new HashMap<>());
//        }
        
        for(int i=0;i<ruleStrings.length;i++) {
        	ArrayList<String> rhs = new ArrayList<String>();
        	ArrayList<String> first = new ArrayList<String>();
        	
        	String[] ruleStrings2 = ruleStrings[i].split("/");
        	String[] firstSetStrings2 = firstSetStrings[i].split("/");
        	String[] followSetStrings2 = followSetStrings[i].split("/");
        	
        	String lhs = ruleStrings2[0];
        	String follow = followSetStrings2[1];
        	
        	String[] ruleStrings3 = ruleStrings2[1].split(",");
        	String[] firstSetStrings3 = firstSetStrings2[1].split(",");
        	
        	for(int j=0;j<ruleStrings3.length;j++) {
        		rhs.add(ruleStrings3[j]);
        		first.add(firstSetStrings3[j]);
        	}
        	Rules R = new Rules(lhs,rhs,first,follow);
        	t.rules.add(R);
        }
        
        
        for(Rules r : t.rules) {
        	HashMap<String, String> innerMap = new HashMap<String,String>();
        	 for (int i = 0; i < r.firstSet.size(); i++) {
        	        String firstSymbol = r.firstSet.get(i);
        	        String production = r.rhs.get(i);

        	        if (!firstSymbol.equals("e")) {
        	        	
        	        	 for (int j = 0; j < firstSymbol.length(); j++) {
        	        		 String firstelemt = String.valueOf(firstSymbol.charAt(j));
        	 	        	innerMap.put(firstelemt, production);
        	 	        	ll.put(r.lhs,innerMap);
        	        	 }
 	            
        	        } else {
        	            // Add entries for each symbol in the follow set
        	            for (int j = 0; j < r.followSet.length(); j++) {
        	                String followSymbol = String.valueOf(r.followSet.charAt(j));
        	                innerMap.put(followSymbol, firstSymbol);

        	                
        	                ll.put(r.lhs,innerMap);
        	            }
        	        }
        	    }
        }
        
   
	}

	/**
	 * @param input The string to be parsed by the LL(1) CFG.
	 * 
	 * @return A string encoding a left-most derivation.
	 */
	
	public String parse(String input) {
		// TODO Auto-generated method stub
        //String inputParse = "abadcad$";
        Stack<String> ss = new Stack<String>();
        ss.push("$");
        ss.push("S");
        String lastDerivation= "S";
        String output = "S";
        int i =0;
        input = input + "$";
        while(i<input.length()) {
        	//System.out.println("----------------------------------------------");
        	String elem = ss.pop();
        	 
        	char element = elem.charAt(0);
        	//System.out.println("element pushed :" + element);
        	//System.out.println("input char :" + input.charAt(i));
        	if(Character.isUpperCase(element)) {
                if (ll.containsKey(elem) && ll.get(elem).containsKey(String.valueOf(input.charAt(i)))) {
                    String derive = ll.get(elem).get(String.valueOf(input.charAt(i)));
                  //  System.out.println("derived : " + derive);
                    if(!derive.equals("e")) {
                   // System.out.println("Last Derivation Before : " + lastDerivation);
                    lastDerivation =lastDerivation.substring(1);
                    lastDerivation = derive + lastDerivation;
                   // System.out.println("Last Derivation After : " + lastDerivation);
                    output = output+";"+ input.substring(0, i) + lastDerivation ;
                    for (int j = derive.length() - 1; j >= 0; j--) {
                        ss.push(String.valueOf(derive.charAt(j)));
                    	}
                    }
                    else {
                    	lastDerivation =lastDerivation.substring(1);
                    	//lastDerivation = derive + lastDerivation;
                    	//System.out.println("Last Derivation epsilon : " + lastDerivation);
                        output = output+";"+ input.substring(0, i) + lastDerivation ;                    }
                }
                else {
                	//System.out.println("derived doesnt exists" );
                	output = output+";ERROR";
        			break;
                }
        	}
        	else {
        		if(element == input.charAt(i)) {
        			i++;
        			if(lastDerivation.length()>=1) {
        				lastDerivation =lastDerivation.substring(1);
        			}
        			//System.out.println("elements matched" );
        		}
        		else {
        			output = output+";ERROR";
        			
        			break;
        			
        		}
        	}
        }
       // System.out.println(output);
        
       
		return output;
	}
	

        
	

}


class Rules {
     String lhs;  
     ArrayList<String> rhs; 
     ArrayList<String> firstSet; 
     String followSet;

    public Rules(String lhs, ArrayList<String> rhs,ArrayList<String> firstSet,String followSet) {
        this.lhs = lhs;
        this.rhs = rhs;
        this.firstSet = firstSet;
        this.followSet = followSet;
    }
    

    
}

class Table{
	ArrayList<Rules> rules;
	
	public Table() {
		this.rules = new ArrayList();
	}
	
	
	
}



