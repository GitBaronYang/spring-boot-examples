package com.yang.basicmodel;


/**
 * API统一返回对象
 * 
 * @author klaus
 *
 */
/**
 * @author
 * @E-mail:
 * @date 创建时间：2017年12月4日 上午11:55:01
 * @version 1.0
 * @see {@link HttpResult}
 */
public class ReturnCode {
	/**
	 * 返回状态码
	 */
	private int code;
	/**
	 * 消息
	 */
	private String msg;
	/**
	 * 对象
	 */
	private Object obj;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    @Override
	public String toString() {
		return "ReturnCode [code=" + code + ", msg=" + msg + ", obj=" + obj + "]";
	}

	/**
	 * @author yf 建造者摸模式处理返回数据
	 * @E-mail:
	 * @date 创建时间：2017年12月4日 上午11:55:03
	 * @version 1.0
	 */
	public static class Builder {
		private ReturnCode returnCode;

		public Builder() {
			returnCode = new ReturnCode();
		}

		public Builder code(int code) {
			returnCode.setCode(code);
			return this;
		}
		public Builder msg(String msg) {
			returnCode.setMsg(msg);
			return this;
		}

		public Builder object(Object obj) {
			returnCode.setObj(obj);
			return this;
		}

		public ReturnCode build() {
			return returnCode;
		}
	}

}
