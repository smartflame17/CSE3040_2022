class pair{
	public int x;
	public int y;
	
	public pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public void print() {
		System.out.print("(" + this.x + ", " + this.y + ")");		//prints out x and y as (x, y)
	}
}

public class Getxy_20201572 {
	
	public static void main(String args[]) {

		System.out.print("(x, y) : ");
		for (int i = 1; i <= 9; i++) {
			for (int j = 1; j <= 9; j++) {							//iterate for all (x, y) where 1 <= x,y <= 9
				pair testset = new pair(i, j);
				if(test(testset)) {
					testset.print();
					System.out.print(" ");
				}
			}
		}
	}
	
	public static boolean test(pair set) {							//checks to see if set (x, y) satisfies problem
		if (110 * set.x + set.y + 11 * set.y == 100 * set.y + 11 * set.x)
			return true;											
		else return false;
	}
}
