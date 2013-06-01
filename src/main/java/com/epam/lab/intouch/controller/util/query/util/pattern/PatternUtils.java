package com.epam.lab.intouch.controller.util.query.util.pattern;

import java.util.ArrayList;
import java.util.List;

import com.epam.lab.intouch.controller.util.query.security.SecurityFilter;

/**
 * This class exists for pattern manipulation, first of all in LIKE clause
 * 
 * @author Zatorsky D.B
 * 
 */
public class PatternUtils {

	/**
	 * This method wraps the word in % symbols for LIKE clause. The result is something like %someword%
	 * 
	 * @param word
	 * @return wrapped word
	 */
	public static String fullMatching(String word) {
		String result = null;

		if (word != null && !word.isEmpty()) {
			result = "%" + word + "%";
		}

		return result;
	}

	/**
	 * This method divides input text on tokens for further wrapping. It splits text with every punctuation mark or whitespace character and returns list of
	 * tokens
	 * 
	 * @param text
	 * @return list of tokens
	 */
	public static List<String> splitPunctuationMatch(String text) {
		SecurityFilter filter = new SecurityFilter();
		List<String> preparedOperands = new ArrayList<String>();

		for (String word : splitText(text, "[\\p{Punct}+ \\s]")) {
			String safeWord = filter.replaceUnsafeSymbols(word);
			preparedOperands.add(fullMatching(safeWord));
		}

		return preparedOperands;
	}

	private static List<String> splitText(String text, String regex) {
		List<String> devidedText = new ArrayList<String>();
		for (String word : text.split(regex)) {
			devidedText.add(word);
		}
		return devidedText;
	}

}
