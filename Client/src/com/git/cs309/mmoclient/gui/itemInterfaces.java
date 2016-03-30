public class itemInterfaces {
	
	private static final long serialVersionUID = -506002191290787674L;
	private final int price;
	private final int CombatStyle;
	private final int EquipmentSlot;
	private final int id;
	private final int strength;
	private final int defence;
	private final int level;
	private final String itemName;
	private final boolean stackable;
	
	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @return the style
	 */
	public int getCombatStyle() {
		return CombatStyle;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the strength
	 */
	public int getStrength() {
		return strength;
	}

	/**
	 * @return the defence
	 */
	public int getDefence() {
		return defence;
	}

	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * @return the stackable
	 */
	public boolean isStackable() {
		return stackable;
	}

	/**
	 * @return the slot
	 */
	public int getEquipmentSlot() {
		return EquipmentSlot;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}
	

}
