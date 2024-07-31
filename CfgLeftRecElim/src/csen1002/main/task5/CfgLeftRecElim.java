package csen1002.main.task5;

import java.util.*;

/**
 * Write your info here
 * 
 * @name Mostafa Ashraf
 * @id 49-3737
 * @labNumber 06
 */

public class CfgLeftRecElim {

	ArrayList<Rules> grammer;
	ArrayList<Rules> newGrammer;
	ArrayList<String> ancestors;
	String[] cfgParts;
	/**
	 * Constructs a Context Free Grammar
	 * 
	 * @param cfg A formatted string representation of the CFG. The string
	 *            representation follows the one in the task description
	 *            
	 */
	public CfgLeftRecElim(String cfg) {
		grammer = new ArrayList<>();
    	newGrammer = new ArrayList<>();
    	ancestors = new ArrayList<>();
        
        cfgParts = cfg.split("#");
        String[] varString = cfgParts[0].split(";");
        String[] ruleString = cfgParts[2].split(";");

        for (int i = 0; i < ruleString.length; i++) {
            String[] rulesStringParts = ruleString[i].split("/");

            String rulesKey = rulesStringParts[0];

            ArrayList<String> rulesValues = new ArrayList<>();
            String[] valueArray = rulesStringParts[1].split(",");
            for (String value : valueArray) {
                rulesValues.add(value);
            }

            grammer.add(new Rules(rulesKey, rulesValues));
        }
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return Returns a formatted string representation of the CFG. The string
	 *         representation follows the one in the task description
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub

      String newGramVarialble = "";
      String newGramRules ="";
      
      for(int i=0;i<newGrammer.size();i++) {
    	  newGramVarialble = newGramVarialble+ newGrammer.get(i).getVariable()+";";
    	  newGramRules= newGramRules +newGrammer.get(i).toString();
      }
      
      if(!newGramVarialble.isEmpty()) {
    	  newGramVarialble = newGramVarialble.substring(0, newGramVarialble.length() - 1);
      }
      if(!newGramRules.isEmpty()) {
    	  newGramRules = newGramRules.substring(0, newGramRules.length() - 1);
      }
      
      String result = "";
      
      result = cfgParts[0] + ";" + newGramVarialble +"#" +cfgParts[1] +"#";
      
      for(int i=0;i<grammer.size();i++) { 
    	  result = result+grammer.get(i).toString(); 
      }
      
      result = result+newGramRules;

      //System.out.println(output); 
      
      
		return result;
	}

	/**
	 * Eliminates Left Recursion from the grammar
	 */
	public void eliminateLeftRecursion() {
		for(int i=0;i<grammer.size();i++) {  
	    	 String exists = ancestorExists(grammer.get(i),ancestors);
 
	    	 while(exists != "") {
	    		 removeAncestor(grammer.get(i),exists,grammer);
	    		 exists = ancestorExists(grammer.get(i),ancestors);
	    	 }
	    	 ancestors.add(grammer.get(i).getVariable());
	    	  
	    	 boolean leftRecursionExist = leftRecursionExists(grammer.get(i));
	    	 if(leftRecursionExist) {
	    		 leftRecursionElem(grammer.get(i),newGrammer);
	    	 }
	    	
	      }
		// TODO Auto-generated method stub
	}
	
    
    
	
	public static String ancestorExists(Rules rule,ArrayList<String> ancestors) {
		for(int i=0;i<rule.getRightHandSide().size();i++) {
			String side = rule.getRightHandSide().get(i);
			String exists = String.valueOf(side.charAt(0));
			
			for(int j=0;j<ancestors.size();j++) {
				if(ancestors.get(j).equals(exists)) {
					return exists+"";
				}
			}
		}
		return "";
	}
	
	public static void removeAncestor(Rules rule, String anc, ArrayList<Rules> grammar) {
	    ArrayList<String> newRightHandSide = new ArrayList<>(); 
	    for (String side : rule.getRightHandSide()) {
	        String exists = String.valueOf(side.charAt(0));
	        if (anc.equals(exists)) {
	            side = side.substring(1);
	            for (Rules grammarRule : grammar) {
	                if (grammarRule.getVariable().equals(anc)) {
	                    for (String right : grammarRule.getRightHandSide()) {
	                        String newRight = right + side;
	                        newRightHandSide.add(newRight);
	                    }
	                }
	            }
	        } else {
	        	newRightHandSide.add(side); 
	        }
	    }
	    rule.setRightHandSide(newRightHandSide);
	}
	
	public static void leftRecursionElem(Rules rule,ArrayList<Rules> newGrammer) {
		 ArrayList<String> alpha = new ArrayList<>();
		 ArrayList<String> beta = new ArrayList<>(); 
		 for (int i =0; i < rule.getRightHandSide().size(); i++) {
			 String sideRight = rule.getRightHandSide().get(i);
			 String sideVariable = String.valueOf(sideRight.charAt(0));
			 if(sideVariable.equals(rule.getVariable())) {
				 sideRight = sideRight.substring(1);
				 sideRight = sideRight+rule.getVariable()+"'";
				 alpha.add(sideRight);
			 }
			 else {
				 sideRight = sideRight+rule.getVariable()+"'";
				 beta.add(sideRight);
			 }
		 }
		 
		 rule.setRightHandSide(beta);
		 alpha.add("e");
		 String newVariable = rule.getVariable()+"'";
		 newGrammer.add(new Rules(newVariable, alpha));
	}
	
	public static boolean leftRecursionExists(Rules rule) {
		 for (int i =0; i < rule.getRightHandSide().size(); i++) {
			 String sideRight = rule.getRightHandSide().get(i);
			 String sideVariable = String.valueOf(sideRight.charAt(0));
			 if(sideVariable.equals(rule.getVariable())) {
				 return true;
			 }

		 }
		 return false;
		 }
		 
	
}





class Rules {
    private String variable;
    private ArrayList<String> rightHandSide;

    public Rules(String variable, ArrayList<String> rightHandSide) {
        this.variable = variable;
        this.rightHandSide = rightHandSide;
    }

    public String getVariable() {
        return variable;
    }

    public ArrayList<String> getRightHandSide() {
        return rightHandSide;
    }
    
    
    public void setRightHandSide(ArrayList<String> newRightHandSide) {
        this.rightHandSide = newRightHandSide;
    }
    
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
        sb.append(variable).append("/");
        for (int i = 0; i < rightHandSide.size(); i++) {
            sb.append(rightHandSide.get(i));
            if (i < rightHandSide.size() - 1) {
                sb.append(",");
            }
        }
        sb.append(";");
        return sb.toString();
    }
}
