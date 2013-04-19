package com.epam.lab.intouch.model.member.tester;

import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.member.tester.enums.TesterSpecialization;

public class Tester extends Member {
	private TesterSpecialization specialization;

	public TesterSpecialization getSpecialization() {
		return this.specialization;
	}

	public void setSpecialization(final TesterSpecialization specialization) {
		this.specialization = specialization;
	}

}
