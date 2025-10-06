package item.armor;

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
import arenaCharacter.*;

public abstract class Armor extends Item {
	/**
	 * 
	*/
	
	private double basePhysDef;
	private double baseMagDef;
	private ArenaCharacter self;
	
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
	
	public Armor(double physDef, double magDef, ArenaCharacter self) {
		/**
		 * 
		*/
		
		super(ItemType.ARMOR);
		this.basePhysDef = physDef;
		this.baseMagDef  = magDef;
		this.self = self;
	}
	
//SETTERS--------------------------------------------------------------------------
	
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
	
	public void setSelf(ArenaCharacter self) {
		/**
		 * Setter for field: self 
		*/
		
		this.self = self;
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
	
	public ArenaCharacter getSelf() {
		/**
		 * Getter for field: self 
		*/
		
		return self;
	}
	
//STAT-MOD-------------------------------------------------------------------------
	
	public abstract void onEq();
	public abstract void onUnEq();
}
