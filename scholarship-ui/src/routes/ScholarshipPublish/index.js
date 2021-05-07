import React from 'react'
import { Table, Switch, Button, Modal,Form,Input,Select,InputNumber,DatePicker,Row,Col} from 'antd'
import moment from "moment";
import authority from "./authority"
import { query,create,update,del,queryType,queryLib,querySffdj} from './service'
import CustomBreadcrumb from '../../components/CustomBreadcrumb/index'
import {pageNo,pageSize, pageSizeOptions, total} from '../../config/common'
import SearchBox from './SearchBox'

@Form.create() 
export default class ScholarshipPublish extends React.Component {
  constructor(props){
    super(props);
    this.state={
      addFlag:false,      //模拟框是否显示标志
      visible:false,
      isLevel:false,      //等级是否显示标志
      title:"",           //模拟框标题
      typeList:[],        //奖学金类型列表
      levelList:[],       //奖学金等级列表
      sqxnList:[],        //申请学年列表
      jdbzList:[],        //绩点标准列表
      cjbzList:[],        //成绩标准列表
      selectRow:{},       //修改回显数据
      formData:[],        //列表数据
      endValue:null,      //结束时间
      startValue:null     //开始时间
    }
  }

  // 查询参数
  params = {
    pageNo: pageNo,
    pageSize: pageSize,
    total:total
  }

  componentDidMount(){
    this.indexQuery();
  }

  //表单查询
  indexQuery=(params)=>{
    let _params = {
      ...this.params,
      ...params
    }
    if(_params.sorter&&_params.sorter.field&&_params.sorter.order){
      _params["sorter"]={
        field: _params.sorter.field,  //排序字段
        order:_params.sorter.order.substring(0,_params.sorter.order.length-3),  //排序方式
      }
    }
    query(_params,(data)=>{
      this.params.total = data.length>0?data[0].total:0   
      this.setState({
        formData: data
      })
    })
  }

  //搜索
  onSearch=params=>{
    this.params.pageNo = 1
    this.params = { ...this.params, param: params }
    this.indexQuery()
  }

  //提交表单
  handleSubmit=()=>{
    const {selectRow} = this.state
    this.props.form.validateFields((err, formData) => {
      if (!err) {
        formData.sffb=formData.sffb?1:0 //1为true，0为false
        if(Object.keys(selectRow).length==0){
          create(formData, authority.create, () => {
            this.indexQuery()
            this.setState({
              addFlag:false,
            })
          })
        }else{
          formData.fbid=selectRow.fbid  //设置修改主键
          update(formData, authority.update, () => {
            this.indexQuery()
            this.setState({
              selectRow:{},
              addFlag:false,
            })
          })
        }
        this.props.form.resetFields() //清空输入框
      }
    })
  }

  //取消
  handleCancel = () => {
    this.props.form.resetFields() //清空输入框
    this.setState({
      addFlag: false,
      selectRow:{}
    })
  }
  
  //显示新增模拟框
  showAddModal = () => {
    this.setState({addFlag:!this.state.addFlag,title:"新增"});
    //查询奖学金类型列表
    queryType((data)=>{
      this.setState({
        typeList: data
      })
    })
    //查询奖学金等级列表
    queryLib("JXJDJ",(data)=>{
      this.setState({
        levelList: data
      })
    })
    //查询申请学年
    queryLib("SQXN",(data)=>{
      this.setState({
        sqxnList: data
      })
    })
    //查询绩点标准
    queryLib("JDBZ",(data)=>{
      this.setState({
        jdbzList: data
      })
    })
    //查询成绩标准
    queryLib("CJBZ",(data)=>{
      this.setState({
        cjbzList: data
      })
    })
  }
  
  //显示修改模拟框
  showUpdateModal=(record)=>{
    this.setState({
      addFlag:!this.state.addFlag,
      selectRow:record,
      title:'修改'
    })
    //查询奖学金类型列表
    queryType((data)=>{
      this.setState({
        typeList: data
      })
    })
    //查询奖学金等级列表
    queryLib("JXJDJ",(data)=>{
      this.setState({
        levelList: data
      })
    })
    //查询申请学年
    queryLib("SQXN",(data)=>{
      this.setState({
        sqxnList: data
      })
    })
    //查询绩点标准
    queryLib("JDBZ",(data)=>{
      this.setState({
        jdbzList: data
      })
    })
    //查询成绩标准
    queryLib("CJBZ",(data)=>{
      this.setState({
        cjbzList: data
      })
    })
  }
  
  //删除
  handleDelete=(record)=>{
    let _this=this;
    Modal.confirm({
      title: '提示',
      content: '确认要删除吗？',
      onOk() {
        del([record.fbid], authority.delete, () => {
          _this.indexQuery()
        })
      }
    })
  }
  //切换工作发布状态
  updateStatus = (checked, record) => {
    record.sffb=checked?1:0
    update(record, authority.update, (data) => {
      this.setState({})
    })
  }

  //限制开始日期不得高于结束日期
  disabledStartDate = (startValue) => {
    const { endValue } = this.state
    if (!startValue || !endValue) {
      return false
    }
    return startValue.valueOf() > endValue.valueOf()
  }

  //限制结束日期不得低于开始日期
  disabledEndDate = (endValue) => {
    const { startValue } = this.state
    if (!endValue || !startValue) {
      return false
    }
    return endValue.valueOf() <= startValue.valueOf()
  }

  //开始日期改变事件
  onStartChange = (value) => {
    this.onChange("startValue", value)
  }

  //结束日期改变事件
  onEndChange = (value) => {
    this.onChange("endValue", value)
  }

  //设置日期值
  onChange = (field, value) => {
    this.setState({
      [field]: value,
    })
  }

  //奖学金类型列表点击事件
  hanldeClick=(dyid)=>{
    querySffdj(dyid,(data)=>{
      this.setState({isLevel:data})
    })
  }

  // 切换页数
  pageChange = pageNo => {
    this.params.pageNo = pageNo
    this.indexQuery()
  }

  // 切换条数
  onShowSizeChange = (current, pageSize) => {
    this.params.pageNo = 1
    this.params.pageSize = pageSize
    this.indexQuery()
  }

  //是否显示高级搜索
  handleVisible=()=>{
    this.setState({
      visible:!this.state.visible
    })
  }

  //表格排序改变事件
  handleChangeTable=(pagination,filters,sorter,extra)=>{
    this.params.sorter=sorter
    this.indexQuery()
  }

  render() {
    const { getFieldDecorator } = this.props.form;
    const formItemLayout = {
      labelCol: {
        span:5,
      },
      wrapperCol: {
        span:18,
      },
    }
    //日期控件布局
    const formItemLayout2 = {
      labelCol: {
          span: 10
      },
      wrapperCol: {
          span: 14,
      }
    }
    const colSpan = {
      md: 12,
      xxl: 12
    }
    const columns = [
      {
        title: '序号',
        dataIndex: 'fbid',
        width: '10%',
        align: 'center',
        render: (text, record, index) => {
          return ((this.params.pageNo - 1) * this.params.pageSize + index + 1)
        },
      },
      {
        title: '奖学金名称',
        dataIndex: 'jxjmc',
        align: 'center',
        width: '11%',
      },
      {
        title: '申请学年',
        dataIndex: 'sqxn',
        align: 'center',
        width: '9%',
        sorter:true,
      },
      {
        title: '奖金',
        dataIndex: 'jxjje',
        align: 'center',
        width: '7%',
        sorter:true,
      },
      {
        title: '绩点标准',
        dataIndex: 'jdbz',
        align: 'center',
        width: '15%',
      },
      {
        title: '成绩标准',
        dataIndex: 'cjbz',
        align: 'center',
        width: '15%',
      },
      {
        title: '开始时间',
        dataIndex: 'kssj',
        align: 'center',
        width: '9%',
        sorter:true,
        render: (text) => moment(text).format("YYYY-MM-DD")
      },
      {
        title: '结束时间',
        dataIndex: 'jssj',
        align: 'center',
        width: '9%',
        sorter:true,
        render: (text) => moment(text).format("YYYY-MM-DD")
      },
      {
        title: '是否发布',
        dataIndex: 'sffb',
        align: 'center',
        width: '7%',
        render: (text, record) => {
          return <Switch checkedChildren="是" unCheckedChildren="否" checked={record.sffb===1} onChange={(checked) => this.updateStatus(checked, record)}
          />
        }
      },
      {
        title: '操作',
        dataIndex: 'operation',
        align: 'center',
        width: '10%',
        render: (text, record) => (
          <div>
            <a onClick={()=>this.showUpdateModal(record)}>修改</a>&nbsp;&nbsp;
            <a onClick={()=>this.handleDelete(record)}>删除</a>
          </div>
        ),
      }
    ]
    const {jxjmc,dyid,jxjdj,sqxn,jdbz,cjbz,kssj,jssj,jxjje,sffb } = this.state.selectRow;
    const {pageNo,pageSize,total}=this.params
    return (
      <div>
        <CustomBreadcrumb arr={['奖学金信息发布管理']} />
        <Button type="primary" onClick={this.showAddModal} style={{ marginBottom: 10 }}>新增</Button>
        <a onClick={this.handleVisible} style={{float:'right',marginTop:-50}}>高级搜索</a>
        <SearchBox visible={this.state.visible} onSearch={this.onSearch}/>
        <Table
          bordered
          ref="table"
          columns={columns}
          dataSource={this.state.formData}
          rowKey={row=>row.fbid}
          onChange={this.handleChangeTable}
          pagination={
            {
              showSizeChanger: true,  //显示页面列表
              showQuickJumper:true,  //是否可以快速跳转至某页
              showLessItems:true,   //是否显示较少页面内容,当它为true时,意思是当前页面 左右显示各 1 个,为false时,代表当前页面 左右各显示 2 个
              pageSize:pageSize,    //页面大小
              current:pageNo,       //当前页
              total:total,            //记录总数
              pageSizeOptions:pageSizeOptions,  //页面大小列表
              onChange: (page, pageSize) => {this.pageChange(page)},
              onShowSizeChange:(current, size)=>this.onShowSizeChange(current,size),
              showTotal: (total, range) => `每页${pageSize}条记录 , 共${total}条记录`
            }
          }
        />
        <Modal title={this.state.title} width={600} visible={this.state.addFlag} onOk={this.handleSubmit} onCancel={this.handleCancel}>
          <Form onSubmit={this.handleSubmit}>
            <Form.Item {...formItemLayout} style={{ marginBottom: 0 }} label="奖学金名称">
              {getFieldDecorator('jxjmc', {
                initialValue: jxjmc,
                rules: [
                  {
                    required: true,
                    message: '必填',
                  },
                ],
              })(<Input placeholder="请输入奖学金名称" />)}
            </Form.Item>
            <Form.Item {...formItemLayout} style={{ marginBottom: 0 }} label="奖学金类型">
              {getFieldDecorator('dyid', {
                initialValue: dyid,
                rules: [
                  {
                    required: true,
                    message: '必填',
                  },
                ],
              })(
                <Select placeholder="请选择">
                  {this.state.typeList.map((val)=>(
                    <Select.Option key={val.dyid} value={val.dyid} onClick={()=>this.hanldeClick(val.dyid)}>{val.lx}</Select.Option>
                  ))}
                </Select>)}
            </Form.Item>
            {this.state.isLevel?<Form.Item {...formItemLayout} style={{ marginBottom: 0 }} label="等级">
              {getFieldDecorator('jxjdj', {
                initialValue: jxjdj,
                rules: [
                  {
                    required: true,
                    message: '必填',
                  }
                ],
              })(
                <Select placeholder="请选择">
                  {this.state.levelList.map((val)=>(
                    <Select.Option key={val.dmk}>{val.dmv}</Select.Option>
                  ))}
                </Select>)}
            </Form.Item>:null}
            <Form.Item {...formItemLayout} style={{ marginBottom: 0 }} label="申请学年">
              {getFieldDecorator('sqxn', {
                initialValue: sqxn,
                rules: [
                  {
                    required: true,
                    message: '必填',
                  },
                ],
              })(
                <Select placeholder="请选择">
                  {this.state.sqxnList.map((val)=>(
                    <Select.Option key={val.dmk} value={val.dmv}>{val.dmv}</Select.Option>
                  ))}
                </Select>)}
            </Form.Item>
            <Form.Item {...formItemLayout} style={{ marginBottom: 0 }} label="绩点标准">
              {getFieldDecorator('jdbz', {
                initialValue: jdbz,
                rules: [
                  {
                    required: true,
                    message: '必填',
                  },
                ],
              })(
                <Select placeholder="请选择">
                  {this.state.jdbzList.map((val)=>(
                    <Select.Option key={val.dmk} value={val.dmv}>{val.dmv}</Select.Option>
                  ))}
                </Select>)}
            </Form.Item>
            <Form.Item {...formItemLayout} style={{ marginBottom: 0 }} label="成绩标准">
              {getFieldDecorator('cjbz', {
                initialValue: cjbz,
                rules: [
                  {
                    required: true,
                    message: '必填',
                  },
                ],
              })(
                <Select placeholder="请选择">
                  {this.state.cjbzList.map((val)=>(
                    <Select.Option key={val.dmk} value={val.dmv}>{val.dmv}</Select.Option>
                  ))}
                </Select>)}
            </Form.Item>
            <Row>
              <Col {...colSpan}>
                <Form.Item {...formItemLayout2} label="开始日期:" style={{ marginBottom: 0 }}>
                  {getFieldDecorator("kssj", {
                    initialValue: kssj?moment(kssj):undefined,
                    rules: [{ required: true, message: "必填" }]
                  })(
                    <DatePicker
                      disabledDate={this.disabledStartDate}
                      format="YYYY-MM-DD"
                      placeholder="开始日期"
                      onChange={this.onStartChange}
                    />
                  )}
                </Form.Item>
              </Col>
              <Col {...colSpan}>
                <Form.Item {...formItemLayout2} style={{ marginBottom: 0 }}>
                  {getFieldDecorator("jssj", {
                    initialValue: jssj?moment(jssj): undefined,
                    rules: [{ required: true, message: "必填" }]
                  })(
                    <DatePicker
                      disabledDate={this.disabledEndDate}
                      format="YYYY-MM-DD"
                      placeholder="结束日期"
                      onChange={this.onEndChange}
                    />
                    )}
                  </Form.Item>
                </Col>
              </Row>
            <Form.Item {...formItemLayout} style={{ marginBottom: 0 }} label="奖金">
            {getFieldDecorator("jxjje", {
              initialValue: jxjje,
              rules: [
                {
                  required: true,
                  message: "请输入奖金"
                }
              ]
            })(
              <InputNumber style={{ width: 150 }} placeholder="请输入奖金" min={0} />
              )}
              <span className="ant-form-text"> 元</span>
            </Form.Item>
            <Form.Item {...formItemLayout} style={{ marginBottom: 0 }} label="是否发布">
              {getFieldDecorator('sffb', {
                initialValue: !sffb?"0":sffb,
                rules: [
                  {
                    required: true,
                    message: '必填',
                  },
                ],
              })(<Switch checkedChildren="是" unCheckedChildren="否"  key={sffb===1}  defaultChecked={sffb===1} />)}
            </Form.Item>
          </Form>
        </Modal>
      </div>
    )
  }
}