import React from 'react'
import CustomMenu from "../CustomMenu/index";
import menu from '../../assets/img/sign.png';

const menus = [
  {
    title: '首页',
    icon: 'home',
    key: '/home'
  },
  {
    title: '奖学金信息管理',
    icon: 'bars',
    key: '/home/scholarshipInfo'
  },
  {
    title: '奖学金信息发布管理',
    icon: 'edit',
    key: '/home/scholarshipPublish'
  },
  {
    title: '评奖工作管理',
    icon: 'desktop',
    key: '/home/scholarshipWork',
    subs: [
      {key: '/home/scholarshipWork/apply', title: '奖学金申请', icon: ''},
      {key: '/home/scholarshipWork/approval', title: '奖学金审核', icon: ''},
    ]
  },
  {
    title: '获奖名单管理',
    icon: 'message',
    key: '/home/scholarshipList'
  },
  {
    title: '基本信息管理',
    icon: 'bulb',
    key: '/home/base',
    subs: [
      {key: '/home/base/student', title: '学生信息', icon: ''},
      {key: '/home/base/class', title: '班级信息', icon: ''},
      {key: '/home/base/profession', title: '专业信息', icon: ''},
      {key: '/home/base/department', title: '学院信息', icon: ''},
    ]
  },
  {
    title: '系统管理',
    icon: 'setting',
    key: '/home/system',
    subs: [
      {key: '/home/system/rule', title: '代码规则', icon: ''},
      {key: '/home/system/code', title: '代码库', icon: ''},
    ]
  },
  {
    title: '用户管理',
    icon: 'user',
    key: '/home/user',
  }
]

class SiderNav extends React.Component {
  render() {

    return (
      <div style={{height: '100vh',overflowY:'scroll'}}>
        <img src={menu} width={200} height={70}/>
        <CustomMenu menus={menus}/>
      </div>
    )
  }
}


export default SiderNav