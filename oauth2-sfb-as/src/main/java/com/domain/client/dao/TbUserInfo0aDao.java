package com.domain.client.dao;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.domain.client.model.entity.TbUserInfo0aEntity;
import com.domain.client.model.query.TbUserInfo0aGetOneByUsernameQuery;

import chok2.devwork.dao.BaseDao;

@Repository
public class TbUserInfo0aDao extends BaseDao
{
	@Resource(name = "sqlSessionTemplateMybatisClient")
	private SqlSession sqlSession;

	@Override
	protected SqlSession getSqlSession()
	{
		return sqlSession;
	}

	@Override
	protected String getSqlNamespace()
	{
		return getClass().getName();
	}
	
	public TbUserInfo0aEntity getOneByUsername(TbUserInfo0aGetOneByUsernameQuery query)
	{
		return getOne("getOneByUsername", query);
	}
}
