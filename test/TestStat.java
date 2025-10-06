//package test;
//
///**
// * Program Name:    TestStat.java
// *<p>
// * Purpose:         The purpose of this program is to test class Stat.
// *<p>
// * @version         0.0
// *<p>
// * Created:         February 16, 2025
// *<p>
// * Updated:         MONTH DD, YYYY
// *<p>
// * @author          Sean Dziatkowiec
//*/
//
//import arenaCharacter.*;
//import arenaCharacter.Stat;
//import arenaCharacter.Stat.StatType;
//import arenaCharacter.npc.*;
//import backpack.*;
//import item.*;
//import item.weapon.*;
//import item.useable.*;
//
//import java.util.*;
//
//public class TestStat{
//    /**
//     *
//    */
//
//    public static void main(String [] args){
//        /**
//         *
//        */
//    	
//    	Useable pot = new StatPot();
//    	ArrayList<Item> items = new ArrayList<Item>();
//    	
//    	items.add(pot);
//    	System.out.println(items.getFirst().getItemType());
//    	
//    
//    	
//
//        Stat health = new Stat(StatType.HP);
//        System.out.println("--------------------");
//
//        // Constructor T1
//        System.out.println("Name: " + health.getStatType());
//        System.out.println("Min:  " + health.getMinVal());
//        System.out.println("Max:  " + health.getMaxVal());
//        System.out.println("Val:  " + health.getVal());
//        System.out.println("--------------------");
//
//        // Setter T1
//        health.setMinVal(0.0);
//        health.setMaxVal(100.0);
//        health.setVal(50.0);
//        System.out.println("Name: " + health.getStatType());
//        System.out.println("Min:  " + health.getMinVal());
//        System.out.println("Max:  " + health.getMaxVal());
//        System.out.println("Val:  " + health.getVal());
//        System.out.println("--------------------");
//
//        // Heal T1
//        System.out.println("...healing...");
//        health.heal(10);
//        System.out.println("Val:  " + health.getVal());
//        System.out.println("--------------------");
//
//        // Dmg T1
//        System.out.println("...damaging...");
//        health.dmg(30.47);
//        System.out.println("Val:  " + health.getVal());
//        System.out.println("--------------------");
//
//    }
//
//}
