import request from '@/utils/request'
import { prefixURL } from '@/utils/global'

export function getCourse(data) {
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

export function getChapter(data) {
  return request({
    url: prefixURL + 'chapter/page',
    method: 'post',
    data
  })
}

export function updateCourse(data) {
  return request({
    url: prefixURL + 'course',
    method: 'put',
    data
  })
}

export function updateChapter(data) {
  return request({
    url: prefixURL + 'chapter',
    method: 'put',
    data
  })
}

export function deleteCourse(id) {
  return request({
    url: prefixURL + 'course',
    method: 'delete',
    params: { id }
  })
}
export function deleteChapter(id) {
  return request({
    url: prefixURL + 'chapter',
    method: 'delete',
    params: { id }
  })
}

export function getDirectory(id) {
  return request({
    url: prefixURL + 'course/directory/' + id,
    method: 'get'
  })
}
