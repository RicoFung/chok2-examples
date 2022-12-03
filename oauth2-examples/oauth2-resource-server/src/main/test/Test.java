import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.Application;
import com.api.v2.entity.TbDemo;
import com.api.v2.tbdemo.service.TbDemoService;
import com.api.v3.customtbdemo.dao.CustomTbDemoReadDao;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class Test
{
	
	@Autowired
	TbDemoService service;
	@Autowired
	CustomTbDemoReadDao v3Dao;
	@Autowired
	TbDemoService v3Service;
	
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
	
}
