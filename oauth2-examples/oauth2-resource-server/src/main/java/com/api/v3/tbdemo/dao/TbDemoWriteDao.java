package com.api.v3.tbdemo.dao;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.api.v3.tbdemo.pojo.TbDemo;


@Repository(value = "v3TbDemoWriteDao")
public class TbDemoWriteDao
{
	@Resource//(name = "firstSqlSessionTemplate")
	private SqlSession sqlSession;
	
	public void add(TbDemo tbDemo) 
	{
		this.sqlSession.insert(getClass().getName()+"."+"add", tbDemo);
	}
	
	public void del(String[] idArray)
	{
		this.sqlSession.delete(getClass().getName()+"."+"del", idArray);
	}
	
	public void upd(TbDemo tbDemo)
	{
		this.sqlSession.update(getClass().getName()+"."+"upd", tbDemo);
	}
}
