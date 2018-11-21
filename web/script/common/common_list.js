layui.use(['form', 'layedit', 'laydate', 'laypage'], function(){
    var form = layui.form
    ,layer = layui.layer
    ,layedit = layui.layedit
    ,laydate = layui.laydate
    ,laypage = layui.laypage;
    
    form.render();

    get(CONFIG.offset, CONFIG.length);

    var loaderPage = true;

    function page(count){
      if(count < 1){
        $("#data-list").html("<br/><span style='height:30px;display:block;'>暂无数据</span>");
        return;
      }

      laypage.render({
          elem: 'data-page'
          ,count: count 
          ,jump: function(obj, first){
            if(! first){
              get((obj.curr -1) * CONFIG.length, CONFIG.length); 
            }
          }
      });
    }

    function get(offset, length){

      var requestData = {

          query: INIT.query,

          page:{
            offset: offset,
            length: length
          }

      }

      //获取列表
      $.ajax({
        url: INIT.api_list,
        type:'POST',
        dataType: 'JSON',
        contentType: 'application/json',
        data:JSON.stringify(requestData),
        success:function(data, status){

          if(data.code == 200){
            var datas = data.data;
            var value = '';
            $.each(data.data, function(index, elem){
              value += INIT.handle(index, elem);
            });
            $("#data-list").html(value);
            
            if(loaderPage){
              loaderPage = false;
              page(data.count);
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


function remove(id){
        
        layui.use([], function(){
          var layer = layui.layer;

          if(! getLock()) return;

          var index = layer.confirm();
          layer.confirm('确定删除' + id + '记录吗？', {
            btn: ['删除','取消'] 
          }, function(){

            $.ajax({
              url: INIT.api + '/' + id,
              type:'DELETE',
              dataType: 'JSON',
              contentType: 'application/json',
              success:function(data, status){
                if(data.code == 200){
                   layer.alert("删除成功", {icon: 6}, function(){
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
            
          }, function(){
            releaseLock();
            layer.close(index);
          });

        });
    } 

$(function(){

  if(INIT.init != null && INIT.init != undefined){
    INIT.init(); 
  }

})