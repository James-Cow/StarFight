package starFight;
import java.awt.*;

public class Scout extends Ship {

	int length = 200, width = 120, speed = 10, Bspeed = 25;

	public void drawPlane(Graphics g, Image p1, Image p2, Image p3, Image p4) {
		switch (direction) {
		case 1:
			PY -= speed;
			if (PY < 0) {
				PY = 800;
			}
			g.drawImage(p1, PX - 60, PY - 100, width, length, null);
			rect = new Rectangle(PX - 25, PY - 80, 50, 170);
			break;
		case 2:
			PY += speed;
			if (PY > 800) {
				PY = 0;
			}
			g.drawImage(p2, PX - 60, PY - 100, width, length, null);
			rect = new Rectangle(PX - 25, PY - 90, 50, 170);
			break;
		case 3:
			PX -= speed;
			if (PX < 0) {
				PX = 1400;
			}
			g.drawImage(p3, PX - 100, PY - 60, length, width, null);
			rect = new Rectangle(PX - 80, PY - 25, 170, 50);
			break;
		case 4:
			PX += speed;
			if (PX > 1400) {
				PX = 0;
			}
			g.drawImage(p4, PX - 100, PY - 60, length, width, null);
			rect = new Rectangle(PX - 90, PY - 25, 170, 50);
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
				g.setColor(Color.red);
				g.fillRect(BulletX - 10, BulletY - 15, 20, 50);
				g.setColor(Color.yellow);
				int[] p1xB1 = { BulletX + 10, BulletX, BulletX - 10 };
				int[] p1yB1 = { BulletY - 15, BulletY - 35, BulletY - 15 };
				g.fillPolygon(p1xB1, p1yB1, 3);
				Brect = new Rectangle(BulletX - 10, BulletY - 35, 20, 70);
				break;
			case 2:
				BulletY += Bspeed;
				if (BulletY > 800) {
					BulletY = 0;
				}
				g.setColor(Color.red);
				g.fillRect(BulletX - 10, BulletY - 35, 20, 50);
				g.setColor(Color.yellow);
				int[] p1xB2 = { BulletX + 10, BulletX, BulletX - 10 };
				int[] p1yB2 = { BulletY + 15, BulletY + 35, BulletY + 15 };
				g.fillPolygon(p1xB2, p1yB2, 3);
				Brect = new Rectangle(BulletX - 10, BulletY - 35, 20, 70);
				break;
			case 3:
				BulletX -= Bspeed;
				if (BulletX < 0) {
					BulletX = 1400;
				}
				g.setColor(Color.red);
				g.fillRect(BulletX - 15, BulletY - 10, 50, 20);
				g.setColor(Color.yellow);
				int[] p1xB3 = { BulletX - 15, BulletX - 35, BulletX - 15 };
				int[] p1yB3 = { BulletY - 10, BulletY, BulletY + 10 };
				g.fillPolygon(p1xB3, p1yB3, 3);
				Brect = new Rectangle(BulletX - 35, BulletY - 10, 70, 20);
				break;
			case 4:
				BulletX += Bspeed;
				if (BulletX > 1400) {
					BulletX = 0;
				}
				g.setColor(Color.red);
				g.fillRect(BulletX - 35, BulletY - 10, 50, 20);
				g.setColor(Color.yellow);
				int[] p1xB4 = { BulletX + 15, BulletX + 35, BulletX + 15 };
				int[] p1yB4 = { BulletY - 10, BulletY, BulletY + 10 };
				g.fillPolygon(p1xB4, p1yB4, 3);
				Brect = new Rectangle(BulletX - 35, BulletY - 10, 70, 20);
				break;
			}
		}
	}
}
