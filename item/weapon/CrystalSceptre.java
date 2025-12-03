package item.weapon;


import arenaEnum.itemInfo.AttackType;
import arenaEnum.personStats.Attribute;
import arenaEnum.personStats.StatType;

public class CrystalSceptre extends Weapon {
    /**
     * Default class for class Mage
    */
	
	private double MP_COST = 15;

    private WeaponAttack crystalBlast = new WeaponAttack(
    		AttackType.MAG, "CRYSTAL-BLAST", 6.0, 13.0
    		)
    ;
    private WeaponAttack reflect = new WeaponAttack(
    		AttackType.UTIL, "REFLECT", 5.0, 10.0
    		)
    ;

    public CrystalSceptre() {
        /**
         *
        */

        setName("Crystal Sceptre");
        setBaseDmg(0.0);

        setAttk1(crystalBlast);
        setAttk2(reflect);
        
        setInfo();
    }

    public void genAttk1() {
        /**
         * Attk1 CRYSTAL-BLAST
         * dmgMod will do 2% current hp dmg plus 3x lastHeal val
         *
        */
    	
    	getAttk1().setDmgMod(
    			getBaseDmg() +
    			(0.02 * getSelf().arenaStat(StatType.MP).getVal()) +
    			(0.04 * getSelf().arenaStat(StatType.MAGDEF).getVal()) +
    			(0.68 * getSelf().attr(Attribute.INTELLIGENCE).getVal())
    			)
    	;
    	
    	setAttkDmg(getAttk1().genDmg());
    	
    	getSelf().arenaStat(StatType.MP).dmg(MP_COST);
    }

    public void genAttk2() {
        /**
         * Attk2 REFLECT
         * dmgMod will do 4% missing HP plus 80% last dmg taken plus 5% target
         * maxHp dmg
         * Finally, heal self for 10% of dmg done.
        */
    }

}