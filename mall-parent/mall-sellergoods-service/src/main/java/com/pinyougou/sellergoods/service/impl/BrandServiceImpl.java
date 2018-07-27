package com.pinyougou.sellergoods.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.TbBrandExample;
import com.pinyougou.pojo.TbBrandExample.Criteria;
import com.pinyougou.sellergoods.service.BrandService;

import entity.PageResult;

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

	@Override
	public void delete(Long[] ids) {
		
		for(int i = 0; i < ids.length; i++){
			tbBrandMapper.deleteByPrimaryKey(ids[i]);
		}
		
	}

	@Override
	public PageResult findPage(TbBrand tbBrand, int pageNum, int pageSize) {
		
		PageHelper.startPage(pageNum, pageSize);
		
		TbBrandExample tbBrandExample = new TbBrandExample();
		Criteria criteria = tbBrandExample.createCriteria();
		
		if(tbBrand!=null){
			if(tbBrand.getName()!=null && tbBrand.getName().length()>0){
				criteria.andNameLike("%"+tbBrand.getName()+"%");
			}
			if(tbBrand.getFirstChar()!=null && tbBrand.getFirstChar().length()>0){
				criteria.andFirstCharEqualTo(tbBrand.getFirstChar());
			}		
		}		

		
		Page<TbBrand> page = (Page<TbBrand>) tbBrandMapper.selectByExample(tbBrandExample);
		
		
		return new PageResult(page.getTotal(), page.getResult());
	}
	
	public List<Map> selectOptionList() {
		return tbBrandMapper.selectOptionList();
	}


}
