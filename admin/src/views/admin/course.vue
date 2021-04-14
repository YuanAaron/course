<template>
  <div>
    <p>
      <button v-on:click="add()" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-edit red2"></i>
        新增
      </button>
      &nbsp;
      <button v-on:click="list(1)" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-refresh red2"></i>
        刷新
      </button>
    </p>

    <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="3"></pagination>

    <div class="row">
      <div v-for="course in courses" class="col-md-4">
        <div class="thumbnail search-thumbnail">
          <img v-show="!course.image" class="media-object" src="/static/image/demo-course.jpg" />
          <img v-show="course.image" class="media-object" v-bind:src="course.image" />
          <div class="caption">
            <div class="clearfix">
              <span class="pull-right label label-primary info-label">
                {{COURSE_LEVEL | optionKV(course.level)}}
              </span>
              <span class="pull-right label label-primary info-label">
                {{COURSE_CHARGE | optionKV(course.charge)}}
              </span>
              <span class="pull-right label label-primary info-label">
                {{COURSE_STATUS | optionKV(course.status)}}
              </span>
            </div>

            <h3 class="search-title">
              <a href="#" class="blue">{{course.name}}</a>
            </h3>
            <p>
              <span class="blue bolder bigger-150">{{course.price}}&nbsp;<i class="fa fa-rmb"></i></span>
            </p>
            <p>{{course.summary}}</p>
            <p>
              <span class="badge badge-info">{{course.id}}</span>
              <span class="badge badge-info">排序：{{course.sort}}</span>
              <span class="badge badge-info">时长：{{course.time | formatSecond}}</span>
            </p>
            <p>
              <button v-on:click="toChapter(course)" class="btn btn-white btn-xs btn-info btn-round">
                大章
              </button>

              <button v-on:click="edit(course)" class="btn btn-white btn-xs btn-info btn-round">
                编辑
              </button>

              <button v-on:click="del(course.id)" class="btn btn-white btn-xs btn-warning btn-round">
                删除
              </button>
            </p>
          </div>
        </div>
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
                <label class="col-sm-2 control-label">分类</label>
                <div class="col-sm-10">
                  <ul id="tree" class="ztree"></ul>
                </div>
              </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">名称</label>
                  <div class="col-sm-10">
                    <input type="text" v-model="course.name" class="form-control"
                           placeholder="名称">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">概述</label>
                  <div class="col-sm-10">
                    <input type="text" v-model="course.summary" class="form-control"
                           placeholder="概述">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">时长</label>
                  <div class="col-sm-10">
                    <input type="text" v-model="course.time" class="form-control"
                           placeholder="时长">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">价格（元）</label>
                  <div class="col-sm-10">
                    <input type="text" v-model="course.price" class="form-control"
                           placeholder="价格（元）">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">封面</label>
                  <div class="col-sm-10">
                    <input type="text" v-model="course.image" class="form-control"
                           placeholder="封面">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">级别</label>
                  <div class="col-sm-10">
                    <select type="text" v-model="course.level" class="form-control" placeholder="级别">
                      <option v-for="o in COURSE_LEVEL" v-bind:value="o.key">{{o.value}}</option>
                    </select>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">收费</label>
                  <div class="col-sm-10">
                    <select type="text" v-model="course.charge" class="form-control" placeholder="收费">
                      <option v-for="o in COURSE_CHARGE" v-bind:value="o.key">{{o.value}}</option>
                    </select>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">状态</label>
                  <div class="col-sm-10">
                    <select type="text" v-model="course.status" class="form-control" placeholder="状态">
                      <option v-for="o in COURSE_STATUS" v-bind:value="o.key">{{o.value}}</option>
                    </select>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">报名数</label>
                  <div class="col-sm-10">
                    <input type="text" v-model="course.enroll" class="form-control"
                           placeholder="报名数">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">顺序</label>
                  <div class="col-sm-10">
                    <input type="text" v-model="course.sort" class="form-control"
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
import Pagination from "@/components/pagination";

export default {
  components: {Pagination},
  //[Vue warn]: Do not use built-in or reserved HTML elements as component id: section
  name: 'business-course',
  data: function () {
    return {
      courses: [],
      course: {},
      COURSE_LEVEL: COURSE_LEVEL,
      COURSE_CHARGE: COURSE_CHARGE,
      COURSE_STATUS: COURSE_STATUS,
    }
  },
  mounted: function() {
    let _this = this;
    _this.$refs.pagination.size = 5;
    _this.initTree();
    _this.list(1);

  },
  methods: {
    initTree() {
      let setting = {
        check: {
          enable: true
        },
        data: {
          simpleData: {
            enable: true
          }
        }
      };

      let zNodes = [
        { id:1, pId:0, name:"随意勾选 1", open:true},
        { id:11, pId:1, name:"随意勾选 1-1", open:true},
        { id:111, pId:11, name:"随意勾选 1-1-1"},
        { id:112, pId:11, name:"随意勾选 1-1-2"},
        { id:12, pId:1, name:"随意勾选 1-2", open:true},
        { id:121, pId:12, name:"随意勾选 1-2-1"},
        { id:122, pId:12, name:"随意勾选 1-2-2"},
        { id:2, pId:0, name:"随意勾选 2", checked:true, open:true},
        { id:21, pId:2, name:"随意勾选 2-1"},
        { id:22, pId:2, name:"随意勾选 2-2", open:true},
        { id:221, pId:22, name:"随意勾选 2-2-1", checked:true},
        { id:222, pId:22, name:"随意勾选 2-2-2"},
        { id:23, pId:2, name:"随意勾选 2-3"}
      ];

      $.fn.zTree.init($("#tree"), setting, zNodes);
    },
    //点击【大章】
    //组件（页面）间传输数据可以用h5原生的localStorage、sessionStorage，也可以用js全局变量，也可以用vuex store，
    //但后两者在页面刷新时会丢失数据，所以推荐使用h5原生。
    toChapter(course) {
      let _this=this;
      SessionStorage.set("course",course);
      _this.$router.push("/business/chapter");
    },
    //点击【删除】
    del(id) {
      let _this = this;
      Confirm.show("删除课程表后不能恢复，确认删除？",function () {
        Loading.show();
        _this.$ajax.delete(process.env.VUE_APP_SERVER + '/business/admin/course/delete/' + id).then(res => {
          Loading.hide();
          let resd = res.data;
          if (resd.success) {
            _this.list(1);
            Toast.success("删除成功!")
          }
        })
      });
    },
    //点击【编辑】
    edit(course) {
      let _this=this;
      _this.course=$.extend({},course);
      $("#form-modal").modal("show");
    },
    save() {
      let _this = this;

      //校验
      // 1!=1的设计类似于mybatis的动态sql设计（在拼动态where条件时，会在前面加1==1）
      if (1 != 1
            || !Validator.require(_this.course.name, "名称")
            || !Validator.length(_this.course.name, "名称", 1, 50)
            || !Validator.length(_this.course.summary, "概述", 1, 2000)
            || !Validator.length(_this.course.image, "封面", 1, 100)
      ) {
        return;
      }

      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/course/save',_this.course).then(res => {
        Loading.hide();
        let resd = res.data;
        if (resd.success) {
          $("#form-modal").modal("hide");
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
      _this.course={};
      $("#form-modal").modal("show");

    },
    //列表查询
    list(page) {
      let _this = this;
      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/course/list',{
        page: page,
        size: _this.$refs.pagination.size
      }).then(res => {
        Loading.hide();
        let resd = res.data;
        _this.courses = resd.content.list;
        _this.$refs.pagination.render(page,resd.content.total);
      })
    }
  }
}
</script>

<!--scoped：style下的样式只应用于当前组件，防止互相污染-->
<style scoped>
  .caption h3 {
    font-size: 20px;
  }
</style>