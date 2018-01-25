package com.yang.basicmodel.service.page;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yang.basicmodel.dao.MessageMapper;
import com.yang.basicmodel.entity.Message;

/** 
* @author 雷晨
* @E-mail: 
* @date 创建时间：2017年12月27日 上午10:31:31 
* @version 1.0 
* 参考http://blog.csdn.net/xiaolyuh123/article/details/73506189
*/
@Service
public class PageService {
	@Autowired 
	private MessageMapper messageMapper;
	
	public List<Message> findByPage() {
		PageHelper.startPage(0, 3);
		//Page<Message> pageMessage=messageMapper.findByPage();    从dao层获取返回类型为Page容器
		//List<Message> messageList=pageMessage.getResult();		可以使用getResult方法获取容器的值
		//System.out.println(pageMessage.getPages());				可以使用getPages方法获取容器的总页数
		//System.out.println(pageMessage.getTotal());				可以使用getTotal方法获取容器的总数据量 （还有其他方法，可以点开Page，查看属性get方法）
		//return messageList;
		return null;
	}
}
