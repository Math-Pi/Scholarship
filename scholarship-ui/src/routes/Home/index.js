import React from 'react'
import {Carousel} from 'antd'
import './style.css'


class Home extends React.Component {
  render() {
    return (
      <div style={styles.bg} className='home'>
        <Carousel arrows effect='fade' className='size'>
        <img src={require('../../assets/img/1.jpg')} />
        <img src={require('../../assets/img/2.jpg')} />        
        <img src={require('../../assets/img/3.jpg')} />        
        <img src={require('../../assets/img/4.jpg')} />
        <img src={require('../../assets/img/5.jpg')} />
        </Carousel>
      </div>
    )
  }
}

const styles = {
  bg:{
    position:'absolute',
    top:0,
    left:0,
    width:'100%',
    height:'calc(100vh - 64px)'
  }
}

export default Home