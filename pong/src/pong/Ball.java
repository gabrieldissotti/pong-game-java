package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {
	public double x, y;
	public int width, heigth;
	
	public double dx, dy; // direction
	public double speed = 1.7;
	
	public Ball(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 4;
		this.heigth = 4;
		
		int angle = new Random().nextInt(120-45)+46;
		this.dx = Math.cos(Math.toRadians(angle));
		this.dy = Math.sin(Math.toRadians(angle));
	}
	
	public void tick() {
		if(x + dx * speed + width >= Game.WIDTH || x + dx * speed < 0) 
			dx*=-1;
		
		if(y >= Game.HEIGHT) {
			System.out.println("Inimigo marcou um ponto!");
			new Game();
			return;
		} else if(y < 0) {
			System.out.println("Player marcou um ponto!");
			new Game();
			return;
		}
		
		Rectangle bounds = new Rectangle((int)(x+dx*speed), (int)(y+dy*speed), width, heigth);
		

		Rectangle boundsPlayer = new Rectangle(Game.player.x, Game.player.y, Game.player.width, Game.player.height);
		Rectangle boundsEnemy = new Rectangle((int)Game.enemy.x, (int)Game.enemy.y, Game.enemy.width, Game.enemy.height);


		if(bounds.intersects(boundsPlayer)) {
			int angle = new Random().nextInt(120-45)+46;
			this.dx = Math.cos(Math.toRadians(angle));
			this.dy = Math.sin(Math.toRadians(angle));
			if(dy>0)
				dy*=-1;
		} else if (bounds.intersects(boundsEnemy)) {
			int angle = new Random().nextInt(120-45)+46;
			this.dx = Math.cos(Math.toRadians(angle));
			this.dy = Math.sin(Math.toRadians(angle));
			if(dy<0)
				dy*=-1;
		}
		x+=dx*speed;
		y+=dy*speed;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int) x, (int) y, width, heigth);
	}
}