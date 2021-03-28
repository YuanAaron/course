/*
界面等待框的作用：
1、让用户知道，后端正在处理，耐心等待；
2、防止用户恶意重复点击
*/

//参考自：http://malsup.com/jquery/block/
loading = {
  show: function() {
    $.blockUI({
      message: '<img src="/static/image/loading.gif"/>',
      css: {
        padding: "10px",
        left: "50%",
        width: "80px",
        marginLeft: "-40px"
      }
    });
  },
  hide: function () {
    //本地查询速度太快，loading显示一瞬间就没有了，故意做个延迟
    setTimeout(function () {
      $.unblockUI();
    },500);
  }
};