<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <!--如果想在工具栏加入更多按钮，可以使用插槽方式， slot = 'left' or 'right'-->
      <crudOperation :permission="permission" />
      <!--表单组件-->
      <el-dialog :close-on-click-modal="false" :before-close="crud.cancelCU" :visible.sync="crud.status.cu > 0" :title="crud.status.title" width="500px">
        <el-form ref="form" :model="form" :rules="rules" size="small" label-width="80px">
          <el-form-item label="lilliaFileId">
            <el-input v-model="form.lilliaFileId" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="lilliaFileBatchId" prop="lilliaFileBatchId">
            <el-input v-model="form.lilliaFileBatchId" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="fileName" prop="fileName">
            <el-input v-model="form.fileName" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="fileType" prop="fileType">
            <el-input v-model="form.fileType" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="filePath" prop="filePath">
            <el-input v-model="form.filePath" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="localPath" prop="localPath">
            <el-input v-model="form.localPath" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="fileSize" prop="fileSize">
            <el-input v-model="form.fileSize" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="fileCtime" prop="fileCtime">
            <el-input v-model="form.fileCtime" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="fileCdate" prop="fileCdate">
            <el-input v-model="form.fileCdate" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="fileUtime" prop="fileUtime">
            <el-input v-model="form.fileUtime" style="width: 370px;" />
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
          <el-form-item label="flag" prop="flag">
            <el-input v-model="form.flag" style="width: 370px;" />
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
        <el-table-column prop="lilliaFileId" label="lilliaFileId" />
        <el-table-column prop="lilliaFileBatchId" label="lilliaFileBatchId" />
        <el-table-column prop="fileName" label="fileName" />
        <el-table-column prop="fileType" label="fileType" />
        <el-table-column prop="filePath" label="filePath" />
        <el-table-column prop="localPath" label="localPath" />
        <el-table-column prop="fileSize" label="fileSize" />
        <el-table-column prop="fileCtime" label="fileCtime" />
        <el-table-column prop="fileCdate" label="fileCdate" />
        <el-table-column prop="fileUtime" label="fileUtime" />
        <el-table-column prop="readRet" label="readRet" />
        <el-table-column prop="status" label="status" />
        <el-table-column prop="readStatus" label="readStatus" />
        <el-table-column prop="flag" label="flag" />
        <el-table-column prop="operatorId" label="operatorId" />
        <el-table-column prop="operator" label="operator" />
        <el-table-column prop="comment" label="comment" />
        <el-table-column prop="createTime" label="createTime" />
        <el-table-column prop="updateTime" label="updateTime" />
        <el-table-column v-if="checkPer(['admin','lilliaFile:edit','lilliaFile:del'])" label="操作" width="150px" align="center">
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
import crudLilliaFile from '@/api/lilliaFile'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
// import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

const defaultForm = { lilliaFileId: null, lilliaFileBatchId: null, fileName: null, fileType: null, filePath: null, localPath: null, fileSize: null, fileCtime: null, fileCdate: null, fileUtime: null, readRet: null, status: null, readStatus: null, flag: null, operatorId: null, operator: null, comment: null, createTime: null, updateTime: null }
export default {
  name: 'LilliaFile',
  components: { pagination, crudOperation, udOperation },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  cruds() {
    return CRUD({ title: '文件', url: 'bsc/lilliaFile', idField: 'lilliaFileId', sort: 'lilliaFileId,desc', crudMethod: { ...crudLilliaFile }})
  },
  data() {
    return {
      permission: {
        add: ['admin', 'lilliaFile:add'],
        edit: ['admin', 'lilliaFile:edit'],
        del: ['admin', 'lilliaFile:del']
      },
      rules: {
        lilliaFileBatchId: [
          { required: true, message: '不能为空', trigger: 'blur' }
        ],
        fileName: [
          { required: true, message: '不能为空', trigger: 'blur' }
        ],
        fileType: [
          { required: true, message: '不能为空', trigger: 'blur' }
        ],
        filePath: [
          { required: true, message: '不能为空', trigger: 'blur' }
        ],
        localPath: [
          { required: true, message: '不能为空', trigger: 'blur' }
        ],
        fileSize: [
          { required: true, message: '不能为空', trigger: 'blur' }
        ],
        fileCtime: [
          { required: true, message: '不能为空', trigger: 'blur' }
        ],
        fileCdate: [
          { required: true, message: '不能为空', trigger: 'blur' }
        ],
        fileUtime: [
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
        flag: [
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
