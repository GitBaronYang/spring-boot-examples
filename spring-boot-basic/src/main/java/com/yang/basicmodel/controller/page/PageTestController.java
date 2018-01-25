package com.yang.basicmodel.controller.page;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yang.basicmodel.ReturnCode;
import com.yang.basicmodel.entity.Message;
import com.yang.basicmodel.service.page.PageService;

@RestController
public class PageTestController {
	@Autowired PageService pageService;
	
	@GetMapping("/test/page")
	public ReturnCode testPage(){
		List<Message> messageList = pageService.findByPage();
		 ReturnCode code = new ReturnCode.Builder().code(1).msg("成功").build();
	        return code;
	}
}
