import React from 'react';
import {Form, Input, Button,Row, Col,message} from 'antd'

@Form.create()
export default class SearchBox extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
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

    render() {
        const { getFieldDecorator } = this.props.form
        return (
            <div>
                <Form layout='inline' style={{ marginBottom: 16 }}>
                    <Row style={{marginLeft:100,marginTop:-45}}>
                        <Col span={6}>
                            <Form.Item label="用户名">
                                {getFieldDecorator('username')(
                                    <Input
                                        autoComplete='off'  //禁止输入框自动填充
                                        onPressEnter={this.onSearch}
                                        style={{ width: 200 }}
                                        placeholder="用户名"
                                    />
                                )}
                            </Form.Item>
                        </Col>
                        <Col span={4}>
                            <Form.Item style={{ marginRight: 0, width: '100%' }} wrapperCol={{ span: 24 }}>
                                <div style={{ textAlign: 'right' }}>
                                    <Button type="primary" icon='search' onClick={this.onSearch}>搜索</Button>&emsp;
                                    <Button icon="reload" onClick={this.onReset}>重置</Button>
                                </div>
                            </Form.Item>
                        </Col>
                    </Row>
                </Form>
            </div>
        );
    }
}
