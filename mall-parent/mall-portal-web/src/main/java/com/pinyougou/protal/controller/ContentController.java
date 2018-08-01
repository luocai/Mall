package com.pinyougou.protal.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.content.service.ContentService;
import com.pinyougou.pojo.TbContent;

@RestController
@RequestMapping("/content")
public class ContentController {

	@Reference
	private ContentService contentService;
	
	/**
	 * 根据广告分类ID查询广告列表
	 * @param categoryId
	 * @return
	 */
	@RequestMapping("/findByCategoryId")
	public List<TbContent> findByCategoryId(Long categoryId) {
		System.out.print("缉拿了");
		List<TbContent> list =  contentService.findByCategoryId(categoryId);
		
//		for(int i = 0; i < list.size() ; i++){
//			
//			System.out.println(list.get(i));
//		}
		
		return list;
	}		
}

