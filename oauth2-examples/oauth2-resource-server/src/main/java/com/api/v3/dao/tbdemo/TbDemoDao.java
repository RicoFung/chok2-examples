package com.api.v3.dao.tbdemo;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.api.v3.dao.tbdemo.po.TbDemoGetListParamPO;
import com.api.v3.dao.tbdemo.po.TbDemoGetListResultPO;
import com.api.v3.dao.tbdemo.po.TbDemoGetOneParamPO;
import com.api.v3.dao.tbdemo.po.TbDemoGetOneResultPO;
import com.api.v3.entity.TbDemo;

@Repository(value = "v3TbDemoDao")
public class TbDemoDao //extends BaseDao<TbDemo,Long>
{
	@SuppressWarnings("unused")
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
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

	public TbDemoGetOneResultPO getOne(TbDemoGetOneParamPO paramPO) 
	{
		return this.sqlSession.selectOne(getClass().getName()+"."+"getOne", paramPO.getTcRowid());
	}
	
	public List<TbDemoGetListResultPO> getList(TbDemoGetListParamPO paramPO) 
	{
		return this.sqlSession.selectList(getClass().getName()+"."+"getList", paramPO);
	}
}
