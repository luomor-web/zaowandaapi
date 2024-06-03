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
          <el-form-item label="题目id" prop="qid">
            <el-input v-model="form.qid" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="获得积分">
            <el-input v-model="form.integral" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="用户id" prop="uid">
            <el-input v-model="form.uid" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="用户答案" prop="userAnswer">
            <el-input v-model="form.userAnswer" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="0-答错, 1-答对">
            <el-input v-model="form.status" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="创建时间" prop="createTime">
            <el-input v-model="form.createTime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="更新时间">
            <el-input v-model="form.updateTime" style="width: 370px;" />
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
        <el-table-column prop="qid" label="题目id" />
        <el-table-column prop="integral" label="获得积分" />
        <el-table-column prop="uid" label="用户id" />
        <el-table-column prop="userAnswer" label="用户答案" />
        <el-table-column prop="status" label="0-答错, 1-答对" />
        <el-table-column prop="createTime" label="创建时间" />
        <el-table-column prop="updateTime" label="更新时间" />
        <el-table-column v-if="checkPer(['admin','questionTodayAnswer:edit','questionTodayAnswer:del'])" label="操作" width="150px" align="center">
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
import crudQuestionTodayAnswer from '@/api/questionTodayAnswer'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
// import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

const defaultForm = { id: null, qid: null, integral: null, uid: null, userAnswer: null, status: null, createTime: null, updateTime: null }
export default {
  name: 'QuestionTodayAnswer',
  components: { pagination, crudOperation, udOperation },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  cruds() {
    return CRUD({ title: '每日一题答题', url: 'bsc/questionTodayAnswer', idField: 'id', sort: 'id,desc', crudMethod: { ...crudQuestionTodayAnswer }})
  },
  data() {
    return {
      permission: {
        add: ['admin', 'questionTodayAnswer:add'],
        edit: ['admin', 'questionTodayAnswer:edit'],
        del: ['admin', 'questionTodayAnswer:del']
      },
      rules: {
        qid: [
          { required: true, message: '题目id不能为空', trigger: 'blur' }
        ],
        uid: [
          { required: true, message: '用户id不能为空', trigger: 'blur' }
        ],
        userAnswer: [
          { required: true, message: '用户答案不能为空', trigger: 'blur' }
        ],
        createTime: [
          { required: true, message: '创建时间不能为空', trigger: 'blur' }
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
