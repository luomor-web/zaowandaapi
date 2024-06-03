import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'bsc/userExportLog',
    method: 'post',
    data
  })
}

export function del(ids) {
  return request({
    url: 'bsc/userExportLog/',
    method: 'delete',
    data: ids
  })
}

export function edit(data) {
  return request({
    url: 'bsc/userExportLog',
    method: 'put',
    data
  })
}

export default { add, edit, del }
