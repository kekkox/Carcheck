package it.carcheck.model;

public class LocationManager {

	public static LocationManager getInstance() {
		if(instance == null)
			instance = new LocationManager();
		
		return instance;
	}
	
	private LocationManager() {
		this.cityManager = new CityManager();
		this.provinceManager = new ProvinceManager();
		this.regionManager = new RegionManager();
	}
	
	public CityManager getCityManager() {
		return cityManager;
	}

	public ProvinceManager getProvinceManager() {
		return provinceManager;
	}

	public RegionManager getRegionManager() {
		return regionManager;
	}

	private CityManager cityManager;
	private ProvinceManager provinceManager;
	private RegionManager regionManager;
	
	private static LocationManager instance;
}
