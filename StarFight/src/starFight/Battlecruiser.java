package starFight;
import java.awt.*;

public class Battlecruiser extends Ship {

	int length = 250, width = 180, speed = 5, Bspeed = 20;

	public void drawPlane(Graphics g, Image p1, Image p2, Image p3, Image p4) {
		switch (direction) {
		case 1:
			PY -= speed;
			if (PY < 0) {
				PY = 800;
			}
			rect = new Rectangle(PX - 80, PY - 95, 160, 190);
			g.drawImage(p1, PX - 90, PY - 125, width, length, null);
			break;
		case 2:
			PY += speed;
			if (PY > 800) {
				PY = 0;
			}
			rect = new Rectangle(PX - 80, PY - 95, 160, 190);
			g.drawImage(p2, PX - 90, PY - 125, width, length, null);
			break;
		case 3:
			PX -= speed;
			if (PX < 0) {
				PX = 1400;
			}
			rect = new Rectangle(PX - 95, PY - 80, 190, 160);
			g.drawImage(p3, PX - 125, PY - 90, length, width, null);
			break;
		case 4:
			PX += speed;
			if (PX > 1400) {
				PX = 0;
			}
			rect = new Rectangle(PX - 95, PY - 80, 190, 160);
			g.drawImage(p4, PX - 125, PY - 90, length, width, null);
			break;
		}
	}

	public void drawBullet(Graphics g, Image p5) {
		if (fire == true) {
			if (fireSetup == true) {
				switch (direction) {
				case 1:
					BulletX = PX;
					BulletY = PY - 80;
					break;
				case 2:
					BulletX = PX;
					BulletY = PY + 80;
					break;
				case 3:
					BulletX = PX - 80;
					BulletY = PY;
					break;
				case 4:
					BulletX = PX + 80;
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
			Brect = new Rectangle(BulletX - 60, BulletY - 60, 120, 120);
			g.drawImage(p5, BulletX - 90, BulletY - 90, 180, 180, null);
		}
	}
}
