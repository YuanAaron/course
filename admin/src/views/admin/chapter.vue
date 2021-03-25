<template>
  <div>
    <p>
      <button v-on:click="add()" class="btn btn-white btn-default btn-round">
        <!--fa(fontawesome)：图标样式-->
        <i class="ace-icon fa fa-edit red2"></i>
        新增
      </button>
      &nbsp;
      <button v-on:click="list(1)" class="btn btn-white btn-default btn-round">
        <!--fa(fontawesome)：图标样式-->
        <i class="ace-icon fa fa-refresh red2"></i>
        刷新
      </button>
    </p>
    <!--
    在JS中需要通过document.querySelector("#demo")来获取dom节点，然后再获取这个节点的值。
    在Vue中，我们不用获取dom节点，元素绑定ref之后(取别名)，直接通过this.$refs.refName即可调用，这样可以减少获取dom节点的消耗。
    关于Vue中的ref和refs参考：https://blog.csdn.net/qq_38128179/article/details/88876060
    -->

    <!--默认显示多少个按钮可以修改-->
    <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="3"></pagination>
    <!--
    删除多余的row和col-xs-12:
    两者都是bootstrap栅格系统的内置样式，用于响应式页面的布局
    -->
    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>
        <th>ID</th>
        <th>课程ID</th>
        <th>名称</th>
        <th>操作</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="chapter in chapters">
        <td>{{chapter.id}}</td>
        <td>{{chapter.courseId}}</td>
        <td>{{ chapter.name }}</td>
        <td>
          <div class="hidden-sm hidden-xs btn-group">
            <button class="btn btn-xs btn-success">
              <i class="ace-icon fa fa-check bigger-120"></i>
            </button>

            <button class="btn btn-xs btn-info">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>

            <button class="btn btn-xs btn-danger">
              <i class="ace-icon fa fa-trash-o bigger-120"></i>
            </button>

            <button class="btn btn-xs btn-warning">
              <i class="ace-icon fa fa-flag bigger-120"></i>
            </button>
          </div>

          <div class="hidden-md hidden-lg">
            <div class="inline pos-rel">
              <button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto">
                <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
              </button>

              <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                <li>
                  <a href="#" class="tooltip-info" data-rel="tooltip" title="View">
                                  <span class="blue">
                                    <i class="ace-icon fa fa-search-plus bigger-120"></i>
                                  </span>
                  </a>
                </li>

                <li>
                  <a href="#" class="tooltip-success" data-rel="tooltip" title="Edit">
                                  <span class="green">
                                    <i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
                                  </span>
                  </a>
                </li>

                <li>
                  <a href="#" class="tooltip-error" data-rel="tooltip" title="Delete">
                                  <span class="red">
                                    <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                  </span>
                  </a>
                </li>
              </ul>
            </div>
          </div>
        </td>
      </tr>
      </tbody>
    </table>

    <!--
    模态框：相关的代码可以放在html代码的顶部或底部
    分为三部分：model-header：标题；model-body: 主体内容（放置表单的位置）；model-footer：底部按钮
    -->
    <div class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">表单</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="form-group">
                <!--<label for="id">使用场景：点击复选框checkbox时选中，使用label for后，点击label的文字也能选中复选框-->
                <label for="inputName" class="col-sm-2 control-label">名称</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" id="inputName" placeholder="名称">
                </div>
              </div>
              <div class="form-group">
                <label for="inputName1" class="col-sm-2 control-label">课程ID</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" id="inputName1" placeholder="课程ID">
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <!--
            模态框的弹出和关闭：可以使用js代码，也可以使用button属性（data-dismiss="css选择器"关闭，data-toggle="css选择器"打开）
            -->
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            <button type="button" class="btn btn-primary">保存</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

  </div>
</template>

<script>
import Pagination from "@/components/pagination"; //这里直接用@也可以

export default {
  components: {Pagination},
  name: 'chapter',
  data: function () {
    return {
      chapters: []
    }
  },
  mounted: function() {
    let _this = this;
    //子组件welcome调用父组件admin的方法，可以使用$parent
    //sidebar激活样式方法一
    // this.$parent.activeSideBar("business-chapter-sidebar");
    _this.$refs.pagination.size = 5;
    _this.list(1);

  },
  methods: {
    add() {
      //let _this=this;
      // $(".modal").modal({backdrop: "static"}); //禁止点空白的地方关闭模态框（某些场景会用到这个功能）
      $(".modal").modal("show"); //$(".modal") css选择器，因为模块框代码中有class="modal"的样式；modal()是内置的方法，用于弹出或关闭模态框

    },
    //接口请求参数传递，尽量使用post：使用get请求在url里拼接参数的话，会使url变得很长，有些浏览器或服务器会对url长度做限制，导致请求失败
    list(page) {
      let _this = this;
      _this.$ajax.post('http://127.0.0.1:9000/business/admin/chapter/list',{
        page: page,
        size: _this.$refs.pagination.size
      }).then(res => {
        // console.log("查询大章列表结果为：",res);
        _this.chapters = res.data.list;
        _this.$refs.pagination.render(page,res.data.total);
      })
    }
  }
}
</script>