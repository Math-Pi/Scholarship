import React from 'react';
import {Form, Input, Button,Row, Col,message, Select} from 'antd'
import { queryLib } from './service'

@Form.create()
export default class SearchBox extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            sqxnList:[],        //申请学年列表     
            jdbzList:[],        //绩点标准列表
            cjbzList:[],        //成绩标准列表   
        }
    }

    //搜索
    // 查询
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
        const {sqxnList,jdbzList,cjbzList}=this.state
        //只查询一次
        {(sqxnList.length===0||jdbzList.length===0||cjbzList.length===0) && queryLib(dmk,(data)=>{
            if(dmk==="SQXN"){
                this.setState({
                    sqxnList: data
                })
            }
            if(dmk==="JDBZ"){
                this.setState({
                    jdbzList: data
                })
            }
            if(dmk==="CJBZ"){
                this.setState({
                    cjbzList: data
                })
            }
        })}
    }

    render() {
        const { getFieldDecorator } = this.props.form
        return (
            <div>
                {this.props.visible&&
                <Form layout='inline' style={{ marginBottom: 16}}>
                    <Row style={{marginLeft:100,marginTop:-45}}>
                        <Col span={6}>
                            <Form.Item label="奖学金名称">
                                {getFieldDecorator('jxjmc')(
                                    <Input
                                        autoComplete='off'  //禁止输入框自动填充
                                        onPressEnter={this.onSearch}
                                        style={{ width: 200 }}
                                        placeholder="奖学金名称"
                                    />
                                )}
                            </Form.Item>
                        </Col>
                        <Col span={5} style={{marginLeft:-10}}>
                            <Form.Item label="申请学年">
                                {getFieldDecorator('sqxn')(
                                    <Select
                                        onFocus={()=>this.handleClick("SQXN")}
                                        style={{ width: 100 }}
                                        placeholder="请选择"
                                    >{this.state.sqxnList.map((val)=>(
                                        <Select.Option key={val.dmk} value={val.dmv}>{val.dmv}</Select.Option>
                                    ))}
                                    </Select>
                                )}
                            </Form.Item>
                        </Col>
                        <Col span={5} style={{marginLeft:-70}}>
                            <Form.Item label="绩点标准">
                                {getFieldDecorator('jdbz')(
                                    <Select
                                        onFocus={()=>this.handleClick("JDBZ")}
                                        style={{ width: 150 }}
                                        placeholder="请选择"
                                    >{this.state.jdbzList.map((val)=>(
                                        <Select.Option key={val.dmk} value={val.dmv}>{val.dmv}</Select.Option>
                                    ))}
                                    </Select>
                                )}
                            </Form.Item>
                        </Col>
                        <Col span={5} style={{marginLeft:-10}}>
                            <Form.Item label="成绩标准">
                                {getFieldDecorator('cjbz')(
                                    <Select
                                        onFocus={()=>this.handleClick("CJBZ")}
                                        style={{ width: 150 }}
                                        placeholder="请选择"
                                    >{this.state.cjbzList.map((val)=>(
                                        <Select.Option key={val.dmk} value={val.dmv}>{val.dmv}</Select.Option>
                                    ))}
                                    </Select>
                                )}
                            </Form.Item>
                        </Col>
                        <Col span={5} style={{marginLeft:-20}}>
                            <Form.Item label="是否发布">
                                {getFieldDecorator('sffb')(
                                    <Select
                                        style={{ width: 100 }}
                                        placeholder="请选择"
                                    >{[{dmk:0,dmv:"否"},{dmk:1,dmv:"是"}].map((val)=>(
                                        <Select.Option key={val.dmk} value={val.dmk}>{val.dmv}</Select.Option>
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
