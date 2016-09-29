package starFight;
import java.awt.*;

public class Raven extends Ship {

	int length = 240, width = 200, speed = 10, number = 0;
	Rectangle[] Brects = new Rectangle[5];
	int[] bulletStrength = new int[5];

	public void drawPlane(Graphics g, Image p1, Image p2, Image p3, Image p4) {
		switch (direction) {
		case 1:
			PY -= speed;
			if (PY < 0) {
				PY = 800;
			}
			rect = new Rectangle(PX - 60, PY - 80, 120, 140);
			g.drawImage(p1, PX - 100, PY - 120, width, length, null);
			break;
		case 2:
			PY += speed;
			if (PY > 800) {
				PY = 0;
			}
			rect = new Rectangle(PX - 60, PY - 60, 120, 140);
			g.drawImage(p2, PX - 100, PY - 120, width, length, null);
			break;
		case 3:
			PX -= speed;
			if (PX < 0) {
				PX = 1400;
			}
			rect = new Rectangle(PX - 80, PY - 60, 140, 120);
			g.drawImage(p3, PX - 120, PY - 100, length, width, null);
			break;
		case 4:
			PX += speed;
			if (PX > 1400) {
				PX = 0;
			}
			rect = new Rectangle(PX - 60, PY - 60, 140, 120);
			g.drawImage(p4, PX - 120, PY - 100, length, width, null);
			break;
		}
	}

	public void drawBullet(Graphics g, Image p5) {
		if (fire == true) {
			if (fireSetup == true) {
				switch (direction) {
				case 1:
					BulletX = PX;
					BulletY = PY + 60;
					break;
				case 2:
					BulletX = PX;
					BulletY = PY - 60;
					break;
				case 3:
					BulletX = PX + 60;
					BulletY = PY;
					break;
				case 4:
					BulletX = PX - 60;
					BulletY = PY;
					break;
				}
				fireSetup = false;
			}
			Brects[number] = new Rectangle(BulletX - 50, BulletY - 50, 100, 100);
			g.drawImage(p5, BulletX - 50, BulletY - 50, 100, 100, null);
		}
	}
}