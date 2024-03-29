package com.api.v2.dao;

import javax.annotation.Resource;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.api.v2.entity.TbDemo;

import chok.devwork.BaseDao;

@Repository(value = "v2TbDemoDao")
public class TbDemoDao extends BaseDao<TbDemo,Long>
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
