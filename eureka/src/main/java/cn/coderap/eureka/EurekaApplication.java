package cn.coderap.eureka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {

	/**
	 * springboot默认集成slf4j框架，它定义了一些日志接口，以规范日志输出，真正的实现是logback或log4j。
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(EurekaApplication.class);

//	public static void main(String[] args) {
//		SpringApplication.run(EurekaApplication.class, args);
//	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(EurekaApplication.class);
		ConfigurableEnvironment env = app.run(args).getEnvironment();
		//启动日志可以按需扩展，甚至可以打印一些有趣的字符图案
		LOGGER.info("启动成功!");
		LOGGER.info("Eureka地址：\thttp://127.0.0.1:{}",env.getProperty("server.port"));
	}

}
