var INIT = {

      /**
       * 操作接口
       * @type {[type]}
       */
      api: CONFIG.api_template_config,
      
      /**
       * 查询接口
       * @type {[type]}
       */
      api_list: CONFIG.api_template_config_list,
      
      /**
       * 更新页面
       * @type {String}
       */
      page_update: '/page/tool/module-temp/config_input.html',

      /**
       * 查询内容接口
       * @type {String}
       */
      page_content: '/page/tool/module-temp/content_list.html',

      /**
       * 查询条件
       * @type {Object}
       */
      query: {},

      /**
       * 列表遍历回调方法
       * @param  {[type]} index [description]
       * @param  {[type]} elem  [description]
       * @return {[type]}       [description]
       */
      handle: function(index, elem){
        var content = '<tr>'+
                          '<th>' + elem.id + '</th>'+
                          '<th>' + elem.name + '</th>'+
                          '<th>' + elem.prefix + '</th>'+
                          '<th>' + elem.suffix + '</th>'+
                          '<th>' + elem.propertiesJson + '</th>'+
                          '<th>' + elem.accountId + '</th>'+
                          '<th>' + 
                            '<button class="layui-btn layui-btn-warm layui-btn-sm" onclick="jump(\'' + INIT.page_update + CONFIG.updateMark + urlFormat('&', {id:elem.id}) + '\')">修改</button>' +
                            '<button class="layui-btn layui-btn-danger layui-btn-sm" onclick="remove(\'' + elem.id + '\')">删除</button>' +
                            '<button class="layui-btn layui-btn-sm" onclick="jump(\'' + INIT.page_content + urlFormat('?', {id:elem.id}) + '\')">模板es</button>' +
                          '</th>'+
                        '</tr> ';
        return content;
      }
};