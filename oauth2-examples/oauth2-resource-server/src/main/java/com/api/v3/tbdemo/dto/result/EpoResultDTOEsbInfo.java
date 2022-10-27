package com.api.v3.tbdemo.dto.result;

import java.io.Serializable;

import org.springframework.util.ObjectUtils;

import chok.util.TimeUtil;
import io.swagger.v3.oas.annotations.media.Schema;

public class EpoResultDTOEsbInfo implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Schema(title ="返回状态", required = true)
	private String	returnStatus	= EpoEsbInfoConstants.SUCCESS_STATUS;
	@Schema(title ="返回code", required = true)
	private String	returnCode		= EpoEsbInfoConstants.SUCCESS_CODE;
	@Schema(title ="响应Message")
	private String	returnMsg		= "";
	@Schema(title ="请求时间")
	private String	requestTime		= "";
	@Schema(title ="响应时间", required = true)
	private String	responseTime	= "";
	@Schema(title ="接口编码")
	private String	instld			= "";
	private String	total			= "";
	@Schema(title ="总页数")
	private String	totalPage		= "";
	@Schema(title ="当前页")
	private String	thisPage		= "";
	@Schema(title ="下一页")
	private String	nextPage		= "";
	@Schema(title ="预留字段1")
	private String	att1			= "";
	@Schema(title ="预留字段2")
	private String	att2			= "";
	@Schema(title ="预留字段3")
	private String	att3			= "";
	@Schema(title ="总数")
	
	public String getReturnStatus()
	{
		return returnStatus;
	}

	public void setReturnStatus(String returnStatus)
	{
		this.returnStatus = returnStatus;
	}

	public String getReturnCode()
	{
		return returnCode;
	}

	public void setReturnCode(String returnCode)
	{
		this.returnCode = returnCode;
	}

	public String getReturnMsg()
	{
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg)
	{
		this.returnMsg = returnMsg;
	}

	public String getRequestTime()
	{
		return requestTime;
	}

	public void setRequestTime(String requestTime)
	{
		this.requestTime = requestTime;
	}

	public String getResponseTime()
	{
		return responseTime;
	}

	public void setResponseTime(String responseTime)
	{
		this.responseTime = responseTime;
	}

	public String getInstld()
	{
		return instld;
	}

	public void setInstld(String instld)
	{
		this.instld = instld;
	}

	public String getAtt1()
	{
		return att1;
	}

	public void setAtt1(String att1)
	{
		this.att1 = att1;
	}

	public String getAtt2()
	{
		return att2;
	}

	public void setAtt2(String att2)
	{
		this.att2 = att2;
	}

	public String getAtt3()
	{
		return att3;
	}

	public void setAtt3(String att3)
	{
		this.att3 = att3;
	}

	public String getTotal()
	{
		return total;
	}

	public void setTotal(String total)
	{
		this.total = total;
	}

	public String getTotalPage()
	{
		return totalPage;
	}

	public void setTotalPage(String totalPage)
	{
		this.totalPage = totalPage;
	}

	public String getThisPage()
	{
		return thisPage;
	}

	public void setThisPage(String thisPage)
	{
		this.thisPage = thisPage;
	}

	public String getNextPage()
	{
		return nextPage;
	}

	public void setNextPage(String nextPage)
	{
		this.nextPage = nextPage;
	}
	
	public void init(String instld, String requestTime)
	{
		this.setInstld(instld);
		this.setReturnMsg("");
		this.setReturnCode(EpoEsbInfoConstants.SUCCESS_CODE);
		this.setReturnStatus(EpoEsbInfoConstants.SUCCESS_STATUS);
		this.setRequestTime(requestTime);
		this.setResponseTime("");
	}
	
	public void setSuccess(String instld, String msg)
	{
		this.setInstld(instld);
		this.setReturnMsg(msg);
		this.setReturnCode(EpoEsbInfoConstants.SUCCESS_CODE);
		this.setReturnStatus(EpoEsbInfoConstants.SUCCESS_STATUS);
		this.setResponseTime(TimeUtil.getCurrentMillTime());
	}
	
	public void setError(String instld, String msg)
	{
		this.setInstld(instld);
		this.setReturnMsg(msg);
		this.setReturnCode(EpoEsbInfoConstants.ERROR_CODE);
		this.setReturnStatus(EpoEsbInfoConstants.ERROR_STATUS);
		this.setResponseTime(TimeUtil.getCurrentMillTime());
	}
	
	public void setFinish() 
	{
		this.setResponseTime(TimeUtil.getCurrentMillTime());
	}
	
	public void setPageInfo(Integer total, Integer totalPage, Integer thisPage, Integer nextPage)
	{
		this.setTotal("" + (!ObjectUtils.isEmpty(total) ? total : ""));
		this.setTotalPage("" + (!ObjectUtils.isEmpty(totalPage) ? totalPage : ""));
		this.setThisPage("" + (!ObjectUtils.isEmpty(thisPage) ? thisPage : ""));
		this.setNextPage("" + (!ObjectUtils.isEmpty(nextPage) ? nextPage : ""));
	}
}
