
package serverPackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.lang.Math;

public abstract class Personnage {

	private int life;
	private int strength;
	private int luck;
	private int resistance;
	protected String persoType;

	private int posX;
	private int posY;

	private int id;

	//private ArrayList<String> listItem;
	protected char affichage;

	public Personnage(int x, int y) {
		// default value
		/*
		 * this.life =50 ; this.strength= 50; this.luck = 6; this.resistance =
		 * 50;
		 * 
		 * this.listItem.add("");
		 */

		/** Set position **/
		this.posX = x;
		this.posY = y;

	}

	// getter and setters
	public int getLife() {
		return this.life;
	}

	public void setLife(int newLife) {
		this.life = newLife;
	}

	/* getter and setter position */
	public int getPersoPosX() {
		return this.posX;
	}

	public void setPersoPosX(int newPosX) {
		this.posX = newPosX;
	}

	public int getPersoPosY() {
		return this.posY;
	}

	public void setPersoPosY(int newPosY) {
		this.posY = newPosY;
	}

	/* getter and setter Luck */
	public int getLuck() {
		return this.luck;
	}

	public void setLuck(int newluck) {
		this.luck = newluck;
	}

	public int getResistance() {
		return this.resistance;
	}

	public void setResistance(int newResistance) {
		this.resistance = newResistance;
	}

	public int getStrength() {
		return this.strength;
	}

	public void setStrength(int newStrength) {
		this.strength = newStrength;
	}

	public void attack(Personnage target) {
		System.out.println("vous avez attack� !");
		int damage = 0;
		float factor = ((float) this.getLuck() / 12);
		damage = ((int) (this.getStrength() * (Math.random() + factor)) - target.getResistance());
		System.out.println("damage: " + damage);
		if (damage >= 0) {
			int targetNewLife = target.getLife() - damage;
			if (targetNewLife <= 0) {
				target.die();
			} else {
				target.setLife(targetNewLife);
			}
		} else {
			System.out.println("attack not effective!");
		}

	}

	public void die() {
		this.setLife(0);
		System.out.println("you're dead!");

	}

	public void dropItem() {

	}

	// Moving management

	public void move(String key) {
		
	}

}
