package com.ls.cloud.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;

@FeignClient(name="lscloudlogin")
public interface AccountService {
	@RequestMapping(value="/refresh/token/{accesstoken}",method=RequestMethod.POST)
	public JSONObject refreshToken(@RequestParam("accesstoken") String accesstoken);

}
