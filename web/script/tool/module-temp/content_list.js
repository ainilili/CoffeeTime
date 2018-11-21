var INIT = {

      /**
       * 操作接口
       * @type {[type]}
       */
      api: CONFIG.api_template_content,
      
      /**
       * 查询接口
       * @type {[type]}
       */
      api_list: CONFIG.api_template_content_list,
      
      /**
       * 更新页面地址
       * @type {String}
       */
      page_update: '/page/tool/module-temp/content_input.html',
      
      /**
       * 查询条件
       * @type {Object}
       */
      query: {
        tempConfigId: getParam('id')
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
                        '<th>' + elem.name + '</th>'+
                        //'<th class="nico-ellipsis" style="max-width:100px">' + elem.content + '</th>'+
                        '<th>' + elem.fileName + '</th>'+
                        '<th>' + elem.fileFormat + '</th>'+
                        '<th>' + 
                          '<button class="layui-btn layui-btn-warm layui-btn-sm" onclick="jump(\'' + INIT.page_update + CONFIG.updateMark + urlFormat('&', {id:elem.id}) + '\')">修改</button>' +
                          '<button class="layui-btn layui-btn-danger layui-btn-sm" onclick="remove(\'' + elem.id + '\')">删除</button>' +
                        '</th>'+
                      '</tr> ';
        return content;
      }
};
