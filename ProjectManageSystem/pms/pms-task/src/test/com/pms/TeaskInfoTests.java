package com.pms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TeaskInfoTests.class)
public class TeaskInfoTests {

	@Autowired
	//private ITaskInfoDao iTaskInfoDao;

	@Test
	public void test1() {

		System.out.println("Hello world!");

	}

}
