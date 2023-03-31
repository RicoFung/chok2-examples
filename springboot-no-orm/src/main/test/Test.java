import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.Application;
import com.domain.customtbdemo.model.entity.CustomTbDemoEntity;
import com.domain.customtbdemo.service.CustomTbDemoService;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class Test
{
	
	@Autowired
	CustomTbDemoService v3Service;
	
//	@org.junit.Test
	public void testTransaction()
	{
		CustomTbDemoEntity demo = new CustomTbDemoEntity();
		demo.setTcCode("c3");
		demo.setTcName("n3");
		try
		{
//			v3Service.create(demo);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
