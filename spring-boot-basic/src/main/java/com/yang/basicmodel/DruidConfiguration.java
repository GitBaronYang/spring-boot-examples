package com.yang.basicmodel;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;

/**
 * @author yf druid 配置文件启动web监控管理。 
 * 开发访问方式http://IP:port/druid/sql.html
 * 正式部署访问方式http://IP:port/{项目名}/druid/sql.html
 * @date 创建时间：2017年12月14日 下午2:48:25
 * @version 1.0
 * 参考http://www.jianshu.com/p/e3cd2e1c2b0c
 * 数据库连接池应该设置多大
 * 参考https://www.jianshu.com/p/a8f653fc0c54?from=singlemessage
 */
@Configuration
public class DruidConfiguration {
    private Logger logger = LoggerFactory.getLogger(DruidConfiguration.class);

    /**
     * @功能描述 把配置文件的参数自动赋值到dataSource里。
     * @return
     */
    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource druidDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        //添加自定义wall配置
        List<Filter> filterList = new ArrayList<>();
        filterList.add(wallFilter());
        dataSource.setProxyFilters(filterList);
        return dataSource;
    }

    /**
     * @功能描述 配置监控界面
     * @return
     */
    @Bean
    public ServletRegistrationBean druidStatViewServlet() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        // IP白名单 (没有配置或者为空，则允许所有访问)
        // registrationBean.addInitParameter("allow", "127.0.0.1");
        // IP黑名单 (存在共同时，deny优先于allow)
        // registrationBean.addInitParameter("deny", "");
        registrationBean.addInitParameter("loginUsername", "admin");
        registrationBean.addInitParameter("loginPassword", "admin");
        registrationBean.addInitParameter("resetEnable", "false");
        return registrationBean;
    }

    /**
     * @功能描述 编写过滤器，配合druid监控的使用
     * @return
     */
    @Bean
    public FilterRegistrationBean druidWebStatViewFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new WebStatFilter());
        registrationBean.addInitParameter("urlPatterns", "/*");
        registrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
        return registrationBean;
    }

    /**
     * @功能描述 
     * 配置文件原有配置
     * spring.datasource.filters=stat,wall,log4j
     * 因为直接在数据库这样配置就无法允许mybaits一次性查询多条sql.所以需要自定义配置wallConfig
     * @return
     */
    @Bean
    public WallFilter wallFilter() {
        WallFilter wallFilter = new WallFilter();
        wallFilter.setConfig(wallConfig());
        return wallFilter;
    }

    @Bean
    public WallConfig wallConfig() {
        WallConfig config = new WallConfig();
        // 允许一次执行多条语句
        config.setMultiStatementAllow(true);
        // 允许非基本语句的其他语句
        config.setNoneBaseStatementAllow(true);
        return config;
    }
}
