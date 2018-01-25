package com.yang.basicmodel.exception;

import com.yang.basicmodel.enums.IResultEnumRole;

/**
 * 
 * @author klaus
 *
 */
public class CustomException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private Integer code;

	public CustomException(IResultEnumRole resultEnum) {
		super(resultEnum.getMsg());
		this.code = resultEnum.getCode();
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
}
