package com.example.tldspringboot;

import com.example.tldspringboot.ioc.ApplicationContextProvider;
import com.example.tldspringboot.ioc.Base64Encoder;
import com.example.tldspringboot.ioc.Encoder;
import com.example.tldspringboot.ioc.UrlEncoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;

@SpringBootApplication
@ServletComponentScan(basePackages = {"com.example.tldspringboot"})
public class TldSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(TldSpringBootApplication.class, args);
		System.out.println(Base64.getEncoder().encodeToString("harry".getBytes()));
		ApplicationContext context = ApplicationContextProvider.getContext();

		//Base64Encoder base64 = context.getBean(Base64Encoder.class);
		//UrlEncoder urlEncoder = context.getBean(UrlEncoder.class);
		//Encoder encoder = new Encoder(base64);

		//Encoder encoder = context.getBean(Encoder.class);

		Encoder encoder = context.getBean("urlEncode", Encoder.class);
		String url = "www.naver.com/books/it?=page=10&size=20&name=spring-boot";

		String result = encoder.encode(url);
		System.out.println(result);

		//encoder.setIEncoder(urlEncoder);
		//result = encoder.encode(url);
		//System.out.println(result);
	}

}

@Configuration
class AppConfig{

	@Bean("base64Encode")
	public Encoder encoder(Base64Encoder base64Encoder) {
		return new Encoder(base64Encoder);
	}

	@Bean("urlEncode")
	public Encoder encoder(UrlEncoder urlEncoder) {
		return new Encoder(urlEncoder);
	}
}
