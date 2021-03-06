package cn.coderap.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

@SpringBootApplication
@EnableEurekaClient
public class GatewayApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(GatewayApplication.class);


	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(GatewayApplication.class);
		ConfigurableEnvironment env = app.run(args).getEnvironment();
		//启动日志可以按需扩展，甚至可以打印一些有趣的字符图案
		LOGGER.info("启动成功!");
		LOGGER.info("Gateway地址：\thttp://127.0.0.1:{}",env.getProperty("server.port"));
	}

	/**
	 * 跨域总结：
	 * 单个SpringBoot应用使用CorsConfig解决跨域问题；使用SpringCloud Gateway的使用CorsWebFilter解决跨域问题
	 * 跨域
	 */
	@Bean
	public CorsWebFilter corsFilter() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(Boolean.TRUE);
		config.addAllowedMethod("*");
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.setMaxAge(3600L);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
		source.registerCorsConfiguration("/**", config);
		return new CorsWebFilter(source);
	}

}
