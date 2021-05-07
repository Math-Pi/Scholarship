import { message } from "antd"
import { post,get, remove, insert } from "../../../utils/request"
import { profession,common } from "../../../config/api"

export function query(params,callback){
  post(profession.query,params).then((response) => {
    if (response.code == '200') {
      callback(response.data)
    } else {
      if (response.message) {
        message.error(response.message)
      } else {
        message.error('查询失败！')
      }
    }
  })
}
export function create(params,permission,callback){
  insert(profession.create,params, {}, { headers: { permission: permission }}).then((response) => {
    if (response.code == '200') {
      callback(response.data)
      message.info('添加成功！')
    } else {
      if (response.message) {
        message.error(response.message)
      } else {
        message.error('添加失败！')
      }
    }
  })
}
export function update(params,permission,callback){
  post(profession.update,params, {}, { headers: { permission: permission }}).then((response) => {
    if (response.code == '200') {
      callback(response.data)
      message.info('修改成功！')
    } else {
      if (response.message) {
        message.error(response.message)
      } else {
        message.error('修改失败！')
      }
    }
  })
}
export function del(params,permission,callback){
  remove(profession.delete,params, {}, { headers: { permission: permission }}).then((response) => {
    if (response.code == '200') {
      callback(response.data)
      message.info('删除成功！')
    } else {
      if (response.message) {
        message.error(response.message)
      } else {
        message.error('删除失败！')
      }
    }
  })
}
export function queryDept(callback){
  get(common.queryDept,).then((response) => {
    if (response.code == '200') {
      callback(response.data)
    } else {
      if (response.message) {
        message.error(response.message)
      } else {
        message.error('查询失败！')
      }
    }
  })
}