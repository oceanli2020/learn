import request from '@/utils/request'
import { prefixURL } from '@/utils/global'

export function getLiveInfo(id) {
  return request({
    url: prefixURL + 'live',
    method: 'get',
    params: { id }
  })
}

export function updateLive(data) {
  return request({
    url: prefixURL + 'live',
    method: 'put',
    data
  })
}

export function getLiveList(data) {
  return request({
    url: prefixURL + 'live/page',
    method: 'post',
    data
  })
}

export function getReplayList(data) {
  return request({
    url: prefixURL + 'replay/page',
    method: 'post',
    data
  })
}

export function deleteReplay(id) {
  return request({
    url: prefixURL + 'replay/' + id,
    method: 'delete'
  })
}
