package com.pinyougou.sellergoods.service;

import java.util.List;

import com.pinyougou.pojo.TbBrand;

import entity.PageResult;
import entity.Result;


/**
 * @ClassName: BrandService
 * @Description: TODO(品牌接口)
 * @author:  cai
 * @date: 2018年7月17日 上午11:19:21
*/
public interface BrandService {
	
	public List<TbBrand> findAll();
	
	//分页
	public PageResult findPage(int pageNum, int pageSize);
	
	//增加
	public void add(TbBrand tbBrand);
	
	//修改
	public void edit(TbBrand tbBrand);
	
	//根据id查找
	public TbBrand findById(Long id);
}
