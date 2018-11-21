$(function(){
  layui.use(['form', 'layedit', 'laydate'], function(){
    var form = layui.form
    ,layer = layui.layer
    ,layedit = layui.layedit
    ,laydate = layui.laydate;
    
    form.render();

    form.verify({
      pass: [/(.+){6,12}$/, '密码必须6到12位']
    });

    //监听提交
    form.on('submit(login)', function(data){
      
      $.ajax({
        url: CONFIG.api_account_login,
        type:'post',
        dataType: 'JSON',
        contentType: 'application/json',
        data:JSON.stringify(data.field),
        success:function(data, status){
          if(data.code == 200){
              window.location.href = '/index.html';
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

      return false;
    });

    
  });

})