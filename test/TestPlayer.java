//package test;
//
//import java.io.IOException;
//
///**
// * Program Name:    TestPlayer.java
// *<p>
// * Purpose:         The purpose of this program is to
// *<p>
// * @version         0.0
// *<p>
// * Created:         March 06, 2025
// *<p>
// * Updated:         MONTH DD, YYYY
// *<p>
// * @author          Sean Dziatkowiec
//*/
//
//import arenaCharacter.*;
//import arenaCharacter.ArenaCharacter.CharClass;
//import arenaCharacter.CharAttr.Attribute;
//import arenaCharacter.Stat.StatType;
//import arenaCharacter.npc.*;
//import backpack.*;
//import item.*;
//import item.Item.ItemType;
//import item.weapon.*;
//import item.useable.*;
//
//public class TestPlayer{
//    /**
//     *
//    */
//
//    public static void main(String [] args)throws IOException{
//        /**
//         *
//        */
//    	
//    	Player player = new Player();
//    	System.out.println(player.toString());
//    	
//    	System.out.println("...adding items....");
//    	player.bp().addItem(new StatPot(1, StatType.HP, 20.0));
//    	player.bp().addItem(new StatPot(1, StatType.MP, 20.0));
//    	player.bp().addItem(new StatPot(1, StatType.SP, 20.0));
//    	player.bp().addItem(new SteelGreatAxe());
//    	System.out.println(player.allInfoString());
//    	
//    	System.out.println("....damaging by 57.0......");
//    	player.stat(StatType.HP).dmg(57.0);
//    	System.out.println(player.allInfoString());
//    	
//    	System.out.println("....using Potion of Health.......");
//    	
//    	player.bp().look(ItemType.USEABLE);
//    	player.bp().setItemSlot(0);
//    	((Useable)player.bp().grabItem()).use();
//    	System.out.println(player.allInfoString());
//    	
//    	System.out.println("......all attributes +50..........");
//    	player.attr(Attribute.VIGOR).incAttr(50);
//    	player.attr(Attribute.WILLPOWER).incAttr(50);
//    	player.attr(Attribute.INTELLIGENCE).incAttr(50);
//    	player.attr(Attribute.DEXTERITY).incAttr(50);
//    	player.state().updateStats();
//    	player.state().fullHealHpMpSp();
//
//    	
//    	String testStr = player.bp().toString() + player.equipSlot().toString();
//    	String sub     = testStr.substring(0, (player.bp().toString() + player.equipSlot().toString()).length());
//    	System.out.println(sub);
//    	
//    	
//    	
//
//    }
//
//}
