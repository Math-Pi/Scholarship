/**
 * 搜索区域--展开/收起
 * @Author: qizc
 * @Date:   2018-09-14 16:38:47
 * @Last Modified by:   qizc
 * @Last Modified time: 2018-09-27 11:43:13
 */
'use strict';

import React from 'react';
import PropTypes from 'prop-types';
import { Icon, Row, Col } from 'antd';
import classNames from 'classnames';
import styles from './index.css';

export default class SearchBox extends React.Component {
    static propTypes = {
        hideSwitch: PropTypes.bool,
        defaultOpen: PropTypes.bool,
        openChange: PropTypes.func
    };
    static defaultProps = {
        hideSwitch: false,
        defaultOpen: false, //默认状态
        openChange: Function.prototype
    };

    constructor(props) {
        super(props);
        const { defaultOpen } = props;
        this.state = {
            open: props.defaultOpen,
            title: defaultOpen ? '收起' : '展开'
        };
    }

    // 开关
    setOpen = () => {
        this.setState(
            {
                open: !this.state.open,
                title: !this.state.open ? '收起' : '展开'
            },
            () => {
                this.props.openChange(!this.state.open);
            }
        );
    };

    render() {
        const {
            className,
            hideSwitch,
            TopItem,
            Btn,
            title,
            children
        } = this.props;

        return (
            <Form layout='inline' style={{ marginBottom: 16 }}>
                <Row>
                    <Col span={6}>
                        <Form.Item label="用户名">
                            {getFieldDecorator('username')(
                                <Input
                                    onPressEnter={this.onSearch}
                                    style={{ width: 200 }}
                                    placeholder="用户名"
                                />
                            )}
                        </Form.Item>
                    </Col>
                    <Col span={7}>
                        <Form.Item label="注册开始时间">
                            {getFieldDecorator('startTime')(
                                <DatePicker style={{ width: 200 }} showTime />
                            )}
                        </Form.Item>
                    </Col>
                    <Col span={7}>
                        <Form.Item label="注册截止时间">
                            {getFieldDecorator('endTime')(
                                <DatePicker style={{ width: 200 }} showTime />
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
        );
    }
}
