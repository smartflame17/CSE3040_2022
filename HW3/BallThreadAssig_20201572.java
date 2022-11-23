import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class BallThreadAssig_20201572 extends Frame implements ActionListener{  	 
	private Canvas canvas; 
	private ArrayList<Ball> balls;
	
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
			//Ball b = new Ball(canvas, canvas.getWidth() / 2 + 16, canvas.getHeight() / 2 + 16, 2, 2, 16);
         	//b.start();
         	//balls.add(b);
         	//b = new Ball(canvas, canvas.getWidth() / 2 - 16, canvas.getHeight() / 2 + 16, -2, 2, 16);
         	//b.start();
         	//balls.add(b);
         	//b = new Ball(canvas, canvas.getWidth() / 2, canvas.getHeight() / 2 - 16, 0, -2, 16);
         	//b.start();
         	//balls.add(b);
         	Ball b = new Ball(canvas, canvas.getWidth() / 2 + 16, canvas.getHeight() / 2, 2, 0, 16);
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
				//if (balls.get(i).isAlive()) {								//if current ball is still active
					for (int j = i+1; j < balls.size(); j++) {
						//if (balls.get(j).isAlive()) {
							if (BallCollided(balls.get(i), balls.get(j))) {
								balls.get(i).isCollided = true;
								balls.get(j).isCollided = true;
								switch(GetCollisionType(balls.get(i), balls.get(j))) {
								case 0:{	//horizontal collision
									if (balls.get(i).x > balls.get(j).x){
										HandleHorizontalCollision(balls.get(i), balls.get(j));
									}
									else HandleHorizontalCollision(balls.get(j), balls.get(i));
									break;
								}
								case 1:{	//vertical collision
									if (balls.get(i).y < balls.get(j).y) {
										HandleVerticalCollision(balls.get(i), balls.get(j));
									}
									else HandleVerticalCollision(balls.get(j), balls.get(i));
									break;
								}
								}
								balls.remove(i);
								balls.remove(j);
								break;
							}
						//}
					}
					break;
					//if (!balls.get(i).isAlive()) {
					//	balls.remove(i);
					//	i--;
					//}
				//}
				//else {
				//	balls.remove(i);
				//	i--;
				//}
			}
		}
	}
	
	//returns true if distance of two ball's center coords are closer than the sum of radius
	public boolean BallCollided(Ball a, Ball b) {
		if ((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y) <= 
				(a.diameter / 2 + b.diameter / 2) * (a.diameter / 2 + b.diameter / 2)) {
			return true;
		}
		return false;
	}
	
	//returns type of collision depending on difference of coordinates
	public int GetCollisionType(Ball a, Ball b) {
		int xDiff = Math.abs(a.x - b.x);
		int yDiff = Math.abs(a.y - b.y);
		
		if(xDiff >= yDiff) {		//collided more horizontally
			return 0;
		}
		else return 1;				//collided more vertically
	}
	
	
	public void HandleHorizontalCollision(Ball r, Ball l) {	//r is in right side than l
		if(r.diameter > 1) {
			Ball b = new Ball(canvas, (r.x + l.x)/2 + r.diameter/4 + 1, 
					(r.y + l.y)/2 - r.diameter/4 - 1, 2, -2, r.diameter/2);
			b.start();
			balls.add(b);
			b = new Ball(canvas, (r.x + l.x)/2 + r.diameter/4 + 1, 
					(r.y + l.y)/2 + r.diameter/4 + 1, 2, 2, r.diameter/2);
			b.start();
			balls.add(b);
		}
		if(l.diameter > 1) {
			Ball b = new Ball(canvas, (r.x + l.x)/2 - l.diameter/4 - 1, 
					(r.y + l.y)/2 - l.diameter/4 - 1, -2, -2, l.diameter/2);
			b.start();
			balls.add(b);
			b = new Ball(canvas, (r.x + l.x)/2 - l.diameter/4 - 1, 
					(r.y + l.y)/2 + l.diameter/4 + 1, -2, 2, l.diameter/2);
			b.start();
			balls.add(b);
		}
	}
	
	
	public void HandleVerticalCollision(Ball r, Ball l) {	//r is higher than l
		if(r.diameter > 1) {
			Ball b = new Ball(canvas, (r.x + l.x)/2 - r.diameter/4 - 1, 
					(r.y + l.y)/2 - r.diameter/4 - 1, -2, -2, r.diameter/2);
			b.start();
			balls.add(b);
			b = new Ball(canvas, (r.x + l.x)/2 + r.diameter/4 + 1, 
					(r.y + l.y)/2 - r.diameter/4 - 1, 2, -2, r.diameter/2);
			b.start();
			balls.add(b);
		}
		if(l.diameter > 1) {
			Ball b = new Ball(canvas, (r.x + l.x)/2 - l.diameter/4 - 1, 
					(r.y + l.y)/2 + l.diameter/4 + 1, -2, 2, l.diameter/2);
			b.start();
			balls.add(b);
			b = new Ball(canvas, (r.x + l.x)/2 + l.diameter/4 + 1, 
					(r.y + l.y)/2 + l.diameter/4 + 1, 2, 2, l.diameter/2);
			b.start();
			balls.add(b);
		}
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
	private int x;
	private int y;
	private int dx;
	private int dy; 
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
     			Thread.sleep(20); 
     		} 
     		catch(InterruptedException e) {}
     	}
  		Graphics g = box.getGraphics();
  		g.setColor(box.getBackground());
  		g.fillOval(x - diameter/2, y - diameter/2, diameter, diameter);
  		g.dispose();
  	}
		
		
	}
}

