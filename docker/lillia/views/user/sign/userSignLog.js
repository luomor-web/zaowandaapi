import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'bsc/userSignLog',
    method: 'post',
    data
  })
}

export function del(ids) {
  return request({
    url: 'bsc/userSignLog/',
    method: 'delete',
    data: ids
  })
}

export function edit(data) {
  return request({
    url: 'bsc/userSignLog',
    method: 'put',
    data
  })
}

export default { add, edit, del }
