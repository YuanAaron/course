package cn.coderap.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
@EnableEurekaClient
public class SystemApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(SystemApplication.class);


	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SystemApplication.class);
		ConfigurableEnvironment env = app.run(args).getEnvironment();
		//启动日志可以按需扩展，甚至可以打印一些有趣的字符图案
		LOGGER.info("启动成功!");
		LOGGER.info("System地址：\thttp://127.0.0.1:{}",env.getProperty("server.port"));
	}

}
