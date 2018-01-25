package com.yang.basicmodel.aspect;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yang.basicmodel.ReturnCode;



/** 
* @author  yf
* Http拦截器，处理webtoken
* @date 创建时间：2017年12月1日 下午6:55:55 
* @version 1.0   
*/
public class HTTPBearerAuthorizeAttribute implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	    
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, filterConfig.getServletContext());

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;

		// 登录和测试接口放行，其余接口全部监听
		boolean isSwagger = httpRequest.getRequestURL().toString().contains("swagger");
		boolean isSwaggerApi = httpRequest.getRequestURL().toString().contains("api-docs");
		boolean isLogin = httpRequest.getRequestURL().toString().endsWith("login");
		boolean isExit = httpRequest.getRequestURL().toString().endsWith("exit");
		
		if (isLogin||isExit || isSwagger || isSwaggerApi) {
			chain.doFilter(request, response);
			return;
		}

		 String webtoken = httpRequest.getHeader("x-access-token");// 从请求header中拿出webtoken
		if ((webtoken != null) && (webtoken.length() > 7)) {
/*			SysUser sysUser = MeasuringUtil.getUserByWebToken(httpRequest);
			sysUser = GetRedisData.GetUser(sysUser.getfId());
			if (sysUser != null)// 有缓存数据时表示请求合法
			{
                chain.doFilter(request, response);
                return;
			}*/
		}else
		{
	          //测试项目
            chain.doFilter(request, response);
            return;
		}

		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.setCharacterEncoding("UTF-8");
		httpResponse.setContentType("application/json; charset=utf-8");
		httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

		ObjectMapper mapper = new ObjectMapper();
		
		
		//((HttpServletResponse) response).sendRedirect("");  
		//直接重定向
		ReturnCode msg = new ReturnCode();
		msg.setCode(HttpServletResponse.SC_UNAUTHORIZED);
		msg.setMsg("未登陆或登陆超时");
		httpResponse.getWriter().write(mapper.writeValueAsString(msg));
		return;
	}

	@Override
	public void destroy() {
	}
}
