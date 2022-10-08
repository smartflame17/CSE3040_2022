class LottoMachine {
	
	private int[] jackpot;									//stores winning number
	private int[] roll;										//stores every roll
	private int lotto_length;								//stores length of winning number and roll
	private int iteration;									//stores # of rolls
	private int req;										//minimum required matches to terminate
	private int check;										//stores # of matches per roll
	
	public LottoMachine(int[] winning_num, int req) {
		this.lotto_length = winning_num.length;
		this.roll = new int[this.lotto_length];
		this.jackpot = winning_num;
		this.iteration = 0;
		this.req = req;
	}
	
	public void RollMachine() {
		for (int i = 0; i < this.lotto_length; i++) {
			roll[i] = (int)(Math.random()*50);
			for (int j = 0; j < i; j++) {
				if (roll[i] == roll[j]) {					//decrement i to re-roll if duplicate
					i--;
					break;									//break to reduce unnecessary comparison
				}
			}
		}													//roll has random unique integers
		this.iteration++;
	}
	
	public boolean CheckNum() {
		this.check = 0;
		for (int i = 0; i < this.lotto_length; i++) {
			for (int j = 0; j < this.lotto_length; j++) {
				if (roll[i] == jackpot[j]) this.check++;
			}
		}
		if (this.check >= this.req) return true;			//if current roll meets requirements, return true
		else return false;
	}
	
	public void PrintRoll() {
		System.out.print("Final result : ");
		for (int i = 0; i < this.lotto_length; i++) {
			System.out.print(this.roll[i] + " ");
		}
		System.out.println();
	}
	public void PrintIter() {
		System.out.println("Number of iterations : " + this.iteration);
	}
}

public class Lotto_20201572 {
	
	public static void main(String[] args) {
		
		int[] winning_num = {7, 18, 32, 37, 44};
		
		LottoMachine machine = new LottoMachine(winning_num, 3);
		do {
			machine.RollMachine();
		} while(!machine.CheckNum());
		machine.PrintRoll();
		machine.PrintIter();
	}
}
