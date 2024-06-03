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
          <el-form-item label="题库分类id" prop="pid">
            <el-input v-model="form.pid" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="问题id" prop="questionId">
            <el-input v-model="form.questionId" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="用户id" prop="uid">
            <el-input v-model="form.uid" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="纠错原因">
            <el-input v-model="form.reason" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="1-用户提交纠错,2-管理员审核通过,3-审核不通过">
            <el-input v-model="form.status" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="审核不通过原因">
            <el-input v-model="form.checkReason" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="管理员用户id">
            <el-input v-model="form.checkUid" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="创建时间">
            <el-input v-model="form.checkTime" style="width: 370px;" />
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
        <el-table-column prop="pid" label="题库分类id" />
        <el-table-column prop="questionId" label="问题id" />
        <el-table-column prop="uid" label="用户id" />
        <el-table-column prop="reason" label="纠错原因" />
        <el-table-column prop="status" label="1-用户提交纠错,2-管理员审核通过,3-审核不通过" />
        <el-table-column prop="checkReason" label="审核不通过原因" />
        <el-table-column prop="checkUid" label="管理员用户id" />
        <el-table-column prop="checkTime" label="创建时间" />
        <el-table-column prop="createTime" label="创建时间" />
        <el-table-column prop="updateTime" label="更新时间" />
        <el-table-column v-if="checkPer(['admin','userCorrectQuestion:edit','userCorrectQuestion:del'])" label="操作" width="150px" align="center">
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
import crudUserCorrectQuestion from '@/api/userCorrectQuestion'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
// import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

const defaultForm = { id: null, pid: null, questionId: null, uid: null, reason: null, status: null, checkReason: null, checkUid: null, checkTime: null, createTime: null, updateTime: null }
export default {
  name: 'UserCorrectQuestion',
  components: { pagination, crudOperation, udOperation },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  cruds() {
    return CRUD({ title: '查询纠错', url: 'bsc/userCorrectQuestion', idField: 'id', sort: 'id,desc', crudMethod: { ...crudUserCorrectQuestion }})
  },
  data() {
    return {
      permission: {
        add: ['admin', 'userCorrectQuestion:add'],
        edit: ['admin', 'userCorrectQuestion:edit'],
        del: ['admin', 'userCorrectQuestion:del']
      },
      rules: {
        pid: [
          { required: true, message: '题库分类id不能为空', trigger: 'blur' }
        ],
        questionId: [
          { required: true, message: '问题id不能为空', trigger: 'blur' }
        ],
        uid: [
          { required: true, message: '用户id不能为空', trigger: 'blur' }
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
