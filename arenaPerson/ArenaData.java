package arenaPerson;

import java.util.ArrayList;
import arenaEnum.personInfo.CharClass;
import arenaEnum.personStats.Attribute;
import arenaEnum.personStats.StatType;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class ArenaData {
	/**
	 * This is a class used for class ArenaCharacter. All the stats for a single 
	 * ArenaCharacter can be accessed from an object of this class.
	*/
	
	ArrayList<ArenaStat> allStats;
	ArrayList<ArenaAttr> allAttr;
	
	private ArenaStat hp;
	private ArenaStat mp;
	private ArenaStat sp;
	
	private ArenaStat physDef;
	private ArenaStat magDef;
	private ArenaStat crit;
	private ArenaStat speed;
	
	private ArenaAttr vigor;
	private ArenaAttr wp;
	private ArenaAttr intel;
	private ArenaAttr dex;
	
	private DoubleProperty mvSpeed;
	
	// Default values.
	final double DEF_XP_MAX = 100.0;
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
	
	public ArenaData() {
		/**
		 * 
		*/
		
		// Initialize new stats.
		hp = new ArenaStat(StatType.HP);
		mp = new ArenaStat(StatType.MP);
		sp = new ArenaStat(StatType.SP);
		
		physDef = new ArenaStat(StatType.PHYSDEF);
		magDef = new ArenaStat(StatType.MAGDEF);
		crit = new ArenaStat(StatType.CRIT);
		speed = new ArenaStat(StatType.SPEED);
		
		// Initialize new attributes.
		vigor =  new ArenaAttr(Attribute.VIGOR);
		wp =  new ArenaAttr(Attribute.WILLPOWER);
		intel =  new ArenaAttr(Attribute.INTELLIGENCE);
		dex =  new ArenaAttr(Attribute.DEXTERITY);
		
		mvSpeed = new SimpleDoubleProperty();
		
		allStats = new ArrayList<ArenaStat>();
		allStats.add(hp());
		allStats.add(mp());
		allStats.add(sp());
		allStats.add(physDef());
		allStats.add(magDef());
		allStats.add(crit());
		allStats.add(speed());
		
		allAttr = new ArrayList<ArenaAttr>();
		allAttr.add(vigor());
		allAttr.add(wp());
		allAttr.add(intel());
		allAttr.add(dex());
	}
	
//GETTERS--------------------------------------------------------------------------

	public ArenaStat hp() {
		/**
		 * Getter for field: hp
		*/
		
		return hp;
	}
	public ArenaStat mp() {
		/**
		 * Getter for field: mp
		*/
		
		return mp;
	}
	public ArenaStat sp() {
		/**
		 * Getter for field: sp
		*/
		
		return sp;
	}
	
	public ArenaStat physDef() {
		/**
		 * Getter for field: physDef
		*/
		
		return physDef;
	}
	public ArenaStat magDef() {
		/**
		 * Getter for field: magDef
		*/
		
		return magDef;
	}
	public ArenaStat crit() {
		/**
		 * Getter for field: crit
		*/
		
		return crit;
	}
	public ArenaStat speed() {
		/**
		 * Getter for field: speed
		*/
		
		return speed;
	}
	public DoubleProperty speedProperty() {
		/*
		 * 
		*/
		
		return mvSpeed;
	}
	
	public ArenaAttr vigor() {
		/**
		 * Getter for field: vigor
		*/
		
		return vigor;
	}
	public ArenaAttr wp() {
		/**
		 * Getter for field: wp
		*/
		
		return wp;
	}
	public ArenaAttr intel() {
		/**
		 * Getter for field: intel
		*/
		
		return intel;
	}
	public ArenaAttr dex() {
		/**
		 * Getter for field: dex
		*/
		
		return dex;
	}
	
	public ArrayList<ArenaAttr> getAllAttr() {
		/*
		 * 
		*/
		
		return allAttr;
	}
	
	public ArrayList<ArenaStat> getAllStats() {
		/*
		 * 
		*/
		
		return allStats;
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
		setAttrMins();
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
		);
		mp().setMaxVal(
				DEF_MP_MAX +
				(3.2 * wp().getVal()) +
				(7.8 * intel().getVal())
		);
		sp().setMaxVal(
				DEF_SP_MAX + 
				( ( 3.2 * wp().getVal() ) + 
				( 7.8 * dex().getVal() ) )
		);
		
		physDef().setVal(
				(1.5 * wp().getVal()) +
				(1.5 * vigor().getVal()) + 
				(0.5 * dex().getVal())
		);
		magDef().setVal(
				(1.5 * intel().getVal()) +
				(0.5 * wp().getVal()) +
				(1.5 * dex().getVal())
		);
		
		
		crit().setVal(
				DEF_CRIT_VAL + 
				(0.0001 * dex().getVal()) + 
				(0.0001 * wp().getVal())
		);
		speed().setVal(
				DEF_SPEED_VAL + 1 +
				( 0.03 * dex().getVal() ) + 
				( 0.015 * wp().getVal())
		);
		mvSpeed.set(speed().getVal());
	}
	
	public void fullHealHpMpSp() {
		/**
		 * 
		*/
		
		hp().fullHeal();
		mp().fullHeal();
		sp().fullHeal();
	}
	
	public void setAttrMins() {
		/*
		 * 
		*/
		
		for (int a = 0; a < getAllAttr().size(); ++a) {
			getAllAttr().get(a).setMinVal(getAllAttr().get(a).getVal());
		}
	}
	

}
