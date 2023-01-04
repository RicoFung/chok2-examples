package com.api.v3.tbdemo.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.api.v3.tbdemo.domain.data.TbDemoGetListData;
import com.api.v3.tbdemo.domain.data.TbDemoGetOneData;
import com.api.v3.tbdemo.domain.entity.TbDemoEntity;
import com.api.v3.tbdemo.domain.query.TbDemoGetListQuery;
import com.api.v3.tbdemo.domain.query.TbDemoGetOneQuery;


@Repository(value = "v3TbDemoWriteDao")
public class TbDemoDao
{
	@Resource//(name = "firstSqlSessionTemplate")
	private SqlSession sqlSession;
	
	public void add(TbDemoEntity entity) 
	{
		this.sqlSession.insert(getClass().getName()+"."+"add", entity);
	}
	
	public void del(String[] idArray)
	{
		this.sqlSession.delete(getClass().getName()+"."+"del", idArray);
	}
	
	public void upd(TbDemoEntity entity)
	{
		this.sqlSession.update(getClass().getName()+"."+"upd", entity);
	}
	
	public TbDemoGetOneData getOne(TbDemoGetOneQuery query) 
	{
		return this.sqlSession.selectOne(getClass().getName()+"."+"getOne", query);
	}
	
	public List<TbDemoGetListData> getList(TbDemoGetListQuery query) 
	{
		return this.sqlSession.selectList(getClass().getName()+"."+"getList", query);
	}
}
