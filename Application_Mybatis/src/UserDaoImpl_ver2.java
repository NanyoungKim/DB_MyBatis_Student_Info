package com.zdq.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import com.zdq.dao.UserDao;
import com.zdq.entiy.User;
import com.zdq.util.MybatisUtil;
import com.zdq.util.PageUtil;

public class UserDaoImpl implements UserDao {

	public void selectAll(PageUtil<User> page) {
		SqlSession session = MybatisUtil.getSession();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num1", page.getIndex() * page.getSize());
		map.put("num2", (page.getIndex() - 1) * page.getSize());
		List<User> list = session.selectList("UserInfo.all", map);
		page.setCount((Integer) session.selectOne("UserInfo.count"));
		page.setList(list);
		MybatisUtil.closeSession();
	}

	public int del(int id) {
		SqlSession session = MybatisUtil.getSession();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		int num = session.delete("UserInfo.del", map);
		session.commit();
		MybatisUtil.closeSession();
		return num;
	}

	public User ById(int id) {
		SqlSession session = MybatisUtil.getSession();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		User u = session.selectOne("UserInfo.ById", map);
		MybatisUtil.closeSession();
		return u;
	}

	public int upd(User u) {
		SqlSession session = MybatisUtil.getSession();
		int num = session.update("UserInfo.upd", u);
		session.commit();
		MybatisUtil.closeSession();
		return num;
	}

	public int add(User u) {
		SqlSession session = MybatisUtil.getSession();
		int num = session.delete("UserInfo.add", u);
		session.commit();
		MybatisUtil.closeSession();
		return num;
	}
}
