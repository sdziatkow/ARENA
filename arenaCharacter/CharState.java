package arenaCharacter;

import arenaCharacter.CharAttr.Attribute;
import arenaCharacter.Stat.StatType;
import arenaCharacter.ArenaCharacter.CharClass;

/**
 * Program Name:    CharacterAspect.java
 *<p>
 * Purpose:         The purpose of this program is to
 *<p>
 * @version         0.0
 *<p>
 * Created:         April 30, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/


public class CharState {
	/**
	 * This is a class used for class ArenaCharacter. All the stats for a single 
	 * ArenaCharacter can be accessed from an object of this class.
	*/
	
	private ArenaCharacter character;
	
	private Stat hp;
	private Stat mp;
	private Stat sp;
	
	private Stat physDef;
	private Stat magDef;
	private Stat crit;
	private Stat speed;
	
	private CharAttr vigor;
	private CharAttr wp;
	private CharAttr intel;
	private CharAttr dex;
	
	// Default values.
	final double DEF_HP_MAX = 100.0;
	final double DEF_MP_MAX = 100.0;
	final double DEF_SP_MAX = 100.0;
	final double DEF_PHYSD_MAX = 1000.0;
	final double DEF_MAGD_MAX = 1000.0;
	final double DEF_CRIT_MAX = 100.0;
	final double DEF_SPEED_MAX = 500.0;
	final double DEF_CRIT_VAL = 0.02;
	final double DEF_SPEED_VAL = 0.00;
	
//CONSTRUCTORS---------------------------------------------------------------------
	
	public CharState() {
		/**
		 * 
		*/
	}
	
	public CharState(ArenaCharacter character) {
		/**
		 * 
		*/
		
		this.character = character;
		
		// Initialize new stats.
		hp = new Stat(StatType.HP);
		mp = new Stat(StatType.MP);
		sp = new Stat(StatType.SP);
		
		physDef = new Stat(StatType.PHYSDEF);
		magDef = new Stat(StatType.MAGDEF);
		crit = new Stat(StatType.CRIT);
		speed = new Stat(StatType.SPEED);
		
		// Initialize new attributes.
		vigor =  new CharAttr(Attribute.VIGOR);
		wp =  new CharAttr(Attribute.WILLPOWER);
		intel =  new CharAttr(Attribute.INTELLIGENCE);
		dex =  new CharAttr(Attribute.DEXTERITY);
	}
	
//GETTERS--------------------------------------------------------------------------
		
	public Stat hp() {
		/**
		 * Getter for field: hp
		*/
		
		return hp;
	}
	public Stat mp() {
		/**
		 * Getter for field: mp
		*/
		
		return mp;
	}
	public Stat sp() {
		/**
		 * Getter for field: sp
		*/
		
		return sp;
	}
	
	public Stat physDef() {
		/**
		 * Getter for field: physDef
		*/
		
		return physDef;
	}
	public Stat magDef() {
		/**
		 * Getter for field: magDef
		*/
		
		return magDef;
	}
	public Stat crit() {
		/**
		 * Getter for field: crit
		*/
		
		return crit;
	}
	public Stat speed() {
		/**
		 * Getter for field: speed
		*/
		
		return speed;
	}
	
	public CharAttr vigor() {
		/**
		 * Getter for field: vigor
		*/
		
		return vigor;
	}
	public CharAttr wp() {
		/**
		 * Getter for field: wp
		*/
		
		return wp;
	}
	public CharAttr intel() {
		/**
		 * Getter for field: intel
		*/
		
		return intel;
	}
	public CharAttr dex() {
		/**
		 * Getter for field: dex
		*/
		
		return dex;
	}
	
//SET-STATS------------------------------------------------------------------------
	
	public void setDefaultVals(CharClass charClass) {
		/**
		 * Default values for every class.
		 * Requires a character class because the class bonuses are automatically 
		 * addded.
		*/
		
		// Max vals.
		hp().setMaxVal(DEF_HP_MAX);
		mp().setMaxVal(DEF_MP_MAX);
		sp().setMaxVal(DEF_SP_MAX);
		physDef().setMaxVal(DEF_PHYSD_MAX);
		magDef().setMaxVal(DEF_MAGD_MAX);
		crit().setMaxVal(DEF_CRIT_MAX);
		speed().setMaxVal(DEF_SPEED_MAX);
		
		// Initial vals
		crit().setVal(DEF_CRIT_VAL);
		speed().setVal(DEF_SPEED_VAL);
		
		// Each class starts with total 25 points.
		switch (charClass) {
		
			// BRUTE: vigor+15 willpower+10
			case CharClass.BRUTE:
				vigor().incAttr(16);
				wp().incAttr(9);
				break;
				
			// BARBARIAN: vigor+10 willpower+13 dexterity+2
			case CharClass.BARBARIAN:
				vigor().incAttr(14);
				wp().incAttr(8);
				dex().incAttr(3);
				break;
				
			// DUELIST: vigor+5 willpower+5 dexterity+15
			case CharClass.DUELIST:
				vigor().incAttr(5);
				wp().incAttr(7);
				intel().incAttr(3);
				dex().incAttr(10);
				break;
				
			// RANGER: willpower+5 dexterity+20
			case CharClass.RANGER:
				wp().incAttr(7);
				intel().incAttr(7);
				dex().incAttr(11);
				break;
				
			// willpower+15 intelligence+5 dexterity+5
			case CharClass.MONK:
				wp().incAttr(9);
				intel().incAttr(9);
				dex().incAttr(7);
				break;
				
			// MAGE: willpower+5 intelligence+20 
			case CharClass.MAGE:
				wp().incAttr(8);
				intel().incAttr(16);
				dex().incAttr(1);
				break;
		}
		
		updateStats();
		fullHealHpMpSp();
	}
	
	public void updateStats() {
		/**
		 * This method will update the ArenaCharacters stats based on their 
		 * attributes.
		*/
		
		hp().setMaxVal(
				DEF_HP_MAX + 
				(7.8 * vigor().getVal()) + 
				( 3.2 * wp().getVal() )
				)
		;
		mp().setMaxVal(
				DEF_MP_MAX +
				(3.2 * wp().getVal()) +
				(7.8 * intel().getVal())
				)
		;
		sp().setMaxVal(
				DEF_SP_MAX + 
				( ( 3.2 * wp().getVal() ) + 
				( 7.8 * dex().getVal() ) )
				) 
		;
		
		physDef().setVal(
				(1.5 * wp().getVal()) +
				(1.5 * vigor().getVal()) + 
				(0.5 * dex().getVal())
				)
		;
		magDef().setVal(
				(1.5 * intel().getVal()) +
				(0.5 * wp().getVal()) +
				(1.5 * dex().getVal())
				)
		;
		
		
		crit().setVal(
				DEF_CRIT_VAL + 
				(0.0001 * dex().getVal()) + 
				(0.0001 * wp().getVal())
				)
		;
		speed().setVal(
				DEF_SPEED_VAL + 
				( 2.4 * dex().getVal() ) + 
				( 0.6 * wp().getVal())
				)
		;
	}
	
	public void fullHealHpMpSp() {
		/**
		 * 
		*/
		
		hp().fullHeal();
		mp().fullHeal();
		sp().fullHeal();
	}
	

}
