import React from 'react'
import { withRouter, Switch, Redirect } from 'react-router-dom'
// import Library from '../../routes/System/Library'
import LoadableComponent from '../../utils/LoadableComponent'
import PrivateRoute from '../PrivateRoute'

const Home = LoadableComponent(()=>import('../../routes/Home/index'))  //参数一定要是函数，否则不会懒加载，只会代码拆分

//奖学金信息管理
const ScholarshipInfo = LoadableComponent(()=>import('../../routes/ScholarshipInfo/index'))

//奖学金信息发布管理
const ScholarshipPublish = LoadableComponent(()=>import('../../routes/ScholarshipPublish/index'))

//评奖工作管理
const Apply = LoadableComponent(()=>import('../../routes/ScholarshipWork/Apply/index'))       //申请
const Approval = LoadableComponent(()=>import('../../routes/ScholarshipWork/Approval/index')) //审批

//获奖名单管理
const ScholarshipList = LoadableComponent(()=>import('../../routes/ScholarshipList/index'))

//基本信息管理
const Student = LoadableComponent(()=>import('../../routes/Base/Student/index'))          //学生信息
const Class = LoadableComponent(()=>import('../../routes/Base/Class/index'))              //班级信息
const Profession = LoadableComponent(()=>import('../../routes/Base/Profession/index'))    //专业信息
const Department = LoadableComponent(()=>import('../../routes/Base/Department/index'))    //学院信息

//系统管理
const Rule = LoadableComponent(()=>import('../../routes/System/Rule/index'))            //代码规则
const Library = LoadableComponent(()=>import('../../routes/System/Library/index'))      //代码库

//用户管理
const User = LoadableComponent(()=>import('../../routes/User/index'))  

@withRouter
class ContentMain extends React.Component {
  render () {
    return (
      <div style={{padding: 16, position: 'relative'}}>
        <Switch>
          <PrivateRoute exact path='/home' component={Home}/>

          <PrivateRoute exact path='/home/scholarshipInfo' component={ScholarshipInfo}/>

          <PrivateRoute exact path='/home/scholarshipPublish' component={ScholarshipPublish}/>

          <PrivateRoute exact path='/home/scholarshipWork/apply' component={Apply}/>
          <PrivateRoute exact path='/home/scholarshipWork/approval' component={Approval}/>

          <PrivateRoute exact path='/home/scholarshipList' component={ScholarshipList}/>

          <PrivateRoute exact path='/home/base/student' component={Student}/>

          <PrivateRoute exact path='/home/base/class' component={Class}/>

          <PrivateRoute exact path='/home/base/profession' component={Profession}/>

          <PrivateRoute exact path='/home/base/department' component={Department}/>
          
          <PrivateRoute exact path='/home/system/rule' component={Rule}/>
          <PrivateRoute exact path='/home/system/code' component={Library}/>

          <PrivateRoute exact path='/home/user' component={User}/>

          <Redirect exact from='/' to='/home'/>
        </Switch>
      </div>
    )
  }
}

export default ContentMain