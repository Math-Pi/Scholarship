import React, {Component} from 'react';
import PrivateRoute from './components/PrivateRoute'
import {Route,Switch} from 'react-router-dom'
import Login from './routes/Login'
// import Login from './routes/Login2'
import Index from './routes/Index'
import './App.css'
import './assets/font/iconfont.css'


class App extends Component {
  render() {
    return (
      <Switch>
        <Route path='/login' component={Login}/>
        <PrivateRoute path='/' component={Index}/>  {/* 要经过认证才能访问 */}
      </Switch>
    )
  }
}

export default App;
