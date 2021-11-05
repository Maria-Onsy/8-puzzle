import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;



public class main {

	public static void main(String[] args) throws IOException {
		/*ArrayList<Integer> num = new ArrayList<>();
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
		*/
		//int [][] board = {{1,2,5},{3,4,0},{6,7,8}};
		//int[][] board = {{6,7,2},{3,0,1},{8,5,4}};	
	
		
	//scan initial state
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the initial state.\n");
		String brd = sc.nextLine();
		brd.replaceAll(" ", "");
		brd.replaceAll("\n", "");
		while(brd.length() != 17) {
			System.out.println("Error:Wrong initial state!\n");
			System.out.println("Enter correct initial state.\n");
			brd = sc.nextLine();
		}
		int [][] board = new int[3][3];
		boolean corr = false;
		while(!corr) {
			int k=0;
			boolean wrg = false;
			ArrayList<Integer> avb = new ArrayList<>();
			for(int i=0;i<9;i++) {
				avb.add(i);
			}
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					if((k%2 == 0)) {
						int t = brd.charAt(k)-'0';
						if(avb.contains(t)) {
							board[i][j]= t;
							avb.remove(avb.indexOf(t));
						}
						else {
							System.out.println("Error:Wrong initial state!\n");
							wrg = true;
							break;
						}
					}
					else {
						j--;
						if(brd.charAt(k)!=',') {
							System.out.println("Error:Wrong initial state!\n");
							wrg = true;
							break;
						}
					}
					k++;
				}
				if(wrg) {break;}
			}
			if(wrg) {
				System.out.println("Enter correct initial state.\n");
				brd = sc.nextLine();
			}
			else {corr = true;}
		}
		
		
		
		//System.out.println("parent");
		//printBoard(parent.getBoard());
		//System.out.println();
		//System.out.println();
		
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
		
		//BFS
		SearchAlgorithms search = new SearchAlgorithms(100000);
		long start = System.currentTimeMillis();
		State parentB = new State(board, null, 0,0);
		ArrayList<String> Bexpanded = new ArrayList<String>(); 
		State resB = search.BFSsearch(parentB, goal, Bexpanded);
		long end = System.currentTimeMillis();
		long runTime = end - start;
		printResult("BFS search.txt", parentB, runTime, resB, Bexpanded, search.getCounter(),search.getSearchDepth());
		
		//DFS
		start = System.currentTimeMillis();
		State parentD = new State(board, null, 0,0);
		ArrayList<String> Dexpanded = new ArrayList<String>(); 
		State resD = search.DFSsearch(parentD, goal, Dexpanded);
		end = System.currentTimeMillis();
		runTime = end - start;
		printResult("DFS search.txt", parentD, runTime, resD, Dexpanded, search.getCounter(),search.getSearchDepth());
		
		//A* using Manhatten distance
		start = System.currentTimeMillis();
		State parentM = new State(board, null, 0,0);
		ArrayList<String> Mexpanded = new ArrayList<String>(); 
		State resM = search.Asearch(parentM, goal, Mexpanded, 'M');
		end = System.currentTimeMillis();
		long counter1 = search.getCounter();
		runTime = end - start;
		printResult("A search(Manhattan).txt", parentM, runTime, resM, Mexpanded, search.getCounter(),search.getSearchDepth());
		
		//A* using Euclidean distance
		start = System.currentTimeMillis();
		State parentE = new State(board, null, 0,0);
		ArrayList<String> Eexpanded = new ArrayList<String>(); 
		State resE = search.Asearch(parentE, goal, Eexpanded,'E');
		end = System.currentTimeMillis();
		long counter2 = search.getCounter();
		runTime = end - start;
		printResult("A search(Euclidean).txt", parentE, runTime, resE, Eexpanded, search.getCounter(),search.getSearchDepth());
		
		System.out.println("The results are stored in files.");
		
		//print the comparison between number of expanded nodes//
		printcomparison("compare.txt",counter1, counter2);
		System.out.println();
		
/*      System.out.print("run time = ");
		System.out.print(runTime);
		System.out.println();
		if(res != null) {
		System.out.println("depth= "+ res.getDepth());
		System.out.println("cost= "+ res.getDepth());
		System.out.println("expanded nodes:");
		//printExpanded(expanded);
		System.out.println();
		System.out.println("path:");
		//printPath(res);
		}
		else {
			System.out.println("Number of iterations: ");
			System.out.println(search.getCounter());
		}
		
		ArrayList<State> children = parent.getChildren();
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

	
	public static void printBoard(int[][]arr,FileWriter file) throws IOException {
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				file.write(Integer.toString(arr[i][j]));
				//System.out.print(arr[i][j]);
				//System.out.print("\t");
				file.write("\t");
			}
			//System.out.println();
			file.write("\n");
		}
	}
	
	
	public static void printExpanded(ArrayList<String> ex, FileWriter file) throws IOException {
		for(int i=0;i<ex.size();i++) {
			//System.out.println("node:");
			file.write("node "+i+" :\n");
			String curr = ex.get(i);
			for(int j=0;j<3;j++) {
				for(int k=0;k<3;k++) {
				//System.out.print(curr.charAt(k+3*j));
				file.write(curr.charAt(k+3*j));
				//System.out.print("\t");
				file.write("\t");
				}
				//System.out.println();
				file.write("\n");
			}
			//System.out.println();
			file.write("\n");
		}
	}
	
    public static void printPath(State res,FileWriter file) throws IOException {
		ArrayList<String> path = new ArrayList<String>();
		path.add(res.toString());
		while(res.getParent()!=null) {
			res = res.getParent();
			path.add(res.toString());
		}
		for(int i=path.size()-1;i>=0;i--) {
			//System.out.println("node:");
			file.write("node:\n");
			String curr = path.get(i);
			for(int j=0;j<3;j++) {
				for(int k=0;k<3;k++) {
				//System.out.print(curr.charAt(k+3*j));
				file.write(curr.charAt(k+3*j));
				//System.out.print("\t");
				file.write("\t");
				}
				//System.out.println();
				file.write("\n");
			}
			//System.out.println();
			file.write("\n");
		}
		
	}
    
    public static void printResult(String name,State parent, long runtime, State res, ArrayList<String> expanded, long counter, int depth) throws IOException {
    	FileWriter file = new FileWriter(name);
    	file.write("intial state:\n");
    	printBoard(parent.getBoard(),file);
    	file.write("run time = ");
		file.write(Long.toString(runtime));
		file.write(" millisecond\n");
		if(res != null) {
			file.write("Search depth= "+ depth+"\n");
			file.write("cost= "+ res.getDepth()+"\n");
			file.write("Number of expanded nodes: "+ expanded.size()+ "\n");
			file.write("\n");
			file.write("--------------------------------------------------------------------------------------");
			file.write("\n");
			file.write("path:\n");
			printPath(res,file);
			file.write("\n");
			file.write("--------------------------------------------------------------------------------------");
			file.write("\n");
			file.write("expanded nodes:\n");
			printExpanded(expanded,file);
		}
		else {
			file.write("No result found after " + counter + " iterations.\n");
			file.write("Maxinmum depth reached = "+ depth+"\n");
			file.write("Number of expanded nodes: "+ expanded.size()+ "\n");
		}
		file.close();

    	
    }
	
	public static void printcomparison(String name,long counter1, long counter2) throws IOException {
    	FileWriter file = new FileWriter(name);
    	file.write("Number of nodes expanded by Manhattan heuristic:  ");
    	file.write(Long.toString(counter1+1));
    	file.write("\n");
    	file.write("Number of nodes expanded by Euclidean heuristic:  ");
    	file.write(Long.toString(counter2+1));
    	file.write("\n");
	
		file.close();

    	
    }

}
