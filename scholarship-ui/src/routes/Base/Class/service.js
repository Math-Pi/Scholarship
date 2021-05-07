import { message } from "antd"
import { post,get, remove, insert } from "../../../utils/request"
import { common, _class } from "../../../config/api"

export function query(params,callback){
  post(_class.query,params).then((response) => {
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
  insert(_class.create,params, {}, { headers: { permission: permission }}).then((response) => {
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
  post(_class.update,params, {}, { headers: { permission: permission }}).then((response) => {
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
  remove(_class.delete,params, {}, { headers: { permission: permission }}).then((response) => {
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
export function queryPro(params,callback){
  get(common.queryPro,params).then((response) => {
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
export function queryLib(params,callback){
  get(common.queryLib+params).then((response) => {
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