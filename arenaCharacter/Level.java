package arenaCharacter;

/**
 * Program Name:    Level.java
 *<p>
 * Purpose:         The purpose of this program is to create a system for 
 * 					ArenaCharacters to have a level and level up.
 *<p>
 * @version         0.0
 *<p>
 * Created:         November 12, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

public class Level {
	/*
	 * 
	*/
	
	public final int DEFAULT_MAX_XP = 100;
	
	private int maxXp;
	private int currXp;
	private int lvl;
	private int skillPoints;
	
//CONSTRUCTORS---------------------------------------------------------------------
	
	public Level() {
		/*
		 * Default constructor for class Level.
		*/
		
		maxXp = DEFAULT_MAX_XP;
		currXp = 0;
		lvl = 0;
		skillPoints = 0;
	}
	
//SETTERS--------------------------------------------------------------------------
	
	public void setMaxXp(int maxXp) {
		/*
		 * Setter for field: maxXp
		*/
		
		this.maxXp = maxXp;
	}
	
	public void setXp(int xp) {
		/*
		 * Setter for field: currXp
		*/
		
		this.currXp = xp;
	}
	
	public void setLvl(int lvl) {
		/*
		 * Setter for field: lvl
		*/
		
		this.lvl = lvl;
	}
	
	public void setSkillPoints(int points) {
		/*
		 * Setter for field: skillPoints
		*/
		
		this.skillPoints = points;
	}
	
//GETTERS--------------------------------------------------------------------------	
	
	public int getMaxXp() {
		/*
		 * Getter for field: maxXp
		*/
		
		return maxXp;
	}
	
	public int getXp() {
		/*
		 * Getter for field: 
		*/
		
		return currXp;
	}
	
	public int getLvl() {
		/*
		 * Getter for field: 
		*/
		
		return lvl;
	}
	
	public int getSkillPoints() {
		/*
		 * Getter for field: 
		*/
		
		return skillPoints;
	}
	
//LEVEL----------------------------------------------------------------------------
	
	public void incLvl() {
		/*
		 * 
		*/
		
		setLvl(getLvl() + 1);
	}
	
	public void incSkillPoints() {
		/*
		 * 
		*/
		
		setSkillPoints(getSkillPoints() + 1);
	}
	
	public void decSkillPoints() {
		/*
		 * 
		*/
		
		setSkillPoints(getSkillPoints() - 1);
	}
	
	public void incXp() {
		/*
		 * 
		*/
		
		setXp(getXp() + 1);
		
		if (getXp() >= getMaxXp()) {
			onLvlUp();
		}
	}
	
	public void incXp(int amnt) {
		/*
		 * 
		*/
		
		for (int x = 0; x < amnt; ++x) {
			incXp();
		}
	}
	
	public void onLvlUp() {
		/*
		 * 
		*/
		
		incLvl();
		incSkillPoints();
		setXp(0);
		setMaxXp(DEFAULT_MAX_XP * (int)(getLvl() * 1.5));
	}
	

}
