package com.epam.lab.intouch.model.member.tester;

import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.member.tester.enums.TesterSpecialization;

public class Tester extends Member {
	private TesterSpecialization specialization;

	public TesterSpecialization getSpecialization() {
		return specialization;
	}

	public void setSpecialization(TesterSpecialization specialization) {
		this.specialization = specialization;
	}

}
