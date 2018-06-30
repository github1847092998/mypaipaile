package com.paishop.testUser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.paishop.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml","classpath:spring-mybatis.xml"})
public class TestUser {
	@Test
	public void testAddUser() {
		User user = new User();
		//user.setId(1000001);
		user.setOpenid("A324LIUXIAO123456B");
		user.setUnionid("hjlgasde");
		user.setGender(1);
		
		
		
	}

}
