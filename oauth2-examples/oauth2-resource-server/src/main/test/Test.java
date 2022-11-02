import java.util.List;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.Application;
import com.api.v2.entity.TbDemo;
import com.api.v2.tbdemo.service.TbDemoService;
import com.api.v3.repository.tbdemo.dao.param.TbDemoGetListParam;
import com.api.v3.repository.tbdemo.dao.param.TbDemoGetOneParam;
import com.api.v3.repository.tbdemo.dao.po.TbDemoGetListPO;
import com.api.v3.repository.tbdemo.dao.po.TbDemoGetOnePO;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class Test
{
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	TbDemoService service;
	@Autowired
	com.api.v3.repository.tbdemo.dao.TbDemoDao v3Dao;
	@Autowired
	com.api.v3.module.tbdemo.service.TbDemoService v3Service;
	
//	@org.junit.Test
	public void testTransaction()
	{
		TbDemo demo = new TbDemo();
		demo.setTcCode("c3");
		demo.setTcName("n3");
		try
		{
			service.add(demo);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
//	@org.junit.Test
	public void testDaoGetOne()
	{
		TbDemoGetOneParam paramPO = new TbDemoGetOneParam();
		paramPO.setTcRowid(91402l);
		TbDemoGetOnePO resultPO = v3Dao.getOne(paramPO);
		logger.info("[resultPO <= {}]", resultPO.toString());
	}
	
//	@org.junit.Test
//	public void testDaoGetList()
//	{
//		TbDemoGetListParamPO paramPO = new TbDemoGetListParamPO();
//		paramPO.setTcCode("c1");
//		List<TbDemoGetListResultPO> resultPO = v3Dao.getList(paramPO);
//		logger.info("[resultPO <= {}]", resultPO.toString());
//	}
	
//	@org.junit.Test
//	public void testServiceGetOne()
//	{
//		TbDemoGetOneParamBO paramBO = new TbDemoGetOneParamBO();
//		paramBO.setTcRowid(91402l);
//		ChokResultBO<TbDemoGetOneResult> resultBO = v3Service.getOne(paramBO);
//		logger.info("[{}]", resultBO.toString());
//	}
//	
//	@org.junit.Test
//	public void testServiceGetList()
//	{
//		TbDemoGetListParamBO paramBO = new TbDemoGetListParamBO();
//		paramBO.setTcCode("c1");
//		ChokResultBO<List<TbDemoGetListResult>> resultBO = v3Service.getList(paramBO);
//		logger.info("[{}]", resultBO.toString());
//	}
}
