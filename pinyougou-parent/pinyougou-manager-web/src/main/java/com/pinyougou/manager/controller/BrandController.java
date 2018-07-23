package com.pinyougou.manager.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;

import entity.PageResult;
import entity.Result;

@RestController
@RequestMapping("/brand")
public class BrandController {

	@Reference
	private BrandService brandService;
	
	@RequestMapping("/findAll")
	public List<TbBrand> findAll(){
		List<TbBrand> list =  brandService.findAll();
		System.out.println("大小是：" + list.size());
		return list;
	}
	
	@RequestMapping("/findPage")
	public PageResult findPage(int pageNum, int pageSize){
		
		System.out.println(pageNum + " ... " + pageSize);
		return brandService.findPage(pageNum, pageSize);
		
	}
	
	@RequestMapping("/add")
	public Result add(@RequestBody TbBrand tbBrand){
		
System.out.println(tbBrand.getName());
		try{
			brandService.add(tbBrand);
			return new Result(true, "增加成功");
		}catch(Exception e){
			
			return new Result(false, "增加失败");
		}
		
	}
	
	@RequestMapping("/edit")
	public Result edit(@RequestBody TbBrand tbBrand){
		
		try{
			brandService.edit(tbBrand);
			
			return new Result(true, "修改成功");
		}catch(Exception e){
			return new Result(false, "修改失败");
		}
		
	}
	
	@RequestMapping("/findById")
	public TbBrand findById(Long id){
System.out.println("id是：" + id);		
		return brandService.findById(id);
	}
	
}
