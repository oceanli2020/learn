import request from '@/utils/request'
import { prefixURL } from '@/utils/global'

export function getComment(data) {
  return request({
    url: prefixURL + 'comment/page',
    method: 'post',
    data
  })
}

export function saveComment(data) {
  return request({
    url: prefixURL + 'comment/save',
    method: 'post',
    data
  })
}
export function getChildComment(data) {
  return request({
    url: prefixURL + 'comment/child/page',
    method: 'post',
    data
  })
}
