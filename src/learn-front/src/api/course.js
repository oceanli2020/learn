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

export function getChildrenType(parentId) {
  return request({
    url: prefixURL + 'course/type/children/' + parentId,
    method: 'get'
  })
}
export function getParentsType(id) {
  return request({
    url: prefixURL + 'course/type/parents/' + id,
    method: 'get'
  })
}
export function getCourseInfo(courseId) {
  return request({
    url: prefixURL + 'course/' + courseId,
    method: 'get'
  })
}
export function getOptions() {
  return request({
    url: prefixURL + 'course/type/options',
    method: 'get'
  })
}

export function saveCourse(name, courseTypeId) {
  const data = {
    name,
    courseTypeId
  }
  return request({
    url: prefixURL + 'course/save',
    method: 'post',
    data
  })
}
export function getChapterList(courseId) {
  return request({
    url: prefixURL + 'chapter/' + courseId,
    method: 'get'
  })
}

export function getCourseList() {
  return request({
    url: prefixURL + 'course/list',
    method: 'get'
  })
}
