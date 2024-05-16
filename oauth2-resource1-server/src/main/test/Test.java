import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.Application;
import com.domain.customtbdemo.dao.CustomTbDemoDao;
import com.domain.tbdemo.model.entity.TbDemoEntity;
import com.domain.tbdemo.service.TbDemoService;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class Test
{
	
	@Autowired
	CustomTbDemoDao v3Dao;
	@Autowired
	TbDemoService v3Service;
	
//	@org.junit.Test
	public void testTransaction()
	{
		TbDemoEntity demo = new TbDemoEntity();
		demo.setTcCode("c3");
		demo.setTcName("n3");
		try
		{
			v3Service.create(demo);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
