import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;


public class main {

	public static void main(String[] args) {
		ArrayList<Integer> num = new ArrayList<>();
		num.add(0);
		num.add(1);
		num.add(2);
		num.add(3);num.add(4);num.add(5);num.add(6);num.add(7);num.add(8);
		Collections.shuffle(num);
		int [][] board = new int[3][3];
		int k=0;
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				board[i][j]= num.get(k);
				k++;
			}
		}
		
		//int [][] board = {{1,2,5},{3,4,0},{6,7,8}};
	
		
		State parent = new State(board, null, 0,0);
		
		System.out.println("parent");
		printBoard(parent.getBoard());
		System.out.println();
		System.out.println();
		
		int [][] boardg = new int[3][3];
		int kg=0;
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				boardg[i][j]= kg;
				kg++;
			}
		}
		
		State goal = new State(boardg, null,0,0);
	/*	PriorityQueue<State> explored = new PriorityQueue<State>();
		HashMap<String, Double> frontierCost = new HashMap<String, Double>();
		frontierCost.put(goal.toString(), goal.getCost());
		explored.add(goal);
		
		int [][] boardg2 = new int[3][3];
		int kg2=0;
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				boardg2[i][j]= kg2;
				kg2++;
			}
		}
		State goal2 = new State(boardg2, null,3,4);
		
		boolean t = explored.contains(goal2);
		
		System.out.println(t);
		
		explored.remove(goal2);
		explored.add(goal2);
		frontierCost.replace(goal2.toString(), goal2.getCost());
         t = explored.contains(goal);
         double h = explored.poll().getCost();
		
		System.out.println(t);
		System.out.println(h);
         h = frontierCost.get(goal2.toString());
		
		System.out.println(h);
	*/
		
		//BFS tryBfs = new BFS();
		SearchAlgorithms search = new SearchAlgorithms();
		long start = System.currentTimeMillis();
		ArrayList<String> expanded = new ArrayList<String>(); 
		State res = search.Asearch(parent, goal, expanded,'M');
		long end = System.currentTimeMillis();
		long runTime = end - start;
		
		System.out.print("run time = ");
		System.out.print(runTime);
		System.out.println();
		System.out.println("depth= "+ res.getDepth());
		System.out.println("cost= "+ res.getDepth());
		System.out.println("expanded nodes:");
		printExpanded(expanded);
		System.out.println();
		System.out.println("path:");
		printPath(res);
		
		
/*		ArrayList<State> children = parent.getChildren();
		for(int i=0;i<children.size();i++) {
			System.out.println("child"+i);
			printBoard(children.get(i).getBoard());
			System.out.println();
			System.out.println();
		}
		
		System.out.println("parent");
		printBoard(parent.getBoard());
		System.out.println();
		System.out.println();
		
		System.out.println();
		System.out.println("new level");
		System.out.println();
		System.out.println();
		
		
		for(int i=0;i<children.size();i++) {
			System.out.println("parent: child"+i);
			printBoard(children.get(i).getBoard());
			System.out.println();
			System.out.println();
			ArrayList<State> grandchildren = children.get(i).getChildren();
			for(int j=0;j<grandchildren.size();j++) {
				System.out.println("child"+j);
				printBoard(grandchildren.get(j).getBoard());
				System.out.println();
				System.out.println();
			}
		}
	*/	
		

	}
	
	public static void printBoard(int[][]arr) {
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				System.out.print(arr[i][j]);
				System.out.print("\t");
			}
			System.out.println();
		}
	}
	
	public static void printExpanded(ArrayList<String> ex) {
		for(int i=0;i<ex.size();i++) {
			System.out.println("node:");
			String curr = ex.get(i);
			for(int j=0;j<3;j++) {
				for(int k=0;k<3;k++) {
				System.out.print(curr.charAt(k+3*j));
				System.out.print("\t");
				}
				System.out.println();
			}
			System.out.println();
		}
	}
	
    public static void printPath(State res) {
		ArrayList<String> path = new ArrayList<String>();
		path.add(res.toString());
		while(res.getParent()!=null) {
			res = res.getParent();
			path.add(res.toString());
		}
		for(int i=path.size()-1;i>=0;i--) {
			System.out.println("node:");
			String curr = path.get(i);
			for(int j=0;j<3;j++) {
				for(int k=0;k<3;k++) {
				System.out.print(curr.charAt(k+3*j));
				System.out.print("\t");
				}
				System.out.println();
			}
			System.out.println();
		}
		
	}

}
