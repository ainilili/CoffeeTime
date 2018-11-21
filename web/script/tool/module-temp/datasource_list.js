var INIT = {

      /**
       * 操作接口
       * @type {[type]}
       */
      api: CONFIG.api_template_datasource,
      
      /**
       * 查询接口
       * @type {[type]}
       */
      api_list: CONFIG.api_template_datasource_list,
      
      /**
       * 更新页面地址
       * @type {String}
       */
      page_update: '/page/tool/module-temp/datasource_input.html',
      
      /**
       * 查询条件
       * @type {Object}
       */
      query: {
        
      },
      
      /**
       * 列表遍历回调方法
       * @param  {[type]} index [description]
       * @param  {[type]} elem  [description]
       * @return {[type]}       [description]
       */
      handle: function(index, elem){
        var content = '<tr>'+
                        '<th>' + elem.id + '</th>'+
                        '<th>' + elem.username + '</th>'+
                        //'<th class="nico-ellipsis" style="max-width:100px">' + elem.content + '</th>'+
                        '<th>' + elem.url + '</th>'+
                        '<th>' + 
                          '<button class="layui-btn layui-btn-warm layui-btn-sm" onclick="jump(\'' + INIT.page_update + CONFIG.updateMark + urlFormat('&', {id:elem.id}) + '\')">修改</button>' +
                          '<button class="layui-btn layui-btn-danger layui-btn-sm" onclick="remove(\'' + elem.id + '\')">删除</button>' +
                        '</th>'+
                      '</tr> ';
        return content;
      }
};
