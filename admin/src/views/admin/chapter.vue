<template>
  <div>
    <h4 class="lighter">
      <i class="ace-icon fa fa-hand-o-right icon-animated-hand-pointer blue"></i>
      <router-link to="/business/course" class="pink"> {{course.name}} </router-link>
    </h4>
    <hr>
    <p>
      <!--不带参数的跳转可以直接用router-link（类似a标签），如果还有其他操作，可以写个click方法。比如从课程跳转到大章时，需要先缓存课程信息，所以写click方法-->
      <router-link to="/business/course" class="btn btn-white btn-default btn-round">
        <!--fa(fontawesome)：图标样式-->
        <i class="ace-icon fa fa-arrow-left"></i>
        返回课程
      </router-link>
      &nbsp;
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
            <button v-on:click="toSection(chapter)" class="btn btn-white btn-xs btn-info btn-round">
              小节
            </button>

            <button v-on:click="edit(chapter)" class="btn btn-white btn-xs btn-info btn-round">
              编辑
            </button>

            <!--delete是js关键字，在Vue中也不能使用-->
            <button v-on:click="del(chapter.id)" class="btn btn-white btn-xs btn-warning btn-round">
              删除
            </button>
          </div>

          <!--
            hidden-md: 中等屏幕（>=992px）隐藏，其他可见；与之相反的是visible-md
            hidden-lg: 大屏幕(>=1200px)隐藏，其他可见;与之相反的是visible-lg
            具体可参考：https://v3.bootcss.com/css/#responsive-utilities-classes
          -->
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
    <!--小优化：目前的modal弹出、关闭都是使用class类型的css选择器（id、class、标签三种方式）选择页面元素，
    如果页面复杂（一个页面多个modal）,这时候用class选择，可能会出现重复，所以需要给每个modal增加id属性-->
    <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
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
                  <input type="text" v-model="chapter.name" class="form-control" id="inputName" placeholder="名称">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">课程</label>
                <div class="col-sm-10">
                  <p class="form-control-static">{{course.name}}</p>
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <!--
            模态框的弹出和关闭：可以使用js代码，也可以使用button属性（data-dismiss="css选择器"关闭，data-toggle="css选择器"打开）
            -->
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            <button type="button" class="btn btn-primary" v-on:click="save()">保存</button>
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
      chapters: [],
      //新增、编辑模态框中的内容
      chapter: {},
      course: {}
    }
  },
  mounted: function() {
    let _this = this;
    //子组件welcome调用父组件admin的方法，可以使用$parent
    //sidebar激活样式方法一
    // this.$parent.activeSideBar("business-chapter-sidebar");
    _this.$refs.pagination.size = 5;
    //1、页面从课程跳过来，有没有{}影响不大
    //2、直接访问大章页面，有{}返回{},没有{}返回undefined，{}.name不会报错，而undefined.name会报错
    let course  = SessionStorage.get("course") || {};
    //此时是直接访问的大章页面
    if (Tool.isEmpty(course)) {
      _this.$router.push("/welcome");
    }
    _this.course = course;
    _this.list(1);

  },
  methods: {
    //点击【小节】
    toSection(chapter) {
      let _this=this;
      SessionStorage.set("chapter",chapter);
      _this.$router.push("/business/section");
    },
    //点击【删除】
    del(id) {
      let _this = this;
      Confirm.show("删除大章后不能恢复，确认删除？",function () {
        Loading.show();
        _this.$ajax.delete(process.env.VUE_APP_SERVER + '/business/admin/chapter/delete/' + id).then(res => {
          Loading.hide();
          let resd = res.data;
          if (resd.success) {
            _this.list(1);
            //放list前后都可以
            Toast.success("删除成功!")
          }
        })
      });
    },
    //点击【编辑】
    edit(chapter) {
      let _this=this;
      //问题：编辑并点击取消后，仍然显示编辑后的数据（没有进入数据库，刷新后正常显示）
      //原因：编辑的是this.chapter，但是它是从this.chapters中取出来的，这样修改表单（this.chapter）时会
      // 影响表格（this.chapters）行数据，这就是Vue的双向绑定的问题
      //解决方案：副本，即把chapter复制一份到空对象，然后再将复制得到的对象赋值给_this.chapter，之后如果修改_this.chapter
      //就不会影响chapter，也即不会影响_this.chapters。
      _this.chapter=$.extend({},chapter);
      $("#form-modal").modal("show");
    },
    save() {
      let _this = this;

      //校验
      if (!Validator.require(_this.chapter.name,"名称")
        || !Validator.length(_this.chapter.courseId,"课程ID",1,8)) {
        return;
      }
      _this.chapter.courseId=_this.course.id;

      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/chapter/save',_this.chapter).then(res => {
        Loading.hide();
        //列表查询业务上一般都是成功的（查不到数据也是成功的），因此不需要判断success;
        //保存有可能失败，所以需要判断success
        let resd = res.data;
        if (resd.success) {
          //关闭模态框
          $("#form-modal").modal("hide");
          //刷新
          _this.list(1);
          Toast.success("保存成功！")
        } else {
          Toast.warning(resd.message);
        }
      })
    },
    //点击【新增】
    add() {
      let _this=this;
      //问题：点击编辑按钮后（之后不管取消还是修改），会改变_this.chapter，这时点击新增按钮会有内容（同样是Vue的双向绑定，一般点击新增按钮后应该为空）
      //解决方案：添加下面一句即可
      _this.chapter={};
      // $("#form-modal").modal({backdrop: "static"}); //禁止点空白的地方关闭模态框（某些场景会用到这个功能）
      $("#form-modal").modal("show"); //$(".modal") css选择器，因为模块框代码中有class="modal"的样式；modal()是内置的方法，用于弹出或关闭模态框

    },
    //列表查询
    //接口请求参数传递，尽量使用post：使用get请求在url里拼接参数的话，会使url变得很长，有些浏览器或服务器会对url长度做限制，导致请求失败
    list(page) {
      let _this = this;
      Loading.show();
      //生产环境和开发环境的IP地址是不一样的，所以不能把固定的IP地址写在代码里
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/chapter/list',{
        page: page,
        size: _this.$refs.pagination.size,
        //大章页面应该只显示当前课程的所有大章
        courseId: _this.course.id
      }).then(res => {
        Loading.hide();
        let resd = res.data;
        _this.chapters = resd.content.list;
        _this.$refs.pagination.render(page,resd.content.total); //TODO 直接访问大章页面，前端报错：Uncaught (in promise) TypeError: Cannot read property 'render' of undefined
      })
    }
  }
}
</script>