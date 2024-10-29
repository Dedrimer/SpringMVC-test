package com.zjtec.travel;

import com.zjtec.travel.controller.RegisterController;
import org.apache.catalina.startup.Tomcat;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;

public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    private int port = 8082;
    private String contextPath = "";
    public void start() throws Exception {
        //新构建一个Tomcat实例
        Tomcat tomcat = new Tomcat();
        //获取 SpringMVC 配置文件的路径
        URL url = getClass().getClassLoader().getResource("druid.properties");
        logger.info("URL path:"+url.getPath());
        //获取项目根目录
        String pwd = StringUtils.substringBefore(url.getPath(), "/target/classes");
        logger.info("BaseDir:"+pwd);
        //设置项目根目录为 BaseDir
        tomcat.setBaseDir(pwd);
        tomcat.setPort(port);

        StringBuilder webAppBuilder = new StringBuilder();
        //设置 war 目录
        webAppBuilder.append(pwd).append(File.separator).append("target/zjtec_travel");
        String webapp = webAppBuilder.toString();

        tomcat.addWebapp(contextPath, webapp);
        tomcat.enableNaming();
        tomcat.start();//启动
        tomcat.getServer().await();
    }

    public static void main(String[] args) throws Exception {
        Application starter = new Application();
        starter.start();
    }
}
