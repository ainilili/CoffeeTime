var editIndex;

var INIT = {

	/**
	 * 操作接口
	 * @type {[type]}
	 */
	api: CONFIG.api_template_content,

	/**
	 * 初始化回调函数
	 * @return {[type]} [description]
	 */
	init: function(){
		$("#tempConfigId").val(getParam('id'));
		// layui.use('layedit', function(){
		//   var layedit = layui.layedit
		//   ,$ = layui.jquery;
		  
		//   //构建一个默认的编辑器
		//   editIndex = layedit.build('content',{
		//   	height: 600
		//   });
		  
		// });
	},

	/**
	 * 在Get方法执行成功后回调
	 * @return {[type]} [description]
	 */
	initAfterGet: function(){
		// layui.use('layedit', function(){
		//   var layedit = layui.layedit
		//   ,$ = layui.jquery;
		  
		//   //构建一个默认的编辑器
		//   editIndex = layedit.build('content',{
		//   	height: 600
		//   });
		  
		// });
	},

	/**
	 * 自定义验证
	 * @type {Object}
	 */
	verify:{
          content: function(value) { 
          	// layui.use('layedit', function(){
          	// 	var layedit = layui.layedit;
           //  	$("#content").val(layedit.getContent(editIndex));
           //  });
            var access = $("#content").val() != null && $("#content").val() != '';
              if(! access){
                return "内容不能为空";
              }
          }
     }

	

};
