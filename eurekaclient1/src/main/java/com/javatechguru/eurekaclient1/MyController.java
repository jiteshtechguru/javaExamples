package com.javatechguru.eurekaclient1;

import ch.qos.logback.core.net.SyslogOutputStream;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MyController {

	@Autowired
	RestTemplate restTemplate;
	@Autowired
	FeignClientExample feignClientExample;

	@GetMapping("/client1")
	String getStr() {
		return "from client1 ";
	}

	@GetMapping("/")
	String init() {
		return "from client1";
	}
	
	
	@GetMapping("/fromSchool")
	@CircuitBreaker(name = "CircuitBreakerService", fallbackMethod = "fallbackMethod1")

	public ResponseEntity<String> all() {
		//ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/", String.class);
		ResponseEntity<String> response = restTemplate.getForEntity("http://school", String.class);
		return response;
	}
	public ResponseEntity<String> fallbackMethod1(Exception ex) {
		return new ResponseEntity<>("fallback method call " +ex, HttpStatus.SERVICE_UNAVAILABLE);
	}
	int i=0;
	@GetMapping("retryTesting")
	@Retry(name = "RETRY-ID", fallbackMethod = "retryFallbackMehthod")
	public String retryTest(){
		i++;
		ResponseEntity<String> response = restTemplate.getForEntity("http://school", String.class);
		return "retry method call :"+ i + response.getBody();
		}

	public String retryFallbackMehthod(Exception e){
		return i+" retry fallback method call "+e;
	}


	@GetMapping("rateLimitTesting")
	@RateLimiter(name = "rateLimiterId", fallbackMethod = "rateLimiterFallbackMehthod")
	public ResponseEntity<String> rateLimitTesting(){
		System.out.println("rateLimitTesting call before");
		ResponseEntity<String> response = restTemplate.getForEntity("http://school", String.class);
		i++;
		System.out.println("rateLimitTesting==call after" );

		return response;
	}

	public ResponseEntity<String> rateLimiterFallbackMehthod(Exception e){
		return new ResponseEntity<>(i+" fallback method",HttpStatus.BAD_REQUEST);
	}


	@GetMapping("/feignDemo")
	public String getFromSchool(){
		return feignClientExample.getSchool();
	}


}
