package it.carcheck.fastcrud.util;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import it.carcheck.fastcrud.core.EntityType;
import it.carcheck.fastcrud.core.TableName;
import it.carcheck.fastcrud.core.enums.PKType;
import it.carcheck.fastcrud.core.enums.Type;

public class BeanManager {
	
	private Object beanObject;
	
	/**
	 * Bean manager constructor
	 * @param bean Object that represent user database table
	 */
	public BeanManager(Object bean)
	{
		beanObject = bean;
	}
	
	/**
	 * Make new list of bean object with database result
	 * @param result Result of database
	 * @param beanObject User bean object
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> ArrayList<T> MakeBeanObjectArray(ResultSet result, Object beanObject)
	{
		ArrayList<T> objects = new ArrayList<T>();
		
		try{
			Class<? extends Object> beanClass = beanObject.getClass();
			Field[] fields = beanClass.getDeclaredFields();
			
			while(result.next())
			{
				Object _object = beanClass.newInstance();
				
				for(Field field : fields)
				{
					field.setAccessible(true);
					field.set(_object, result.getObject(field.getName()));
				}
				
				objects.add((T)_object);
			}
			
			return objects;
			
		}catch(Exception e) {}
		
		return null;
	}
	
	/**
	 * Map an object to insert into database
	 * @return ArrayList of mapped object
	 */
	public ArrayList<Tuple<String, String>> Map()
	{
		List<Field> fields = new ArrayList<Field>();
		Class<? extends Object> beanClass = beanObject.getClass();
		
		fields = Arrays.asList(beanClass.getDeclaredFields()); 
		ArrayList<Tuple<String, String>> mapping = new ArrayList<Tuple<String, String>>();
		
		for(int i = 0; i < fields.size(); i++)
		{
			Field field = fields.get(i);
			field.setAccessible(true);
			try {
				String key = field.getName();
				Object value = field.get(beanObject);
				
				if(field.getAnnotation(EntityType.class) != null)
					if(field.getAnnotation(EntityType.class).type() == Type.PrimaryKey &&
					   field.getAnnotation(EntityType.class).pkType() == PKType.Auto_Increment)
						continue;
				
			mapping.add(new Tuple<String, String>(key, value.toString()));
				
			} catch (IllegalArgumentException e) {}
			  catch (IllegalAccessException e) {}
		}
		
		Collections.reverse(mapping);
		return mapping;
	}
	
	/**
	 * Get a primary key name of your bean object
	 * @return Primary key name
	 */
	public String GetPrimaryKeyName()
	{
		try{
			Class<? extends Object> beanClass = beanObject.getClass();
			
			Field[] fields = beanClass.getDeclaredFields();
			for(Field field : fields)
			{
				if(field.getAnnotation(EntityType.class) != null)
					if(field.getAnnotation(EntityType.class).type() == Type.PrimaryKey)
						return field.getName();
			}
			
		}catch(Exception e) {}
		
		return null;
	}
	
	/**
	 * Get primary key from your bean object
	 * @return Primary Key
	 */
	public String GetPrimaryKey()
	{
		try{
			Class<? extends Object> beanClass = beanObject.getClass();
			
			Field[] fields = beanClass.getDeclaredFields();
			for(Field field : fields)
			{
				if(field.getAnnotation(EntityType.class) != null)
				{
					if(field.getAnnotation(EntityType.class).type() == Type.PrimaryKey)
					{
						field.setAccessible(true);
						return field.get(beanObject).toString();
					}
				}
			}
			
		}catch(Exception e) {}
		
		return null;
	}
	
	/**
	 * Get database table name
	 * @return Table name
	 */
	public String GetTableName()
	{
		try{
			Class<? extends Object> beanClass = beanObject.getClass();
			
			@SuppressWarnings("rawtypes")
			Constructor[] constructors = beanClass.getConstructors();

			for(Constructor<?> constructor : constructors)
			{
				if(constructor.getAnnotation(TableName.class) != null)
					return ((TableName)constructor.getAnnotation(TableName.class)).name();
			}
			
		}catch(Exception e) {}
		
		return null;
	}

}
