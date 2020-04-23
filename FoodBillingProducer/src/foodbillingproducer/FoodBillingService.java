package foodbillingproducer;

public interface FoodBillingService {
	public void defaultList();
	public void printItemList();
	public String LoginVerification(String username,String password);
	public void addItem(String itemName, float itemPrice);
	public void removeItem(int itemId);
	public float calculateBill(int itemId, int Qty,int count);
	public float calcSubTotal(float total, float discount);
	public int getListSize();
	public float calcBalance(float subTotal, float cash);
}
