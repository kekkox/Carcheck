package it.carcheck.fastcrud.util;

public class Tuple<Key, Value> {

	private Key key;
	private Value value;
	
	public Tuple(Key key, Value value)
	{
		this.key = key;
		this.value = value;
	}
	
	public Key GetKey()
	{
		return this.key;
	}
	
	public Value GetValue()
	{
		return this.value;
	}
	
	public void SetKey(Key key)
	{
		this.key = key;
	}
	
	public void SetValue(Value value)
	{
		this.value = value;
	}
	
}
