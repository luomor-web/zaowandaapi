<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <!--如果想在工具栏加入更多按钮，可以使用插槽方式， slot = 'left' or 'right'-->
      <crudOperation :permission="permission" />
      <!--表单组件-->
      <el-dialog :close-on-click-modal="false" :before-close="crud.cancelCU" :visible.sync="crud.status.cu > 0" :title="crud.status.title" width="500px">
        <el-form ref="form" :model="form" :rules="rules" size="small" label-width="80px">
          <el-form-item label="ID">
            <el-input v-model="form.id" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="昵称">
            <el-input v-model="form.name" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="1-启用,0-禁用" prop="status">
            <el-input v-model="form.status" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="创建时间" prop="createTime">
            <el-input v-model="form.createTime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="更新时间">
            <el-input v-model="form.updateTime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="上级标签" prop="pid">
            <el-input v-model="form.pid" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="href">
            <el-input v-model="form.category" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="排序字段" prop="sort">
            <el-input v-model="form.sort" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="chapter_id" prop="chapterId">
            <el-input v-model="form.chapterId" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="chapter_ratio" prop="chapterRatio">
            <el-input v-model="form.chapterRatio" style="width: 370px;" />
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="text" @click="crud.cancelCU">取消</el-button>
          <el-button :loading="crud.status.cu === 2" type="primary" @click="crud.submitCU">确认</el-button>
        </div>
      </el-dialog>
      <!--表格渲染-->
      <el-table ref="table" v-loading="crud.loading" :data="crud.data" size="small" style="width: 100%;" @selection-change="crud.selectionChangeHandler">
        <el-table-column type="selection" width="55" />
        <el-table-column prop="id" label="ID" />
        <el-table-column prop="name" label="昵称" />
        <el-table-column prop="status" label="1-启用,0-禁用" />
        <el-table-column prop="createTime" label="创建时间" />
        <el-table-column prop="updateTime" label="更新时间" />
        <el-table-column prop="pid" label="上级标签" />
        <el-table-column prop="category" label="href" />
        <el-table-column prop="sort" label="排序字段" />
        <el-table-column prop="chapterId" label="chapter_id" />
        <el-table-column prop="chapterRatio" label="chapter_ratio" />
        <el-table-column v-if="checkPer(['admin','questionMenu:edit','questionMenu:del'])" label="操作" width="150px" align="center">
          <template slot-scope="scope">
            <udOperation
              :data="scope.row"
              :permission="permission"
            />
          </template>
        </el-table-column>
      </el-table>
      <!--分页组件-->
      <pagination />
    </div>
  </div>
</template>

<script>
import crudQuestionMenu from '@/api/questionMenu'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
// import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

const defaultForm = { id: null, name: null, status: null, createTime: null, updateTime: null, pid: null, category: null, sort: null, chapterId: null, chapterRatio: null }
export default {
  name: 'QuestionMenu',
  components: { pagination, crudOperation, udOperation },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  cruds() {
    return CRUD({ title: '题目分类', url: 'bsc/questionMenu', idField: 'id', sort: 'id,desc', crudMethod: { ...crudQuestionMenu }})
  },
  data() {
    return {
      permission: {
        add: ['admin', 'questionMenu:add'],
        edit: ['admin', 'questionMenu:edit'],
        del: ['admin', 'questionMenu:del']
      },
      rules: {
        status: [
          { required: true, message: '1-启用,0-禁用不能为空', trigger: 'blur' }
        ],
        createTime: [
          { required: true, message: '创建时间不能为空', trigger: 'blur' }
        ],
        pid: [
          { required: true, message: '上级标签不能为空', trigger: 'blur' }
        ],
        sort: [
          { required: true, message: '排序字段不能为空', trigger: 'blur' }
        ],
        chapterId: [
          { required: true, message: 'chapter_id不能为空', trigger: 'blur' }
        ],
        chapterRatio: [
          { required: true, message: 'chapter_ratio不能为空', trigger: 'blur' }
        ]
      }    }
  },
  methods: {
    // 钩子：在获取表格数据之前执行，false 则代表不获取数据
    [CRUD.HOOK.beforeRefresh]() {
      return true
    }
  }
}
</script>

<style scoped>

</style>
