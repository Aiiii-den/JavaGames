

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

	public boolean upPressed, downPressed, leftPressed, rightPressed;

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code=e.getKeyCode();

		if(code==KeyEvent.VK_W || code==KeyEvent.VK_UP) {
			this.upPressed=true;
		}
		if(code==KeyEvent.VK_S || code==KeyEvent.VK_DOWN) {
			this.downPressed=true;
		}
		if(code==KeyEvent.VK_A || code==KeyEvent.VK_LEFT) {
			this.leftPressed=true;
		}
		if(code==KeyEvent.VK_D || code==KeyEvent.VK_RIGHT) {
			this.rightPressed=true;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code=e.getKeyCode();

		if(code==KeyEvent.VK_W || code==KeyEvent.VK_UP) {
			this.upPressed=false;
		}
		if(code==KeyEvent.VK_S || code==KeyEvent.VK_DOWN) {
			this.downPressed=false;
		}
		if(code==KeyEvent.VK_A || code==KeyEvent.VK_LEFT) {
			this.leftPressed=false;
		}
		if(code==KeyEvent.VK_D || code==KeyEvent.VK_RIGHT) {
			this.rightPressed=false;
		}

	}

}
