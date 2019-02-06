package it.carcheck.database;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;

import it.dsoft.fastcrud.DatabaseConfiguration;
import it.dsoft.fastcrud.FastCrud;

public class CarcheckDatabase extends FastCrud {

	public CarcheckDatabase(DatabaseConfiguration configuration) throws ClassNotFoundException {
		super(configuration);
	}
	
	public static CarcheckDatabase begin(DatabaseConfiguration configuration) throws ClassNotFoundException {
		if(instance == null)
			instance = new CarcheckDatabase(configuration);
		
		return instance;
	}
	
	public static CarcheckDatabase getInstance() {
		return instance;
	}
	
	public <T> ArrayList<T> find(String query, Class<T> clazz) throws Exception {
		LinkedHashSet<T> result = (LinkedHashSet<T>) super.read(query, clazz);
		
		ArrayList<T> elements = new ArrayList<>();
		
		Iterator<T> iterator = result.iterator();
		while(iterator.hasNext()) {
			elements.add(iterator.next());
		}
		
		return elements;
	}

	private static CarcheckDatabase instance;
}
