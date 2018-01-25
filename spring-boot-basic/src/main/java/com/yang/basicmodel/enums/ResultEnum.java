package com.yang.basicmodel.enums;

/**
 *  统一错误记录
 * @author klaus
 *
 */
public enum ResultEnum implements IResultEnumRole{
	UNKONW_ERROR(-1, "未知错误"), SUCCESS(0, "成功"),CUSTOM_ERROR(2,"自定义异常");

	private Integer code;

	private String msg;

	ResultEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	@Override
	public Integer getCode() {
		return code;
	}

	@Override
	public String getMsg() {
		return msg;
	}
}
