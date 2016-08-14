package character;

import java.awt.Color;
import java.awt.Graphics2D;

import math.Vector2f;

public class Attributes {
	public int health, maxHealth, attackRange, level, experience;
	public float speed, attackDelay;
	private Character host;
	
	public Attributes(Character host) {
		this.host = host;
		maxHealth = 100;
		health = maxHealth;
		attackRange = 30;
		level = 1;
		experience = 0;
		speed = 5.f;
		attackDelay = 2.f;
	}
	
	public Attributes(Character host, float speed, float attackDelay, int attackRange, int maxHealth, int experience) {
		this.host = host;
		this.maxHealth = maxHealth;
		health = maxHealth;
		this.experience = experience;
		level = 1;
		this.speed = speed;
		this.attackDelay = attackDelay;
		this.attackRange = attackRange;
	}
	
	public Character getHost() {
		return host;
	}
	
	public void drawAttackRange(Graphics2D g, Vector2f drawPosition, Vector2f drawScale, float xScaleLocal) {
		g.setColor(new Color(1f, 0f, 0f, 1f));
		g.drawLine(
				Math.round(drawPosition.x),
				Math.round(drawPosition.y + host.getHeight()/2),
				Math.round(drawPosition.x + attackRange*xScaleLocal),
				Math.round(drawPosition.y + host.getHeight()/2)
				);
	}
}
