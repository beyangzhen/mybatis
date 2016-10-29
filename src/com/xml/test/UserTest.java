package com.xml.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.xml.edu.entity.User;
import com.xml.edu.entity.UserCondition;

public class UserTest {
	private SqlSession session;
	
	@Before
	public void init() throws IOException {
	   String resource = "mybatis-config.xml";
//	   InputStream inputStream = UserTest.class.getClassLoader().getResourceAsStream(resource);
	   InputStream inputStream = Resources.getResourceAsStream(resource);
	   SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	   session = sqlSessionFactory.openSession(true); // 默认为false，true:自动提交  false:手动提交
	}
	
	@After
	public void destory() {
		// session.commit(); // openSession()不设置时需要手动提交
		session.close();
	}
	
    @Test
    public void saveUser() {
 	   String statement = "com.xml.mapper.UserMapper.saveUser";
 	   User user = new User("yy", 60);
 	   session.insert(statement, user);
    }
    
    @Test
    public void deleteUser() {
 	   String statement = "com.xml.mapper.UserMapper.deleteUserById";
 	   session.delete(statement, 3);
    }
    
    @Test
    public void updateUser() {
 	   String statement = "com.xml.mapper.UserMapper.updateUser";
 	   User user = new User(1, "yy", 60);
 	   session.update(statement, user);
    }
    
    @Test
    public void findUser() {
 	   String statement = "com.xml.mapper.UserMapper.selectAllUser";
 	   List<User> userList = session.selectList(statement);
 	   // 使用Arrays.asList()能打印集合里对象中的内容
 	   System.out.println(Arrays.asList(userList));
    }
    
    @Test
    public void findUserById() {
 	   String statement = "com.xml.mapper.UserMapper.selectUserById";
 	   User user = session.selectOne(statement, 1);
 	   System.out.println(user);
    }
    
    /**
	 *  多条件查询（第二个参数为 UserCondition ）：
	 * 		minAge
	 * 		maxAge
	 * 		name
	 */
	@Test
	public void findUserByCondition() {
		String statement = "com.xml.mapper.UserMapper.findUserByCondition";
		UserCondition parameter = new UserCondition("%y%", 10, 40);
		List<User> userList = session.selectList(statement, parameter);
		System.out.println(Arrays.asList(userList));
	}
	
	/**
	 *  多条件查询（第二个参数为 Map ）：
	 */
	@Test
	public void findUserByCondition2() {
		String statement = "com.xml.mapper.UserMapper.findUserByCondition2";
		Map<String,Object> map = new HashMap<>();
		map.put("name", "%y%");
		map.put("minAge", 10);
		map.put("maxAge", 40);
		List<User> userList = session.selectList(statement, map);
		System.out.println(Arrays.asList(userList));
	}
	
	/**
	 *  多条件查询（避免写${}时，为空而报错）：
	 */
	@Test
	public void findUserByCondition3() {
		String statement = "com.xml.mapper.UserMapper.findUserByCondition3";
		Map<String,Object> map = new HashMap<>();
		map.put("name", "%y%");
//		map.put("minAge", 10);
//		map.put("maxAge", 40);
		List<User> userList = session.selectList(statement, map);
		System.out.println(Arrays.asList(userList));
	}
	
	/**
	 *  测试include标签
	 */
	@Test
	public void testInclude() {
		String statement = "com.xml.mapper.UserMapper.findUserById3";
		User user = session.selectOne(statement, 5);
		System.out.println(user);
	}
	
	/**
	 *  测试set标签
	 */
	@Test
	public void testUpdate() {
		String statement = "com.xml.mapper.UserMapper.updateUser";
		User user = new User(5, "bruce", 100);
		session.update(statement, user);
	}
	
	/**
	 *  测试foreach标签（list集合）
	 */
	@Test
	public void testForEach() {
		List<Integer> ids = new ArrayList<>();
		ids.add(7);
		ids.add(11);
		ids.add(13);
		ids.add(15);
		String statement = "com.xml.mapper.UserMapper.findUserByIds";
		List<User> userList = session.selectList(statement, ids);
		System.out.println(Arrays.asList(userList));
	}
	
	/**
	 *  测试foreach标签（array数组）
	 */
	@Test
	public void testArray(){
		Integer[] ids = new Integer[]{7,11,13,15};
		String statement = "com.xml.mapper.UserMapper.findUserByIds2";
		List<User> userList = session.selectList(statement, ids);
		System.out.println(Arrays.asList(userList));
	}
	
	/**
	 *  调用存储过程
	 */
	@Test
	public void testProduce(){
		String statement = "com.wxhl.edu.mapper.UserMapper.getUserAgeByName";
		Map<String, Object> map = new HashMap<>();
		map.put("userName", "bruce");
		map.put("userAge", 0);
		session.selectList(statement, map);
		System.out.println("age:>>>>" + map.get("userAge"));
	}
	
}
