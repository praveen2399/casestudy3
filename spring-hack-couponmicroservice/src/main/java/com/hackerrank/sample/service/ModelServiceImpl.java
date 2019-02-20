package com.hackerrank.sample.service;

import com.hackerrank.sample.excpetion.BadResourceRequestException;
import com.hackerrank.sample.excpetion.NoSuchResourceFoundException;
import com.hackerrank.sample.model.Model;
import com.hackerrank.sample.repository.ModelRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("modelService")
public class ModelServiceImpl implements ModelService {
   
   public String getCoupon(Double price) throws InterruptedException
	{
		long starttime=System.currentTimeMillis();
		if(price != null)
		{
			
				Thread.sleep(5000);
			
			System.err.println("Time taken "+(System.currentTimeMillis()-starttime));
			if(price <100)
			{
				return "5% off on next purchase";
			}
			else
			{
				return "15% off on next purchase";
			}
		}
		else
		{
			return "No Coupon";
		}
	}
}
