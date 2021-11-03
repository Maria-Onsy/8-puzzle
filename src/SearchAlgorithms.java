import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;


public class SearchAlgorithms {

	public State BFSsearch(State initial,State goal,ArrayList<String> expanded) {
		Queue<State> frontier = new LinkedList<State>();
		frontier.add(initial);
		Set<String> explored = new HashSet<String>();
		
		while(!frontier.isEmpty()) {
			State curr = frontier.remove();
			explored.add(curr.toString());
			
			//printBoard(curr.getBoard());
			expanded.add(curr.toString());
			
			if (curr.equals(goal)) {
				return curr;
			}
			
			ArrayList<State> children = curr.getChildren('B');
			for(int i=0;i<children.size();i++) {
				if(!(explored.contains(children.get(i).toString())||frontier.contains(children.get(i)))) {
					frontier.add(children.get(i));
				}
			}
			
		}
		return null;
	}
	
	public State DFSsearch(State initial,State goal,ArrayList<String> expanded) {
		Stack<State> frontier = new Stack<State>();
		frontier.push(initial);
		Set<String> explored = new HashSet<String>();
		
		while(!frontier.isEmpty()) {
			State curr = frontier.pop();
			explored.add(curr.toString());
			
			expanded.add(curr.toString());
			
			if (curr.equals(goal)) {
				return curr;
			}
			
			ArrayList<State> children = curr.getChildren('D');
			for(int i=0;i<children.size();i++) {
				if(!(explored.contains(children.get(i).toString())||frontier.contains(children.get(i)))) {
					frontier.push(children.get(i));
				}
			}
			
		}
		return null;
	}
	
	public State Asearch(State initial,State goal,ArrayList<String> expanded, char type) {
		PriorityQueue<State> frontier = new PriorityQueue<State>();
		frontier.add(initial);
		Set<String> explored = new HashSet<String>();
		HashMap<String, Double> frontierCost = new HashMap<String, Double>();
		frontierCost.put(initial.toString(), initial.getCost());
		
		while(!frontier.isEmpty()) {
			State curr = frontier.poll();
			frontierCost.remove(curr.toString());
			explored.add(curr.toString());
			
			expanded.add(curr.toString());
			
			if (curr.equals(goal)) {
				return curr;
			}
			
			ArrayList<State> children = curr.getChildren(type);
			for(int i=0;i<children.size();i++) {
				State child = children.get(i);
				if(!(explored.contains(child.toString())||frontier.contains(child))) {
					frontier.add(child);
					frontierCost.put(child.toString(), child.getCost());
				}
				else if(frontier.contains(child)) {
					if(child.getCost()<frontierCost.get(child.toString())) {
						frontier.remove(child);
						frontier.add(child);
						frontierCost.replace(child.toString(), child.getCost());
					}
				}
			}
			
		}
		return null;
	}
	
	
}
