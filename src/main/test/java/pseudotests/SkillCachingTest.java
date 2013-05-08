package pseudotests;

import com.epam.lab.intouch.controller.exception.DataAccessingException;
import com.epam.lab.intouch.controller.util.cache.SkillCache;

public class SkillCachingTest {

	public static void main(String[] args) throws DataAccessingException {
		Long start = System.currentTimeMillis();
		SkillCache skillCache = SkillCache.getInstance();

		System.out.println(skillCache.getSkillNames());
		System.out.println(skillCache.getSkillTypes());

		System.out.println(System.currentTimeMillis() - start);

		start = System.currentTimeMillis();

		System.out.println(skillCache.getSkillNames());
		System.out.println(skillCache.getSkillTypes());

		System.out.println(System.currentTimeMillis() - start);

		start = System.currentTimeMillis();

		System.out.println(skillCache.getSkillNames());
		System.out.println(skillCache.getSkillTypes());

		System.out.println(System.currentTimeMillis() - start);

	}

}
