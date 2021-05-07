import React from 'react'
import CustomBreadcrumb from '../../../components/CustomBreadcrumb/index'
import { Table, Button, Modal,Form,Input} from 'antd'
import { query,create,update,del} from './service'
import {pageNo,pageSize, pageSizeOptions, total} from '../../../config/common'
import authority from "./authority"
@Form.create() 
export default class Department extends React.Component {
  constructor(props){
    super(props);
    this.state={
      addFlag:false,
      sortedInfo: null,
      title:"",
      selectRow:{},
      formData:[],          //列表数据
      isUpdate:false,       //是否更新
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

  //提交表单
  handleSubmit=()=>{
    const {selectRow} = this.state
    this.props.form.validateFields((err, formData) => {
      if (!err) {
        if(Object.keys(selectRow).length==0){
          create(formData, authority.create, () => {
            this.indexQuery()
            this.setState({
              addFlag:false,
            })
          })
        }else{
          formData.xyid=selectRow.xyid
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
  handleCancel = e => {
    this.props.form.resetFields() //清空输入框
    this.setState({
      addFlag: false,
      selectRow:{}
    });
  }

  //新增
  showAddModal = () => {
    this.setState({
      addFlag:!this.state.addFlag,
      title:'新增',
      isUpdate:false,
    });
  }

  //显示修改模拟框
  showUpdateModal=(record)=>{
    this.setState({
      addFlag:!this.state.addFlag,
      selectRow:record,
      title:'修改',
      isUpdate:true,
    })
  }

  //删除
  handleDelete=(record)=>{
    let _this=this;
    Modal.confirm({
      title: '提示',
      content: '确认要删除吗？',
      onOk() {
        del([record.xyid], authority.delete, () => {
          _this.indexQuery()
        })
      }
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
    const columns = [
      {
        title: '序号',
        dataIndex: 'xyid',
        width: '10%',
        align: 'center',
        render: (text, record, index) => {
          return ((this.params.pageNo - 1) * this.params.pageSize + index + 1)
        },
      },
      {
        title: '学院编号',
        dataIndex: 'xybh',
        width: '30%',
        align: 'center',
        sorter:true
      },
      {
        title: '学院名称',
        dataIndex: 'xymc',
        width: '40%',
        align: 'center',
      },
      {
        title: '专业数量',
        dataIndex: 'sum',
        align: 'center',
        width: '10%',
        sorter:true
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
    const {xybh,xymc}=this.state.selectRow
    const {pageNo,pageSize,total}=this.params
    return (
      <div>
        <CustomBreadcrumb arr={['学院信息管理']} />
        <Button type="primary" onClick={this.showAddModal} style={{ marginBottom: 10 }}>新增</Button>
        <Table
          bordered
          columns={columns}
          dataSource={this.state.formData}
          rowKey={row=>row.xyid}
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
            <Form.Item {...formItemLayout} style={{ marginBottom: 0 }} label="学院编号">
              {getFieldDecorator('xybh', {
                initialValue: xybh,
                rules: [
                  {
                    required: true,
                    message: '必填',
                  },
                ],
              })(<Input disabled={this.state.isUpdate} placeholder="请输入学院编号" />)}
            </Form.Item>        
            <Form.Item {...formItemLayout} style={{ marginBottom: 0 }} label="学院名称">
              {getFieldDecorator('xymc', {
                initialValue: xymc,
                rules: [
                  {
                    required: true,
                    message: '必填',
                  },
                ],
              })(<Input placeholder="请输入学院名称" />)}
            </Form.Item>       
          </Form>
        </Modal>
      </div>
    )
  }
}