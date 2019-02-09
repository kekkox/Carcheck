package it.dsoft.fastcrud.utility;

public class CharacterChecking {
	public static String replaceInvalidCharacters(String str) {
		return str.replace("'", "\'");
	}
}
