package com.api.v3.repository.tbdemo.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.api.v3.repository.tbdemo.dao.param.TbDemoGetListParam;
import com.api.v3.repository.tbdemo.dao.param.TbDemoGetOneParam;
import com.api.v3.repository.tbdemo.dao.po.TbDemoGetListPO;
import com.api.v3.repository.tbdemo.dao.po.TbDemoGetOnePO;
import com.api.v3.repository.tbdemo.entity.TbDemo;

@Repository(value = "v3TbDemoDao")
public class TbDemoDao
{
	@Resource//(name = "firstSqlSessionTemplate")
	private SqlSession sqlSession;
	
	public void add(TbDemo po)
	{
		this.sqlSession.insert(getClass().getName()+"."+"add", po);
	}
	
	public void del(String[] idArray)
	{
		this.sqlSession.delete(getClass().getName()+"."+"del", idArray);
	}
	
	public void upd(TbDemo po)
	{
		this.sqlSession.update(getClass().getName()+"."+"upd", po);
	}

	public TbDemoGetOnePO getOne(TbDemoGetOneParam paramPO) 
	{
		return this.sqlSession.selectOne(getClass().getName()+"."+"getOne", paramPO);
	}
	
	public List<TbDemoGetListPO> getList(TbDemoGetListParam paramPO) 
	{
		return this.sqlSession.selectList(getClass().getName()+"."+"getList", paramPO);
	}
}
