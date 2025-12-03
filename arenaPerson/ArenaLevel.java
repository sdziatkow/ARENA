package arenaPerson;

import java.util.ArrayList;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

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

public class ArenaLevel {
	/*
	 * 
	*/
	
	public final int DEFAULT_MAX_XP = 100;
	
	private int maxXp;
	private int currXp;
	private int overflow;
	
	private int lvl;
	private boolean lvlUpReady;
	private int skillPoints;
	
	private ArrayList<String> info;
	private DoubleProperty dispVal;
	
//CONSTRUCTORS---------------------------------------------------------------------
	
	public ArenaLevel() {
		/*
		 * Default constructor for class Level.
		*/
		
		maxXp = DEFAULT_MAX_XP;
		currXp = 0;
		overflow = 0;
		
		lvl = 0;
		lvlUpReady = false;
		skillPoints = 0;
		
		info = new ArrayList<String>();
		setInfo();
		
		dispVal = new SimpleDoubleProperty(currXp / maxXp);
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
		
		if (xp > getMaxXp()) {
			currXp = getMaxXp();
		}
		else if (xp < 1) {
			currXp = 0;
		}
		else {
			currXp = xp;
		}
		
		setInfo();
		dispVal.set(getXp() / getMaxXp());
	}
	
	public void setOverflow(int over) {
		/*
		 * Setter for field: currXp
		*/
		
		if (over < 1) {
			overflow = 0;
		}
		else {
			overflow = over;
		}
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
	
	public void setInfo() {
		/*
		 * 
		*/
		
		getInfo().clear();
		getInfo().add("LEVEL:");
		getInfo().add(String.valueOf(getLvl()));
		getInfo().add("XP:");
		getInfo().add(
				"[ "
				+ String.valueOf(getXp())
				+ " / "
				+ String.valueOf(getMaxXp())
				+ " ]"
		);
		getInfo().add("SKILL-POINTS:");
		getInfo().add(String.valueOf(getSkillPoints()));
		getInfo().add(String.valueOf(isLvlUpReady()));
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
	
	public int getOverflow() {
		/*
		 * 
		*/
		
		return overflow;
	}
	
	public int getLvl() {
		/*
		 * Getter for field: 
		*/
		
		return lvl;
	}
	
	public boolean isLvlUpReady() {
		/*
		 * 
		*/
		
		lvlUpReady = getXp() == getMaxXp();
		
		return lvlUpReady;
	}
	
	public int getSkillPoints() {
		/*
		 * Getter for field: 
		*/
		
		return skillPoints;
	}
	
	public ArrayList<String> getInfo() {
		/*
		 * 
		*/
		
		return info;
	}
	
	public DoubleProperty dispVal() {
		/*
		 * 
		 * 
		*/
		
		return dispVal;
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
		
		if (isLvlUpReady()) {
			incOverflow();
		}
		else {
			setXp(getXp() + 1);
		}
	}
	
	
	public void incXp(int amnt, boolean fromOverflow) {
		/*
		 * 
		*/
		
		for (int x = 0; x < amnt; ++x) {
			
			if (fromOverflow && getOverflow() > 0) {
				incXp();
				decOverflow();
			}
			else {
				incXp();
			}
		}
	}
	
	public void incOverflow() {
		/*
		 * 
		*/
		
		setOverflow(getOverflow() + 1);
	}
	
	public void decOverflow() {
		/*
		 * 
		*/
		
		setOverflow(getOverflow() - 1);
	}
	
	public void onLvlUp() {
		/*
		 * 
		*/
		
		int lastMax = getMaxXp();
		
		incLvl();
		incSkillPoints();
		setMaxXp(DEFAULT_MAX_XP * (getLvl() + 1));
		setXp(0);
		incXp(getOverflow(), true);
		
		setInfo();
		
		dispVal.set(getXp() / getMaxXp());
	}
	

}
