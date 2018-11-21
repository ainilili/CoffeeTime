$(function(){
  layui.use(['form', 'layedit', 'laydate'], function(){
    var form = layui.form
    ,layer = layui.layer
    ,layedit = layui.layedit
    ,laydate = layui.laydate;
    
    form.render();

    form.verify({
      pass: [/(.+){6,12}$/, '密码必须6到12位']
      ,repass: function(value){
        if(value != $("#accountWord").val()){
          $("#reAccountWord").val('');
          return "两次输入的密码不一致";
        }
      }
    });

    //监听提交
    form.on('submit(register)', function(data){
      
      $.ajax({
        url: CONFIG.api_account_register,
        type:'post',
        dataType: 'JSON',
        contentType: 'application/json',
        data:JSON.stringify(data.field),
        success:function(data, status){
          if(data.code == 200){
            layer.alert("注册成功，跳转登录~", {icon: 6}, function(){
              //window.location.reload();
              window.location.href = '/page/login/login.html';
            });
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