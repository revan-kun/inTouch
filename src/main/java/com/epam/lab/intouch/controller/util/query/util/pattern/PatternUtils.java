package com.epam.lab.intouch.controller.util.query.util.pattern;

import java.util.ArrayList;
import java.util.List;

import com.epam.lab.intouch.controller.util.query.security.SecurityFilter;

public class PatternUtils {

	public static String fullMatching(String word) {
		String result = null;

		if (word != null && !word.isEmpty()) {
			result = "%" + word + "%";
		}

		return result;
	}

	public static List<String> splitPunctuationMatch(String text) {
		SecurityFilter filter = new SecurityFilter();
		List<String> preparedOperands = new ArrayList<String>();

		for (String word : splitText(text, " ")) {
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
