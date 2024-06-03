import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'bsc/examInfo',
    method: 'post',
    data
  })
}

export function del(ids) {
  return request({
    url: 'bsc/examInfo/',
    method: 'delete',
    data: ids
  })
}

export function edit(data) {
  return request({
    url: 'bsc/examInfo',
    method: 'put',
    data
  })
}

export default { add, edit, del }
