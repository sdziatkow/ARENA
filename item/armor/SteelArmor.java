package item.armor;

/**
 * Program Name:    ClothClothes.java
 *<p>
 * Purpose:         The purpose of this program is to create a default armor item.
 *<p>
 * @version         0.0
 *<p>
 * Created:         November 18, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

public class SteelArmor extends Armor {
	/*
	 * 
	*/
	
	private final String NAME = "Steel Armor";
	private final double PHYS_DEF = 15.0;
	private final double MAG_DEF = 4.0;
	
	public SteelArmor() {
		/*
		 * 
		*/
		
		super();
		setName(NAME);
		setBasePhysDef(PHYS_DEF);
		setBaseMagDef(MAG_DEF);
		
		setInfo();
	}

}
