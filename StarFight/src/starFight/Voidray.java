package starFight;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Voidray extends Ship {

	int length = 240, width = 120, speed = 7, chargeTime = 2000;
	Timer ChargeTimer;
	boolean charging = false, fired = false;

	class Charging extends TimerTask {
		public void run() {
			fired = true;
			charging = false;
			ChargeTimer.cancel();
		}
	}

	public void Charge() {
		ChargeTimer = new Timer();
		ChargeTimer.schedule(new Charging(), chargeTime);
	}

	public void drawPlane(Graphics g, Image p1, Image p2, Image p3, Image p4) {
		switch (direction) {
		case 1:
			PY -= speed;
			if (PY < 0) {
				PY = 800;
			}
			rect = new Rectangle(PX - 35, PY - 90, 70, 190);
			g.drawImage(p1, PX - 60, PY - 120, width, length, null);
			break;
		case 2:
			PY += speed;
			if (PY > 800) {
				PY = 0;
			}
			rect = new Rectangle(PX - 35, PY - 100, 70, 190);
			g.drawImage(p2, PX - 60, PY - 120, width, length, null);
			break;
		case 3:
			PX -= speed;
			if (PX < 0) {
				PX = 1400;
			}
			rect = new Rectangle(PX - 90, PY - 35, 190, 70);
			g.drawImage(p3, PX - 120, PY - 60, length, width, null);
			break;
		case 4:
			PX += speed;
			if (PX > 1400) {
				PX = 0;
			}
			rect = new Rectangle(PX - 100, PY - 35, 190, 70);
			g.drawImage(p4, PX - 120, PY - 60, length, width, null);
			break;
		}
	}

	public void drawBullet(Graphics g, Image p5) {
		if (charging == true) {
			switch (direction) {
			case 1:
				BulletX = PX;
				BulletY = PY - 90;
				break;
			case 2:
				BulletX = PX;
				BulletY = PY + 90;
				break;
			case 3:
				BulletX = PX - 90;
				BulletY = PY;
				break;
			case 4:
				BulletX = PX + 90;
				BulletY = PY;
				break;
			}
			Bdirection = direction;
			g.setColor(Color.cyan);
			switch (Bdirection) {
			case 1:
				g.fillRect(BulletX - 5, BulletY - 15, 10, 60);
				break;
			case 2:
				g.fillRect(BulletX - 5, BulletY - 45, 10, 60);
				break;
			case 3:
				g.fillRect(BulletX - 15, BulletY - 5, 60, 10);
				break;
			case 4:
				g.fillRect(BulletX - 45, BulletY - 5, 60, 10);
				break;
			}
		}

		if (fired == true) {
			g.setColor(Color.cyan);
			switch (Bdirection) {
			case 1:
				g.fillRect(BulletX - 5, 0, 10, 800);
				Brect = new Rectangle(BulletX - 5, 0, 10, 800);
				break;
			case 2:
				g.fillRect(BulletX - 5, 0, 10, 800);
				Brect = new Rectangle(BulletX - 5, 0, 10, 800);
				break;
			case 3:
				g.fillRect(0, BulletY - 5, 1400, 10);
				Brect = new Rectangle(0, BulletY - 5, 1400, 10);
				break;
			case 4:
				g.fillRect(0, BulletY - 5, 1400, 10);
				Brect = new Rectangle(0, BulletY - 5, 1400, 10);
				break;
			}
		} else {
			Brect = new Rectangle(0, 0, 0, 0);
		}
	}
}