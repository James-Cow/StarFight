package starFight;
import java.awt.*;

public class Tempest extends Ship {
	int length = 500, width = 400, speed, Bspeed = 8, enlarge = 0, enlargeSpeed = 2;
	boolean charging = false, increase = true;

	public void drawPlane(Graphics g, Image p1, Image p2, Image p3, Image p4) {
		switch (direction) {
		case 1:
			PY -= speed;
			if (PY < 0) {
				PY = 800;
			}
			rect = new Rectangle(PX - 80, PY + 50, 160, 100);
			g.drawImage(p1, PX - 200, PY - 250, width, length, null);
			break;
		case 2:
			PY += speed;
			if (PY > 800) {
				PY = 0;
			}
			rect = new Rectangle(PX - 80, PY - 150, 160, 100);
			g.drawImage(p2, PX - 200, PY - 250, width, length, null);
			break;
		case 3:
			PX -= speed;
			if (PX < 0) {
				PX = 1400;
			}
			rect = new Rectangle(PX + 50, PY - 80, 100, 160);
			g.drawImage(p3, PX - 250, PY - 200, length, width, null);
			break;
		case 4:
			PX += speed;
			if (PX > 1400) {
				PX = 0;
			}
			rect = new Rectangle(PX - 150, PY - 80, 100, 160);
			g.drawImage(p4, PX - 250, PY - 200, length, width, null);
			break;
		}
	}

	public void drawBullet(Graphics g, Image p5) {
		if (charging == true) {
			speed = 2;
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
			g.drawImage(p5, (int) (BulletX - (60 + enlarge) / 2), (int) (BulletY - (60 + enlarge) / 2), 60 + enlarge,
					60 + enlarge, null);
			Brect = new Rectangle(0, 0, 0, 0);
			if (enlarge < 300) {
				if (increase == true) {
					enlarge += enlargeSpeed;
				} else if (increase == false) {
					enlarge -= enlargeSpeed;
				}
			}
			if (enlarge >= 300) {
				increase = false;
				enlarge -= 2;
			}
			if (enlarge <= 30) {
				increase = true;
				enlarge += 2;
			}
			bulletStrength = 0;
		} else {
			speed = 6;
		}

		if (fire == true) {
			if (fireSetup == true) {
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
			Brect = new Rectangle((int) (BulletX + 30 - (60 + enlarge) / 2), (int) (BulletY + 30 - (60 + enlarge) / 2),
					enlarge, enlarge);
			g.drawImage(p5, (int) (BulletX - (60 + enlarge) / 2), (int) (BulletY - (60 + enlarge) / 2), 60 + enlarge,
					60 + enlarge, null);
			if (enlarge > 150) {
				bulletStrength = 2;
				if (enlarge > 290) {
					bulletStrength = 3;
				}
			} else {
				bulletStrength = 1;
			}
		}
	}
}
