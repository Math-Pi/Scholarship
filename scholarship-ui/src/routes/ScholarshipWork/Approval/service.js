import { message } from "antd"
import { get,post, insert } from "../../../utils/request"
import { approval } from "../../../config/api"

export function query(params,callback){
  post(approval.query,params).then((response) => {
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
  insert(approval.create,params, {}, { headers: { permission: permission }}).then((response) => {
    if (response.code == '200') {
      callback(response.data)
      message.info('审批成功！')
    } else {
      if (response.message) {
        message.error(response.message)
      } else {
        message.error('审批失败！')
      }
    }
  })
}
