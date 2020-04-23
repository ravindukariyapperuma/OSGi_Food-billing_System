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
	}

	public void stop(BundleContext Context) throws Exception {
		System.out.println("Stop Food Billing Consumer Service");
		Context.ungetService(serviceReference);
	}
}
