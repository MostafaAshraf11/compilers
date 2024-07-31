package csen1002.main.task2;
import java.util.*;

/**
 * Write your info here
 * 
 * @name Mostafa Ashraf Aboelenien
 * @id 49-3737
 * @labNumber 06
 */

public class NfaToDfa {
	String input2;
	ArrayList<DFAState> finalDFA;
	String EndState;
	String dfAFinalStartState;
	ArrayList<DFATransition> dFATransition;

	/**
	 * Constructs a DFA corresponding to an NFA
	 * 
	 * @param input A formatted string representation of the NFA for which an
	 *              equivalent DFA is to be constructed. The string representation
	 *              follows the one in the task description
	 */
	public NfaToDfa(String input) {
		input2=input;
		// TODO Auto-generated constructor stub
		String[] Reg = input.split("#");
		String[]states = Reg[0].split(";");
		String[] alphabet = Reg[1].split(";");
		String []transitionsNFA = Reg[2].split(";");
		
		int startState =Integer.parseInt(Reg[3]);
		EndState = Reg[4];
		
		ArrayList<NFAStates> nfaStates = new ArrayList<NFAStates>();
		HashMap<Integer, HashSet<Integer>> epsilonTable = new HashMap<>();
		Queue<DFAState> queue = new LinkedList<DFAState>();
		
		for(int i=0;i<states.length;i++) {
			int state = Integer.parseInt(states[i]);
			epsilonTable.put(state,new HashSet<>());
			epsilonTable.get(state).add(state);
			NFAStates n = new NFAStates(state);
			nfaStates.add(n);
			
			for(int j=0;j<transitionsNFA.length;j++) {
				String [] transitionState = transitionsNFA[j].split(",");
				int temp = Integer.parseInt(transitionState[0]);
				String tansitionEps = transitionState[1];
				if(temp == state) {
					if( tansitionEps.equals("e")) {
						int temp2 = Integer.parseInt(transitionState[2]);
						epsilonTable.get(state).add(temp2);
					}
					else {
						n.transition.add(tansitionEps+","+transitionState[2]);
					}
					
				}
			}
				
		}
		
		boolean change = true;
		while(change==true) {
			change = false;
			for (Map.Entry<Integer, HashSet<Integer>> entry : epsilonTable.entrySet()) {
				HashSet<Integer> epsTransitions = entry.getValue();
				HashSet<Integer> currentTransitions = new HashSet<>(epsTransitions);
                for (int transitionState : currentTransitions) {
                    if (epsTransitions.addAll(epsilonTable.get(transitionState))) {
                    	change = true;
                    }	
                }
				
			}
			
		}
	
		
		HashSet<Integer> valueSet = new HashSet<>();
		valueSet.add(-1);
		epsilonTable.put(-1, valueSet);
		
		HashSet<Integer> intialDFAState = new HashSet<Integer>();
		intialDFAState.add(startState);
		
		 finalDFA = new ArrayList<DFAState>();
		
		
		DFAState startSt = new DFAState(intialDFAState);
		queue.add(startSt);
		
		ArrayList<DFAState> ancestors = new ArrayList<>();
		
		dFATransition = new ArrayList<>();
		
		
		HashSet<Integer> temmmp = startSt.getDFAStates(epsilonTable);
		
		 dfAFinalStartState = String.join("/", temmmp.toString().replaceAll("[\\[\\]]", "").split(", "));
		
		
		
		while(!queue.isEmpty()) {
			DFAState df = queue.remove();		
			HashSet<Integer> dfaEpsilon = df.getDFAStates(epsilonTable);
			df.nfaStates = dfaEpsilon;
			ancestors.add(df);
			finalDFA.add(df);
			
			for(int i=0;i<alphabet.length;i++) {
				String searchSymbol = alphabet[i];
				HashSet<Integer> tempState = new HashSet<Integer>();
				
				for (int state : dfaEpsilon) {
					if(state==-1) {
						
					}else {
					tempState.addAll(nfaStates.get(state).getTransitionsStates(searchSymbol));
					}
				}
				if(tempState.isEmpty()) {
					tempState.add(-1);
				}
				DFAState tempdf = new DFAState(tempState);
				
				HashSet<Integer> tempState2 = tempdf.getDFAStates(epsilonTable);
				
				tempdf.nfaStates = tempState2;
				
				DFATransition d = new DFATransition(df.nfaStates,alphabet[i],tempState2);
				Boolean exists = true;
				for(int j=0;j<dFATransition.size();j++) {
					if(dFATransition.get(j).equals(d)) {
						exists= false;
						
					}
				}
					if(exists==true) {
						dFATransition.add(d);
						DFAState temp = new DFAState(tempState2);
						Boolean duplicate = false;
						for(int k=0;k<ancestors.size();k++) {
							if(ancestors.get(k).nfaStates.equals(temp.nfaStates)) {
								duplicate = true;
								
							}
						}
						if(duplicate==false) {

							
							queue.add(temp);	
							
							ancestors.add(temp);
							//finalDFA.add(temp);
						}
					}
				
				}
			
		}
		
		for (int i = 0; i < finalDFA.size() - 1; i++) {
		    int maxIndex = i;
		    for (int j = i + 1; j < finalDFA.size(); j++) {
		        if (sortingSets(finalDFA.get(maxIndex).nfaStates, finalDFA.get(j).nfaStates)) {
		            maxIndex = j;
		        }
		    }
		    if (maxIndex != i) {
		    	
		        DFAState temp = finalDFA.get(i);
		        finalDFA.set(i, finalDFA.get(maxIndex));
		        finalDFA.set(maxIndex, temp);
		    }
		}
	}

	/**
	 * @return Returns a formatted string representation of the DFA. The string
	 *         representation follows the one in the task description
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		
        
		
        StringBuilder resultStates = new StringBuilder();
        for (DFAState dfaState : finalDFA) {
            HashSet<Integer> nfaStatestemp = dfaState.nfaStates;
            String stateStr = String.join("/", nfaStatestemp.toString().replaceAll("[\\[\\]]", "").split(", "));
            resultStates.append(stateStr).append(";");
        }

        if (resultStates.length() > 0) {
        	resultStates.deleteCharAt(resultStates.length() - 1);
        }

        
		ArrayList<DFATransition> finalDFATransition = new ArrayList<>();
		for(int i=0;i<finalDFA.size();i++) {
			for(int j=0;j<dFATransition.size();j++) {
				if(finalDFA.get(i).nfaStates.equals(dFATransition.get(j).sourceState)) {
					finalDFATransition.add(dFATransition.get(j));
				}
			}
			
		}
		StringBuilder stringBuilder = new StringBuilder();
		String separator = "";

		for (DFATransition transition : finalDFATransition) {
		    stringBuilder.append(separator);
		    appendStates(stringBuilder, transition.sourceState);
		    stringBuilder.append(",").append(transition.symbol).append(",");
		    appendStates(stringBuilder, transition.destinationState);
		    separator = ";";
		}

		String finalString = stringBuilder.toString();
		
		
		String[] Reg = input2.split("#");	
		String alphabet = Reg[1];
		
        StringBuilder result = new StringBuilder();
        
        String[] eeend = EndState.split(";");
        
        for (DFAState state : finalDFA) {
            for (String endState : eeend) {
                if (state.nfaStates.contains(Integer.parseInt(endState))) {
                    if (result.length() > 0) {
                        result.append(";");
                    }
                    result.append(String.join("/", state.nfaStates.toString().replaceAll("[\\[\\]]", "").split(", ")));
                    break;
                }
            }
        }

       
		
		
		return resultStates +"#" + alphabet +"#" + finalString +"#"+ dfAFinalStartState +"#" + result  ;
	}
	
	public static void appendStates(StringBuilder stringBuilder, HashSet<Integer> states) {
	    boolean first = true;
	    for (Integer state : states) {
	        if (!first) {
	            stringBuilder.append("/");
	        }
	        stringBuilder.append(state);
	        first = false;
	    }
	}
	
	public HashSet<Integer> getDFAStates(HashMap<Integer, HashSet<Integer>> epsilonTable,HashSet<Integer> DFAstate) {
        HashSet<Integer> result = new HashSet<>();
        
        for (int state : DFAstate) {
            HashSet<Integer> values = epsilonTable.getOrDefault(state, new HashSet<>());
            result.addAll(values);
        }
        
        return result;
        
    }
	

	public static boolean sortingSets(HashSet<Integer> set1,HashSet<Integer> set2) {
		Integer[] array1 = set1.toArray(new Integer[0]);
        Integer[] array2 = set2.toArray(new Integer[0]);
        
        int n = Math.min(array1.length, array2.length);
        for (int i = 0; i < n; i++) {
            if (array1[i] > (array2[i])) {
                return true;
            }
            else if(array1[i] < (array2[i])) {
            	return false;
            }
            
        }
        if(array1.length <= array2.length) {
        	return false;
        }
        else {
        	return true;
        }
        
	}

}
class NFAStates {
	Integer id;
	ArrayList<String> transition;

	public NFAStates(Integer id) { 
		this.id = id;
		this.transition = new ArrayList<>();
	}
	
	
    public HashSet<Integer> getTransitionsStates(String search) {
        HashSet<Integer> states = new HashSet<>();

        for (String transition : transition) {
            String[] transpart = transition.split(",");
            String symbol = transpart[0];
            int destin = Integer.parseInt(transpart[1]);

            if (search.equals(symbol)) {
                states.add(destin);
            }
        }

        return states;
    }
    
	public boolean sortingSets(HashSet<Integer> set1,HashSet<Integer> set2) {
		Integer[] array1 = set1.toArray(new Integer[0]);
        Integer[] array2 = set2.toArray(new Integer[0]);
        
        int n = Math.min(array1.length, array2.length);
        for (int i = 0; i < n; i++) {
            if (array1[i] > (array2[i])) {
                return false;
            }
            else if(array1[i] < (array2[i])) {
            	return true;
            }
        }
        return array1.length <= array2.length;

        
	}
	
}

 class DFAState {
    HashSet<Integer> nfaStates;

    DFAState(HashSet<Integer> nfaStates) {
        this.nfaStates = nfaStates;
    }
    
    public HashSet<Integer> getDFAStates(HashMap<Integer, HashSet<Integer>> epsilonTable) {
        HashSet<Integer> result = new HashSet<>();
        for (int state : nfaStates) {
            HashSet<Integer> values = epsilonTable.getOrDefault(state, new HashSet<>());
            result.addAll(values);
        }
        return result;
    }
    
    
}

// Define a class for DFA transitions
 class DFATransition {
    HashSet<Integer> sourceState;
    String symbol;
    HashSet<Integer> destinationState;

    DFATransition(HashSet<Integer> sourceState, String symbol, HashSet<Integer> destinationState) {
        this.sourceState = sourceState;
        this.symbol = symbol;
        this.destinationState = destinationState;
    }
    
    public boolean equals(Object o) {
        if (o == null) { 
        	return false;
        }
        DFATransition temp = (DFATransition) o;
        return sourceState.equals(temp.sourceState) &&
                symbol.equals(temp.symbol) &&
                destinationState.equals(temp.destinationState);
    }
    
    
}
