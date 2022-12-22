
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.plaf.synth.SynthOptionPaneUI;

public class GamePanel extends JPanel implements Runnable{
	
	//SCREEN SETTINGS
	final int originalTileSize=16; //16x16 tile --> default size of characters
	final int scale=3;
	
	final int tileSize=originalTileSize*scale; //48x48 tile --> actual tile size
	final int maxScreenCol=16; //16 tiles horizontally
	final int maxScreenRow=12; //12 tiles vertically
	final int screenWidth=tileSize*maxScreenCol; //768 pixels
	final int screenHeight=tileSize*maxScreenRow; //576 pixels
	
	//FPS
	final int FPS=60;
	
	KeyHandler keyH=new KeyHandler();
	Thread gameThread;
	
	//Set player's default position
	int playerX=100;
	int playerY=100;
	int playerSpeed=4;
	
	//CONSTRUTOR
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(this.keyH);
		this.setFocusable(true);
	}

	public void startGameThread() {
		
		gameThread=new Thread(this);
		gameThread.start();
	}

	@Override 
	public void run() { //SLEEP METHOD
		
		
		double drawInterval=1000000000/FPS; // redraw every 0.016 seconds
		double nextDrawTime=System.nanoTime()+drawInterval;
		
		while(this.gameThread!=null) {
			
			//1 UPDATE: update information, such as character position
			this.update();
	
			//2 DRAW: draw the screen with the updated information
			this.repaint();		
			
			try {
				double remainingTime=nextDrawTime-System.nanoTime();
				remainingTime/=1000000;
				
				if(remainingTime<=0) {
					remainingTime=0;
				}
				
				Thread.sleep((long) remainingTime);
				nextDrawTime+=drawInterval;
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
//	@Override		
//	public void run() { DELTA METHOD
//	
//		double drawInterval=1000000000/this.FPS;
//		double delta=0;
//		long lastTime=System.nanoTime();
//		long currentTime;	
//		long timer=0;
//		int drawCount=0;
//		
//		while(this.gameThread!=null) {
//			currentTime=System.nanoTime();
//			delta+=(currentTime-lastTime)/drawInterval;
//			timer+=(currentTime-lastTime);
//			lastTime=currentTime;
//			
//			if(delta>1) {
//				update();
//				repaint();
//				delta--;
//				drawCount++;
//			}
//			
//			if(timer>=100000000) {
//				System.out.println("FPS: "+drawCount);
//				drawCount=0;
//				timer=0;
//			}
//		
//		}
//	}
	
	public void update() {
		
		if(this.keyH.upPressed==true) {
			this.playerY-=this.playerSpeed;
		}
		else if(this.keyH.downPressed==true) {
			this.playerY+=this.playerSpeed;
		}
		else if(this.keyH.leftPressed==true) {
			this.playerX-=this.playerSpeed;
		}
		else if(this.keyH.rightPressed==true) {
			this.playerX+=this.playerSpeed;
		}
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.WHITE);
		g2.fillRect(this.playerX, this.playerY, this.tileSize, this.tileSize);
		g2.dispose();
	}

}
