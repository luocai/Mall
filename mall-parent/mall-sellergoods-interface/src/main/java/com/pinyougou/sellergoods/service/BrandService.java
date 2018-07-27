package com.pinyougou.sellergoods.service;

import java.util.List;
import java.util.Map;

import com.pinyougou.pojo.TbBrand;

import entity.PageResult;


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
	
	//删除多个
	public void delete(Long[] ids);
	
	//条件查询
	public PageResult findPage(TbBrand tbBrand, int pageNum, int pageSize);
	
	/**
	 * 品牌下拉框数据
	 */
	List<Map> selectOptionList();

}
