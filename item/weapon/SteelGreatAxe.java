package item.weapon;

import arenaEnum.itemInfo.AttackType;
import arenaEnum.personStats.Attribute;
import arenaEnum.personStats.StatType;

public class SteelGreatAxe extends Weapon {
    /**
     * Default weapon for class Goblin
    */

    private WeaponAttack roundSwing = new WeaponAttack(
    		AttackType.PHYS, "ROUND SWING", 3.0, 10.0
    		)
    ;
    private WeaponAttack fell     = new WeaponAttack(
    		AttackType.PHYS, "FELL", 7.0, 14.0
    		)
    ;

    public SteelGreatAxe() {
        /**
         *
        */

        setName("Steel Great Axe");
        
        setBaseDmg(3.0);
        setAttk1(roundSwing);
        setAttk2(fell);
        
        setInfo();
    }

    public void genAttk1() {
        /**
         * Attk1 FELL
         * dmgMod is 10% of target's current hp
        */
    	
    	
    	getAttk2().setDmgMod(
    			getBaseDmg() +
    			(0.05 * getSelf().arenaStat(StatType.HP).getVal()) +
    			(0.1 * getSelf().attr(Attribute.VIGOR).getVal())
    			)
    	;
    	
    	setAttkDmg(getAttk2().genDmg());

    }

    public void genAttk2() {
        /**
         * Attk2 ROUND SWING
         *
         * dmgMod plus 3x normal roll
         * Will heal for 100% of dmg dealt plus 40% missing health
        */

    }
}
