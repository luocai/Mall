package com.pinyougou.sellergoods.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;

import entity.PageResult;
import entity.Result;

@Service
public class BrandServiceImpl implements BrandService{

	@Autowired
	private TbBrandMapper tbBrandMapper;
	
	@Override
	public List<TbBrand> findAll() {
		
		return tbBrandMapper.selectByExample(null);
	}

	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		
		PageHelper.startPage(pageNum, pageSize);
		
		Page<TbBrand> page = (Page<TbBrand>) tbBrandMapper.selectByExample(null);
		
		return new PageResult(page.getTotal(),page.getResult());
	}

	@Override
	public void add(TbBrand tbBrand) {
		
		tbBrandMapper.insert(tbBrand);
		
	}

	@Override
	public void edit(TbBrand tbBrand) {
		
		tbBrandMapper.updateByPrimaryKeySelective(tbBrand);
		
	}

	@Override
	public TbBrand findById(Long id) {
		
		return tbBrandMapper.selectByPrimaryKey(id);
	}
	
	

}
