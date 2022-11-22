import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class BallThreadAssig_20201572 extends Frame implements ActionListener{  	 
	private Canvas canvas; 
	public ArrayList<Ball> balls;
	
	public BallThreadAssig_20201572(){  	
		canvas = new Canvas();
		balls = new ArrayList<Ball>();
      	add("Center", canvas);
      	Panel p = new Panel();
		Button s = new Button("Start");
		p.add(s);	
      	s.addActionListener(this);
      	add("South", p);
   	}
	
	public void actionPerformed(ActionEvent evt){  	
		if (evt.getActionCommand() == "Start"){  	//adds 5 balls
			Ball b = new Ball(canvas, canvas.getWidth() / 2 + 16, canvas.getHeight() / 2 + 16, 2, 2, 16);
         	b.start();
         	balls.add(b);
         	b = new Ball(canvas, canvas.getWidth() / 2 - 16, canvas.getHeight() / 2 + 16, -2, 2, 16);
         	b.start();
         	balls.add(b);
         	b = new Ball(canvas, canvas.getWidth() / 2, canvas.getHeight() / 2 - 16, 0, -2, 16);
         	b.start();
         	balls.add(b);
         	b = new Ball(canvas, canvas.getWidth() / 2 + 16, canvas.getHeight() / 2, 2, 0, 16);
         	b.start();
         	balls.add(b);
         	b = new Ball(canvas, canvas.getWidth() / 2 - 16, canvas.getHeight() / 2, -2, 0, 16);
         	b.start();
         	balls.add(b);
         	this.StartBallLoop();
      	}
   	}
	
	//Loop until all balls disappear
	public void StartBallLoop() {
		while (!balls.isEmpty()) {
			for(int i = 0; i < balls.size(); i++) {
				if (balls.get(i).isAlive()) {								//if current ball is still active
					for (int j = i+1; j < balls.size(); j++) {
						if (balls.get(j).isAlive()) {
							if (BallCollided(balls.get(i), balls.get(j))) {
								balls.get(i).isCollided = true;
								balls.get(j).isCollided = true;
								if(balls.get(i).diameter > 1) {
									Ball b = new Ball(canvas, (balls.get(i).x + balls.get(j).x)/2 + balls.get(i).diameter / 2 + 1, 
											(balls.get(i).y + balls.get(j).y)/2 - balls.get(i).diameter / 2 - 1, 2, -2, balls.get(i).diameter / 2);
									b.start();
									balls.add(b);
									b = new Ball(canvas, (balls.get(i).x + balls.get(j).x)/2 + balls.get(i).diameter / 2 + 1, 
											(balls.get(i).y + balls.get(j).y)/2 + balls.get(i).diameter / 2 + 1, 2, 2, balls.get(i).diameter / 2);
									b.start();
									balls.add(b);
								}
								if(balls.get(j).diameter > 1) {
									Ball b = new Ball(canvas, (balls.get(i).x + balls.get(j).x)/2 - balls.get(j).diameter / 2 - 1, 
											(balls.get(i).y + balls.get(j).y)/2 - balls.get(j).diameter / 2 - 1, -2, -2, balls.get(j).diameter / 2);
									b.start();
									balls.add(b);
									b = new Ball(canvas, (balls.get(i).x + balls.get(j).x)/2 - balls.get(j).diameter / 2 - 1, 
											(balls.get(i).y + balls.get(j).y)/2 + balls.get(j).diameter / 2 + 1, -2, 2, balls.get(j).diameter / 2);
									b.start();
									balls.add(b);
								}
								break;
							}
						}
					}
					if (!balls.get(i).isAlive()) {
						balls.remove(i);
						i--;
					}
				}
				else {
					balls.remove(i);
					i--;
				}
			}
		}
	}
	
	//returnes true if distance of two ball's center coords are closer than the sum of radius
	public boolean BallCollided(Ball a, Ball b) {
		if (Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y)) <= a.diameter / 2 + b.diameter / 2) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args){  	
		BallThreadAssig_20201572 f = new BallThreadAssig_20201572();
      	f.setSize(400, 300);
      	WindowDestroyer listener = new WindowDestroyer();  
      	f.addWindowListener(listener);
      	f.setVisible(true);  
   }
  
class Ball extends Thread{  	
	private static Canvas box;
	private int diameter;
	private int x = 0;
	private int y = 0;
	private int dx = 2;
	private int dy = 2; 
	private boolean isCollided;
	
	public Ball(Canvas c, int x, int y, int dx, int dy, int diameter){ 
		box = c; 
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.diameter = diameter;
		isCollided = false;
	}
	
	public void draw(){
		  	Graphics g = box.getGraphics();
		  	g.fillOval(x - diameter/2, y - diameter/2, diameter, diameter);
		  	g.dispose();	
	}
	public void move(){
		Graphics g = box.getGraphics();
  		g.setXORMode(box.getBackground());
  		g.fillOval(x - diameter/2, y - diameter/2, diameter, diameter);
  		x += dx;	
  		y += dy;
  		Dimension d = box.getSize();
  		if (x < 0) { x = 0; dx = -dx; }												//ball hits left wall, reverse x movement
  		if (x + diameter/2 >= d.width) { x = d.width - diameter/2; dx = -dx; }		//ball hits right wall
  		if (y < 0) { y = 0; dy = -dy; }												//ball hits top wall
  		if (y + diameter/2 >= d.height) { y = d.height - diameter/2; dy = -dy; }	//ball hits bottom wall
  		g.fillOval(x - diameter/2, y - diameter/2, diameter, diameter);
  		g.dispose();	
  	}
	
	public void run(){  	
		draw();
  		while (!isCollided){  	
  			move();
     		try { 
     			Thread.sleep(5); 
     		} 
     		catch(InterruptedException e) {}
     		}  
  		}
	}
}

class WindowDestroyer extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
     		System.exit(0);
     	}
}