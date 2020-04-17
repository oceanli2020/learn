import request from '@/utils/request'
import { prefixURL } from '@/utils/global'

export function uploadAvatar(data) {
  return request({
    url: prefixURL + 'upload/avatar',
    method: 'post',
    headers: { 'Content-Type': 'multipart/form-data' },
    data
  })
}
export function uploadVideo(data) {
  return request({
    url: prefixURL + 'upload/video',
    method: 'post',
    headers: { 'Content-Type': 'multipart/form-data' },
    data
  })
}
