import java.util.ArrayList;

public class State implements Comparable<State> {
  private int[][]board = new int[3][3];
  private State parent;
  private ArrayList<State> children = new ArrayList<State>();
  private double cost;
  private int depth;
  
  public State(int[][]board ,State parent) {
	  this.board = board;
	  this.parent = parent;
  }
  
  public State(int[][]board ,State parent,int depth, double cost) {
	  this.board = board;
	  this.parent = parent;
	  this.depth = depth;
	  this.cost = cost;
  }

public ArrayList<State> getChildren(char type){
	if(children.isEmpty()) {
		boolean found = false;
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if (board[i][j]== 0) {
					if((i-1)>=0) {
						int [][] childBoard1 = new int[3][3];
						for(int l=0;l<3;l++) {
							for(int m=0;m<3;m++) {
								childBoard1[l][m]= board[l][m];
							}
						}
						swap(i, j, i-1, j, childBoard1);
						double heuristic = calculate_heuristic(type, childBoard1);
						State child1 = new State(childBoard1, this, this.depth+1, this.depth+1+heuristic);
						this.children.add(child1);
					}
					if((i+1)<3) {
						int [][] childBoard2 = new int[3][3];
						for(int l=0;l<3;l++) {
							for(int m=0;m<3;m++) {
								childBoard2[l][m]= board[l][m];
							}
						}
						swap(i, j, i+1, j, childBoard2);
						double heuristic = calculate_heuristic(type, childBoard2);
						State child2 = new State(childBoard2, this, this.depth+1, this.depth+1+heuristic);
						this.children.add(child2);
					}
					if((j-1)>=0) {
						int [][] childBoard3 = new int[3][3];
						for(int l=0;l<3;l++) {
							for(int m=0;m<3;m++) {
								childBoard3[l][m]= board[l][m];
							}
						}
						swap(i, j, i, j-1,childBoard3);
						double heuristic = calculate_heuristic(type, childBoard3);
						State child3 = new State(childBoard3, this, this.depth+1, this.depth+1+heuristic);
						this.children.add(child3);
					}
					if((j+1)<3) {
						int [][] childBoard4 = new int[3][3];
						for(int l=0;l<3;l++) {
							for(int m=0;m<3;m++) {
								childBoard4[l][m]= board[l][m];
							}
						}
						swap(i, j, i, j+1, childBoard4);
						double heuristic = calculate_heuristic(type, childBoard4);
						State child4 = new State(childBoard4, this, this.depth+1, this.depth+1+heuristic);
						this.children.add(child4);
					}
					found = true;
					break;
				}
			}
			if(found) {break;}
		}
	}
	return children;
}

private void swap(int x1 , int x2 , int y1 , int y2, int[][]arr) {
	int temp = arr[x1][x2];
  	arr[x1][x2] = arr[y1][y2];
  	arr[y1][y2] = temp;
}

public ArrayList<State> getNeighbours(){
	return parent.children;	
}


public int[][] getBoard() {
	return board;
}

public void setBoard(int[][] board) {
	this.board = board;
}

public State getParent() {
	return parent;
}

public void setParent(State parent) {
	this.parent = parent;
}


public void setChildren(ArrayList<State> children) {
	this.children = children;
}


@Override
public boolean equals(Object st) {
	for(int i=0;i<3;i++) {
		for(int j=0;j<3;j++) {
			if(((State)st).board[i][j]!=this.board[i][j]) {
				return false;}
		}
	}
	return true;
}

public String toString() {
	String st = "";
	for(int i=0;i<3;i++) {
		for(int j=0;j<3;j++) {
			st += this.board[i][j];
		}
	}
	return st;
}

public double getCost() {
	return cost;
}

public void setCost(double cost) {
	this.cost = cost;
}

public int getDepth() {
	return depth;
}

public void setDepth(int depth) {
	this.depth = depth;
} 

private double calculate_heuristic(char type, int[][]boardc) {
	double cost = 0;
	int pos[][]= {{0,0},{0,1},{0,2},{1,0},{1,1},{1,2},{2,0},{2,1},{2,2}};
	if(type == 'M') {
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				int num = boardc[i][j];
				cost += Math.abs(i-pos[num][0])+Math.abs(j-pos[num][1]);
			}
		}
	}
	else if(type == 'E') {
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				int num = boardc[i][j];
				cost += Math.sqrt(Math.pow(i-pos[num][0], 2)+ Math.pow(j-pos[num][1], 2));
			}
		}
	}
	return cost;
}


@Override
public int compareTo(State s2) {
	
	return this.getCost() > s2.getCost() ? 1 : -1;
}

}