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
          <el-form-item label="用户id" prop="uid">
            <el-input v-model="form.uid" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="消耗积分">
            <el-input v-model="form.integral" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="题库id">
            <el-input v-model="form.pid" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="邮箱">
            <el-input v-model="form.email" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="0-用户提交, 1-导出成功, 2-导出失败">
            <el-input v-model="form.status" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="创建时间" prop="createTime">
            <el-input v-model="form.createTime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="更新时间">
            <el-input v-model="form.updateTime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="1-题库  2-试卷" prop="exportType">
            <el-input v-model="form.exportType" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="试卷id">
            <el-input v-model="form.examId" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="发送邮件附件地址pdf用到">
            <el-input v-model="form.filePath" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="0-完整题库 1-模拟考试试卷">
            <el-input v-model="form.questionType" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="模拟考试试卷题目数量">
            <el-input v-model="form.questionNumber" style="width: 370px;" />
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
        <el-table-column prop="uid" label="用户id" />
        <el-table-column prop="integral" label="消耗积分" />
        <el-table-column prop="pid" label="题库id" />
        <el-table-column prop="email" label="邮箱" />
        <el-table-column prop="status" label="0-用户提交, 1-导出成功, 2-导出失败" />
        <el-table-column prop="createTime" label="创建时间" />
        <el-table-column prop="updateTime" label="更新时间" />
        <el-table-column prop="exportType" label="1-题库  2-试卷" />
        <el-table-column prop="examId" label="试卷id" />
        <el-table-column prop="filePath" label="发送邮件附件地址pdf用到" />
        <el-table-column prop="questionType" label="0-完整题库 1-模拟考试试卷" />
        <el-table-column prop="questionNumber" label="模拟考试试卷题目数量" />
        <el-table-column v-if="checkPer(['admin','userExportLog:edit','userExportLog:del'])" label="操作" width="150px" align="center">
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
import crudUserExportLog from '@/api/userExportLog'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
// import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

const defaultForm = { id: null, uid: null, integral: null, pid: null, email: null, status: null, createTime: null, updateTime: null, exportType: null, examId: null, filePath: null, questionType: null, questionNumber: null }
export default {
  name: 'UserExportLog',
  components: { pagination, crudOperation, udOperation },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  cruds() {
    return CRUD({ title: '导出记录', url: 'bsc/userExportLog', idField: 'id', sort: 'id,desc', crudMethod: { ...crudUserExportLog }})
  },
  data() {
    return {
      permission: {
        add: ['admin', 'userExportLog:add'],
        edit: ['admin', 'userExportLog:edit'],
        del: ['admin', 'userExportLog:del']
      },
      rules: {
        uid: [
          { required: true, message: '用户id不能为空', trigger: 'blur' }
        ],
        createTime: [
          { required: true, message: '创建时间不能为空', trigger: 'blur' }
        ],
        exportType: [
          { required: true, message: '1-题库  2-试卷不能为空', trigger: 'blur' }
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
