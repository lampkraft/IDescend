package character;

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
}
