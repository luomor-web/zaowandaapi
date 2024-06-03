import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'bsc/userIntegralLog',
    method: 'post',
    data
  })
}

export function del(ids) {
  return request({
    url: 'bsc/userIntegralLog/',
    method: 'delete',
    data: ids
  })
}

export function edit(data) {
  return request({
    url: 'bsc/userIntegralLog',
    method: 'put',
    data
  })
}

export default { add, edit, del }
