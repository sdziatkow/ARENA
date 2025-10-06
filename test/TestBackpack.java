//package test;
//
//import item.useable.*;
//
//import arenaCharacter.Stat.StatType;
//
///**
// * Program Name:    TestBackpack.java
// *<p>
// * Purpose:         The purpose of this program is to
// *<p>
// * @version         0.0
// *<p>
// * Created:         MONTH DD, YYY
// *<p>
// * Updated:         MONTH DD, YYYY
// *<p>
// * @author          Sean Dziatkowiec
//*/
//
//import backpack.*;
//import item.*;
//import item.Item.ItemType;
//import item.weapon.*;
//
//public class TestBackpack{
//    /**
//     *
//    */
//
//    public static void main(String [] args){
//        /**
//         *
//        */
//    	
//    	Backpack bp = new Backpack();
//    	SteelRapier rapier = new SteelRapier();
//    	
//       	System.out.println(bp.toString());
//    	bp.addItem(new StatPot(1, StatType.HP, 20.0));
//    	bp.addItem(new StatPot(1, StatType.MP, 20.0));
//    	bp.addItem(new StatPot(1, StatType.SP, 20.0));
//    	System.out.println(bp.toString());
//    	bp.addItem(new StatPot(1, StatType.HP, 20.0));
//    	bp.addItem(new StatPot(1, StatType.MP, 20.0));
//    	bp.addItem(new StatPot(1, StatType.SP, 20.0));
//    	System.out.println(bp.toString());
//    	
//    	bp.addItem(rapier);
//    	System.out.println(bp.toString());
//    	
//    	bp.removeItem(ItemType.USEABLE, 1);
//    	System.out.println(bp.toString());
//    	bp.removeItem(ItemType.USEABLE, 1);
//    	System.out.println(bp.toString());
//    	
//    	bp.removeItem(rapier);
//    	System.out.println(bp.toString());
//    	
//    	bp.addItem(new StatPot(1, StatType.MP, 20.0));
//    	System.out.println(bp.toString());
//
//
//    }
//
//}