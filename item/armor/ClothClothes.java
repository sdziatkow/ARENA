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

public class ClothClothes extends Armor{
	/*
	 * Default Armor
	*/
	
	private final String NAME = "Cloth Clothes";
	private final double PHYS_DEF = 1.0;
	private final double MAG_DEF = 0.0;
	
//CONSTRUCTORS---------------------------------------------------------------------
	
	public ClothClothes() {
		/*
		 * 
		*/
		
		super();
		setName(NAME);
		setBasePhysDef(PHYS_DEF);
		setBaseMagDef(MAG_DEF);
		
		setInfo();
	}
	
//SETTERS--------------------------------------------------------------------------
	
//GETTERS--------------------------------------------------------------------------
	
	

}
