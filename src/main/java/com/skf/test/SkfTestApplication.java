package com.skf.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@SpringBootApplication
public class SkfTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkfTestApplication.class, args);
	}

}
