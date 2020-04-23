package foodbillingproducer;

import java.util.ArrayList;

public class FoodBillingServiceImpl implements FoodBillingService {
	
	ArrayList<String> items = new ArrayList<String>();
	ArrayList<Float> price = new ArrayList<Float>();
		
		public void defaultList() {
			
			items.add("Rice and Curry");
			price.add((float) 150.00);
			
			items.add("Fried Rice");
			price.add((float) 200.00);
			
			items.add("Hoppers");
			price.add((float) 10.00);
			
			items.add("Nouldes");
			price.add((float) 130.00);
			
			items.add("Rotti");
			price.add((float) 40.00);
		}

		public void printItemList() {
			System.out.println("------------Item List-------------");
			for (int i = 0; i < price.size(); i++)
			  {
		        System.out.println(i+1 + "  " +items.get(i) +"  Rs:"+price.get(i));
		      }
			System.out.println("----------------------------------");
		}
		
		public String LoginVerification(String username,String password) {
			if((username.equals("admin")) && (password.equals("123"))){
				return "admin";
			}
			else if((username.equals("cashier")) && (password.equals("789"))) {
				return "cashier";
			}else {
				return "invalid";
			}
		}
		
		public void addItem(String itemName, float itemPrice) {
			items.add(itemName);
			price.add((float) itemPrice);
		}
		
		public void removeItem(int itemId) {
			items.remove(itemId-1);
			price.remove(itemId-1);
		}
		
		public float calculateBill(int itemId, int Qty,int count) {
			float itemPrice = price.get(itemId-1);
			float total = (itemPrice*Qty);
			System.out.println((count+1) +") "+ items.get(itemId-1) +" X "+ Qty + " = " + total);
			return total;
		}
		
		public float calcSubTotal(float total, float discount) {
			float subtotal = (total-((total/100)*discount));
			return subtotal;
		}

		public int getListSize() {
			
			return items.size();
		}
		
		public float calcBalance(float subTotal, float cash) {
			
			return (cash - subTotal);
		}
}
