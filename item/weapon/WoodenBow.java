package item.weapon;

import arenaEnum.itemInfo.AttackType;
import arenaEnum.personStats.Attribute;
import arenaEnum.personStats.StatType;

public class WoodenBow extends Weapon {
    /**
     * Default weapon for class Ranger
    */

    private WeaponAttack pierce = new WeaponAttack(
    		AttackType.PHYS, "PIERCE", 5.0, 12.0
    		)
    ;
    private WeaponAttack cripple = new WeaponAttack(
    		AttackType.PHYS, "CRIPPLE", 5.0, 10.0
    		)
    ;

    public WoodenBow() {
        /**
         *
        */

        setName("Wooden Bow");
        
        setBaseDmg(10.0);
        setAttk1(pierce);
        setAttk2(cripple);
        
        setInfo();
    }

    public void genAttk1() {
        /**
         * Attk1 PIERCE
         * Will do 33% missing hp dmg plus half of a normal roll as dmg mod if
         * target is crippled.
         * OtherWise dmg mod is 0.
         * Crippled is only set to false if the target is hit while crippled and
         * above half hp.
        */
    	
    	getAttk1().setDmgMod(
    			getBaseDmg() +
    			( 0.02 * getSelf().arenaStat(StatType.SP).getVal() ) +
    			( 0.02 * getSelf().arenaStat(StatType.SPEED).getVal() ) +
    			( 0.5 * getSelf().attr(Attribute.DEXTERITY).getVal() )
    			)
    	;
    	
    	setAttkDmg(getAttk1().genDmg());
    }

    public void genAttk2() {
        /**
         * Attk2 CRIPPLE
         * Will always apply cripple.
         * If target has cripple applied, set dmgMod to a normal attk1 roll.
         * Otherwise dmgMod is 0
        */

    }

}
