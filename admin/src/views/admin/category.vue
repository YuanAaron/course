<template>
  <div>
    <div class="row">
      <div class="col-md-6">
        <p>
          <button v-on:click="add1()" class="btn btn-white btn-default btn-round">
            <i class="ace-icon fa fa-edit red2"></i>
            新增一级
          </button>
          &nbsp;
          <button v-on:click="all()" class="btn btn-white btn-default btn-round">
            <i class="ace-icon fa fa-refresh red2"></i>
            刷新
          </button>
        </p>
        <table id="level1-table" class="table  table-bordered table-hover">
          <thead>
          <tr>
            <th>id</th>
            <th>名称</th>
            <th>顺序</th>
            <td>操作</td>
          </tr>
          </thead>

          <!--动态class：v-bind:class=json表达式，key为样式，value为样式显示的条件，即value为true时，key的样式生效。
          此外，它可以和原生的class并存-->
          <tbody>
          <tr v-for="category in level1" v-on:click="onclicklevel1(category)" v-bind:class="{'active':category.id===active.id}">
            <td>{{category.id}}</td>
            <td>{{category.name}}</td>
            <td>{{category.sort}}</td>
            <td>
              <div class="hidden-sm hidden-xs btn-group">
                <button v-on:click="edit(category)" class="btn btn-xs btn-info">
                  <i class="ace-icon fa fa-pencil bigger-120"></i>
                </button>

                <button v-on:click="del(category.id)" class="btn btn-xs btn-danger">
                  <i class="ace-icon fa fa-trash-o bigger-120"></i>
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
      </div>
      <div class="col-md-6">
        <p>
          <button v-on:click="add2()" class="btn btn-white btn-default btn-round">
            <i class="ace-icon fa fa-edit red2"></i>
            新增二级
          </button>
        </p>
        <table id="level2-table" class="table  table-bordered table-hover">
          <thead>
          <tr>
            <th>id</th>
            <th>名称</th>
            <th>顺序</th>
            <td>操作</td>
          </tr>
          </thead>

          <tbody>
          <tr v-for="category in level2">
            <td>{{category.id}}</td>
            <td>{{category.name}}</td>
            <td>{{category.sort}}</td>
            <td>
              <div class="hidden-sm hidden-xs btn-group">
                <button v-on:click="edit(category)" class="btn btn-xs btn-info">
                  <i class="ace-icon fa fa-pencil bigger-120"></i>
                </button>

                <button v-on:click="del(category.id)" class="btn btn-xs btn-danger">
                  <i class="ace-icon fa fa-trash-o bigger-120"></i>
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
      </div>
    </div>

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
                  <label class="col-sm-2 control-label">父分类</label>
                  <div class="col-sm-10">
                    <p  class="form-control-static">{{active.name||"无"}}</p>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">名称</label>
                  <div class="col-sm-10">
                    <input type="text" v-model="category.name" class="form-control"
                           placeholder="名称">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">顺序</label>
                  <div class="col-sm-10">
                    <input type="text" v-model="category.sort" class="form-control"
                           placeholder="顺序">
                  </div>
                </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            <button type="button" class="btn btn-primary" v-on:click="save()">保存</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

  </div>
</template>

<script>

export default {
  //[Vue warn]: Do not use built-in or reserved HTML elements as component id: section
  name: 'business-category',
  data: function () {
    return {
      categorys: [],
      category: {},
      level1: [],
      level2: [],
      active: {},
    }
  },
  mounted: function() {
    let _this = this;
    _this.all();

  },
  methods: {
    //点击【删除】
    del(id) {
      let _this = this;
      Confirm.show("删除分类后不能恢复，确认删除？",function () {
        Loading.show();
        _this.$ajax.delete(process.env.VUE_APP_SERVER + '/business/admin/category/delete/' + id).then(res => {
          Loading.hide();
          let resd = res.data;
          if (resd.success) {
            _this.all();
            Toast.success("删除成功!")
          }
        })
      });
    },
    //点击【编辑】
    edit(category) {
      let _this=this;
      _this.category=$.extend({},category);
      $("#form-modal").modal("show");
    },
    save() {
      let _this = this;

      //校验
      // 1!=1的设计类似于mybatis的动态sql设计（在拼动态where条件时，会在前面加1==1）
      if (1 != 1
            || !Validator.require(_this.category.parent, "父id")
            || !Validator.require(_this.category.name, "名称")
            || !Validator.length(_this.category.name, "名称", 1, 50)
      ) {
        return;
      }

      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/category/save',_this.category).then(res => {
        Loading.hide();
        let resd = res.data;
        if (resd.success) {
          $("#form-modal").modal("hide");
          _this.all();
          Toast.success("保存成功！")
        } else {
          Toast.warning(resd.message);
        }
      })
    },
    //点击【新增一级】
    add1() {
      let _this=this;
      _this.active={}; //重要
//      _this.level2=[]; //TODO 啥作用？？？
      _this.category={
        parent: '00000000'
      };
      $("#form-modal").modal("show");

    },
    //点击【新增二级】
    add2() {
      let _this=this;
      if (Tool.isEmpty(_this.active)) {
        Toast.warning("请先点击一级分类");
        return;
      }
      _this.category={
        parent: _this.active.id
      };
      $("#form-modal").modal("show");

    },
    //列表查询
    all() {
      let _this = this;
      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/category/all').then(res => {
        Loading.hide();
        let resd = res.data;
        _this.categorys = resd.content;

        // 将所有记录格式化成树形结构
        _this.level1 = [];
        for (let i = 0; i < _this.categorys.length; i++) {
          let c1 = _this.categorys[i];
          //一级分类的父id默认设定为“00000000”
          if (c1.parent === '00000000') {
            _this.level1.push(c1);
            for (let j = 0; j< _this.categorys.length; j++) {
              let c2 = _this.categorys[j];
              // 二级分类c2的父亲是c1
              if (c2.parent === c1.id) {
                if (Tool.isEmpty(c1.children)) {
                  c1.children=[];
                }
                c1.children.push(c2);
              }
            }
          }
        }

        //问题：新增、修改、删除二级分类时，没有马上刷新出来，但再次点击一下一级分类中选中的表格行就能够显示出二级分类列表
        //解决方案：让程序自动触发点击事件，点击激活的行
        //为什么在这里解决：对一级或二级分类的增删改查,都会调用all()刷新数据,所以在all()的后面触发一次表格行点击事件
        this.level2=[];
        //注意：界面的渲染需要等Vue绑定好变量后才做，所以延时100ms
        //小技巧：当界面用了Vue，又用了jquery(特别是第三方插件)，当觉得代码没问题，但是效果没出来时，可以加个小延时，等待Vue绑定好变量再跑jquery代码
        setTimeout(function () {
          $("tr.active").trigger("click");
        },100);
      })
    },
    onclicklevel1(category) {
      let _this = this;
      _this.active = category;
      _this.level2 = category.children;
    }
  }
}
</script>

<style scoped>
  .active td{
    background-color: #d6e9c6 !important;
    color: #1e82d2;
    font-weight: bolder;
  }
</style>