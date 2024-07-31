package csen1002.main.task1;
import java.util.*;
/**
 * Write your info here
 * 
 * @name Mostafa Ashraf Aboelenien
 * @id 49-3737
 * @labNumber 06
 */

public class RegExToNfa {

	/**
	 * Constructs an NFA corresponding to a regular expression based on Thompson's
	 * construction
	 * 
	 * @param input The alphabet and the regular expression in postfix notation for
	 *              which the NFA is to be constructed
	 */
    String alphabet;
    String regEx;
    NFA finalNFA;
	
	public RegExToNfa(String input) {
		// TODO Auto-generated constructor stub
		String[] Reg = input.split("#");
		 alphabet = Reg[0];
		String RegEx = Reg[1];
		
		//System.out.println(RegEx);
		
		int number = 0;
		
Stack<NFA> stack = new Stack<>();
		
		for(int i = 0 ; i < RegEx.length(); i++) { 
		    char c = RegEx.charAt(i); 
		    
		    if(c=='e') {
		      String startState = ""+number;
		      String finalState = ""+(number+1);
		      String transitionAndDestination = "e"+","+(number+1);
		      
		      States sState =new States(startState);
		      States fState =new States(finalState);
		      
		      sState.addTransition(transitionAndDestination);
		      NFA n = new NFA(startState,finalState);
		      n.addState(sState);
		      n.addState(fState);
		      
		      number+=2;
		      stack.push(n);
		    }
		    else if(c=='.') {
		    	NFA n2 = stack.pop();
		    	NFA n1 = stack.pop();
				for(int j=0;j<n2.statesList.size();j++) {
					
					States state2 = n2.statesList.get(j);
					if(state2.id.equals(n2.startState)) {
						
						for(int k=0;k<n1.statesList.size();k++) {
							
							States state1 = n1.statesList.get(k);
							if(state1.id.equals(n1.finalState)) {
								
								for(int l =0;l<state2.transition.size();l++) {
									String transition = state2.transition.get(l);
									state1.addTransition(transition);
								}
								n2.removeState(state2);
							}
						}
					}
				}
				
				NFA newNFA = new NFA(n1.getStartState(),n2.getFinalState());
						
				for(int j=0;j<n1.statesList.size();j++) {
					States state = n1.statesList.get(j);
					newNFA.addState(state);
				}
				
				for(int j=0;j<n2.statesList.size();j++) {
					States state = n2.statesList.get(j);
					newNFA.addState(state);
				}
		    	stack.push(newNFA);
		    }
		    else if(c=='|') {
		    	NFA n2 = stack.pop();
		    	NFA n1 = stack.pop();
		    	
		    	String startState = ""+number;
		    	
			    String finalState = ""+(number+1)+"";
			    
				States sState =new States(startState);
				States fState =new States(finalState);
			    
				String transitionStart1 = "e"+","+(n1.getStartState());
				String transitionStart2 = "e"+","+(n2.getStartState());	
				String transitionEnd = "e"+","+(fState.id);
				
				for(int j=0;j<n1.statesList.size();j++) {
					States state1 = n1.statesList.get(j);
					if(state1.id.equals(n1.finalState)) {
						state1.addTransition(transitionEnd);
					}
				}
				
				for(int j=0;j<n2.statesList.size();j++) {
					States state2 = n2.statesList.get(j);
					if(state2.id.equals(n2.finalState)) {
						state2.addTransition(transitionEnd);
					}
				}
				
				sState.addTransition(transitionStart1);
				sState.addTransition(transitionStart2);
			
				
		    	NFA newNFa = new NFA(startState,finalState);
		    	newNFa.addState(sState);
		    	newNFa.addState(fState);
		    	
				for(int j=0;j<n1.statesList.size();j++) {
					States state = n1.statesList.get(j);
					newNFa.addState(state);
				}
				
				for(int j=0;j<n2.statesList.size();j++) {
					States state = n2.statesList.get(j);
					newNFa.addState(state);
				}
		    	number+=2;
		    	stack.push(newNFa);
		    }
		    else if(c=='*') {
		    	NFA n1 = stack.pop();
		    	States oldStartState = n1.getStartStateState();
		    	States oldFinalState = n1.getFinalStateState();
		    	String transitionFtoS = "e"+","+oldStartState.id;
		    	oldFinalState.addTransition(transitionFtoS);
		    	
		    	String newStartState = ""+number;
			    String newFinalState = ""+(number+1);
			    States sState =new States(newStartState);
				States fState =new States(newFinalState);
				
			    String transition2FtoF = "e"+","+(fState.id);
			    oldFinalState.addTransition(transition2FtoF);
			    String transitionStoS = "e"+","+oldStartState.id;
			    sState.addTransition(transitionStoS);
			    String transition2StoF = "e"+","+(fState.id);
			    sState.addTransition(transition2StoF);
			   
			    n1.setStartState(sState.id);
			    n1.setFinalState(fState.id);
			    n1.addState(sState);
			    n1.addState(fState);
			    
			    stack.push(n1);
			    number+=2;
 	
		    }
		    else {
			  String startState = ""+number;
			  String finalState = ""+(number+1);
			  String transitionAndDestination = c+","+(number+1);
			      
			  States sState =new States(startState);
			  States fState =new States(finalState);
			      
			  sState.addTransition(transitionAndDestination);
			  NFA n = new NFA(startState,finalState);
			  n.addState(sState);
			  n.addState(fState);
			      
			  number+=2;
			  stack.push(n);
		    }
		    
		}
		
		finalNFA = stack.pop();
		finalNFA.SortStates();
		finalNFA.sortTransitions();
		
		

		
		
	}

	/**
	 * @return Returns a formatted string representation of the NFA. The string
	 *         representation follows the one in the task description
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String answer = finalNFA.printStates();
		answer+="#";
		answer +=alphabet+"#";
		answer +=finalNFA.printStatesTranisitons();
		answer = answer.substring(0, answer.length() - 1);

		
		answer+="#"+finalNFA.startState+"#"+finalNFA.finalState;
		
		return answer;
	}
	
	

}

class NFA{
	String startState;
	ArrayList<States> statesList;
	String finalState;
	public NFA(String startState,String finalState) {
        this.startState = startState;
        this.statesList = new ArrayList<States>() ;
        this.finalState = finalState;
    }

	    // Method to add a state to the NFA
	public void addState(States state) {
	    statesList.add(state);
	}

	    // Method to remove a state from the NFA
	public void removeState(States state) {
	     statesList.remove(state);
	}
	
	public String getStartState() {
	        return startState;
	}
	
	public void setStartState(String startState) {
	        this.startState = startState;
	}
	
	public String getFinalState() {
	        return finalState;
	}

	public void setFinalState(String finalState) {
	        this.finalState = finalState;
	}
	
	public States getStartStateState() {
	
		for(int i=0;i<statesList.size();i++) {
			States state1 = statesList.get(i);
			if(state1.id.equals(startState)) {
				return state1;
			}
		}
		return null;
	}
	
	public States getFinalStateState() {
		
		for(int i=0;i<statesList.size();i++) {
			States state1 = statesList.get(i);
			if(state1.id.equals(finalState)) {
				return state1;
			}
		}
		return null;
	}
	
	public void SortStates() {
		for(int i =0;i<statesList.size()-1;i++) {
			int min = i;
			 for (int j = i + 1; j < statesList.size(); j++) {
				 int id1 = Integer.parseInt(statesList.get(j).id);
	             int id2 = Integer.parseInt(statesList.get(min).id); 
	                if (id1 < id2) {
	                    min = j;
	                }
			 }
	            if (min != i) {
	                States temp = statesList.get(i);
	                statesList.set(i, statesList.get(min));
	                statesList.set(min, temp);
	            }
		}
	}
	
	public void sortTransitions() {
		for(int i =0;i<statesList.size()-1;i++) {
			statesList.get(i).SortTransition();
		}
	}
	
    public String printStates() {
    	String stateString = "";
		for(int i =0;i<statesList.size();i++) {
			States s = statesList.get(i);
			if(i==statesList.size()-1) {
				stateString += s.id;
				
			}else {
				stateString += s.id+";";
			}
			
		}
		return stateString;
    }
    
    public String printStatesTranisitons() {
    	String transition ="";
		for(int i =0;i<statesList.size()-1;i++) {
			transition += statesList.get(i).printTrainsitions();
		}
		return transition;
    }

}

class States {
    String id; 
    ArrayList<String> transition;
    
    public States(String id) {
        this.id = id;
        this.transition = new ArrayList<>();
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public void addTransition(String transitionElement) {
        this.transition.add(transitionElement);
    }
    
	public void SortTransition() {
		for(int i =0;i<transition.size()-1;i++) {
			int min = i;
			 for (int j = i + 1; j < transition.size(); j++) {
				 String transition1 = transition.get(j);
		         String transition2 = transition.get(min);
		         char char1 = transition1.charAt(1); 
		         char char2 = transition2.charAt(1);
		         if (char1 < char2) {
		                min = j;
		         } else if (char1 == char2) {
		        	 String[] trans1part = transition1.split(",");
		        	 String[] trans2part = transition2.split(",");
		        	 int num1 = Integer.parseInt(trans1part[1]);
		        	 int num2 = Integer.parseInt(trans2part[1]);

		              if (num1 < num2) {
		                    min = j;
		                }
		            }
			 }
			 
	            if (min != i) {
	                String temp = transition.get(i);
	                transition.set(i, transition.get(min));
	                transition.set(min, temp);
	            }
		}
	}
	
	public String printTrainsitions(){
		String stateTransition="";
		for(int i =0;i<transition.size();i++) {
			String[] transpart = transition.get(i).split(",");
			String symbol = transpart[0];
			String destin = transpart[1];		
			stateTransition += id+","+symbol+","+destin+";";
		}
		return stateTransition;
	}
    
    
}



