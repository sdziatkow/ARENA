package worldStage;

import arenaEnum.personInfo.CharClass;
import arenaEnum.personStats.Attribute;
import arenaEnum.personStats.StatType;
import arenaPerson.ArenaPerson;
import arenaPerson.ArenaPlayer;
import arenaPerson.npc.NPCElm;
import arenaPerson.npc.NPCWilhelm;
import backpack.Backpack;
import backpack.EquipSlots;
import item.armor.SteelArmor;
import item.useable.StatPot;
import item.weapon.BronzeHandAxe;
import item.weapon.CrystalSceptre;
import item.weapon.SteelGreatAxe;
import item.weapon.SteelRapier;
import item.weapon.WoodenBow;
import movement.Movement;
import movement.NpcMovement;
import movement.PlayerMovement;
import sprite.StoneTower;
import sprite.charSprite.ElmSprite;
import sprite.charSprite.EnemyTestSprite;
import sprite.charSprite.OrcSprite;
import sprite.charSprite.PlayerSprite;

public class StageOneData extends WorldData{
	/*
	 * 
	*/
	
	public StageOneData() {
		/*
		 * 
		*/
		
		super();
		
		// Add ArenaPerson and corresponding sprite. (PLAYER ALWAYS @ IDX ZERO)
		persons.add(new ArenaPlayer(CharClass.DUELIST));
		charSprites.add(new PlayerSprite());
		
//		persons.add(new NPCElm());
//		charSprites.add(new ElmSprite());
		for (int n = 0; n < 1; ++n) {
			persons.add(new NPCWilhelm());
			charSprites.add(new EnemyTestSprite());
		}

		
		// Add stone towers.
		for (int t = 0; t < 5; ++t) {
			staticSprites.add(new StoneTower());
			staticSprites.get(t).setPos(500 + (t * 100), 500 - (t * 50));
		}
		
		init();
		
		// Set position and colBoxes for mvmnt objects because all colBoxes are 
		// present only after init() is called.
		for (int t = 0; t < persons.size(); ++t) {
			mvmnts.get(t).setPos(700 + (t * 200), 550);
	        System.out.println(persons.get(t).getID());
		}
		
		ArenaPerson.setOtherPersons(persons);
		Movement.setColBoxes(colBoxes);
		
		// Add items to persons.
		persons.get(0).bp().addItem(new SteelRapier());
		persons.get(0).bp().addItem(new SteelArmor());
		persons.get(0).bp().addItem(new StatPot(500, StatType.HP, 500000.0));
		persons.get(0).lvl().incXp(100000, false);
//		persons.get(0).attr(Attribute.WILLPOWER).incAttr(100);
//		persons.get(0).attr(Attribute.DEXTERITY).incAttr(100);
//		persons.get(0).data().updateStats();
		
		persons.get(1).equipSlot().equipItem(new SteelGreatAxe());
//		persons.get(2).equipSlot().equipItem(new CrystalSceptre());
//		persons.get(3).equipSlot().equipItem(new BronzeHandAxe());
//		persons.get(4).equipSlot().equipItem(new WoodenBow());
//		persons.get(5).equipSlot().equipItem(new SteelRapier());
		
		
	}
}
