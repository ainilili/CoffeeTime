var editIndex;

var INIT = {

  /**
   * 操作接口
   * @type {[type]}
   */
  api: CONFIG.api_template_datasource,

  /**
   * 初始化回调函数
   * @return {[type]} [description]
   */
  init: function(){
    
  },

  /**
   * 在Get方法执行成功后回调
   * @return {[type]} [description]
   */
  initAfterGet: function(){
    
  },

  /**
   * 自定义验证
   * @type {Object}
   */
  verify:{
          content: function(value) { 
            // layui.use('layedit', function(){
            //  var layedit = layui.layedit;
           //   $("#content").val(layedit.getContent(editIndex));
           //  });
            var access = $("#content").val() != null && $("#content").val() != '';
              if(! access){
                return "内容不能为空";
              }
          }
     }

  

};
