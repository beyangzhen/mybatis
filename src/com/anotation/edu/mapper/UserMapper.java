package com.anotation.edu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.anotation.edu.entity.User;


public interface UserMapper {
	/**
	 *  #{} 1. 解析的是占位符（？），传入数据当成一个字符串
	 *      2. 可以防止SQL注入
	 *		3. 若为空，不会报错 
	 *
	 *  ${} 1. 传入数据直接生成在sql中
	 *		2. 不能防止sql注入
	 *		3. 若为空，会报错
	 *
	 *  --> MyBatis排序时，使用order by动态参数，用$而不是#  
	 */
	@Insert(" insert into users(name,age) values(#{name} , #{age}) ")
	public int add(User user);
	
	@Delete(" delete from users where id= #{id}")
	public int delete(int id);
	
	@Update("update users set name=#{name} ,age=#{age} where id=#{id}")
	public int update(User user);
	
	@Select("select * from users where id=#{id}")
	public User findUserById(int id);
	
	// 也可以这样指定表字段和类属性不一样时的匹配关系 @Select("select id as order_id, orderNo as order_name, orderCount as order_count from orders")
	@Select("select * from users")
	@Results({
		// 表字段和类属性不一样时，可以指定匹配关系
		@Result(id=true, property="id", column="id", javaType=Integer.class),
		@Result(property="name", column="name", javaType=String.class),
		@Result(property="age", column="age", javaType=Double.class)
	})
	public List<User> findAll();
}
