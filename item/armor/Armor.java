package item.armor;

import arenaEnum.itemInfo.ItemType;
import arenaEnum.personStats.StatType;
import arenaPerson.*;

/**
 * Program Name:    Armor.java
 *<p>
 * Purpose:         The purpose of this program is to create armor items that can 
 * 					be used by ArenaCharacters.
 *<p>
 * @version         0.0
 *<p>
 * Created:         May 01, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import item.*;

public abstract class Armor extends Item {
	/**
	 * 
	*/
	
	private double basePhysDef;
	private double baseMagDef;
	private ArenaPerson self;
	
//CONSTRUCTORS---------------------------------------------------------------------
	
	public Armor() {
		/**
		 * 
		*/
		
		super(ItemType.ARMOR);
		this.basePhysDef = 0.0;
		this.baseMagDef  = 0.0;
	}
	
	public Armor(double physDef, double magDef) {
		/**
		 * 
		*/
		
		super(ItemType.ARMOR);
		this.basePhysDef = physDef;
		this.baseMagDef  = magDef;
	}
	
	public Armor(double physDef, double magDef, ArenaPerson self) {
		/**
		 * 
		*/
		
		super(ItemType.ARMOR);
		setSelf(self);
		
		this.basePhysDef = physDef;
		this.baseMagDef  = magDef;
	}
	
//SETTERS--------------------------------------------------------------------------
	
	public void setInfo() {
		/*
		 * 
		*/
		
		getInfo().clear();
		getInfo().add(getName());
		getInfo().add("PHYSDEF:");
		getInfo().add(String.valueOf(getBasePhysDef()));
		getInfo().add("MAGDEF:");
		getInfo().add(String.valueOf(getBaseMagDef()));
		
	}
	
	public void setBasePhysDef(double physDef) {
		/**
		 * Setter for field: basePhysDef
		*/
		
		this.basePhysDef = physDef;
	}
	
	public void setBaseMagDef(double magDef) {
		/**
		 * Setter for field: baseMagDef
		*/
		
		this.baseMagDef = magDef;
	}
	
//GETTERS--------------------------------------------------------------------------
	
	public double getBasePhysDef() {
		/**
		 * Getter for field: basePhysDef
		*/
		
		return basePhysDef;
	}
	
	public double getBaseMagDef() {
		/**
		 * Getter for field: baseMagDef
		*/
		
		return baseMagDef;
	}
	
//STAT-MOD-------------------------------------------------------------------------
	
	public void onEq() {
		/*
		 * 
		*/
		
		getSelf().arenaStat(StatType.PHYSDEF).heal(getBasePhysDef());
		getSelf().arenaStat(StatType.MAGDEF).heal(getBaseMagDef());
	}
	
	public void onUnEq() {
		/*
		 * 
		*/
		
		getSelf().arenaStat(StatType.PHYSDEF).dmg(getBasePhysDef());
		getSelf().arenaStat(StatType.MAGDEF).dmg(getBaseMagDef());
	}
}
