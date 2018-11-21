layui.use(['form', 'layedit', 'laydate'], function(){
    var form = layui.form
    ,layer = layui.layer
    ,layedit = layui.layedit
    ,laydate = layui.laydate;

    if(isUpdate()){
      init(getParam('id'));
    }

    form.render();

    if(! isNull(INIT.verify)){
      form.verify(INIT.verify);
    }

    //监听提交
    form.on('submit(submit)', function(data){
     
      if(isUpdate()){
        update(data, getParam('id'));
      }else{
        add(data);
      }
      return false;
    });
    

    function add(data){

      if(! getLock()) return;

      $.ajax({
        url: INIT.api,
        type:'PUT',
        dataType: 'JSON',
        contentType: 'application/json',
        data:JSON.stringify(data.field),
        success:function(data, status){
          if(data.code == 200){
              layer.alert("添加成功", {icon: 6}, function(){
                reload();
              });
          }else{
             layer.alert(data.msg, {icon: 2});
          }
        },
        complete:function(XMLHttpRequest,textStatus){
          releaseLock();
        },
        error:function(XMLHttpRequest,textStatus,errorThrown){
          layer.alert(errorThrown, {icon: 2});
        }
      });
    }

    function update(data, id){
      
      if(! getLock()) return;
      var normalParam = {
        id:id
      };

      $.ajax({
        url: INIT.api + urlFormat('?', normalParam),
        type:'POST',
        dataType: 'JSON',
        contentType: 'application/json',
        data:JSON.stringify(data.field),
        success:function(data, status){
          if(data.code == 200){
              layer.alert("更新成功", {icon: 6}, function(){
                reload();
              });
          }else{
             layer.alert(data.msg, {icon: 2});
          }
        },
        complete:function(XMLHttpRequest,textStatus){
          releaseLock();
        },
        error:function(XMLHttpRequest,textStatus,errorThrown){
          layer.alert(errorThrown, {icon: 2});
        }
      });
    }

    function init(id){
      $.ajax({
        url: INIT.api + '/' + id,
        type:'GET',
        dataType: 'JSON',
        contentType: 'application/json',
        success:function(data, status){
          if(data.code == 200){
             //这里回填数据
             form.val('data-form', data.data);
             form.render();
             if(! isNull(INIT.initAfterGet)){
                INIT.initAfterGet(); 
             }
          }else{
             layer.alert(data.msg, {icon: 2});
          }
        },
        complete:function(XMLHttpRequest,textStatus){
        },
        error:function(XMLHttpRequest,textStatus,errorThrown){
          // layer.alert(errorThrown, {icon: 2});
           console.log(errorThrown);
        }
      });
    }
    
  });

$(function(){
    if(! isNull(INIT.init)){
        INIT.init(); 
     }
})