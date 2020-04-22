import request from '@/utils/request'
import { prefixURL } from '@/utils/global'

export function saveVideo(data) {
  return request({
    url: prefixURL + 'video/save',
    method: 'post',
    data
  })
}
export function getVideo(data) {
  return request({
    url: prefixURL + 'video/page',
    method: 'post',
    data
  })
}

export function updateVideo(data) {
  return request({
    url: prefixURL + 'video',
    method: 'put',
    data
  })
}
export function deleteVideo(id) {
  return request({
    url: prefixURL + 'video',
    method: 'delete',
    params: { id }
  })
}

export function like(id, mark) {
  return request({
    url: prefixURL + 'video/like/' + id,
    method: 'post',
    params: { mark }
  })
}

export function getAmount(id) {
  return request({
    url: prefixURL + 'video/amount/' + id,
    method: 'get'
  })
}

export function getLike(data) {
  return request({
    url: prefixURL + 'video/like/page',
    method: 'post',
    data
  })
}
