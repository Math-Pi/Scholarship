import React from 'react';
import {Form, Input, Button,Row, Col,message, Select} from 'antd'
import {  } from './service'

@Form.create()
export default class SearchBox extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            spzt:[{dmk:0,dmv:"暂存"},{dmk:1,dmv:"已提交"},{dmk:2,dmv:"审批中"},{dmk:3,dmv:"审批通过"},{dmk:4,dmv:"审批不通过"},{dmk:5,dmv:"驳回"}]
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
        const {spzt}=this.state
        //只查询一次
        // {(source.length===0||level.length===0) && queryLib(dmk,(data)=>{
        //     if(dmk==="JLJJB"){
        //         this.setState({
        //             level: data
        //         })
        //     }
        //     if(dmk==="ZJLY"){
        //         this.setState({
        //             source: data
        //         })
        //     }
        // })}
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
                        <Col span={6}>
                            <Form.Item label="审批状态">
                                {getFieldDecorator('spzt')(
                                    <Select
                                        onFocus={()=>this.handleClick("JLJJB")}
                                        style={{ width: 100 }}
                                        placeholder="请选择"
                                    >{this.state.spzt.map((val)=>(
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
