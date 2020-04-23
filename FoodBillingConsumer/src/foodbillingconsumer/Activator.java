package foodbillingconsumer;

import foodbillingproducer.FoodBillingService;
import foodbillingproducer.FoodBillingServiceImpl;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {

	ServiceReference serviceReference;

	public void start(BundleContext Context) throws Exception {
		System.out.println("Start Food Billing Consumer Service");
		serviceReference = Context.getServiceReference(FoodBillingService.class.getName());
		FoodBillingService foodBillingService = (FoodBillingService) Context.getService(serviceReference);

try
		{  
			String username, password,choice,itemName ;
			float itemPrice, discount,cash, balance;
			int itemId;
			FoodBillingService foodbillingservice = new FoodBillingServiceImpl();
			foodbillingservice.defaultList();
			Scanner scanner = new Scanner(System.in);
			while(true) {
				System.out.println("<< if you wont exit enter 'end' for username >>");
				System.out.println("====== Login =========================");
				System.out.print("Enter UserName : ");
				username = scanner.next();
				if( username.equals("end")) {
					System.out.println("Ending Food billing");
					break;
				}
				System.out.print("Enter Password : ");
				password = scanner.next();
				System.out.println("======================================\n");
				choice = foodbillingservice.LoginVerification(username, password);
				if(choice=="admin") {
					System.out.println("~~~~~~~~~~~ Welcome Admin ~~~~~~~~~~~");
					foodbillingservice.printItemList();
					while(true) {
						System.out.print("You wont Add item or Remove item or logout (add / remove / logout) :");
						choice = scanner.next();
						if(choice.equals("add")) {
							System.out.print("Enter Item Name : ");
							itemName = scanner.next();
							System.out.print("Enter Item Price : ");
							itemPrice = scanner.nextFloat();
							foodbillingservice.addItem(itemName, itemPrice);
							foodbillingservice.printItemList();
						}else if(choice.equals("remove")) {
							System.out.print("Enter Item Id : ");
							itemId = scanner.nextInt();
							foodbillingservice.removeItem(itemId);
							foodbillingservice.printItemList();
						}else if(choice.equals("logout")) {
							break;
						}else {
							System.out.println("Error: Invalide input");
						}
					}
				}
				else if (choice == "cashier") {
					System.out.println("~~~~~~~~~~~ Welcome Cashier ~~~~~~~~~~~");
					while(true) {
						float total =0;
						System.out.print("You wont logout? (y / n) : ");
						choice = scanner.next();
						System.out.print("\n");
						if(choice.equals("y")) {
							break;
						}else {
							foodbillingservice.printItemList();
							System.out.println("<< If need get total enter 0 >>");
							System.out.println("_______________Bill_________________");
							int count = 0;
							while(true) {
								System.out.print("Enter Item Id : ");
								itemId = scanner.nextInt();
								if(itemId == 0) {
									break;
								}else if((itemId<=foodbillingservice.getListSize()) && itemId>0) {
									System.out.print("Enter Qty : ");
									int Qty = (int) scanner.nextFloat();
									total = total + foodbillingservice.calculateBill(itemId, Qty, count);
									System.out.println("---------------------------");
									count++;
								}else {
									System.out.println("Error : invalide input");
								}
							}
							System.out.println("---------------------------");
							System.out.println("Tatal Amount = " + total);
							System.out.print("Enter Discount % : " );
							discount = scanner.nextFloat();
							float subTotal = foodbillingservice.calcSubTotal(total, discount);
							System.out.println("===========================");
							System.out.println("Sub Tatal = " + subTotal);
							System.out.println("===========================");
							System.out.print("Enter Cash : " );
							cash = scanner.nextFloat();
							balance = foodbillingservice.calcBalance(subTotal, cash);
							System.out.println("Balance = " + balance);
							System.out.println("No of Items = " + count);
							System.out.println("____________________________________");
						}
					}
				}
				else {
					System.out.println("Incorrect login");
				}
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}	
	}

	public void stop(BundleContext Context) throws Exception {
		System.out.println("Stop Food Billing Consumer Service");
		Context.ungetService(serviceReference);
	}
}
