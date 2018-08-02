app.service("brandService",function($http){
			
			this.findAll = function(){
				return $http.get("../brand/findAll.do");
			}
			
			this.findPage = function(page,rows){
				return $http.get('../brand/findPage.do?pageNum='+page+'&pageSize='+rows);
			}
			
			this.findOne = function(id){
				return $http.get("../brand/findById.do?id="+ id);
			}
			
			this.add = function(entity){
				return $http.post("../brand/add.do", entity);
			}
			
			this.edit = function(entity){
				return $http.post("../brand/edit.do", entity);
			}
			
			this.delete = function(ids){
				return $http.get("../brand/delete.do?ids=" + ids );
			}
			
			this.search = function(page,rows,searchEntity){
				return $http.post("../brand/search.do?pageNum=" + page +"&pageSize=" +rows,searchEntity);
			}
			
			//下拉列表数据
			this.selectOptionList=function(){
				return $http.get('../brand/selectOptionList.do');
			}	

		})
		
		
		