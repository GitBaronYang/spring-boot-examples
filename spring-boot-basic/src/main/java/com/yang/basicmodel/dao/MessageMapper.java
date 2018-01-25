package com.yang.basicmodel.dao;

import org.apache.ibatis.annotations.Mapper;

import com.github.pagehelper.Page;
import com.yang.basicmodel.entity.Message;

@Mapper
public interface MessageMapper {
	//Page<Message> findByPage();
}
