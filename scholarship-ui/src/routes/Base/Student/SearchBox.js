import React from 'react';
import {Form, Input, Button,Row, Col,message, Select} from 'antd'
import { queryDept,queryPro,queryClass,queryLib } from './service'

@Form.create()
export default class SearchBox extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            gradeList:[],        //年级列表     
            classList:[],        //班级列表
            proList:[],        //专业列表   
            deptList:[],        //院系列表   
        }
    }

    //搜索
    onSearch = () => {
        this.props.form.validateFields((err, values) => {
            if (!err) {
                this.props.onSearch({ ...values })
            }
        })
    }

    //重置函数
    onReset = () => {
        this.props.form.resetFields()
        this.onSearch()
        message.success('重置成功')
    }
    handleClick=(dmk)=>{
        const {gradeList,classList,proList,deptList}=this.state
        switch(dmk){
            case "NJ":
                gradeList.length===0&& queryLib(dmk,(data)=>{
                    this.setState({
                        gradeList: data
                    })
                })
                break
            case "BJ":
                classList.length===0&&queryClass("",(data)=>{
                    this.setState({
                        classList: data
                    })
                })
                break
            case "ZY":
                proList.length===0&&queryPro("",(data)=>{
                    this.setState({
                        proList: data
                    })
                })
                break
            case "XY":
                deptList.length===0&& queryDept((data)=>{
                    this.setState({
                        deptList: data
                    })
                })
                break
            default:

        }
    }

    render() {
        const { getFieldDecorator } = this.props.form
        return (
            <div>
                {this.props.visible&&
                <Form layout='inline' style={{ marginBottom: 16}}>
                    <Row style={{marginLeft:100,marginTop:-45}}>
                        <Col span={6}>
                            <Form.Item label="学号">
                                {getFieldDecorator('xsbh')(
                                    <Input
                                        autoComplete='off'  //禁止输入框自动填充
                                        onPressEnter={this.onSearch}
                                        style={{ width: 150 }}
                                        placeholder="学号"
                                    />
                                )}
                            </Form.Item>
                        </Col>
                        <Col span={5} style={{marginLeft:-100}}>
                            <Form.Item label="年级">
                                {getFieldDecorator('nj')(
                                    <Select
                                        onFocus={()=>this.handleClick("NJ")}
                                        style={{ width: 100 }}
                                        placeholder="请选择"
                                        allowClear={true}
                                    >{this.state.gradeList.map((val)=>(
                                        <Select.Option key={val.dmk} value={val.dmv}>{val.dmv}</Select.Option>
                                    ))}
                                    </Select>
                                )}
                            </Form.Item>
                        </Col>
                        <Col span={5} style={{marginLeft:-90}}>
                            <Form.Item label="班级">
                                {getFieldDecorator('bjbh')(
                                    <Select
                                        onFocus={()=>this.handleClick("BJ")}
                                        style={{ width: 100 }}
                                        placeholder="请选择"
                                        allowClear={true}
                                    >{this.state.classList.map((val)=>(
                                        <Select.Option key={val.bjbh} value={val.bjbh}>{val.bjbh}</Select.Option>
                                    ))}
                                    </Select>
                                )}
                            </Form.Item>
                        </Col>
                        <Col span={5} style={{marginLeft:-100}}>
                            <Form.Item label="专业">
                                {getFieldDecorator('zybh')(
                                    <Select
                                        onFocus={()=>this.handleClick("ZY")}
                                        style={{ width: 150 }}
                                        placeholder="请选择"
                                        allowClear={true}
                                    >{this.state.proList.map((val)=>(
                                        <Select.Option key={val.zybh} value={val.zybh}>{val.zymc}</Select.Option>
                                    ))}
                                    </Select>
                                )}
                            </Form.Item>
                        </Col>
                        <Col span={6} style={{marginLeft:-40}}>
                            <Form.Item label="院系">
                                {getFieldDecorator('xybh')(
                                    <Select
                                        onFocus={()=>this.handleClick("XY")}
                                        style={{ width: 200 }}
                                        placeholder="请选择"
                                        allowClear={true}
                                    >{this.state.deptList.map((val)=>(
                                        <Select.Option key={val.xybh} value={val.xybh}>{val.xymc}</Select.Option>
                                    ))}
                                    </Select>
                                )}
                            </Form.Item>
                        </Col>
                        <Col span={4} style={{marginLeft:-20}}>
                            <Form.Item style={{ marginRight: 0, width: '100%' }} wrapperCol={{ span: 24 }}>
                                <div style={{ textAlign: 'right' }}>
                                    <Button type="primary" icon='search' onClick={this.onSearch}>搜索</Button>&emsp;
                                    <Button icon="reload" onClick={this.onReset}>重置</Button>
                                </div>
                            </Form.Item>
                        </Col>
                    </Row>
                </Form>}
            </div>
        );
    }
}
