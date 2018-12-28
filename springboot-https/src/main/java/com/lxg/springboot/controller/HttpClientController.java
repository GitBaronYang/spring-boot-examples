package com.lxg.springboot.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.lxg.springboot.ResultData;
import com.lxg.springboot.Token;

/**
 * Created by lxg
 * on 2017/2/6.
 */
@RestController
public class HttpClientController {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(){        
        String forObject = restTemplate.getForObject("http://www.baidu.com", String.class);
        System.out.println(forObject);
        return forObject;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){        
        MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
        postParameters.add("account", "**");
        postParameters.add("pw", "**");
        HttpHeaders headers = new HttpHeaders();
        headers.add("sign", "**");
        headers.add("timestamp", "a");
        headers.add("agent", "0");
        headers.add("brandid", "00");
        headers.add("language", "zh");
        headers.add("userid", "**");
        headers.add("content-type", "application/x-www-form-urlencoded");
        HttpEntity<MultiValueMap<String, Object>> r = new HttpEntity<>(postParameters, headers);

        String data= restTemplate.postForObject("https://api.yespowering.cn/login/", r, String.class);
        System.out.println(data.toString());
        //反序列化返回消息信息，得到接口返回code的msg. ResultData.getData() 需要根据明确类进行第二次反序列胡
        ResultData  resultData = JSONObject.parseObject(data,ResultData.class);
        System.out.println(resultData.toString());
        //第二次反序列化得到data
        Token tk = JSONObject.parseObject(resultData.getData().toString(),Token.class);
        System.out.println(tk.toString());
        return data.toString();
    }
    
    @RequestMapping(value = "/deviceList", method = RequestMethod.GET)
    public String deviceList(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("sign", "532146B9A862CF7C148C34F0F183A097");
        headers.add("timestamp", "a");
        headers.add("agent", "0");
        headers.add("brandid", "00");
        headers.add("language", "zh");
        headers.add("token", Token.token);
        HttpEntity<String> r = new HttpEntity<>(null,headers);
        ResponseEntity<String> exchange = restTemplate.exchange("https://api.yespowering.cn/native/device/group/type/",HttpMethod.GET,r,String.class);
        System.out.println(exchange.getBody());
        return exchange.getBody();
    }
    
    
    @RequestMapping(value = "/data/{boxid}", method = RequestMethod.GET)
    public String data(@PathVariable("boxid") String boxid){        
        HttpHeaders headers = new HttpHeaders();
        headers.add("sign", "532146B9A862CF7C148C34F0F183A097");
        headers.add("timestamp", "a");
        headers.add("agent", "0");
        headers.add("brandid", "00");
        headers.add("language", "zh");
        headers.add("token", Token.token);
        HttpEntity<String> r = new HttpEntity<>(null,headers);
        Map<String, String> map = new HashMap<>();
        map.put("box_id", boxid);
        ResponseEntity<String> exchange = restTemplate.exchange("https://api.yespowering.cn/dev/data/realtime/?box_id="+boxid,HttpMethod.GET,r,String.class);
        System.out.println(exchange.getBody());
        return exchange.getBody();
    }
    
}
