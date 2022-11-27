package com.api.v3.repository.tbdemo.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.api.v3.repository.tbdemo.dao.param.TbDemoGetListParam;
import com.api.v3.repository.tbdemo.dao.param.TbDemoGetOneParam;
import com.api.v3.repository.tbdemo.dao.po.TbDemoGetListPo;
import com.api.v3.repository.tbdemo.dao.po.TbDemoGetOnePo;
import com.api.v3.repository.tbdemo.entity.TbDemo;

@Repository(value = "v3TbDemoDao")
public class TbDemoDao
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

	public TbDemoGetOnePo getOne(TbDemoGetOneParam param) 
	{
		return this.sqlSession.selectOne(getClass().getName()+"."+"getOne", param);
	}
	
	public List<TbDemoGetListPo> getList(TbDemoGetListParam param) 
	{
		return this.sqlSession.selectList(getClass().getName()+"."+"getList", param);
	}
}
