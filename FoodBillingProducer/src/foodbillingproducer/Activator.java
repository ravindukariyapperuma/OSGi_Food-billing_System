package foodbillingproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	ServiceRegistration publishServiceRegistration;

	public void start(BundleContext Context) throws Exception {
		System.out.println("Food Billing Service Start");
		FoodBillingService foodbillingservice = new FoodBillingServiceImpl();
		publishServiceRegistration = Context.registerService(
				FoodBillingService.class.getName(), foodbillingservice, null);
	}

	
}
