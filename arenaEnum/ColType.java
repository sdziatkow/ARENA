package arenaEnum;

public enum ColType {
	/**
	 * @const STAGEBOX: The bounds of the stage itself.
	 * @const HURTBOX: Takes damage if hitbox enters bounds.
	 * @const HITBOX: Deals damage if entering a hurtbox.
	 * @const DETECTBOX: For detection of other boxes from far away.
	 * @const INTERACTBOX: Can interact if within bounds.
	 * @const WORLDBOX: General collision for anything with collision.
	 * @const CHECKBOX: Used to check for moving and or attacking.
	*/
	STAGEBOX,
	HURTBOX,
	HITBOX,
	DETECTBOX,
	INTERACTBOX,
	WORLDBOX,
	CHECKBOX
}
