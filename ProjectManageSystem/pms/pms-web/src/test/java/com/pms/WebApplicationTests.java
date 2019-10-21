package com.pms;

import com.pms.base.vo.DataTable;
import com.pms.entity.CodeReviewEntity;
import com.pms.entity.taskInfo.TaskInfoEntity;
import com.pms.service.ICodeReviewService;
import com.pms.service.svcInterface.taskInfo.ITaskInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebApplicationTests {

	@Resource
	private ITaskInfoService taskInfoService;
	@Resource
	private ICodeReviewService codeReviewService;

	@Test
	public void contextLoads() {

		List<CodeReviewEntity> list = codeReviewService.list();
		System.out.println(list.toString());
		System.out.println("Hello world!");
		DataTable<CodeReviewEntity> dt = new DataTable<CodeReviewEntity>();
		Map<String,String> sortsMap = new HashMap<>();
		sortsMap.put("id","desc");
		dt.setSorts(sortsMap);
		dt.setPageNumber(1);
		dt.setPageSize(2);
		DataTable<CodeReviewEntity> outDt = codeReviewService.pageSearch(dt);
		List<CodeReviewEntity> resultList = outDt.getRows();
		resultList.stream().forEach(x -> System.out.println(x.toString()));
		int counts = outDt.getTotal();
		System.out.println("counts = " + counts);
		CodeReviewEntity codeReviewEntity = codeReviewService.getById(1);
		System.out.println(codeReviewEntity.getCheckTime());

	}

	@Test
	public void testTaskController() {
		Map<String,Object> inMap = new HashMap<>();
		inMap.put("task_code","123456");
		List<TaskInfoEntity> result = taskInfoService.getTaskDetailByMap(inMap);
		if(result != null && result.size() > 0) {
			System.out.println(result.get(0).getFounder());
		}
	}

}
