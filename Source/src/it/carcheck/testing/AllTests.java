package it.carcheck.testing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ FastCrudTesting.class,
				AddressManagerTesting.class,
				AdhesionRequestManagerTesting.class,
				AdminManagerTesting.class,
				CityManagerTesting.class,
				PeopleManagerTesting.class,
				ProvinceManagerTesting.class,
				RegionManagerTesting.class,
				VehicleComplaintHistoryManagerTesting.class,
				VehicleComplaintManagerTesting.class,
				VehicleInspectionManagerTesting.class,
				VehicleManagerTesting.class,
				WorkshopAdhesionManagerTesting.class,
				WorkshopManagerTesting.class})
public class AllTests {

}
