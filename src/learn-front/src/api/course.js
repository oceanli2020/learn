import request from '@/utils/request'
import { prefixURL } from '@/utils/global'

export function getCourse(current, size, sort, query) {
  const data = {
    current,
    size,
    sort,
    query
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

export function getCourseInfo(courseId) {
  return request({
    url: prefixURL + 'course/' + courseId,
    method: 'get'
  })
}
