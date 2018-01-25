package com.yang.basicmodel.enums;

/**
 * @ClassName: ResultEnumRole 
 * @Description: 结果异常枚举基类接口,用户需要自定义异常枚举请集成此接口
 * @author 宁煜
 * @date 2017年12月7日 上午9:57:38 
 *
 */
public interface IResultEnumRole {

	/**
	 * @功能描述 定义返回码
	 * @return
	 */
	Integer getCode();
	
	/**
	 * @功能描述 定义返回消息
	 * @return
	 */
	String getMsg();
}
