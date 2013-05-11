package pseudotests;

import com.epam.lab.intouch.controller.util.query.security.SecurityFilter;

public class SecurityFilterTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SecurityFilter filter=new SecurityFilter();
		System.out.println(filter.replaceUnsafeSymbols("1: aaa%aaa"));
		System.out.println(filter.replaceUnsafeSymbols("2: aaa_aaa"));
		System.out.println(filter.replaceUnsafeSymbols("3: aaa\"aaa"));
		System.out.println(filter.replaceUnsafeSymbols("4: aaa'aaa"));

	}

}
