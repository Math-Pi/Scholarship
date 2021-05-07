import React from 'react'
import { Icon, Badge, Dropdown, Menu, Modal } from 'antd'
import screenfull from 'screenfull'
import { inject, observer } from 'mobx-react'
import { Link, withRouter } from 'react-router-dom'
import { isAuthenticated } from '../../utils/Session'
import EditInfoModal  from './EditInfoModal'
import EditPasswordModal  from './EditPasswordModal'
//withRouter一定要写在前面，不然路由变化不会反映到props中去
@withRouter @inject('appStore') @observer
class HeaderBar extends React.Component {
  state = {
    isFullscreen: false,    //控制页面全屏
    color: '#13C2C2',
    infoVisible: false,     //控制修改用户信息的模态框
    passwordVisible: false,   //控制修改密码的模态框
    icon: 'arrows-alt',
    count: 100,
    visible: false,
    avatar: require('./img/qq.jpg')
  }

  componentDidMount () {
    screenfull.onchange(() => {
      this.setState({
        icon: screenfull.isFullscreen ? 'shrink' : 'arrows-alt'
      })
    })
  }

  componentWillUnmount () {
    screenfull.off('change')
  }

  /**
     * 展开/关闭修改信息模态框
     */
  toggleInfoVisible = (visible) => {
    this.setState({
        infoVisible: visible
    })
  }
  /**
     * 展开/关闭修改密码模态框
     */
  togglePasswordVisible = (visible) => {
    this.setState({
        passwordVisible: visible
    })
  }
  /**
     * 切换全屏
     */
  toggleFullscreen = () => {
    if (screenfull.enabled) {
      screenfull.toggle()
      
    }
  }
  toggle = () => {
    this.props.onToggle()
  }
  logout = () => {
    this.props.appStore.toggleLogin(false)
    this.props.history.push(this.props.location.pathname)
  }

  render () {
    const {icon, count, visible, avatar} = this.state
    const {appStore, collapsed, location} = this.props
    const notLogin = (
      <div>
        <Link to={{pathname: '/login', state: {from: location}}} style={{color: 'rgba(0, 0, 0, 0.65)'}}>登录</Link>&nbsp;
        <img src={require('../../assets/img/defaultUser.jpg')} alt=""/>
      </div>
    )
    const menu = (
      <Menu className='menu'>
        <Menu.ItemGroup title='用户中心' className='menu-group'>
          <Menu.Item>你好 - {isAuthenticated()}</Menu.Item>
          <Menu.Item key={1} onClick={() => this.toggleInfoVisible(true)}><Icon type="user" />编辑个人信息</Menu.Item>
          <Menu.Item key={77} onClick={() => this.togglePasswordVisible(true)}><Icon type="edit" />修改密码</Menu.Item>
          <Menu.Item><span onClick={this.logout}>退出登录</span></Menu.Item>
        </Menu.ItemGroup>
        <Menu.ItemGroup title='设置中心' className='menu-group'>
          <Menu.Item key={3} onClick={this.toggleFullscreen}><Icon type={isFullscreen ? 'fullscreen-exit' : 'fullscreen'} />切换全屏</Menu.Item>
        </Menu.ItemGroup>
      </Menu>
    )
    const login = (
      <Dropdown overlay={menu}>
        <img onClick={() => this.setState({visible: true})} src={avatar} alt=""/>
      </Dropdown>
    )
    const { isFullscreen, color, infoVisible, passwordVisible } = this.state
    return (
      <div id='headerbar'>
        <Icon
          type={collapsed ? 'menu-unfold' : 'menu-fold'}
          className='trigger'
          onClick={this.toggle}/>
        <div style={{lineHeight: '64px', float: 'right'}}>
          <ul className='header-ul'>
            <li>
              {appStore.isLogin ? login : notLogin}
            </li>
          </ul>
        </div>
        <Modal
          footer={null} closable={false}
          visible={visible}
          wrapClassName="vertical-center-modal"
          onCancel={() => this.setState({visible: false})}>
          <img src={avatar} alt="" width='100%'/>
        </Modal>
        <EditInfoModal toggleVisible={this.toggleInfoVisible} visible={infoVisible} />
        <EditPasswordModal toggleVisible={this.togglePasswordVisible} visible={passwordVisible} />
      </div>
    )
  }
}

export default HeaderBar