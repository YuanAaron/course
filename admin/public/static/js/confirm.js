//封装确认弹出框

//以删除为例，当点击确认删除时，会触发删除，但这个删除的动作是由外部来定义的，这种情况下就用到了回调函数（简单理解，
// 将一个函数以参数的形式传递到另一个函数里去执行）。

//在自定义组件中经常用到回调函数

Confirm = {
  show: function (msg,callback) {
    //来自：https://sweetalert2.github.io/#examples
    Swal.fire({
      title: '确认？',
      text: msg,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: '确认!'
    }).then((result) => {
      if (result.isConfirmed) {
        if (callback) {
          callback();
        }
      }
    })
  }
};