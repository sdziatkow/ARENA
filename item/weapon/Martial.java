package item.weapon;

import arenaEnum.itemInfo.AttackType;
import arenaEnum.personInfo.CharClass;
import arenaEnum.personStats.Attribute;

public class Martial extends Weapon {
    /**
     * Default weapon for all Arena Characters.
     *
     * ArenaCharacters of class Monk get special bonuses to their martial dmg
    */

    private WeaponAttack punch = new WeaponAttack(
    		AttackType.MIX, "PUNCH", 5.0, 10.0
    		)
    ;
    private WeaponAttack kick  = new WeaponAttack(
    		AttackType.MIX, "KICK", 6.0, 11.0
    		)
    ;
    

    public Martial() {
        /**
         *
        */

        setName("Martial");

        setBaseDmg(10.0);
        setAttk1(punch);
        setAttk2(kick);
        
        setInfo();
    }

    public void genAttk1() {
        /**
         * Attk1 PUNCH
         * dmgMod for class Monk only; see below.
        */
    	
    	if (getSelf().getCharClass().equals(CharClass.MONK)) {
    		getAttk1().setDmgMod(
    				(2 * getSelf().attr(Attribute.WILLPOWER).getVal()) +
    				(0.5 * getSelf().attr(Attribute.INTELLIGENCE).getVal())
    				)
    		;
    	}
    	
    	setAttkDmg(getAttk1().genDmg());

    }

    public void genAttk2() {
        /**
         * Attk2 KICK
         * dmgMod for class Monk only; see below.
        */

    }
}
