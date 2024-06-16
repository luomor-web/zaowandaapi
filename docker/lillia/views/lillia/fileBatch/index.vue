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
            <el-input v-model="form.lilliaFileBatchId" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="uploadName" prop="uploadName">
            <el-input v-model="form.uploadName" style="width: 370px;" />
          </el-form-item>
          <el-form-item label=" 1- 2-" prop="uploadType">
            <el-input v-model="form.uploadType" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="num" prop="num">
            <el-input v-model="form.num" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="uploadTotalNum" prop="uploadTotalNum">
            <el-input v-model="form.uploadTotalNum" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="uploadSuccessNum" prop="uploadSuccessNum">
            <el-input v-model="form.uploadSuccessNum" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="uploadFailNum" prop="uploadFailNum">
            <el-input v-model="form.uploadFailNum" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="uploadRemoveNum" prop="uploadRemoveNum">
            <el-input v-model="form.uploadRemoveNum" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="unUploadNum" prop="unUploadNum">
            <el-input v-model="form.unUploadNum" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="readNum" prop="readNum">
            <el-input v-model="form.readNum" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="readRet" prop="readRet">
            <el-input v-model="form.readRet" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="status" prop="status">
            <el-input v-model="form.status" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="readStatus" prop="readStatus">
            <el-input v-model="form.readStatus" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="operatorId" prop="operatorId">
            <el-input v-model="form.operatorId" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="operator" prop="operator">
            <el-input v-model="form.operator" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="comment" prop="comment">
            <el-input v-model="form.comment" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="createTime" prop="createTime">
            <el-input v-model="form.createTime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="updateTime" prop="updateTime">
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
        <el-table-column prop="lilliaFileBatchId" label="ID" />
        <el-table-column prop="uploadName" label="uploadName" />
        <el-table-column prop="uploadType" label=" 1- 2-" />
        <el-table-column prop="num" label="num" />
        <el-table-column prop="uploadTotalNum" label="uploadTotalNum" />
        <el-table-column prop="uploadSuccessNum" label="uploadSuccessNum" />
        <el-table-column prop="uploadFailNum" label="uploadFailNum" />
        <el-table-column prop="uploadRemoveNum" label="uploadRemoveNum" />
        <el-table-column prop="unUploadNum" label="unUploadNum" />
        <el-table-column prop="readNum" label="readNum" />
        <el-table-column prop="readRet" label="readRet" />
        <el-table-column prop="status" label="status" />
        <el-table-column prop="readStatus" label="readStatus" />
        <el-table-column prop="operatorId" label="operatorId" />
        <el-table-column prop="operator" label="operator" />
        <el-table-column prop="comment" label="comment" />
        <el-table-column prop="createTime" label="createTime" />
        <el-table-column prop="updateTime" label="updateTime" />
        <el-table-column v-if="checkPer(['admin','lilliaFileBatch:edit','lilliaFileBatch:del'])" label="操作" width="150px" align="center">
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
import crudLilliaFileBatch from '@/api/lilliaFileBatch'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
// import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

const defaultForm = { lilliaFileBatchId: null, uploadName: null, uploadType: null, num: null, uploadTotalNum: null, uploadSuccessNum: null, uploadFailNum: null, uploadRemoveNum: null, unUploadNum: null, readNum: null, readRet: null, status: null, readStatus: null, operatorId: null, operator: null, comment: null, createTime: null, updateTime: null }
export default {
  name: 'LilliaFileBatch',
  components: { pagination, crudOperation, udOperation },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  cruds() {
    return CRUD({ title: '文件批次', url: 'bsc/lilliaFileBatch', idField: 'lilliaFileBatchId', sort: 'lilliaFileBatchId,desc', crudMethod: { ...crudLilliaFileBatch }})
  },
  data() {
    return {
      permission: {
        add: ['admin', 'lilliaFileBatch:add'],
        edit: ['admin', 'lilliaFileBatch:edit'],
        del: ['admin', 'lilliaFileBatch:del']
      },
      rules: {
        uploadName: [
          { required: true, message: '不能为空', trigger: 'blur' }
        ],
        uploadType: [
          { required: true, message: ' 1- 2-不能为空', trigger: 'blur' }
        ],
        num: [
          { required: true, message: '不能为空', trigger: 'blur' }
        ],
        uploadTotalNum: [
          { required: true, message: '不能为空', trigger: 'blur' }
        ],
        uploadSuccessNum: [
          { required: true, message: '不能为空', trigger: 'blur' }
        ],
        uploadFailNum: [
          { required: true, message: '不能为空', trigger: 'blur' }
        ],
        uploadRemoveNum: [
          { required: true, message: '不能为空', trigger: 'blur' }
        ],
        unUploadNum: [
          { required: true, message: '不能为空', trigger: 'blur' }
        ],
        readNum: [
          { required: true, message: '不能为空', trigger: 'blur' }
        ],
        readRet: [
          { required: true, message: '不能为空', trigger: 'blur' }
        ],
        status: [
          { required: true, message: '不能为空', trigger: 'blur' }
        ],
        readStatus: [
          { required: true, message: '不能为空', trigger: 'blur' }
        ],
        operatorId: [
          { required: true, message: '不能为空', trigger: 'blur' }
        ],
        operator: [
          { required: true, message: '不能为空', trigger: 'blur' }
        ],
        comment: [
          { required: true, message: '不能为空', trigger: 'blur' }
        ],
        createTime: [
          { required: true, message: '不能为空', trigger: 'blur' }
        ],
        updateTime: [
          { required: true, message: '不能为空', trigger: 'blur' }
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
