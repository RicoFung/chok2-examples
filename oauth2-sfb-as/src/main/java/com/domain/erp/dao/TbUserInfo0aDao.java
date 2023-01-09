package com.domain.erp.dao;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.domain.erp.model.entity.TbUserInfo0aEntity;
import com.domain.erp.model.query.TbUserInfo0aGetOneByUsernameQuery;

import chok.devwork.BaseDaoV2;

@Repository
public class TbUserInfo0aDao extends BaseDaoV2
{
	@Resource(name = "sqlSessionTemplateMybatis")
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
