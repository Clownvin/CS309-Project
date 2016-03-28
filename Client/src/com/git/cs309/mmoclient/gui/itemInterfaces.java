import com.git.cs309.mmoserver.combat.CombatStyle;
import com.git.cs309.mmoserver.items.EquipmentSlot;



public class itemInterfaces {
	
	private static final long serialVersionUID = -506002191290787674L;
	private final int price;
	private final CombatStyle style;
	private final EquipmentSlot slot;
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
	public CombatStyle getStyle() {
		return style;
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
	public EquipmentSlot getSlot() {
		return slot;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}
	

}
