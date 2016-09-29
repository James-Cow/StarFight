package starFight;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

public class StarFight extends Applet implements MouseListener, MouseMotionListener, KeyListener {
	Font HSFont = new Font("Times New Roman", Font.BOLD, 100);
	Font HSFont1 = new Font("Times New Roman", Font.BOLD, 50);
	Font HSFont3 = new Font("Times New Roman", Font.PLAIN, 20);

	Graphics bufferGraphics;
	Image offscreen;
	Dimension dim;

	Timer LoadingTimer;

	Image mouse, gameScreen, explode, wasd, ijkl, background, loading, omg, homeButton;

	AudioClip[] battleThemes = new AudioClip[3];
	AudioClip victoryTheme;

	Image airship[][] = new Image[7][5];

	boolean game, exploded, hit1, hit2, hit, gameStart, menu, Pressed, load, player1choose, player2choose, runOnce,
			drawHomeButton, HBPressed, fight, fightPressed;

	int X, Y, gameCounter, time, explodeX, explodeY, p1Counter, p2Counter, winner, timer, p1, p2, HSPressed, HSChoice,
			randomNumber, playerChoice;

	Scout scout = new Scout();
	Phoenix phoenix = new Phoenix();
	Battlecruiser battlecruiser = new Battlecruiser();
	Tempest tempest = new Tempest();
	Voidray voidray = new Voidray();
	Mutalisk mutalisk = new Mutalisk();
	Raven raven = new Raven();
	Ship ship[] = new Ship[7];

	Random generator = new Random();

	public void init() {
		this.setSize(1400, 800); // size of applet
		setLayout(null); // determination to hard code everything

		menu = true;
		gameCounter = 0;
		game = true;
		p1Counter = 0;
		p2Counter = 0;
		winner = 0;

		ship[0] = scout;
		ship[1] = phoenix;
		ship[2] = battlecruiser;
		ship[3] = tempest;
		ship[4] = voidray;
		ship[5] = mutalisk;
		ship[6] = raven;

		scout.cooldown = 2000;
		phoenix.cooldown = 2500;
		battlecruiser.cooldown = 3500;
		tempest.cooldown = 0;
		voidray.cooldown = 3000;
		mutalisk.cooldown = 2000;
		raven.cooldown = 1000;

		scout.bulletStrength = 1;
		phoenix.bulletStrength = 1;
		battlecruiser.bulletStrength = 2;
		voidray.bulletStrength = 1;
		mutalisk.bulletStrength = 1;

		dim = getSize();
		offscreen = createImage(dim.width, dim.height);
		bufferGraphics = offscreen.getGraphics();

		omg = getImage(getCodeBase(), "OMG.gif");
		background = getImage(getCodeBase(), "Background.gif");
		loading = getImage(getCodeBase(), "Loading.gif");
		mouse = getImage(getCodeBase(), "Protoss.png");
		homeButton = getImage(getCodeBase(), "Battlecruiser.png");
		gameScreen = getImage(getCodeBase(), "GameScreen.jpg");
		airship[0][0] = getImage(getCodeBase(), "Scout1.png");
		airship[0][1] = getImage(getCodeBase(), "Scout2.png");
		airship[0][2] = getImage(getCodeBase(), "Scout3.png");
		airship[0][3] = getImage(getCodeBase(), "Scout4.png");
		airship[0][4] = getImage(getCodeBase(), "RavenBullet.png");
		airship[1][0] = getImage(getCodeBase(), "Phoenix1.png");
		airship[1][1] = getImage(getCodeBase(), "Phoenix2.png");
		airship[1][2] = getImage(getCodeBase(), "Phoenix3.png");
		airship[1][3] = getImage(getCodeBase(), "Phoenix4.png");
		airship[1][4] = getImage(getCodeBase(), "PhoenixBullet.png");
		airship[2][0] = getImage(getCodeBase(), "Battlecruiser1.png");
		airship[2][1] = getImage(getCodeBase(), "Battlecruiser2.png");
		airship[2][2] = getImage(getCodeBase(), "Battlecruiser3.png");
		airship[2][3] = getImage(getCodeBase(), "Battlecruiser4.png");
		airship[2][4] = getImage(getCodeBase(), "BattlecruiserBullet.png");
		airship[3][0] = getImage(getCodeBase(), "Tempest1.png");
		airship[3][1] = getImage(getCodeBase(), "Tempest2.png");
		airship[3][2] = getImage(getCodeBase(), "Tempest3.png");
		airship[3][3] = getImage(getCodeBase(), "Tempest4.png");
		airship[3][4] = getImage(getCodeBase(), "TempestBullet.png");
		airship[4][0] = getImage(getCodeBase(), "Voidray1.png");
		airship[4][1] = getImage(getCodeBase(), "Voidray2.png");
		airship[4][2] = getImage(getCodeBase(), "Voidray3.png");
		airship[4][3] = getImage(getCodeBase(), "Voidray4.png");
		airship[4][4] = getImage(getCodeBase(), "RavenBullet.png");
		airship[5][0] = getImage(getCodeBase(), "Mutalisk1.png");
		airship[5][1] = getImage(getCodeBase(), "Mutalisk2.png");
		airship[5][2] = getImage(getCodeBase(), "Mutalisk3.png");
		airship[5][3] = getImage(getCodeBase(), "Mutalisk4.png");
		airship[5][4] = getImage(getCodeBase(), "MutaliskBullet.png");
		airship[6][0] = getImage(getCodeBase(), "Raven1.png");
		airship[6][1] = getImage(getCodeBase(), "Raven2.png");
		airship[6][2] = getImage(getCodeBase(), "Raven3.png");
		airship[6][3] = getImage(getCodeBase(), "Raven4.png");
		airship[6][4] = getImage(getCodeBase(), "RavenBullet.png");
		explode = getImage(getCodeBase(), "Explode.png");
		wasd = getImage(getCodeBase(), "wasd.png");
		ijkl = getImage(getCodeBase(), "ijkl.png");

		battleThemes[0] = getAudioClip(getCodeBase(), "Theme1.wav");
		battleThemes[1] = getAudioClip(getCodeBase(), "Theme2.wav");
		battleThemes[2] = getAudioClip(getCodeBase(), "Theme3.wav");
		victoryTheme = getAudioClip(getCodeBase(), "Victory.wav");

		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
	}

	public void Sleep(int num)
	// using one line of "sleep" to replace 8 lines of thread.sleep
	{
		try {
			Thread.sleep(num);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	public void SetFalse() {
		menu = false;
		game = false;
		player1choose = false;
		player2choose = false;
		fight = false;
		drawHomeButton = false;
		p1Counter = 0;
		p2Counter = 0;
		winner = 0;
		battleThemes[0].stop();
		battleThemes[1].stop();
		battleThemes[2].stop();
		victoryTheme.stop();
	}

	public void PositionSetup() {
		gameStart = true;
		timer = 3;
		ship[p1].PositionSetup(1);
		ship[p2].PositionSetup(2);
		tempest.enlarge = 0;
		tempest.charging = false;
		voidray.fired = false;
		mutalisk.explode = false;
		for (int i = 0; i < 5; i++) {
			raven.Brects[i] = new Rectangle(0, 0, 0, 0);
			raven.bulletStrength[i] = 0;
		}
		phoenix.size = 60;
		phoenix.Bspeed = 13;
		phoenix.speed = 7;
		battleThemes[randomNumber].stop();
		randomNumber = generator.nextInt(3);
		battleThemes[randomNumber].play();
	}

	public void GameRunning(Graphics g) {
		g.drawImage(gameScreen, 0, 0, 1400, 800, this); // gameing background

		if (timer < 0) {
			gameStart = false;
		}

		if (gameStart == true) {
			g.setColor(Color.white);
			g.setFont(HSFont);
			g.drawString(timer + "", 1180, 130);
			g.drawString(timer + "", 150, 730);
			timer--;
			Sleep(1000);
		} else {
			if (p1 == 1 || p2 == 1) {
				if (phoenix.ready == true) {
					if (phoenix.Brect.intersects(phoenix.rect)) {
						if (phoenix.size < 180) {
							phoenix.size += 30;
							phoenix.Bspeed += 2;
							phoenix.speed += 2;
						}
						phoenix.fire = false;
						phoenix.Brect = new Rectangle(0, 0, 0, 0);
					}
				}
			}

			if (ship[p1].rect.intersects(ship[p2].rect)) {
				exploded = true;
				time = 0;
				explodeX = (int) ship[p1].rect.getX();
				explodeY = (int) ship[p1].rect.getY();
				PositionSetup();
				hit = true;
			}

			if (ship[p2].Brect.intersects(ship[p1].rect)) {
				exploded = true;
				time = 0;
				explodeX = (int) ship[p1].rect.getX();
				explodeY = (int) ship[p1].rect.getY();
				PositionSetup();
				hit2 = true;
			} else {
				ship[p1].drawPlane(g, airship[p1][0], airship[p1][1], airship[p1][2], airship[p1][3]);
			}

			if (ship[p1].Brect.intersects(ship[p2].rect)) {
				exploded = true;
				time = 0;
				explodeX = (int) ship[p2].rect.getX();
				explodeY = (int) ship[p2].rect.getY();
				PositionSetup();
				hit1 = true;
			} else {
				ship[p2].drawPlane(g, airship[p2][0], airship[p2][1], airship[p2][2], airship[p2][3]);
			}

			if (p1 == 6 || p2 == 6) {
				if (p1 == 6) {
					for (int i = 0; i < 5; i++) {
						if (raven.Brects[i].intersects(ship[p2].rect)) {
							exploded = true;
							time = 0;
							explodeX = (int) ship[p2].rect.getX();
							explodeY = (int) ship[p2].rect.getY();
							PositionSetup();
							hit1 = true;
						}
					}
					if (raven.Brects[raven.number].intersects(ship[p2].Brect)) {
						if (ship[p2].bulletStrength != 0) {
							if (raven.bulletStrength[raven.number] == ship[p2].bulletStrength) {
								raven.fire = false;
								ship[p2].fire = false;
								exploded = true;
								time = 0;
								explodeX = (int) raven.Brects[raven.number].getX();
								explodeY = (int) raven.Brects[raven.number].getY();
								raven.Brects[raven.number] = new Rectangle(0, 0, 0, 0);
								ship[p2].Brect = new Rectangle(0, 0, 0, 0);
								if (p2 == 4) {
									voidray.fired = false;
								}
								if (p2 == 5) {
									mutalisk.explode = false;
									mutalisk.ready = true;
								}
							} else if (raven.bulletStrength[raven.number] < ship[p2].bulletStrength) {
								raven.fire = false;
								exploded = true;
								time = 0;
								explodeX = (int) raven.Brects[raven.number].getX();
								explodeY = (int) raven.Brects[raven.number].getY();
								raven.Brects[raven.number] = new Rectangle(0, 0, 0, 0);
							}
						}
					}
				} else if (p2 == 6) {
					for (int i = 0; i < 5; i++) {
						if (raven.Brects[i].intersects(ship[p1].rect)) {
							exploded = true;
							time = 0;
							explodeX = (int) ship[p1].rect.getX();
							explodeY = (int) ship[p1].rect.getY();
							PositionSetup();
							hit2 = true;
						}
					}
					if (raven.Brects[raven.number].intersects(ship[p1].Brect)) {
						if (ship[p1].bulletStrength != 0) {
							if (raven.bulletStrength[raven.number] == ship[p1].bulletStrength) {
								raven.fire = false;
								ship[p1].fire = false;
								exploded = true;
								time = 0;
								explodeX = (int) raven.Brects[raven.number].getX();
								explodeY = (int) raven.Brects[raven.number].getY();
								raven.Brects[raven.number] = new Rectangle(0, 0, 0, 0);
								ship[p1].Brect = new Rectangle(0, 0, 0, 0);
								if (p1 == 4) {
									voidray.fired = false;
								}
								if (p1 == 5) {
									mutalisk.explode = false;
									mutalisk.ready = true;
								}
							} else if (raven.bulletStrength[raven.number] < ship[p1].bulletStrength) {
								raven.fire = false;
								exploded = true;
								time = 0;
								explodeX = (int) raven.Brects[raven.number].getX();
								explodeY = (int) raven.Brects[raven.number].getY();
								raven.Brects[raven.number] = new Rectangle(0, 0, 0, 0);
							}
						}
					}
				}
			} else if (ship[p1].Brect.intersects(ship[p2].Brect)) {
				if (ship[p1].bulletStrength != 0 && ship[p2].bulletStrength != 0) {
					if (ship[p1].bulletStrength == ship[p2].bulletStrength) {
						ship[p1].fire = false;
						ship[p2].fire = false;
						exploded = true;
						time = 0;
						explodeX = (int) ship[p1].Brect.getX();
						explodeY = (int) ship[p1].Brect.getY();
						ship[p1].Brect = new Rectangle(0, 0, 0, 0);
						ship[p2].Brect = new Rectangle(0, 0, 0, 0);
						if (p1 == 4 || p2 == 4) {
							voidray.fired = false;
						}
						if (p1 == 5 || p2 == 5) {
							mutalisk.explode = false;
							mutalisk.ready = true;
						}
					} else if (ship[p1].bulletStrength > ship[p2].bulletStrength) {
						ship[p2].fire = false;
						exploded = true;
						time = 0;
						explodeX = (int) ship[p1].Brect.getX();
						explodeY = (int) ship[p1].Brect.getY();
						ship[p2].Brect = new Rectangle(0, 0, 0, 0);
						if (p2 == 4) {
							voidray.fired = false;
						}
						if (p2 == 5) {
							mutalisk.explode = false;
							mutalisk.ready = true;
						}
					} else if (ship[p1].bulletStrength < ship[p2].bulletStrength) {
						ship[p1].fire = false;
						exploded = true;
						time = 0;
						explodeX = (int) ship[p1].Brect.getX();
						explodeY = (int) ship[p1].Brect.getY();
						ship[p1].Brect = new Rectangle(0, 0, 0, 0);
						if (p1 == 4) {
							voidray.fired = false;
						}
						if (p1 == 5) {
							mutalisk.explode = false;
							mutalisk.ready = true;
						}
					}
				}
			}

			ship[p1].drawBullet(g, airship[p1][4]);
			ship[p2].drawBullet(g, airship[p2][4]);

			Sleep(20);
		}

		if (hit == true)

		{
			if (p1Counter > 0)
				p1Counter--;
			if (p2Counter > 0)
				p2Counter--;
			hit = false;
		} else if (hit1 == true) {
			p1Counter++;
			hit1 = false;
		} else if (hit2 == true) {
			p2Counter++;
			hit2 = false;
		}

		g.setColor(Color.red);
		g.setFont(HSFont3);
		g.drawString("P1 score: " + p1Counter, 120, 40);
		g.drawString("P2 score: " + p2Counter, 1150, 40);

		if (time < 30 && exploded == true) {
			g.drawImage(explode, explodeX, explodeY, 200, 100, this);
			time++;
		}

		if (p1Counter == 5) {
			winner = 1;
			game = false;
			runOnce = true;
		} else if (p2Counter == 5) {
			winner = 2;
			game = false;
			runOnce = true;
		}

		repaint();
	}

	public void HomeScreen(Graphics g) {
		g.drawImage(background, 0, 0, 1400, 800, this);
		g.setFont(HSFont);
		g.setColor(Color.cyan);
		g.drawString("STAR FIGHTER 2", 280, 150);
		// set font and paints ^

		g.setColor(Color.white);
		switch (HSChoice) {
		case 1:
			if (HSPressed == 1) {
				g.fillRect(550, 200, 300, 100);
			} else {
				g.fillRect(540, 190, 320, 120);
			}
			break;
		case 2:
			if (HSPressed == 2) {
				g.fillRect(550, 370, 300, 100);
			} else {
				g.fillRect(540, 360, 320, 120);
			}
			break;
		case 3:
			if (HSPressed == 3) {
				g.fillRect(550, 540, 300, 100);
			} else {
				g.fillRect(540, 530, 320, 120);
			}
			break;
		}
		// gives white border for the button under the mouse, shrinks when
		// pressed

		if (HSPressed == 1) {
			g.setColor(Color.cyan);
			g.fillRect(560, 210, 280, 80);
			g.setColor(Color.black);
			g.fillRect(565, 215, 270, 70);
		} else {
			g.setColor(Color.cyan);
			g.fillRect(550, 200, 300, 100);
			g.setColor(Color.black);
			g.fillRect(555, 205, 290, 90);
		}
		if (HSPressed == 2) {
			g.setColor(Color.cyan);
			g.fillRect(560, 380, 280, 80);
			g.setColor(Color.black);
			g.fillRect(565, 385, 270, 70);
		} else {
			g.setColor(Color.cyan);
			g.fillRect(550, 370, 300, 100);
			g.setColor(Color.black);
			g.fillRect(555, 375, 290, 90);
		}
		if (HSPressed == 3) {
			g.setColor(Color.cyan);
			g.fillRect(560, 550, 280, 80);
			g.setColor(Color.black);
			g.fillRect(565, 555, 270, 70);
		} else {
			g.setColor(Color.cyan);
			g.fillRect(550, 540, 300, 100);
			g.setColor(Color.black);
			g.fillRect(555, 545, 290, 90);
		}
		// button shrinks when pressed

		g.setColor(Color.cyan);
		g.setFont(HSFont1);
		g.drawString("1 Player", 615, 265);
		g.drawString("2 Player", 615, 435);
		g.drawString("Tutorial", 615, 605);

		g.drawImage(airship[6][2], 1150, 40, 240, 200, this);
		g.drawImage(airship[2][2], 1150, 250, 250, 180, this);
		g.drawImage(airship[3][2], 900, 370, 500, 400, this);
		g.drawImage(airship[0][3], 50, 80, 200, 120, this);
		g.drawImage(airship[1][3], 90, 210, 170, 150, this);
		g.drawImage(airship[4][3], 50, 370, 240, 120, this);
		g.drawImage(airship[5][3], 90, 480, 180, 300, this);
	}

	public void Player1Choose(Graphics g) {
		g.drawImage(gameScreen, 0, 0, 1400, 800, this);

		g.setColor(Color.white);
		g.setFont(HSFont1);
		g.drawString("Player 1 Choose", 550, 70);

		g.setColor(Color.cyan);
		g.fillRect(100, 100, 1200, 600);
		g.setColor(Color.black);
		g.fillRect(120, 120, 1160, 560);

		g.setColor(Color.white);
		g.setFont(HSFont1);
		g.drawImage(airship[6][3], 620, 380, 240, 200, this);
		g.drawString("Raven", 680, 650);
		g.drawImage(airship[4][3], 590, 150, 240, 120, this);
		g.drawString("Voidray", 610, 330);
		g.drawImage(airship[3][3], 800, 170, 500, 400, this);
		g.drawString("Tempest", 970, 640);
		g.drawImage(airship[0][3], 140, 150, 200, 120, this);
		g.drawString("Scout", 170, 330);
		g.drawImage(airship[1][3], 380, 130, 170, 150, this);
		g.drawString("Phoenix", 380, 330);
		g.drawImage(airship[2][3], 140, 380, 250, 180, this);
		g.drawString("Battlecruiser", 140, 650);
		g.drawImage(airship[5][3], 450, 330, 180, 300, this);
		g.drawString("Mutalisk", 450, 650);

		g.setColor(Color.black);
		switch (playerChoice) {
		case 1:
			g.fillRect(140, 150, 200, 120);
			g.drawImage(airship[0][3], 90, 120, 300, 180, this);
			break;
		case 2:
			g.fillRect(380, 130, 170, 150);
			g.drawImage(airship[1][3], 338, 93, 255, 225, this);
			break;
		case 3:
			g.fillRect(140, 380, 250, 180);
			g.drawImage(airship[2][3], 78, 335, 375, 270, this);
			break;
		case 4:
			g.fillRect(840, 170, 430, 400);
			g.drawImage(airship[3][3], 725, 110, 650, 520, this);
			break;
		case 5:
			g.fillRect(590, 150, 240, 120);
			g.drawImage(airship[4][3], 530, 120, 360, 180, this);
			break;
		case 6:
			g.fillRect(450, 340, 180, 260);
			g.drawImage(airship[5][3], 405, 235, 270, 450, this);
			break;
		case 7:
			g.fillRect(620, 380, 240, 200);
			g.drawImage(airship[6][3], 560, 330, 360, 300, this);
			break;
		}
	}

	public void Player2Choose(Graphics g) {
		g.drawImage(gameScreen, 0, 0, 1400, 800, this);

		g.setColor(Color.white);
		g.setFont(HSFont1);
		g.drawString("Player 2 Choose", 550, 70);

		g.setColor(Color.cyan);
		g.fillRect(100, 100, 1200, 600);
		g.setColor(Color.black);
		g.fillRect(120, 120, 1160, 560);

		g.setColor(Color.white);
		g.setFont(HSFont1);
		if (p1 != 6) {
			g.drawImage(airship[6][3], 620, 380, 240, 200, this);
			g.drawString("Raven", 680, 650);
		}
		if (p1 != 4) {
			g.drawImage(airship[4][3], 590, 150, 240, 120, this);
			g.drawString("Voidray", 610, 330);
		}
		if (p1 != 3) {
			g.drawImage(airship[3][3], 800, 170, 500, 400, this);
			g.drawString("Tempest", 970, 640);
		}
		if (p1 != 0) {
			g.drawImage(airship[0][3], 140, 150, 200, 120, this);
			g.drawString("Scout", 170, 330);
		}
		if (p1 != 1) {
			g.drawImage(airship[1][3], 380, 130, 170, 150, this);
			g.drawString("Phoenix", 380, 330);
		}
		if (p1 != 2) {
			g.drawImage(airship[2][3], 140, 380, 250, 180, this);
			g.drawString("Battlecruiser", 140, 650);
		}
		if (p1 != 5) {
			g.drawImage(airship[5][3], 450, 330, 180, 300, this);
			g.drawString("Mutalisk", 450, 650);
		}

		g.setColor(Color.black);
		switch (playerChoice) {
		case 1:
			g.fillRect(140, 150, 200, 120);
			g.drawImage(airship[0][3], 90, 120, 300, 180, this);
			break;
		case 2:
			g.fillRect(380, 130, 170, 150);
			g.drawImage(airship[1][3], 338, 93, 255, 225, this);
			break;
		case 3:
			g.fillRect(140, 380, 250, 180);
			g.drawImage(airship[2][3], 78, 335, 375, 270, this);
			break;
		case 4:
			g.fillRect(840, 170, 430, 400);
			g.drawImage(airship[3][3], 725, 110, 650, 520, this);
			break;
		case 5:
			g.fillRect(590, 150, 240, 120);
			g.drawImage(airship[4][3], 530, 120, 360, 180, this);
			break;
		case 6:
			g.fillRect(450, 340, 180, 260);
			g.drawImage(airship[5][3], 405, 235, 270, 450, this);
			break;
		case 7:
			g.fillRect(620, 380, 240, 200);
			g.drawImage(airship[6][3], 560, 330, 360, 300, this);
			break;
		}
	}

	public void Fight(Graphics g) {
		g.drawImage(gameScreen, 0, 0, 1400, 800, this);

		g.setColor(Color.cyan);
		if (fightPressed == true) {
			g.fillRect(500, 280, 440, 170);
		} else {
			g.fillRect(480, 260, 480, 210);
		}
		g.setColor(Color.black);
		if (fightPressed == true) {
			g.fillRect(520, 300, 400, 130);
		} else {
			g.fillRect(500, 280, 440, 170);
		}
		g.setColor(Color.cyan);
		g.setFont(HSFont);
		g.drawString("FIGHT", 560, 400);
	}

	public void Winner(Graphics g) {
		g.drawImage(gameScreen, 0, 0, 1400, 800, this); // gameing background

		g.setColor(Color.white);
		g.setFont(HSFont);
		g.drawString("WINNER!", 450, 150);

		switch (winner) {
		case 1:
			switch (p1 + 1) {
			case 1:
				g.drawImage(airship[p1][3], 700 - scout.length, 400 - scout.width, scout.length * 2, scout.width * 2,
						this);
				break;
			case 2:
				g.drawImage(airship[p1][3], 700 - phoenix.length, 400 - phoenix.width, phoenix.length * 2,
						phoenix.width * 2, this);
				break;
			case 3:
				g.drawImage(airship[p1][3], 700 - battlecruiser.length, 400 - battlecruiser.width,
						battlecruiser.length * 2, battlecruiser.width * 2, this);
				break;
			case 4:
				g.drawImage(airship[p1][3], 700 - tempest.length, 400 - tempest.width, tempest.length * 2,
						tempest.width * 2, this);
				break;
			case 5:
				g.drawImage(airship[p1][3], 700 - voidray.length, 400 - voidray.width, voidray.length * 2,
						voidray.width * 2, this);
				break;
			case 6:
				g.drawImage(airship[p1][3], 700 - mutalisk.length, 400 - mutalisk.width, mutalisk.length * 2,
						mutalisk.width * 2, this);
				break;
			case 7:
				g.drawImage(airship[p1][3], 700 - raven.length, 400 - raven.width, raven.length * 2, raven.width * 2,
						this);
				break;
			}
			break;
		case 2:
			switch (p2 + 1) {
			case 1:
				g.drawImage(airship[p2][2], 700 - scout.length, 400 - scout.width, scout.length * 2, scout.width * 2,
						this);
				break;
			case 2:
				g.drawImage(airship[p2][2], 700 - phoenix.length, 400 - phoenix.width, phoenix.length * 2,
						phoenix.width * 2, this);
				break;
			case 3:
				g.drawImage(airship[p2][2], 700 - battlecruiser.length, 400 - battlecruiser.width,
						battlecruiser.length * 2, battlecruiser.width * 2, this);
				break;
			case 4:
				g.drawImage(airship[p2][2], 700 - tempest.length, 400 - tempest.width, tempest.length * 2,
						tempest.width * 2, this);
				break;
			case 5:
				g.drawImage(airship[p2][2], 700 - voidray.length, 400 - voidray.width, voidray.length * 2,
						voidray.width * 2, this);
				break;
			case 6:
				g.drawImage(airship[p2][2], 700 - mutalisk.length, 400 - mutalisk.width, mutalisk.length * 2,
						mutalisk.width * 2, this);
				break;
			case 7:
				g.drawImage(airship[p2][2], 700 - raven.length, 400 - raven.width, raven.length * 2, raven.width * 2,
						this);
				break;
			}
			break;
		}
	}

	public void paint(Graphics g) {

		bufferGraphics.clearRect(0, 0, dim.width, dim.height);

		if (menu == true) {
			HomeScreen(bufferGraphics);
		} else if (player1choose == true) {
			Player1Choose(bufferGraphics);
		} else if (player2choose == true) {
			Player2Choose(bufferGraphics);
		} else if (fight == true) {
			Fight(bufferGraphics);
		} else if (game == true) {
			GameRunning(bufferGraphics);
		} else if (winner == 1 || winner == 2) {
			Winner(bufferGraphics);
			if (runOnce == true) {
				battleThemes[randomNumber].stop();
				victoryTheme.play();
				runOnce = false;
			}
		}

		if (drawHomeButton == true) {
			if (HBPressed == true) {
				bufferGraphics.drawImage(homeButton, X - 80, Y - 40, 160, 80, this);
			} else {
				bufferGraphics.drawImage(homeButton, X - 100, Y - 50, 200, 100, this);
			}
			// paints the battle cruiser where the mouse is, shrinks
			// when
			// pressed
		} else if (drawHomeButton == false) { // && drawRightButton == false &&
												// drawLeftButton == false
			if (Pressed == true) {
				bufferGraphics.drawImage(mouse, X, Y, 30, 30, this);
			} else {
				bufferGraphics.drawImage(mouse, X, Y, 40, 40, this);
			}
			// paints the protoss mouse where the mouse is, shrinks when
			// pressed
		}

		bufferGraphics.setColor(Color.white);
		bufferGraphics.setFont(HSFont3);
		bufferGraphics.drawString(X + "  " + Y, X, Y);

		g.drawImage(offscreen, 0, 0, this); // double buffering
	}

	public void update(Graphics g) // important to prevent flickering
	{
		paint(g);
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) {
			ship[p1].direction = 1;
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			ship[p1].direction = 2;
		} else if (e.getKeyCode() == KeyEvent.VK_A) {
			ship[p1].direction = 3;
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			ship[p1].direction = 4;
		} else if (e.getKeyCode() == KeyEvent.VK_Q) {
			if (p1 == 3) {
				if (tempest.charging == false) {
					tempest.charging = true;
					tempest.enlarge = 0;
					tempest.fire = false;
					tempest.increase = true;
				} else if (tempest.charging == true) {
					ship[p1].fireSetup = true;
					ship[p1].fire = true;
					ship[p1].CoolingDown(ship[p1].cooldown);
					ship[p1].ready = false;
					tempest.charging = false;
				}
			} else if (p1 == 4) {
				voidray.charging = true;
				voidray.Charge();
				voidray.fired = false;
			} else if (p1 == 5) {
				if (mutalisk.fire == true) {
					mutalisk.explode = true;
					mutalisk.fire = false;
					mutalisk.CoolingDown(mutalisk.cooldown);
				} else {
					if (mutalisk.ready == true) {
						mutalisk.explode = false;
					}
				}
				if (mutalisk.ready == true) {
					mutalisk.fireSetup = true;
					mutalisk.fire = true;
					mutalisk.ready = false;
				}
			} else if (p1 == 6) {
				if (raven.ready == true) {
					raven.number++;
					if (raven.number == 5) {
						raven.number = 0;
					}
					for (int i = 0; i < 5; i++) {
						raven.bulletStrength[i] = 0;
					}
					raven.bulletStrength[raven.number] = 1;
				}
			}
			if (p1 != 3 && p1 != 5 && ship[p1].ready == true) {
				ship[p1].fireSetup = true;
				ship[p1].fire = true;
				ship[p1].CoolingDown(ship[p1].cooldown);
				ship[p1].ready = false;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			ship[p2].direction = 1;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			ship[p2].direction = 2;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			ship[p2].direction = 3;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			ship[p2].direction = 4;
		} else if (e.getKeyCode() == KeyEvent.VK_SLASH) {
			if (p2 == 3) {
				if (tempest.charging == false) {
					tempest.charging = true;
					tempest.enlarge = 0;
					tempest.fire = false;
					tempest.increase = true;
				} else if (tempest.charging == true) {
					ship[p2].fireSetup = true;
					ship[p2].fire = true;
					ship[p2].CoolingDown(ship[p2].cooldown);
					ship[p2].ready = false;
					tempest.charging = false;
				}
			} else if (p2 == 4) {
				voidray.charging = true;
				voidray.Charge();
				voidray.fired = false;
			} else if (p2 == 5) {
				if (mutalisk.fire == true) {
					mutalisk.explode = true;
					mutalisk.fire = false;
					mutalisk.CoolingDown(mutalisk.cooldown);
				} else {
					if (mutalisk.ready == true) {
						mutalisk.explode = false;
					}
				}
				if (mutalisk.ready == true) {
					mutalisk.fireSetup = true;
					mutalisk.fire = true;
					mutalisk.ready = false;
				}
			} else if (p2 == 6) {
				if (raven.ready == true) {
					raven.number++;
					if (raven.number == 5) {
						raven.number = 0;
					}
					for (int i = 0; i < 5; i++) {
						raven.bulletStrength[i] = 0;
					}
					raven.bulletStrength[raven.number] = 1;
				}
			}
			if (p2 != 3 && p2 != 5 && ship[p2].ready == true) {
				ship[p2].fireSetup = true;
				ship[p2].fire = true;
				ship[p2].CoolingDown(ship[p2].cooldown);
				ship[p2].ready = false;
			}
		}

		repaint();
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

	public void mouseDragged(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
		X = e.getX();
		Y = e.getY();
		// get mouse coordinates

		if (menu == true) {
			if (X > 550 && X < 850 && Y > 200 && Y < 300) {
				HSChoice = 1;
			} else if (X > 550 && X < 850 && Y > 370 && Y < 470) {
				HSChoice = 2;
			} else if (X > 550 && X < 850 && Y > 540 && Y < 640) {
				HSChoice = 3;
			} else {
				HSChoice = 0;
			}
		} else if (player1choose == true) {
			if (X > 140 && X < 340 && Y > 150 && Y < 270) {
				playerChoice = 1;
			} else if (X > 380 && X < 550 && Y > 130 && Y < 280) {
				playerChoice = 2;
			} else if (X > 140 && X < 390 && Y > 380 && Y < 560) {
				playerChoice = 3;
			} else if (X > 800 && X < 1300 && Y > 170 && Y < 570) {
				playerChoice = 4;
			} else if (X > 590 && X < 830 && Y > 150 && Y < 270) {
				playerChoice = 5;
			} else if (X > 450 && X < 630 && Y > 330 && Y < 630) {
				playerChoice = 6;
			} else if (X > 620 && X < 860 && Y > 380 && Y < 580) {
				playerChoice = 7;
			} else {
				playerChoice = 0;
			}
		} else if (player2choose == true) {
			if (X > 140 && X < 340 && Y > 150 && Y < 270) {
				if (p1 != 0) {
					playerChoice = 1;
				}
			} else if (X > 380 && X < 550 && Y > 130 && Y < 280) {
				if (p1 != 1) {
					playerChoice = 2;
				}
			} else if (X > 140 && X < 390 && Y > 380 && Y < 560) {
				if (p1 != 2) {
					playerChoice = 3;
				}
			} else if (X > 800 && X < 1300 && Y > 170 && Y < 570) {
				if (p1 != 3) {
					playerChoice = 4;
				} else {
					playerChoice = 0;
				}
			} else if (X > 590 && X < 830 && Y > 150 && Y < 270) {
				if (p1 != 4) {
					playerChoice = 5;
				}
			} else if (X > 450 && X < 630 && Y > 330 && Y < 630) {
				if (p1 != 5) {
					playerChoice = 6;
				}
			} else if (X > 620 && X < 860 && Y > 380 && Y < 580) {
				if (p1 != 6) {
					playerChoice = 7;
				}
			} else {
				playerChoice = 0;
			}
		}

		if (menu == false) {
			if (X > 1250 && Y > 700) {
				drawHomeButton = true;
			} else {
				drawHomeButton = false;
			}
		}

		repaint();
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		X = e.getX();
		Y = e.getY();
		// get mouse coordinates

		Pressed = true; // the mouse is pressed

		if (menu == true) {
			if (X > 550 && X < 850 && Y > 200 && Y < 300) {
				HSPressed = 1;
			} else if (X > 550 && X < 850 && Y > 370 && Y < 470) {
				HSPressed = 2;
			} else if (X > 550 && X < 850 && Y > 540 && Y < 640) {
				HSPressed = 3;
			}
		} else if (fight == true) {
			if (X > 480 && X < 960 && Y > 260 && Y < 470) {
				fightPressed = true;
			}
		}

		if (drawHomeButton == true) // the home button is pressed
		{
			HBPressed = true;
		}

		repaint();
	}

	public void mouseReleased(MouseEvent e) {
		Pressed = false;
		if (menu == true) {
			HSPressed = 0;
		} else if (fight == true) {
			fightPressed = false;
		}
		if (drawHomeButton == true) {
			HBPressed = false;
		}
		repaint();
	}

	public void mouseClicked(MouseEvent e) {
		X = e.getX();
		Y = e.getY();

		if (menu == true) {
			if (X > 550 && X < 850 && Y > 200 && Y < 300) {
				SetFalse();
			} else if (X > 550 && X < 850 && Y > 370 && Y < 470) {
				SetFalse();
				player1choose = true;
			} else if (X > 550 && X < 850 && Y > 540 && Y < 640) {
				SetFalse();
			}
		} else if (player1choose == true) {
			if (playerChoice != 0) {
				p1 = playerChoice - 1;
				player1choose = false;
				player2choose = true;
				playerChoice = 0;
			}
		} else if (player2choose == true) {
			if (playerChoice != 0) {
				p2 = playerChoice - 1;
				player2choose = false;
				fight = true;
			}
		} else if (fight == true) {
			if (X > 480 && X < 960 && Y > 260 && Y < 470) {
				fight = false;
				game = true;
				PositionSetup();
			}
		}

		if (drawHomeButton == true) // goes back to home screen
		{
			SetFalse();
			menu = true;
		}

		repaint();
	}

	class LoadingTime extends TimerTask {
		public void run() {
			load = false;
			LoadingTimer.cancel();
		}
	}

	public void Loading(int num) {
		LoadingTimer = new Timer();
		LoadingTimer.schedule(new LoadingTime(), num);
	}
}
