package com.yang.basicmodel.handle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yang.basicmodel.ReturnCode;
import com.yang.basicmodel.exception.CustomException;
import com.yang.basicmodel.util.ResultUtil;



/** 
* @author  klaus
* 全局异常处理类，捕获所有来自Controller的异常，打印异常信息到日志文件中，并返回简单错误码给前端。
* @date 创建时间：2017年12月27日 下午12:24:28 
* @version 1.0   
*/
@ControllerAdvice
public class ExceptionHandle {

	private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ReturnCode handle(Exception e) {
		if (e instanceof CustomException) {
		    CustomException userException = (CustomException) e;
			return ResultUtil.error(userException.getCode(), userException.getMessage());
		} else {
			logger.error(String.format("【系统异常】{%s}", e.getStackTrace()[0].toString()));
			return ResultUtil.error(-1, "未知错误");
		}
	}
}
