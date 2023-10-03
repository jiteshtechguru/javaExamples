package com.javatechguru.eurekaclient1;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

//@FeignClient( name= "feignUrl")
@FeignClient( name= "SCHOOL")
public interface FeignClientExample {

    @GetMapping("/")
    String getSchool();
}
