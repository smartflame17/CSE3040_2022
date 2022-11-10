import java.util.Stack;
import java.awt.*;
import java.awt.event.*;


//NEEDS SOME KIND OF PARANTHESES DETECTION (Maybe a boolean confirming pairs are met)
public class Calculator_20201572 extends Frame {
	private Button addBtn, subBtn, mulBtn, divBtn, eqBtn, powBtn, dotBtn; 	//operator buttons
	private Button facBtn, logBtn, log10Btn, rootBtn,  perBtn;				//unary operator buttons
	private Button clrBtn, leftparBtn, rightparBtn;							//control buttons
	private Button[] numBtns;												//contains buttons from 0 to 9 and dot
	private TextField output;												//textfield prints out current operation and result
	private Panel CalculatorBody, row0, row1, row2, row3, row4;				//panels used for calculator formatting
	private String display;													//string displayed in textfield output
	private int leftparNum;													//number of left parantheses in display
	private int rightparNum;												//number of right parantheses in display
	private boolean isCalcComplete;											//determines current state of calculator
	
	
	public Calculator_20201572(String s) {									//constructor creates calculator gui
		super(s);
		CalculatorBody = new Panel();
		row0 = new Panel();
		row1 = new Panel();
		row2 = new Panel();
		row3 = new Panel();
		row4 = new Panel();
		output = new TextField(16);
		addBtn = new Button("+"); subBtn = new Button("-"); mulBtn = new Button("*");
		divBtn = new Button("÷"); perBtn = new Button("%"); eqBtn = new Button("=");
		dotBtn = new Button("."); facBtn = new Button("x!"); logBtn = new Button("ln");
		log10Btn = new Button("log"); rootBtn = new Button("√"); powBtn = new Button("^");
		clrBtn = new Button("CE"); leftparBtn = new Button("("); rightparBtn = new Button(")");
		
		numBtnHandler numHandler = new numBtnHandler();
		opBtnHandler opHandler = new opBtnHandler();
		otherBtnHandler otherHandler = new otherBtnHandler();
		
		numBtns = new Button[11];
		numBtns[10] = dotBtn;
		for (int i = 0; i < numBtns.length-1; i++) {
			numBtns[i] = new Button(String.valueOf(i));
			numBtns[i].setFont(new Font("Monospaced", Font.BOLD, 20));
			numBtns[i].addActionListener(numHandler);
		}
		addBtn.setFont(new Font("Monospaced", Font.BOLD, 20));
		subBtn.setFont(new Font("Monospaced", Font.BOLD, 20));
		mulBtn.setFont(new Font("Monospaced", Font.BOLD, 20));
		divBtn.setFont(new Font("Monospaced", Font.BOLD, 20));
		powBtn.setFont(new Font("Monospaced", Font.BOLD, 20));
		perBtn.setFont(new Font("Monospaced", Font.BOLD, 20));
		eqBtn.setFont(new Font("Monospaced", Font.BOLD, 20));
		eqBtn.setBackground(new Color(66, 133, 244));			//set equal button to google blue color
		dotBtn.setFont(new Font("Monospaced", Font.BOLD, 20));
		facBtn.setFont(new Font("Monospaced", Font.BOLD, 20));
		logBtn.setFont(new Font("Monospaced", Font.BOLD, 20));
		log10Btn.setFont(new Font("Monospaced", Font.BOLD, 20));
		rootBtn.setFont(new Font("Monospaced", Font.BOLD, 20));
		clrBtn.setFont(new Font("Monospaced", Font.BOLD, 20));
		leftparBtn.setFont(new Font("Monospaced", Font.BOLD, 20));
		rightparBtn.setFont(new Font("Monospaced", Font.BOLD, 20));
		
		addBtn.addActionListener(opHandler);
		subBtn.addActionListener(opHandler);
		mulBtn.addActionListener(opHandler);
		divBtn.addActionListener(opHandler);
		powBtn.addActionListener(opHandler);
		perBtn.addActionListener(otherHandler);
		eqBtn.addActionListener(otherHandler);
		dotBtn.addActionListener(numHandler);
		facBtn.addActionListener(otherHandler);
		logBtn.addActionListener(otherHandler);
		log10Btn.addActionListener(otherHandler);
		rootBtn.addActionListener(otherHandler);
		clrBtn.addActionListener(otherHandler);
		leftparBtn.addActionListener(otherHandler);
		rightparBtn.addActionListener(otherHandler);
		
		output.setMaximumSize(new Dimension(200 ,40));
		output.setFont(new Font("Monospaced", Font.BOLD, 20));
		display = "0";
		output.setText(display);
		leftparNum = 0;
		rightparNum = 0;
		isCalcComplete = false;
		row0.setLayout(new GridLayout(1, 5));
		row1.setLayout(new GridLayout(1, 5));
		row2.setLayout(new GridLayout(1, 5));
		row3.setLayout(new GridLayout(1, 5));
		row4.setLayout(new GridLayout(1, 5));
		row0.add(facBtn); row0.add(leftparBtn); row0.add(rightparBtn); row0.add(perBtn); row0.add(clrBtn);
		row1.add(logBtn); row1.add(numBtns[7]); row1.add(numBtns[8]); row1.add(numBtns[9]); row1.add(divBtn);
		row2.add(log10Btn); row2.add(numBtns[4]); row2.add(numBtns[5]); row2.add(numBtns[6]); row2.add(mulBtn);
		row3.add(rootBtn); row3.add(numBtns[1]); row3.add(numBtns[2]); row3.add(numBtns[3]); row3.add(subBtn);
		row4.add(powBtn); row4.add(numBtns[0]); row4.add(dotBtn); row4.add(eqBtn); row4.add(addBtn);
		CalculatorBody.setLayout(new GridLayout(6, 1));
		CalculatorBody.add(output);
		CalculatorBody.add(row0);
		CalculatorBody.add(row1);
		CalculatorBody.add(row2);
		CalculatorBody.add(row3);
		CalculatorBody.add(row4);
		this.add(CalculatorBody);
		this.setSize(400, 600);
	}
	
	
	public int getPrecedence(String op) {
		switch(op) {
		case "^": return 4;
		case "*": return 3; 
		case "÷": return 3; 
		case "+": return 2;
		case "-": return 2;
		default: return 1;
		}
	}
	
	//returns postfix of infix input. Digits are separated by spaces. 
	public String ConvertInfixtoPostfix(String infix) {
		Stack<String> opStack = new Stack<>();
		String postfix = "";
		String[] tmpArray = infix.split(" ");
		

		return postfix;
	}
	
	//calculate postfix into double type result
	public String CalculatePostfix(String postfix) {
		String result = "";
		leftparNum = 0;
		rightparNum = 0;
		isCalcComplete = true;
		return result;
	}
	
	public boolean isOperator(String s) {
		String opList = "+-*÷^";
		if (opList.contains(s))
			return true;
		else return false;
	}
	
	//update display string
	public void AppendtoDisplay(String str) {
		if (display.equals("0")){
			display = str;
		}
		else display += str;
		output.setText(display);
	}
	
	//clears display upon pressing the AC button when display string only has operand / one digit
	public void ClearDisplay() {
		isCalcComplete = false;
		display = "0";
		output.setText(display);
	}
	
	//deletes last element in display string.
	public void DeleteDisplay() {
		if (isCalcComplete) {		//refresh string to 0 for completely new calculation
			ClearDisplay();
			//leftparNum = 0;
			//rightparNum = 0;
			isCalcComplete = false;
			return;
		}
		
		if (display.length() == 2) {
			if (display.charAt(display.length()-2) == '(' || display.charAt(display.length()-1) == '!') {
				if (display.charAt(display.length()-2) == '(') leftparNum--;
				ClearDisplay();
				return;
			}
			else if (Character.isDigit(display.charAt(display.length()-1)) || display.charAt(display.length()-1) == '%' ||
					display.charAt(display.length()-2) == '.' || display.charAt(display.length()-1) == '.') {	//cut string by one if last element is digit or dot or percent
				display = display.substring(0, display.length()-1);
				output.setText(display);
				return;
			}
		}
		if (display.length() > 2) {	//cut string by two if last element is parantheses, factorial
			if (
				display.charAt(display.length()-2) == '(' &&  display.substring(display.length()-3).isBlank() || //insures it is only left parantheses
				display.charAt(display.length()-1) == ')' || display.charAt(display.length()-1) == '!'){	//right parantheses or factorial
				if (display.charAt(display.length()-2) == '(') leftparNum--;
				if (display.charAt(display.length()-1) == ')') rightparNum--;
				display = display.substring(0, display.length()-2);
				output.setText(display);
				return;
			}
			else if (Character.isDigit(display.charAt(display.length()-1)) || display.charAt(display.length()-1) == '%' ||
					display.charAt(display.length()-2) == '.' || display.charAt(display.length()-1) == '.') {	//cut string by one if last element is digit or dot or percent
				display = display.substring(0, display.length()-1);
				output.setText(display);
				return;
			}
			else if (isOperator(display.substring(display.length()-2, display.length()-1))){	//delete operators
				display = display.substring(0, display.length()-3);
				output.setText(display);
				return;
			}
			display = display.substring(0, display.length()-1);	//remove blank at end of log, ln, root
			while (!display.substring(display.length()-1).isBlank()) {	//cut string until space if last element is log, ln, root
				display = display.substring(0, display.length()-1);
				if (display.length() == 0) {
					ClearDisplay(); 
					break;
				}
				if (Character.isDigit(display.charAt(display.length()-1))) {
					output.setText(display);
					break;
				}
			}	
			leftparNum--;
			output.setText(display);
		}
		else ClearDisplay();
	}
	
	//check to not allow two dots in one number, adds to display otherwise
	public void CheckforDecimal(String str) {
		if (str.equals(".")) {
			if (display.lastIndexOf(".") > display.lastIndexOf(" ")) {	//if trying to type two dots in one number
				return;
			}
			display += str;					//add decimal point behind 0
		}
		else if (display.equals("0")){		//first input is digit
			display = str;
		}
		else display += str;				//otherwise simply add
		output.setText(display);
	}
	
	//replace operator if typed twice, adds to display otherwise 
	// NEEDS EXCEPTION FOR MINUS OPERATOR TO MAKE NEGATIVE NUMBERS
	public void CheckforOperator(String str) {
		if (str.equals(" - ")) {
			if (display.equals("0")){
				display = "-";
				output.setText(display);
				return;
			}
			else if (Character.isDigit(display.charAt(display.length()-1))) {			//adding subtraction sign behind digit
				display += str;
				output.setText(display);
				return;
			}
			if (display.substring(display.length()-2, display.length()-1).equals("*") || 
				display.substring(display.length()-2, display.length()-1).equals("÷") || 
				display.substring(display.length()-2, display.length()-1).equals("^") ||
				display.substring(display.length()-2, display.length()-1).equals("(")) {		//if previous input was mul or div, it means putting negative number
				display += "-";
				output.setText(display);
				return;
			}
			else if (isOperator(display.substring(display.length()-2, display.length()-1))) {		//if previous input was also operator, replace
				display = display.substring(0, display.length()-3);
				display += str;
				output.setText(display);
				return;
			}
		}
		if (display.equals("0")) {
			display += str;
		}
		else if (Character.isDigit(display.charAt(display.length()-1))) {
			display += str;
		}
		else if (!Character.isDigit(display.charAt(display.length()-1))) {		//if trying to put operator before any operand
			if(display.substring(display.length()-1).equals(")"))					//is okay if behind )
				display += str;
			else return;
		}
		else if (isOperator(display.substring(display.length()-2, display.length()-1))) {		//if previous input was also operator, replace
			display = display.substring(0, display.length()-3);
			display += str;

		}
		output.setText(display);
	}
	
	public void getValidDisplay() {
		if (!isValidDisplay()) {
			while(leftparNum != rightparNum) {
				display += " )";
				rightparNum++;
			}
		}
	}
	
	private boolean isValidDisplay() {
		if (leftparNum == rightparNum)
			return true;
		else return false;
	}
	
	class numBtnHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Button selected = (Button)e.getSource();
			for(Button btn : numBtns) {
				if (selected == btn) 
					CheckforDecimal(btn.getLabel());
			}
		}
	}
	
	class opBtnHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			isCalcComplete = false;
			Button selected = (Button)e.getSource();
			if (selected == addBtn) 
				CheckforOperator(" + ");
			if (selected == subBtn)
				CheckforOperator(" - ");
			if (selected == mulBtn)
				CheckforOperator(" * ");
			if (selected == divBtn)
				CheckforOperator(" ÷ ");
			if (selected == powBtn)
				CheckforOperator(" ^ ");
		}
	}
	
	//MUST NOT ALLOW RIGHT PARANTHESES AT THE START also only allowed after left parantheses or numbers
	class otherBtnHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			isCalcComplete = false;
			Button selected = (Button)e.getSource();
			if (selected == clrBtn)
				DeleteDisplay();
			if (selected == eqBtn) {
				getValidDisplay();
				display = CalculatePostfix(ConvertInfixtoPostfix(display));
				output.setText(display);
			}
			if (selected == leftparBtn) {
				AppendtoDisplay("( ");
				leftparNum++;
			}
			if (selected == rightparBtn) {
				if (leftparNum > rightparNum) {
					AppendtoDisplay(" )");
					rightparNum++;
				}
			}
			if (selected == facBtn) {
				if (display.equals("0")) {
					display += " !";
					output.setText(display);
				}
				else if (display.substring(display.length()-1).equals(")") || 
						Character.isDigit(display.charAt(display.length()-1)) ||
						display.substring(display.length()-1).equals("%")){
					AppendtoDisplay(" !");
				}
				else if (isOperator(display.substring(display.length()-2, display.length()-1))) {
					display = display.substring(0, display.length()-3);
					AppendtoDisplay(" !");
				}
			}
				
			if (selected == logBtn) {
				AppendtoDisplay("ln( ");
				leftparNum++;
			}
			if (selected == log10Btn) {
				AppendtoDisplay("log( ");
				leftparNum++;
			}
			if (selected == rootBtn) {
				AppendtoDisplay("√( ");
				leftparNum++;
			}
			if (selected == perBtn) {
				if (display.equals("0")) {	
					display += " %";
					output.setText(display);
				}
				else if (Character.isDigit(display.charAt(display.length()-1)) ||
						display.substring(display.length()-1).equals("!") ||
						display.substring(display.length()-1).equals(")"))	//only add percentage after digit or factorial or )
					AppendtoDisplay(" %");
			}

		}
	}
	public static void main(String[] args) {
		Frame f = new Calculator_20201572("Calculator");
		WindowDestroyer WindowListener = new WindowDestroyer();
		f.addWindowListener(WindowListener);
		f.setVisible(true);
	}
}

class WindowDestroyer extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
}

