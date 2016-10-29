package com.anotation.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.anotation.edu.entity.User;
import com.anotation.edu.mapper.UserMapper;

public class UserTest {
	private SqlSession session;
	private UserMapper userMapper;
	
	@Before
	public void init() throws IOException {
	   String resource = "mybatis-config.xml";
//	   InputStream inputStream = UserTest.class.getClassLoader().getResourceAsStream(resource);
	   InputStream inputStream = Resources.getResourceAsStream(resource);
	   SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	   session = sqlSessionFactory.openSession(true); // 默认为false，true:自动提交  false:手动提交
	   
	   // 获取注解时的sql映射类
	   userMapper = session.getMapper(UserMapper.class);
	}
	
	@After
	public void destory() {
		// session.commit(); // openSession()不设置时需要手动提交
		session.close();
	}
	
    @Test
    public void saveUser() {
 	   User user = new User("yy", 60);
	   userMapper.add(user);
    }
    
    @Test
    public void deleteUser() {
    	userMapper.delete(3);
    }
    
    @Test
    public void updateUser() {
 	   User user = new User(1, "yy", 60);
	   userMapper.update(user);
    }
    
    @Test
    public void findUser() {
       List<User> userList=userMapper.findAll();
       // 使用Arrays.asList()能打印集合里对象中的内容
 	   System.out.println(Arrays.asList(userList));
    }
    
    @Test
    public void findUserById() {
 	   User user= userMapper.findUserById(1);
	   System.out.println(user);
    }
}
