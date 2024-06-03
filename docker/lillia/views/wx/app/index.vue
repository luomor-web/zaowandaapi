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
          <el-form-item label="app id">
            <el-input v-model="form.appId" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="app secret">
            <el-input v-model="form.appSecret" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="token">
            <el-input v-model="form.appToken" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="aes 加密key">
            <el-input v-model="form.aesKey" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="消息格式">
            <el-input v-model="form.msgDataFormat" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="1-小程序,2-公众号,3-服务号">
            <el-input v-model="form.type" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="名称">
            <el-input v-model="form.name" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="备注">
            <el-input v-model="form.remark" style="width: 370px;" />
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
        <el-table-column prop="appId" label="app id" />
        <el-table-column prop="appSecret" label="app secret" />
        <el-table-column prop="appToken" label="token" />
        <el-table-column prop="aesKey" label="aes 加密key" />
        <el-table-column prop="msgDataFormat" label="消息格式" />
        <el-table-column prop="type" label="1-小程序,2-公众号,3-服务号" />
        <el-table-column prop="name" label="名称" />
        <el-table-column prop="remark" label="备注" />
        <el-table-column prop="status" label="1-启用,0-禁用" />
        <el-table-column prop="createTime" label="创建时间" />
        <el-table-column prop="updateTime" label="更新时间" />
        <el-table-column v-if="checkPer(['admin','wxApp:edit','wxApp:del'])" label="操作" width="150px" align="center">
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
import crudWxApp from '@/api/wxApp'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
// import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

const defaultForm = { id: null, appId: null, appSecret: null, appToken: null, aesKey: null, msgDataFormat: null, type: null, name: null, remark: null, status: null, createTime: null, updateTime: null }
export default {
  name: 'WxApp',
  components: { pagination, crudOperation, udOperation },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  cruds() {
    return CRUD({ title: '微信app', url: 'bsc/wxApp', idField: 'id', sort: 'id,desc', crudMethod: { ...crudWxApp }})
  },
  data() {
    return {
      permission: {
        add: ['admin', 'wxApp:add'],
        edit: ['admin', 'wxApp:edit'],
        del: ['admin', 'wxApp:del']
      },
      rules: {
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
