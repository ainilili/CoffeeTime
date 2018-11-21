$(function(){

	//Append temp list
	var tempList = '<a href="datasource_list.html"><li>数据源管理</li></a>'+
					'<a href="config_list.html"><li>模板设置</li></a>'+
					'<a href="build.html"><li>模板生成</li></a>';
	$("#temp-options").html(tempList);

	setTimeout(function(){
		$("#tab_temp").addClass('layui-this');	
	},100);
})