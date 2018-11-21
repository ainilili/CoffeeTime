layui.use(['form', 'layedit', 'laydate', 'laypage'], function(){
    var form = layui.form
    ,layer = layui.layer
    ,layedit = layui.layedit
    ,laydate = layui.laydate
    ,laypage = layui.laypage;
    
    form.render();
    
    configMap = new Map();
    tableMap = new Map();

    //获取数据源列表
    $.ajax({
        url: CONFIG.api_template_datasource_list,
        type:'post',
        dataType: 'JSON',
        contentType: 'application/json',
        data:JSON.stringify(getQuery()),
        success:function(data, status){
          if(data.code == 200){
            var datas = data.data;
            var value = '<option value="">请选择数据源</option>';
            $.each(data.data, function(index, elem){
               value += '<option value="' + elem.id + '">' + elem.url + '</option>';
            });
            $("#data-source").html(value);
            form.render('select');
          }else{
             layer.alert(data.msg, {icon: 2});
          }
        },
        complete:function(XMLHttpRequest,textStatus){
        },
        error:function(XMLHttpRequest,textStatus,errorThrown){
          layer.alert(errorThrown, {icon: 2});
        }
      });

    //获取模板设置列表
    $.ajax({
        url: CONFIG.api_template_config_list,
        type:'post',
        dataType: 'JSON',
        contentType: 'application/json',
        data:JSON.stringify(getQuery()),
        success:function(data, status){
          if(data.code == 200){
            var datas = data.data;
            var value = '<option value="">请选择模板</option>';
            $.each(data.data, function(index, elem){
               value += '<option value="' + elem.id + '">' + elem.name + '</option>';
               configMap.set(elem.id, elem);
            });
            $("#temp-config").html(value);
            form.render('select');
          }else{
             layer.alert(data.msg, {icon: 2});
          }
        },
        complete:function(XMLHttpRequest,textStatus){
        },
        error:function(XMLHttpRequest,textStatus,errorThrown){
          layer.alert(errorThrown, {icon: 2});
        }
      });

    //数据源列表添加联动监听
    form.on('select(data-source)', function(data){
      $.get(CONFIG.api_template_datasource_info + '/' + data.value, function(result){
        var tableInfos = '';
        $.each(result.data, function(index, elem){
          tableInfos += '<li><input type="checkbox" name="table|' + elem.tableName + '" lay-skin="primary" title="' + elem.tableName + '" ></li>';
          tableMap.set(elem.tableName, elem);
        });
        $("#table-infos").html(tableInfos);
        form.render('checkbox');
      })
    });

    //模板设置添加联动监听
    form.on('select(temp-config)', function(data){
      $("#propertiesJson").text(configMap.get(data.value).propertiesJson);
    });

    //监听提交
    form.on('submit(submit)', function(data){
      
      // Table多选框检测      
      var checked=$("#table-infos .layui-form-checked");  
      if(checked.length==0){  
        layer.msg('请至少选择一个table');
        return false;  
      }


      var tables = new Array()
      for(var key in data.field){
        if(key.indexOf('table|') != -1 && data.field[key] == 'on'){
          tables.push(tableMap.get(key.split('|')[1]));
        }
      }

      data.field.tables = tables;

      console.log("ajaxDownloadSynchronized");
      var url = CONFIG.api_template_build;
      var fileName = "testAjaxDownload.txt";
      var form = $("<form></form>").attr("action", url).attr("method", "post");
      form.append($("<input></input>").attr("type", "hidden").attr("name", "json").attr("value", JSON.stringify(data.field)));
      form.appendTo('body').submit().remove();

      return false;
    });

});