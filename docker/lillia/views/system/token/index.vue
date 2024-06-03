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
          <el-form-item label="token值" prop="token">
            <el-input v-model="form.token" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="每天最大请求次数">
            <el-input v-model="form.dayMaxLimit" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="并发限制次数">
            <el-input v-model="form.threadLimit" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="1-启用,0-禁用" prop="status">
            <el-input v-model="form.status" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="备注">
            <el-input v-model="form.remark" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="创建时间" prop="createTime">
            <el-input v-model="form.createTime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="更新时间">
            <el-input v-model="form.updateTime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="绑定的微信小程序id">
            <el-input v-model="form.wxAppId" style="width: 370px;" />
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
        <el-table-column prop="token" label="token值" />
        <el-table-column prop="dayMaxLimit" label="每天最大请求次数" />
        <el-table-column prop="threadLimit" label="并发限制次数" />
        <el-table-column prop="status" label="1-启用,0-禁用" />
        <el-table-column prop="remark" label="备注" />
        <el-table-column prop="createTime" label="创建时间" />
        <el-table-column prop="updateTime" label="更新时间" />
        <el-table-column prop="wxAppId" label="绑定的微信小程序id" />
        <el-table-column v-if="checkPer(['admin','sysToken:edit','sysToken:del'])" label="操作" width="150px" align="center">
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
import crudSysToken from '@/api/sysToken'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
// import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

const defaultForm = { id: null, token: null, dayMaxLimit: null, threadLimit: null, status: null, remark: null, createTime: null, updateTime: null, wxAppId: null }
export default {
  name: 'SysToken',
  components: { pagination, crudOperation, udOperation },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  cruds() {
    return CRUD({ title: 'token管理', url: 'bsc/sysToken', idField: 'id', sort: 'id,desc', crudMethod: { ...crudSysToken }})
  },
  data() {
    return {
      permission: {
        add: ['admin', 'sysToken:add'],
        edit: ['admin', 'sysToken:edit'],
        del: ['admin', 'sysToken:del']
      },
      rules: {
        token: [
          { required: true, message: 'token值不能为空', trigger: 'blur' }
        ],
        status: [
          { required: true, message: '1-启用,0-禁用不能为空', trigger: 'blur' }
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
