import java.io.Serializable;

public class ClientShop {
	private static final long serialVersionUID;
	private final String shopName;
	//murchents can have multiple shops and
	//several murchents could access the same shop 
	public ClientShop(final String shopName, final int id, final int price,) {
		this.shopName = shopName;
	}

	public String getName()
	{
		return shopName;
	}
	

}
