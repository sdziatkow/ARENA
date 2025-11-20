package item.weapon;

/**
 * Program Name:    SteelRapier.java
 *<p>
 * Purpose:         The purpose of this program is to create a rapier Weapon for 
 * 					ArenaCharacters to use.
 *<p>
 * @version         0.0
 *<p>
 * Created:         March 06, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import arenaCharacter.CharAttr.Attribute;
import arenaCharacter.Stat.StatType;
import item.weapon.WeaponAttack.AttackType;

public class SteelRapier extends Weapon{
    /**
     * Default weapon for class Duelist
    */

    // attk1 slash will do + PERCENT_HP_DMG of the target's current hp val.
    private final double MISSING_HP_DMG = 0.085;

    private WeaponAttack stab = new WeaponAttack(
    								AttackType.PHYS, "STAB", 3.0, 10.0
    								)
    ;
    private WeaponAttack parry  = new WeaponAttack(
    								  AttackType.DEF, "PARRY", 0.0, 1.0
    								  )
    ;

    public SteelRapier() {
        /**
         *
        */

        setName("Steel Rapier");

        setBaseDmg(5.0);
        setAttk1(stab);
        setAttk2(parry);
        
        setInfo();
    }

    public void genAttk1() {
        /**
         * Attk1 SLASH
         * dmgMod is PERCENT_HP_DMG of the target's current hp plus 22% of a
         * normal roll.
        */
    	
    	getAttk1().setDmgMod(
    			getBaseDmg() +
    			(0.7 * getSelf().attr(Attribute.DEXTERITY).getVal()) +
    			(0.05 * getSelf().stat(StatType.SP).getVal()) +
    			(MISSING_HP_DMG * ( getTarget().stat(StatType.HP).getMaxVal() - 
    			 getTarget().stat(StatType.HP).getVal() ) )
    			)
    	;
    	
    	setAttkDmg(getAttk1().genDmg());
    	

    }

    public void genAttk2() {
        /**
         * Attk2 PARRY
         * If the target is attacking this turn, parry their attack and make them
         * weak.
        */
    	
    	
    	
    	
    }

}

