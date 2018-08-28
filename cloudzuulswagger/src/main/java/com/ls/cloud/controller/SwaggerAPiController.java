package com.ls.cloud.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.ls.cloud.DocumentationConfig;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.swagger.web.SwaggerResource;

@RestController
@RequestMapping("/swaggerAPi")
public class SwaggerAPiController {

	@Autowired
	private DocumentationConfig documentationConfig;
	
	@GetMapping("addServiceApi")
	@ApiOperation(value="addServiceApi")
	public JSONObject addServiceApi(String service_name) {
		JSONObject json = new JSONObject();
		if(StringUtils.isEmpty(service_name)) {
			json.put("status", "failure");
			json.put("msg", "service_name不可为空");
			return json;
		}
		List<SwaggerResource> list = documentationConfig.get();

		for (SwaggerResource swaggerResource : list) {
			if(swaggerResource.getName().equals(service_name)) {
				json.put("status", "failure");
				json.put("msg", service_name+"已添加,请刷新页面");
				return json;
			}
		}
		String location ="/"+service_name+"/v2/api-docs";
		SwaggerResource swaggerResource = documentationConfig
				.swaggerResource(service_name, location, "2.0");
		
		documentationConfig.addResources(swaggerResource);
		
		json.put("status", "success");
		return json;
	}
	
	@GetMapping("removeServiceApi")
	public JSONObject removeServiceApi(String service_name) {
		JSONObject json = new JSONObject();
		if(StringUtils.isEmpty(service_name)) {
			json.put("status", "failure");
			json.put("msg", "service_name不可为空");
			return json;
		}
		List<SwaggerResource> list = documentationConfig.get();

		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			SwaggerResource swaggerResource = (SwaggerResource) iterator.next();
			if(swaggerResource.getName().equals(service_name)) {
				iterator.remove();
				json.put("status", "success");
				json.put("msg", service_name+"api 移除成功");
				return json;
			}
			
		}
		json.put("status", "failure");
		json.put("msg", service_name+"不存在");
		
		return json;
	}
	
	
	
}
