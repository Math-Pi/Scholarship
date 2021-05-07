import React from 'react'
import { Modal, Form, Upload, Icon, message, Input, Radio, DatePicker, Alert,RadioGroup } from 'antd'

@Form.create() 
class EditInfoModal extends React.Component {
    state = {
        uploading: false,
        visible:false
    }
    /**
     * 关闭模态框
     */
    handleCancel = () => {
        this.props.form.resetFields()
        this.props.toggleVisible(false)
    }
    /**
     * 模态框的确定按钮
     */
    handleOk = () => {
        this.props.form.validateFieldsAndScroll((err, values) => {
            if (!err) {
                this.onUpdate(values)
            }
        });
    }

    render() {
        
        const { uploading } = this.state
        const { visible } = this.props
        const { getFieldDecorator, getFieldValue } = this.props.form
        const formItemLayout = {
            labelCol: { span: 6 },
            wrapperCol: { span: 14 },
        }
        return (
            <Modal
                onCancel={this.handleCancel}
                onOk={this.handleOk}
                visible={visible}
                centered
                title="编辑个人信息">
                <div style={{ height: '60vh', overflow: 'auto' }}>
                    <Form>
                        <Form.Item label={'头像'} {...formItemLayout}>
                            {getFieldDecorator('avatar', {
                                rules: [{ required: true, message: '请上传用户头像' }],
                                getValueFromEvent: this._normFile,     //将上传的结果作为表单项的值（用normalize报错了，所以用的这个属性）
                            })(
                                <Upload  style={styles.avatarUploader}>
                                    {/* {avatar ? <img src={avatar} alt="avatar" style={styles.avatar} /> : <Icon style={styles.icon} type={uploading ? 'loading' : 'plus'} />} */}
                                </Upload>
                            )}
                        </Form.Item>
                        <Form.Item label={'用户名'} {...formItemLayout}>
                            {getFieldDecorator('username', {
                                validateFirst: true,
                                rules: [
                                    { required: true, message: '用户名不能为空' },
                                    { pattern: /^[^\s']+$/, message: '不能输入特殊字符' },
                                    { min: 3, message: '用户名至少为3位' }
                                ]
                            })(
                                <Input placeholder="请输入用户名" />
                            )}
                        </Form.Item>
                        <Form.Item label={'出生年月日'} {...formItemLayout}>
                            {getFieldDecorator('birth', {
                                // rules: [{ required: true, message: '请选择出生年月日' }],
                            })(
                                <DatePicker />
                            )}
                        </Form.Item>
                        <Form.Item label={'电话'} {...formItemLayout}>
                            {getFieldDecorator('phone', {
                                // rules: [{ required: true, message: '请输入电话号码' }, { pattern: /^[0-9]*$/, message: '请输入正确的电话号码' }],
                            })(
                                <Input placeholder="请输入电话号码" />
                            )}
                        </Form.Item>
                        <Form.Item label={'所在地'} {...formItemLayout}>
                            {getFieldDecorator('location', {
                                validateFirst: true,
                                // rules: [{ required: true, message: '请输入目前所在地' }],
                            })(
                                <Input placeholder="请输入目前所在地" />
                            )}
                        </Form.Item>
                        <Form.Item label={'性别'} {...formItemLayout}>
                            {getFieldDecorator('gender', {
                                initialValue: '男',
                                rules: [{ required: true, message: '请选择性别' }],
                            })(
                                <Radio.Group>
                                    <Radio value={'男'}>男</Radio>
                                    <Radio value={'女'}>女</Radio>
                                </Radio.Group>
                            )}
                        </Form.Item>
                        <Form.Item>
                            <Alert message={"注：此信息仅为项目模拟数据，无其他用途"} type="info" />
                        </Form.Item>
                    </Form>
                </div>
            </Modal>
        )
    }
}

const styles = {
    avatarUploader: {
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        width: 150,
        height: 150,
        backgroundColor: '#fff'
    },
    icon: {
        fontSize: 28,
        color: '#999'
    },
    avatar: {
        maxWidth: '100%',
        maxHeight: '100%',
    },
}


export default EditInfoModal