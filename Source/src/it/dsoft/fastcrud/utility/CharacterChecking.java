package it.dsoft.fastcrud.utility;

public class CharacterChecking {
	public static String replaceInvalidCharacters(String str) {
		String newString = str;
		newString = newString.replace("'", "\'");
		newString = newString.replace("%", "\u0025"); //%
		
		return newString;
	}
}
