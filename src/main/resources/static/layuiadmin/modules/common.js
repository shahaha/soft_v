/**

 @Name：layuiAdmin 公共业务
 @Author：贤心
 @Site：http://www.layui.com/admin/
 @License：LPPL
    
 */
 
layui.define(function(exports){
  var $ = layui.$
  ,layer = layui.layer
  ,laytpl = layui.laytpl
  ,setter = layui.setter
  ,view = layui.view
  ,admin = layui.admin
  
  //公共业务的逻辑处理可以写在此处，切换任何页面都会执行
  //……
  
  
  
  //退出
  admin.events.logout = function(){
    //执行退出接口
    admin.req({
      url: '/user/logout'
      ,type: 'post'
      ,data: {}
      ,done: function(res){ //这里要说明一下：done 是只有 response 的 code 正常才会执行。而 succese 则是只要 http 为 200 就会执行
        // layui.data('goodsTypes', null);
        // layui.data('info', null);
        layui.data('layuiAdmin', null);
        // layui.data('mapNames', null);
        // layui.data('maps', null);
        // layui.data('personTypes', null);
        // layui.data('tagTypes', null);
        // layui.data('userInfo', null);
        //清空本地记录的 token，并跳转到登入页
        admin.exit(function(){
          location.href = '/toLogin';
        });
      }
      ,fail: function(res){
        layer.msg("退出登录失败，前刷新页面或联系管理员！");
      }
    });
  };

  
  //对外暴露的接口
  exports('common', {});
});