app.controller("brandController",function($scope,$controller,brandService){
	
		$controller('baseController',{$scope:$scope});//继承	
	
			$scope.paginationConf = {
			 currentPage: 1,  //当前页
			 totalItems: 10,  //总条数
			 itemsPerPage: 10,
			 perPageOptions: [10, 20, 30, 40, 50], // 页面选项
			 onChange: function(){
			        $scope.reloadList();//重新加载
			 }
			}; 
			
			/*//重新加载列表 数据
			$scope.reloadList=function(){
				 //切换页码  
				$scope.search( $scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
			}
			*/
			/*//分页
			$scope.findPage=function(page,rows){	
				brandService.findPage().success(
					function(response){
						$scope.list=response.rows;	
						$scope.paginationConf.totalItems=response.total;//更新总记录数
					}			
				);
			} */
			
			//增加
			
			$scope.save = function(){
			
				var Object = null;
				if($scope.entity.id != null){
					Object = brandService.edit($scope.entity);
				}else{
					Object = brandService.add($scope.entity);
				}
/* alert(methodName); */
				Object.success(
					function(response){
						if(response.success){
							$scope.reloadList();
						}else{
							console.log(response);
							alert(response.message);
						}
					}
				)
			}
			
			
			$scope.findOne = function(id){
				brandService.findOne(id).success(
					function(response){
					console.log(response);
						$scope.entity = response;
					}
				)
				
			}
			
			/*//删除
			$scope.selectIds = [];
			
			$scope.updateSelection = function($event,id){
				if($event.target.checked){
					$scope.selectIds.push(id);
console.log($scope.selectIds);
				}else{
					var idx = $scope.selectIds.indexOf(id);
			        $scope.selectIds.splice(idx, 1);//删除 
				
				}
			}*/
			
			//删除
			$scope.delete = function(){
				
				if(confirm("你确定要删除吗?")){
					brandService.delete($scope.selectIds).success(
						function(response){
							if(response.success){
								alert(response.message);
								$scope.reloadList();
							}else{
								alert(response.message);
							}
						}
					)
				}	
			}
			
			$scope.searchEntity = {};
			
			$scope.search = function(page,rows){
				 brandService.search(page,rows,$scope.searchEntity).success(
					function(response){
						$scope.paginationConf.totalItems=response.total;//总记录数 
						$scope.list=response.rows;//给列表变量赋值 
					}
				) 
				
			}
		
		})