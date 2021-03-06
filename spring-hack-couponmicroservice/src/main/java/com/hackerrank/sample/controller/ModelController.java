package com.hackerrank.sample.controller;

import com.hackerrank.sample.model.Model;
import com.hackerrank.sample.service.ModelService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(value="/coupon")
public class ModelController {
    @Autowired
    private ModelService modelService;

    @RequestMapping(value="/getCoupon/{price}")
	public ResponseEntity<String> getCoupon(@PathVariable Double price) throws InterruptedException
	{
		return new ResponseEntity<String>(modelService.getCoupon(price),HttpStatus.OK);
	}

}
