<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <!--如果想在工具栏加入更多按钮，可以使用插槽方式， slot = 'left' or 'right'-->
      <crudOperation :permission="permission" />
      <!--表单组件-->
      <el-dialog :close-on-click-modal="false" :before-close="crud.cancelCU" :visible.sync="crud.status.cu > 0" :title="crud.status.title" width="500px">
        <el-form ref="form" :model="form" :rules="rules" size="small" label-width="80px">
          <el-form-item label="id">
            <el-input v-model="form.id" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="examName">
            <el-input v-model="form.examName" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="0-禁用,1-启用">
            <el-input v-model="form.status" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="创建时间" prop="createTime">
            <el-input v-model="form.createTime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="更新时间">
            <el-input v-model="form.updateTime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="filelabel">
            <el-input v-model="form.fileLabel" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="file 大小">
            <el-input v-model="form.fileSize" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="教材版本version">
            <el-input v-model="form.version" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="pid" prop="pid">
            <el-input v-model="form.pid" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="临时表id用户校验数据完整性">
            <el-input v-model="form.tempId" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="文件类型" prop="fileType">
            <el-input v-model="form.fileType" style="width: 370px;" />
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
        <el-table-column prop="id" label="id" />
        <el-table-column prop="examName" label="examName" />
        <el-table-column prop="status" label="0-禁用,1-启用" />
        <el-table-column prop="createTime" label="创建时间" />
        <el-table-column prop="updateTime" label="更新时间" />
        <el-table-column prop="fileLabel" label="filelabel" />
        <el-table-column prop="fileSize" label="file 大小" />
        <el-table-column prop="version" label="教材版本version" />
        <el-table-column prop="pid" label="pid" />
        <el-table-column prop="tempId" label="临时表id用户校验数据完整性" />
        <el-table-column prop="fileType" label="文件类型" />
        <el-table-column v-if="checkPer(['admin','examInfo:edit','examInfo:del'])" label="操作" width="150px" align="center">
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
import crudExamInfo from '@/api/examInfo'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

const defaultForm = { id: null, examName: null, status: null, createTime: null, updateTime: null, fileLabel: null, fileSize: null, version: null, pid: null, tempId: null, fileType: null }
export default {
  name: 'ExamInfo',
  components: { pagination, crudOperation, rrOperation, udOperation },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  cruds() {
    return CRUD({ title: '试卷管理', url: 'bsc/examInfo', idField: 'id', sort: 'id,desc', crudMethod: { ...crudExamInfo }})
  },
  data() {
    return {
      permission: {
        add: ['admin', 'examInfo:add'],
        edit: ['admin', 'examInfo:edit'],
        del: ['admin', 'examInfo:del']
      },
      rules: {
        createTime: [
          { required: true, message: '创建时间不能为空', trigger: 'blur' }
        ],
        pid: [
          { required: true, message: 'pid不能为空', trigger: 'blur' }
        ],
        fileType: [
          { required: true, message: '文件类型不能为空', trigger: 'blur' }
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
