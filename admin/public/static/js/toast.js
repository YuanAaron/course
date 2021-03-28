//制作消息提示框: 将通用的代码做成组件
//组件选择：如果组件包含html代码，可以做成Vue组件；如果组件只有js代码，可以做成原生的js


//上面的代码来自：https://sweetalert2.github.io/#examples，然后搜索toast
//下面的代码自己写
//这样的toast是js全局变量，可以在其他js文件中使用，也可以在Vue组件中直接使用。
Toast = {
  success: function(msg) {
    Swal.fire({
      position: 'top-end',
      icon: 'success',
      title: msg,
      showConfirmButton: false,
      timer: 3000
    })
  },
  error: function(msg) {
    Swal.fire({
      position: 'top-end',
      icon: 'error',
      title: msg,
      showConfirmButton: false,
      timer: 3000
    })
  },
  warning: function(msg) {
    Swal.fire({
      position: 'top-end',
      icon: 'warning',
      title: msg,
      showConfirmButton: false,
      timer: 3000
    })
  }
};

