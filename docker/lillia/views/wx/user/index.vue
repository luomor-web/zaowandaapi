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
            <el-input v-model="form.nickName" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="头像地址">
            <el-input v-model="form.avatarUrl" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="国家">
            <el-input v-model="form.country" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="性别">
            <el-input v-model="form.gender" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="语言">
            <el-input v-model="form.language" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="省份">
            <el-input v-model="form.province" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="unionid">
            <el-input v-model="form.unionid" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="openid">
            <el-input v-model="form.openid" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="session key">
            <el-input v-model="form.sessionKey" style="width: 370px;" />
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
          <el-form-item label="小程序appid">
            <el-input v-model="form.appId" style="width: 370px;" />
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
        <el-table-column prop="nickName" label="昵称" />
        <el-table-column prop="avatarUrl" label="头像地址" />
        <el-table-column prop="country" label="国家" />
        <el-table-column prop="gender" label="性别" />
        <el-table-column prop="language" label="语言" />
        <el-table-column prop="province" label="省份" />
        <el-table-column prop="unionid" label="unionid" />
        <el-table-column prop="openid" label="openid" />
        <el-table-column prop="sessionKey" label="session key" />
        <el-table-column prop="status" label="1-启用,0-禁用" />
        <el-table-column prop="createTime" label="创建时间" />
        <el-table-column prop="updateTime" label="更新时间" />
        <el-table-column prop="appId" label="小程序appid" />
        <el-table-column v-if="checkPer(['admin','wxUser:edit','wxUser:del'])" label="操作" width="150px" align="center">
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
import crudWxUser from '@/api/wxUser'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
// import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

const defaultForm = { id: null, nickName: null, avatarUrl: null, country: null, gender: null, language: null, province: null, unionid: null, openid: null, sessionKey: null, status: null, createTime: null, updateTime: null, appId: null }
export default {
  name: 'WxUser',
  components: { pagination, crudOperation, udOperation },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  cruds() {
    return CRUD({ title: '微信用户', url: 'bsc/wxUser', idField: 'id', sort: 'id,desc', crudMethod: { ...crudWxUser }})
  },
  data() {
    return {
      permission: {
        add: ['admin', 'wxUser:add'],
        edit: ['admin', 'wxUser:edit'],
        del: ['admin', 'wxUser:del']
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
