//配置参数
var CONFIG = {
	userToken: 'uToken',
  account: 'account',

  updateMark: '?update=true',
  updateMarkKey: 'update',
  updateAndMark: '&update=true',

  offset: 0,
  length: 10,

  api_account_register: '/v1/normal/account/register',
  api_account_login: '/v1/normal/account/login',

  api_template_datasource: '/v1/authc/template/datasource',
  api_template_datasource_list: '/v1/authc/template/datasource/queries',
  api_template_datasource_info: '/v1/authc/template/datasource/info',

  api_template_config: '/v1/authc/template/config',
  api_template_config_list: '/v1/authc/template/config/queries',

  api_template_content: '/v1/authc/template/content',
  api_template_content_list: '/v1/authc/template/content/queries',

  api_template_build: '/v1/authc/template/build',

  example: '/v1/'
}


layui.use('element', function(){
  var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
  
    //监听导航点击
  // element.on('nav(demo)', function(elem){
  //   layer.msg(elem.text());
  // });

  $(function(){
   //bannerTabs
    var bannerTabs = '<li class="layui-nav-item" id="tab_home"><a href="/index.html">首页</a></li>'+
                    '<li class="layui-nav-item" id="tab_temp"><a href="/page/tool/module-temp/build.html">模板生成</a></li>'+
                    '<li class="layui-nav-item"><a href="">社区</a></li>'+
                    '<li class="layui-nav-item nico-right" lay-unselect="" id="login"></li>';
    $("#banner_tabs").html(bannerTabs); 

     //判断是否登录
      var hasBeenLogin = getCookie(CONFIG.userToken) != null;
      var acountName = getCookie(CONFIG.account);
      var loginTag = '<a href="/page/login/login.html">登录</a>';
      if(hasBeenLogin){
        loginTag = '<a href="javascript:;">' + acountName + '</a>'+
            '<dl class="layui-nav-child">'+
            '  <dd><a href="javascript:;">修改信息</a></dd>'+
            '  <dd><a href="javascript:;">安全管理</a></dd>'+
            '  <dd><a href="/page/login/login.html">退了</a></dd>'+
            '</dl>';
      }
    $("#login").html(loginTag); 


    element.render();


  })
  

});


/**
 *获取Cookie
 * 
 * @param  {[type]} c_name [description]
 * @return {[type]}        [description]
 */
function getCookie(c_name){
  if (document.cookie.length>0){
    c_start=document.cookie.indexOf(c_name + "=")
    if (c_start!=-1){ 
      c_start=c_start + c_name.length+1 
      c_end=document.cookie.indexOf(";",c_start)
      if (c_end==-1) c_end=document.cookie.length
        return unescape(document.cookie.substring(c_start,c_end))
      } 
  }
  return null;
}

/**
 * Token认证的Headers
 * 
 * @type {[type]}
 */
var headers = {
  token: getCookie(CONFIG.userToken)
};

/**
 * Ajax全局设置
 * 
 * @type {[type]}
 */
$.ajaxSetup({
    headers:headers
});

/**
 * 获取url中的参数
 * 
 * @param  {[type]} name [description]
 * @return {[type]}      [description]
 */
function getParam(name) {  
  var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
  var r = window.location.search.substr(1).match(reg);
  if (r != null) {
      return unescape(r[2]);
  }
  return null;
}

/**
 * 如果返回True，表示是个update功能
 * 
 * @return {Boolean} [description]
 */
function isUpdate(){
  return getParam(CONFIG.updateMarkKey) == 'true';
}

/**
 * Obj 转成 Url
 * 
 * @param  {[type]} obj [description]
 * @return {[type]}     [description]
 */
function urlFormat(c, obj){
  return c + $.param(obj);
}

/**
 * Jump new html
 * 
 * @param  {[type]} url [description]
 * @return {[type]}     [description]
 */
function jump(url){
  window.location.href = url;
}

/**
 * 刷新当前页面
 * @return {[type]} [description]
 */
function reload(){
  window.location.reload();
}

/**
 * 代表着锁的状态
 *  
 * @type {Boolean}
 */
var lock = false;

/**
 * 获取锁
 * 
 * @return {[type]} [description]
 */
function getLock(){
  if(lock == false){
    lock = true;
    return lock;
  }
  return false;
}

/**
 *释放锁
 * 
 * @return {[type]} [description]
 */
function releaseLock(){
  lock = false;
}

/**
 * 判断是否为空
 * @param  {[type]}  obj [description]
 * @return {Boolean}     [description]
 */
function isNull(obj){
   return ! (obj != null && obj != undefined);
}

/**
 * 生成默认请求条件
 * @param  {[type]} query [description]
 * @param  {[type]} page  [description]
 * @return {[type]}       [description]
 */
function getQuery(query, page){
  return {
      query:query == null ? {} : query,
      page:page == null ? {offset:0, length:-1} : page
  };
}






































