package starFight;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Ship {

	boolean fire, fireSetup, ready;
	int direction, Bdirection, PX, PY, BulletX, BulletY, cooldown, bulletStrength;
	Rectangle rect, Brect;
	Timer CooldownTimer;

	class CooldownCount extends TimerTask {
		public void run() {
			ready = true;
			CooldownTimer.cancel();
		}
	}

	public void CoolingDown(int num) {
		CooldownTimer = new Timer();
		CooldownTimer.schedule(new CooldownCount(), num);
	}

	public void PositionSetup(int num) {
		fire = false;
		fireSetup = false;
		Brect = new Rectangle(0, 0, 0, 0);
		rect = new Rectangle(0, 0, 0, 0);
		ready = true;
		if (num == 1) {
			direction = 4;
			PX = 200;
			PY = 600;
		} else if (num == 2) {
			direction = 3;
			PX = 1200;
			PY = 200;
		}
	}

	public abstract void drawPlane(Graphics g, Image p1, Image p2, Image p3, Image p4);

	public abstract void drawBullet(Graphics g, Image p5);
}

// g.fillRect((int) rect.getX(), (int) rect.getY(), (int) rect.getWidth(), (int) rect.getHeight());