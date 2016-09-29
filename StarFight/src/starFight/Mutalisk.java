package starFight;
import java.awt.*;

public class Mutalisk extends Ship {

	int length = 180, width = 300, speed = 15, Bspeed = 15;
	boolean explode = false;

	public void drawPlane(Graphics g, Image p1, Image p2, Image p3, Image p4) {
		switch (direction) {
		case 1:
			PY -= speed;
			if (PY < 0) {
				PY = 800;
			}
			rect = new Rectangle(PX - 30, PY - 50, 60, 100);
			g.drawImage(p1, PX - 150, PY - 90, width, length, null);
			break;
		case 2:
			PY += speed;
			if (PY > 800) {
				PY = 0;
			}
			rect = new Rectangle(PX - 30, PY - 50, 60, 100);
			g.drawImage(p2, PX - 150, PY - 90, width, length, null);
			break;
		case 3:
			PX -= speed;
			if (PX < 0) {
				PX = 1400;
			}
			rect = new Rectangle(PX - 50, PY - 30, 100, 60);
			g.drawImage(p3, PX - 90, PY - 150, length, width, null);
			break;
		case 4:
			PX += speed;
			if (PX > 1400) {
				PX = 0;
			}
			rect = new Rectangle(PX - 50, PY - 30, 100, 60);
			g.drawImage(p4, PX - 90, PY - 150, length, width, null);
			break;
		}
	}

	public void drawBullet(Graphics g, Image p5) {
		if (explode == true) {
			Brect = new Rectangle(BulletX - 60, BulletY - 60, 120, 120);
			g.drawImage(p5, BulletX - 90, BulletY - 90, 180, 180, null);
		} else if (fire == true) {
			if (fireSetup == true) {
				switch (direction) {
				case 1:
					BulletX = PX;
					BulletY = PY - 30;
					break;
				case 2:
					BulletX = PX;
					BulletY = PY + 30;
					break;
				case 3:
					BulletX = PX - 30;
					BulletY = PY;
					break;
				case 4:
					BulletX = PX + 30;
					BulletY = PY;
					break;
				}
				Bdirection = direction;
				fireSetup = false;
			}
			switch (Bdirection) {
			case 1:
				BulletY -= Bspeed;
				if (BulletY < 0) {
					BulletY = 800;
				}
				break;
			case 2:
				BulletY += Bspeed;
				if (BulletY > 800) {
					BulletY = 0;
				}
				break;
			case 3:
				BulletX -= Bspeed;
				if (BulletX < 0) {
					BulletX = 1400;
				}
				break;
			case 4:
				BulletX += Bspeed;
				if (BulletX > 1400) {
					BulletX = 0;
				}
				break;
			}
			Brect = new Rectangle(BulletX - 15, BulletY - 15, 30, 30);
			g.drawImage(p5, BulletX - 15, BulletY - 15, 30, 30, null);
		}
	}
}
