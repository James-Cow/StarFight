package starFight;

import java.awt.*;

public class Phoenix extends Ship {

	int length = 170, width = 150, speed = 7, Bspeed = 13, size = 60;

	public void drawPlane(Graphics g, Image p1, Image p2, Image p3, Image p4) {
		switch (direction) {
		case 1:
			PY -= speed;
			if (PY < 0) {
				PY = 800;
			}
			g.drawImage(p1, PX - 75, PY - 85, width, length, null);
			rect = new Rectangle(PX - 70, PY - 70, 140, 110);
			break;
		case 2:
			PY += speed;
			if (PY > 800) {
				PY = 0;
			}
			g.drawImage(p2, PX - 75, PY - 85, width, length, null);
			rect = new Rectangle(PX - 70, PY - 40, 140, 110);
			break;
		case 3:
			PX -= speed;
			if (PX < 0) {
				PX = 1400;
			}
			g.drawImage(p3, PX - 85, PY - 75, length, width, null);
			rect = new Rectangle(PX - 70, PY - 70, 110, 140);
			break;
		case 4:
			PX += speed;
			if (PX > 1400) {
				PX = 0;
			}
			g.drawImage(p4, PX - 85, PY - 75, length, width, null);
			rect = new Rectangle(PX - 40, PY - 70, 110, 140);
			break;
		}
	}

	public void drawBullet(Graphics g, Image p5) {
		if (fire == true) {
			if (fireSetup == true) {
				BulletX = PX;
				BulletY = PY;
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
			Brect = new Rectangle(BulletX - size / 3, BulletY - size / 3, size * 2 / 3, size * 2 / 3);
			g.drawImage(p5, BulletX - size / 2, BulletY - size / 2, size, size, null);
		}
	}
}
