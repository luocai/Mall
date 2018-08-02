 //控制层 
app.controller('itemCatController' ,function($scope,$controller   ,itemCatService){	
	
	$controller('baseController',{$scope:$scope});//继承
	
    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		itemCatService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	}    
	
	//分页
	$scope.findPage=function(page,rows){			
		itemCatService.findPage(page,rows).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	//查询实体 
	$scope.findOne=function(id){				
		itemCatService.findOne(id).success(
			function(response){
				$scope.entity= response;					
			}
		);				
	}
	
	//保存 
	$scope.save=function(entity_1, entity_2){		
		alert("d");
		var serviceObject;//服务层对象  		
//		if(entity_2 != null){
//			alert("2");
//			$scope.entity.parentId = entity_2.id;
//		}else if(entity_1 != null){
//			alert("1");
//			$scope.entity.parentId = entity_1.id;
//		}else{
//			alert("0")
//			$scope.entity.parentId = 0;
//		}
		if($scope.entity.id!=null){//如果有ID
			serviceObject=itemCatService.update( $scope.entity ); //修改  
		}else{
			serviceObject=itemCatService.add( $scope.entity  );//增加 
		}				
		serviceObject.success(
			function(response){
				if(response.success){
					//重新查询 
		        	//$scope.findByParentId();//重新加载
					alert(response.message);
//					if(entity_2 != null){
//						$scope.findByParentId(entity_2.id);
//					}else if(entity_1 != null){
//						$scope.findByParentId(entity_1.id);
//					}else{
//						$scope.findByParentId(0);
//					}
				}else{
					alert(response.message);
				}
			}		
		);				
	}
	
	 
	//批量删除 
	$scope.dele=function(){		
		alert("dd");
		//获取选中的复选框	
		if(confirm("你确定要删除吗？")){
			
			itemCatService.dele( $scope.selectIds ).success(
				function(response){
					if(response.success){
						$scope.reloadList();//刷新列表
						if($scope.entity_2 != null){
							alert("2");
							$scope.findByParentId($scope.entity_2.id);
						}else if($scope.entity_1 != null){
							alert("1");
							$scope.findByParentId($scope.entity_1.id);
						}else{
							alert("0");
							$scope.findByParentId(0);
						}
						$scope.selectIds=[];
					}						
				}		
			);			
		}
			
	}
	
	$scope.searchEntity={};//定义搜索对象 
	
	//搜索
	$scope.search=function(page,rows){			
		itemCatService.search(page,rows,$scope.searchEntity).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	//根据parentid查找
	$scope.findByParentId = function(parentId){
		
		itemCatService.findByParentId(parentId).success(
				function(response){
					$scope.list = response;
				}
		)
	}
	
	//当前级别
	$scope.grade = 1;
	
	$scope.setGrade = function(value){
		//alert(value);
		$scope.grade = value;
	}
	//判断面包屑
	$scope.judge = function(p_entity){
		
		if($scope.grade == 1){
			$scope.entity_1 = null;
			$scope.entity_2 = null;
		}
		if($scope.grade == 2){
			$scope.entity_1 = p_entity;
			$scope.entity_2 = null;
		}
		if($scope.grade == 3){
			$scope.entity_2 = p_entity;
		}
		$scope.findByParentId(p_entity.id);
	}
    
});	
