package com.api.v1.tbdemo.dao;

import javax.annotation.Resource;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.api.v1.tbdemo.entity.TbDemo;

import chok.devwork.BaseDao;

@Repository(value = "v1TbDemoDao")
public class TbDemoDao extends BaseDao<TbDemo, Long>
{
	@Resource//(name = "firstSqlSessionTemplate")
	private SqlSession sqlSession;

	@Override
	protected SqlSession getSqlSession()
	{
		return sqlSession;
	}
	
	@Override
	public Class<TbDemo> getEntityClass()
	{
		return TbDemo.class;
	}
}
