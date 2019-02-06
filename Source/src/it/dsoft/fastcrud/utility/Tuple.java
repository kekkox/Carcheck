package it.dsoft.fastcrud.utility;

/**
 * This class represents a key / value structure
 * @author Daniele De Falco
 *
 * @param <Key> Generic key
 * @param <Value> Generic value
 */
public class Tuple<Key, Value> {

	private Key key;
	private Value value;
	
	/**
	 * Tuple constructor
	 * @param key Generic key
	 * @param value Generic value
	 */
	public Tuple(Key key, Value value) {
		this.key = key;
		this.value = value;
	}
	
	/**
	 * Get key from the tuple
	 * @return Key
	 */
	public Key GetKey() {
		return this.key;
	}
	
	/**
	 * Get value from the tuple
	 * @return
	 */
	public Value GetValue() {
		return this.value;
	}
	
	/**
	 * Set tuple key
	 * @param key Generic key
	 */
	public void SetKey(Key key) {
		this.key = key;
	}
	
	/**
	 * Set tuple value
	 * @param value Generic value
	 */
	public void SetValue(Value value) {
		this.value = value;
	}
}
