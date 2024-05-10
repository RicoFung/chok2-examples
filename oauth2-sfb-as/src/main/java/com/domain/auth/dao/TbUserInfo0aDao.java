package com.domain.auth.dao;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.domain.auth.model.param.TbUserInfo0aGetOneByUsernameParam;
import com.domain.auth.model.result.TbUserInfo0aResult;

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
	
	public TbUserInfo0aResult getOneByUsername(TbUserInfo0aGetOneByUsernameParam param)
	{
		return queryOne("getOneByUsername", param);
	}
}
