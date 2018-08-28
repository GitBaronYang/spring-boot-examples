package com.ls.cloud.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ls.cloud.service.AccountService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;

@Component
public class TokenAuthorFilter extends ZuulFilter {

	
	@Autowired
	private AccountService accountService;

	private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(

			Arrays.asList("/userRegistration","/oauth/token", "/oauth/singOut","/oauth/userid","/swagger-ui.html","/webjars","/v2/api-docs")));
	
	
	
	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		RequestContext requestContext = RequestContext.getCurrentContext();
	    HttpServletRequest request = requestContext.getRequest();
	        //判读是否需要拦击
	    String path =request.getRequestURI();

		
		boolean allowedPath = ALLOWED_PATHS.contains(path);
		if(path.contains("/webjars")||path.contains("/swagger")||
				path.contains("/api-docs")||path.contains("/oauth/token")||
				path.contains("/userRegistration")) {
			allowedPath=false;
			return allowedPath;
		}
		return true;
	}

	@Override
	public Object run() {
		// TODO Auto-generated method stub
		 RequestContext requestContext = RequestContext.getCurrentContext();
	     HttpServletRequest request = requestContext.getRequest();
	     HttpServletResponse response = requestContext.getResponse();
	        //获取传来的参数accessToken
	     Object accessToken = request.getHeader("accessToken");
	     if( accessToken ==null || StringUtils.isEmpty(accessToken.toString())) {
	            //过滤该请求，不往下级服务去转发请求，到此结束
	            requestContext.setSendZuulResponse(false);
	            requestContext.setResponseStatusCode(288);
	            requestContext.setResponseBody("{'code':'-1','ResponseStatusCode':'288'}");
	   	     response.setHeader("accessToken", accessToken.toString());
		     System.out.println(accessToken);   
	   	     return null;
	        }

		
		return null;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	


}