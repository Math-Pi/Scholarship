import React from 'react'
import CustomBreadcrumb from '../../../components/CustomBreadcrumb/index'
import { Table, Button, Modal,Form,Input,Radio, message } from 'antd'
import {pageNo,pageSize, pageSizeOptions, total} from '../../../config/common'
import { query,create} from './service'
import authority from "./authority"
import SearchBox from './SearchBox'

@Form.create() 
export default class Approval extends React.Component {
  constructor(props){
    super(props);
    this.state={
      addFlag:false,
      visible:false,
      formData:[],          //列表数据
      selectedRowKeys:[],   //选中行
      selectedRows:[],      //选中行数据列表
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
    this.state.selectedRows.map((val)=>{
      this.props.form.validateFields((err, formData) => {
        //1为通过，0为不通过，-1为驳回
        formData.spzt=formData.spzt===1?val.spzt+1:formData.spzt===-1?5:4
        formData.sqid=val.sqid
        if (!err) {
          create(formData, authority.create, () => {
            this.indexQuery()
            this.setState({
              addFlag:false,
              selectedRowKeys:[],
              selectedRows:[],
            })
          })
          this.props.form.resetFields() //清空输入框
        }
      })
    })
  }

  //取消
  handleCancel = () => {
    this.props.form.resetFields() //清空输入框
    this.setState({
      addFlag: false,
    })
  }

  //显示审批模拟框
  showAddModal = () => {
    const {selectedRowKeys,selectedRows}=this.state
    const newSelectedRows=selectedRows.filter(val=>{return val.spzt===3 || val.spzt===4}) //3为已提交，4位审批中
    if(selectedRowKeys.length>0){
      if(newSelectedRows.length>0){
        message.warn("只能审批审批状态为“已提交”和“审批中”的申请")
      }else{
        this.setState({
          addFlag:!this.state.addFlag,
        })
      }
    }else{
      message.warn("你还没有选中数据")
    }
  }

  //复选框选中或取消事件
  onSelectChange = (selectedRowKeys,selectedRows) => {
    this.setState({ selectedRowKeys,selectedRows });
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
    
    const columns = [
      {
        title: '序号',
        dataIndex: 'sqid',
        width: '10%',
        align: 'center',
        render: (text, record, index) => {
          return ((this.params.pageNo - 1) * this.params.pageSize + index + 1)
        },
      },
      {
        title: '奖学金名称',
        dataIndex: 'jxjmc',
        width: '15%',
        align: 'center',
      },
      {
        title: '申请人',
        dataIndex: 'sqr',
        width: '10%',
        align: 'center',
      },
      {
        title: '申请材料',
        dataIndex: 'sqcl',
        align: 'center',
        width: '10%',
        render:(text,record)=>{
          return <a href={record.path}>{text}</a>
        }
      },
      {
        title: '审批状态',
        dataIndex: 'spzt',
        align: 'center',
        width: '10%',
        render:(text,record)=>{
          const approvalStatusNameObj = {
            0: "暂存",
            1: <span style={{ color: "#1890ff" }}>已提交</span>,
            2: <span style={{ color: "#529955" }}>审批中</span>,
            3: <span style={{ color: "#00ff00" }}>审批通过</span>,
            4: <span style={{ color: "#ff0000" }}>审批不通过</span>,
            5: <span style={{ color: "#e060f0" }}>驳回</span>
          }
          return approvalStatusNameObj[record.spzt];
        }
      },
      {
        title: '申请描述',
        dataIndex: 'sqms',
        align: 'center',
        width: '40%',
      },
    ]
    const { selectedRowKeys } = this.state;
    const rowSelection = {
      columnWidth: '5%',
      selectedRowKeys,
      onChange:this.onSelectChange,
    }
    const {pageNo,pageSize,total}=this.params 
    return (
      <div>
        <CustomBreadcrumb arr={['评奖工作管理','奖学金审批']} />
        <Button type="primary" onClick={this.showAddModal} style={{ marginBottom: 10 }}>审批</Button>
        <a onClick={this.handleVisible} style={{float:'right',marginTop:-50}}>高级搜索</a>
        <SearchBox visible={this.state.visible} onSearch={this.onSearch}/>
        <Table
          bordered
          columns={columns}
          dataSource={this.state.formData}
          rowKey={row=>row.sqid}
          rowSelection={rowSelection}
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
        <Modal title="审批" width={600} visible={this.state.addFlag} onOk={this.handleSubmit} onCancel={this.handleCancel}>
          <Form onSubmit={this.handleSubmit}>
            <Form.Item {...formItemLayout} style={{ marginBottom: 0 }} label="审批状态">
              {getFieldDecorator('spzt', {
                initialValue: 1,
                rules: [
                  {
                    required: true,
                    message: '必填',
                  },
                ],
              })(
                <Radio.Group onChange={this.onRadioChange}>
                  <Radio value={1}>通过</Radio>
                  <Radio value={0}>不通过</Radio>
                  <Radio value={-1}>驳回</Radio>
                </Radio.Group>)}
            </Form.Item>
            <Form.Item {...formItemLayout} id='remark' style={{ marginBottom: 0 }} label="审批意见">
            {getFieldDecorator('spyj',{
            })(<Input.TextArea rows={4} />)}
            </Form.Item>
          </Form>
        </Modal>
      </div>
    )
  }
}