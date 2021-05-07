import React from 'react'
import { Modal, Input, Form, message } from 'antd'
import { isAuthenticated } from '../../utils/Session'
import {queryPass,updatePass} from './service'

@Form.create() 
class EditPasswordModal extends React.Component {
    handleCancel = () => {
        this.props.form.resetFields()
        this.props.toggleVisible(false)
    }
    handleSubmit=()=>{
        const username=isAuthenticated();
        this.props.form.validateFields((err, formData) => {
            if (!err) {
                queryPass({'username':username},(data)=>{
                    console.log(data)
                    if(formData.oldPassword===data.password){
                        data.password=formData.password
                        updatePass(data)
                    }else{
                        message.error("密码错误！")
                    }
                    this.props.toggleVisible(false)
                    this.props.form.resetFields()
                })
            }
        })
    }

    render() {
        const { visible } = this.props
        const { getFieldDecorator, getFieldValue } = this.props.form

        const formItemLayout = {
            labelCol: { span: 6 },
            wrapperCol: { span: 14 },
        }
        return (
            <Modal
                onCancel={this.handleCancel}
                onOk={this.handleSubmit}
                visible={visible}
                centered
                title="修改密码">
                <Form onSubmit={this.handleSubmit}>
                    <Form.Item label={'用户名'} {...formItemLayout}>
                        {getFieldDecorator('username', {
                            initialValue:isAuthenticated()
                        })(
                            <Input disabled />
                        )}
                    </Form.Item>
                    <Form.Item label={'旧密码'} {...formItemLayout}>
                        {getFieldDecorator('oldPassword', {
                            rules: [{ required: true, message: '请输入旧密码' }],
                        })(
                            <Input
                                placeholder="请输入旧密码"
                                autoComplete="new-password"
                                type={'password'} />
                        )}
                    </Form.Item>
                    <Form.Item label={'新密码'} {...formItemLayout}>
                        {getFieldDecorator('password', {
                            validateFirst: true,
                            rules: [
                                { required: true, message: '密码不能为空' },
                                { pattern: '^[^ ]+$', message: '密码不能有空格' },
                                { min: 3, message: '密码至少为3位' },
                            ]
                        })(
                            <Input
                                placeholder="请输入新密码"
                                autoComplete="new-password"
                                type={'password'} />
                        )}
                    </Form.Item>
                    <Form.Item label={'确认密码'} {...formItemLayout}>
                        {getFieldDecorator('confirmPassword', {
                            validateFirst: true,
                            rules: [
                                { required: true, message: '请确认密码' },
                                {
                                    validator: (rule, value, callback) => {
                                        if (value !== getFieldValue('password')) {
                                            callback('两次输入不一致！')
                                        }
                                        callback()
                                    }
                                },
                            ]
                        })(
                            <Input
                                onPressEnter={this.handleOk}
                                placeholder="请确认密码"
                                autoComplete="new-password"
                                type={'password'} />
                        )}
                    </Form.Item>
                </Form>
            </Modal>
        )
    }
}

export default EditPasswordModal