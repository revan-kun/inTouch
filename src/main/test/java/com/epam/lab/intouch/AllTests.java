package com.epam.lab.intouch;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.epam.lab.intouch.dao.AllDAOTests;
import com.epam.lab.intouch.service.AllServiceTests;

@RunWith(Suite.class)
@SuiteClasses({ AllDAOTests.class, AllServiceTests.class})
public class AllTests {

}
