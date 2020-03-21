import request from '@/utils/request'
import { prefixURL } from '@/utils/global'

export function getCourse(current, size) {
  const data = {
    current,
    size
  }
  return request({
    url: prefixURL + 'course/page',
    method: 'post',
    data
  })
}

export function getCourseType(parentId) {
  return request({
    url: prefixURL + 'course/type/' + parentId,
    method: 'get'
  })
}
