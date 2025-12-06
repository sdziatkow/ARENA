package worldStage;

import arenaEnum.personInfo.CharClass;
import arenaEnum.personStats.StatType;
import arenaPerson.ArenaPlayer;
import arenaPerson.npc.NPCElm;
import arenaPerson.npc.NPCWilhelm;
import item.armor.SteelArmor;
import item.useable.StatPot;
import item.weapon.BronzeHandAxe;
import item.weapon.CrystalSceptre;
import item.weapon.SteelGreatAxe;
import item.weapon.SteelRapier;
import item.weapon.WoodenBow;
import sprite.StoneTower;
import sprite.charSprite.ElmSprite;
import sprite.charSprite.EnemyTestSprite;
import sprite.charSprite.PlayerSprite;

final class StageOneData extends WorldData{
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
		persons.get(0).setName("Hondis");
		charSprites.add(new PlayerSprite());
		
		persons.add(new NPCElm());
		charSprites.add(new ElmSprite());
		for (int n = 0; n < 4; ++n) {
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
		}
		
		// Add items to persons.
		bkpks.get(0).addItem(new SteelRapier());
		bkpks.get(0).addItem(new SteelArmor());
		bkpks.get(0).addItem(new StatPot(5, StatType.HP, 500000.0));
		bkpks.get(0).addItem(new StatPot(5, StatType.MP, 500000.0));
		bkpks.get(0).addItem(new StatPot(5, StatType.SP, 500000.0));
//		persons.get(0).lvl().incXp(100000, false);
//		persons.get(0).attr(Attribute.WILLPOWER).incAttr(100);
//		persons.get(0).attr(Attribute.DEXTERITY).incAttr(100);
		persons.get(0).data().updateStats();
		
		eqSlots.get(2).equipItem(new SteelGreatAxe());
		eqSlots.get(3).equipItem(new BronzeHandAxe());
		eqSlots.get(4).equipItem(new WoodenBow());
		eqSlots.get(5).equipItem(new CrystalSceptre());
		
	}
}
