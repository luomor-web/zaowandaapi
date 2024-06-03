import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'bsc/questionToday',
    method: 'post',
    data
  })
}

export function del(ids) {
  return request({
    url: 'bsc/questionToday/',
    method: 'delete',
    data: ids
  })
}

export function edit(data) {
  return request({
    url: 'bsc/questionToday',
    method: 'put',
    data
  })
}

export default { add, edit, del }
