package com.pinyougou.sellergoods.service.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbSpecificationMapper;
import com.pinyougou.mapper.TbSpecificationOptionMapper;
import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojo.TbSpecificationExample;
import com.pinyougou.pojo.TbSpecificationExample.Criteria;
import com.pinyougou.pojo.TbSpecificationOption;
import com.pinyougou.pojo.TbSpecificationOptionExample;
import com.pinyougou.pojogroup.Specification;
import com.pinyougou.sellergoods.service.SpecificationService;

import entity.PageResult;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {

	@Autowired
	private TbSpecificationMapper specificationMapper;
	
	@Autowired
	private TbSpecificationOptionMapper tbSpecificationOptionMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<TbSpecification> findAll() {
		return specificationMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbSpecification> page=   (Page<TbSpecification>) specificationMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(Specification specification) {
		
		specificationMapper.insert(specification.getSpecification());
		
		for(TbSpecificationOption tbSpecificationOption: specification.getSpecificationOptionList()){
			tbSpecificationOption.setSpecId(specification.getSpecification().getId());
System.out.println(tbSpecificationOption.getSpecId() + "o");
			tbSpecificationOptionMapper.insert(tbSpecificationOption);
		}
		
	}

	
	/**
	 * 修改
	 */
	@Override
  public void update(Specification specification){
//		
//		specificationMapper.updateByPrimaryKey(specification.getSpecification());
//		
//		for(TbSpecificationOption tbSpecificationOption:specification.getSpecificationOptionList()){
//			
//			tbSpecificationOptionMapper.updateByPrimaryKey(tbSpecificationOption);
//		}
//	}	
	
	
	//删除原有的规格选项		
			TbSpecificationOptionExample example=new TbSpecificationOptionExample();
			com.pinyougou.pojo.TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
			criteria.andSpecIdEqualTo(specification.getSpecification().getId());//指定规格ID为条件
			tbSpecificationOptionMapper.deleteByExample(example);//删除		
			//循环插入规格选项
			for(TbSpecificationOption specificationOption:specification.getSpecificationOptionList()){			
				specificationOption.setSpecId(specification.getSpecification().getId());
				tbSpecificationOptionMapper.insert(specificationOption);		
			}	
		}	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public Specification findOne(Long id){
		TbSpecification tbSpecification = specificationMapper.selectByPrimaryKey(id);
		
		TbSpecificationOptionExample tbSpecificationOptionExample = new TbSpecificationOptionExample();
		com.pinyougou.pojo.TbSpecificationOptionExample.Criteria criteria = tbSpecificationOptionExample.createCriteria();
		
		criteria.andSpecIdEqualTo(id);
		List<TbSpecificationOption> list = tbSpecificationOptionMapper.selectByExample(tbSpecificationOptionExample);
		
		Specification specification = new Specification();
		specification.setSpecification(tbSpecification);
		specification.setSpecificationOptionList(list);
		return specification;
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {

		for(Long id:ids){
System.out.println(ids);
			specificationMapper.deleteByPrimaryKey(id);			
			//删除原有的规格选项		
			TbSpecificationOptionExample example=new TbSpecificationOptionExample();
			com.pinyougou.pojo.TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
			criteria.andSpecIdEqualTo(id);//指定规格ID为条件
			tbSpecificationOptionMapper.deleteByExample(example);//删除
		}		
	}

	
	
	@Override
	public PageResult findPage(TbSpecification specification, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbSpecificationExample example=new TbSpecificationExample();
		Criteria criteria = example.createCriteria();
		
		if(specification!=null){			
						if(specification.getSpecName()!=null && specification.getSpecName().length()>0){
				criteria.andSpecNameLike("%"+specification.getSpecName()+"%");
			}
	
		}
		
		Page<TbSpecification> page= (Page<TbSpecification>)specificationMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}

	@Override
	public List<Map> selectOptionList() {
		
		return specificationMapper.selectOptionList();
	}

		
	
}
