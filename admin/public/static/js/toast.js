//制作消息提示框: 将通用的代码做成组件
//组件选择：如果组件包含html代码，可以做成Vue组件；如果组件只有js代码，可以做成原生的js

const Toast = Swal.mixin({
  toast: true,
  position: 'top-end',
  showConfirmButton: false,
  timer: 3000,
  timerProgressBar: true,
  didOpen: (toast) => {
    toast.addEventListener('mouseenter', Swal.stopTimer)
    toast.addEventListener('mouseleave', Swal.resumeTimer)
  }
})

// Toast.fire({
//   icon: 'success',
//   title: 'Signed in successfully'
// })

//上面的代码来自：https://sweetalert2.github.io/#examples，然后搜索toast
//下面的代码自己写
//这样的toast是js全局变量，可以在其他js文件中使用，也可以在Vue组件中直接使用。
toast = {
  success: function(msg) {
    Toast.fire({
      icon: 'success',
      title: msg
    })
  },
  error: function(msg) {
    Toast.fire({
      icon: 'error',
      title: msg
    })
  },
  warning: function(msg) {
    Toast.fire({
      icon: 'warning',
      title: msg
    })
  }
};

