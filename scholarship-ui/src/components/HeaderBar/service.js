import { message } from "antd"
import { get,post } from "../../utils/request"
import { user} from "../../config/api"

export function queryPass(params,callback){
  get(user.getUserByUserName,params).then((response) => {
    if (response.code == '200') {
      callback(response.data)
    } else {
      if (response.message) {
        message.error(response.message)
      } else {
        message.error('密码错误')
      }
    }
  })
}

export function updatePass(params){
    post(user.updatePass,params).then((response) => {
      if (response.code == '200') {
        message.info("密码修改成功！")
    } else {
        if (response.message) {
          message.error(response.message)
        } else {
          message.error('密码修改失败！')
        }
      }
    })
  }