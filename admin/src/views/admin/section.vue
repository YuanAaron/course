<template>
  <div>
    <h4 class="lighter">
      <i class="ace-icon fa fa-hand-o-right icon-animated-hand-pointer blue"></i>
      <router-link to="/business/course" class="pink"> {{course.name}} </router-link>：
      <i class="ace-icon fa fa-hand-o-right icon-animated-hand-pointer blue"></i>
      <router-link to="/business/chapter" class="pink"> {{chapter.name}} </router-link>
    </h4>
    <hr>
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

    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>
        <th>ID</th>
        <th>标题</th>
        <th>视频</th>
        <th>时长</th>
        <th>收费</th>
        <th>顺序</th>
        <td>操作</td>
      </tr>
      </thead>

      <tbody>
      <tr v-for="section in sections">
        <td>{{section.id}}</td>
        <td>{{section.title}}</td>
        <td>{{section.video}}</td>
        <td>{{section.time | formatSecond}}</td>
        <td>{{SECTION_CHARGE | optionKV(section.charge)}}</td>
        <td>{{section.sort}}</td>
        <td>
          <div class="hidden-sm hidden-xs btn-group">
            <button v-on:click="edit(section)" class="btn btn-xs btn-info">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>

            <button v-on:click="del(section.id)" class="btn btn-xs btn-danger">
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
                  <label class="col-sm-2 control-label">标题</label>
                  <div class="col-sm-10">
                    <input type="text" v-model="section.title" class="form-control"
                           placeholder="标题">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">课程</label>
                  <div class="col-sm-10">
                    <p class="form-control-static">{{course.name}}</p>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">大章</label>
                  <div class="col-sm-10">
                    <p class="form-control-static">{{chapter.name}}</p>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">视频</label>
                  <div class="col-sm-10">
                    <input type="text" v-model="section.video" class="form-control"
                           placeholder="视频">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">时长</label>
                  <div class="col-sm-10">
                    <input type="text" v-model="section.time" class="form-control"
                           placeholder="时长">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">收费</label>
                  <div class="col-sm-10">
                    <select type="text" v-model="section.charge" class="form-control" placeholder="收费">
                      <option v-for="o in SECTION_CHARGE" v-bind:value="o.key">{{o.value}}</option>
                    </select>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">顺序</label>
                  <div class="col-sm-10">
                    <input type="text" v-model="section.sort" class="form-control"
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
  name: 'business-section',
  data: function () {
    return {
      sections: [],
      section: {},
      SECTION_CHARGE: SECTION_CHARGE,
      course: {},
      chapter: {}
    }
  },
  mounted: function() {
    let _this = this;
    _this.$refs.pagination.size = 5;
    let course = SessionStorage.get("course") || {};
    let chapter = SessionStorage.get("chapter") || {};
    if (Tool.isEmpty(course) || Tool.isEmpty(chapter)) {
      _this.$router.push("/welcome");
    }
    _this.course = course;
    _this.chapter = chapter;
    _this.list(1);

  },
  methods: {
    //点击【删除】
    del(id) {
      let _this = this;
      Confirm.show("删除小节后不能恢复，确认删除？",function () {
        Loading.show();
        _this.$ajax.delete(process.env.VUE_APP_SERVER + '/business/admin/section/delete/' + id).then(res => {
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
    edit(section) {
      let _this=this;
      _this.section=$.extend({},section);
      $("#form-modal").modal("show");
    },
    save() {
      let _this = this;

      //校验
      // 1!=1的设计类似于mybatis的动态sql设计（在拼动态where条件时，会在前面加1==1）
      if (1 != 1
            || !Validator.require(_this.section.title, "标题")
            || !Validator.length(_this.section.title, "标题", 1, 50)
            || !Validator.length(_this.section.video, "视频", 1, 200)
      ) {
        return;
      }
      _this.section.courseId = _this.course.id;
      _this.section.chapterId = _this.chapter.id;

      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/section/save',_this.section).then(res => {
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
      _this.section={};
      $("#form-modal").modal("show");

    },
    //列表查询
    list(page) {
      let _this = this;
      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/section/list',{
        page: page,
        size: _this.$refs.pagination.size,
        courseId: _this.course.id, //该条件没有也可以
        chapterId: _this.chapter.id
      }).then(res => {
        Loading.hide();
        let resd = res.data;
        _this.sections = resd.content.list;
        _this.$refs.pagination.render(page,resd.content.total);
      })
    }
  }
}
</script>