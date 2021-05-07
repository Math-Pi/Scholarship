import React from 'react'
import CustomBreadcrumb from '../../components/CustomBreadcrumb/index'
import { Table, Button, Modal,Form,Input} from 'antd'
import { query,update,create,del } from './service'
import authority from "./authority"
import {pageNo,pageSize, pageSizeOptions, total} from '../../config/common'
import SearchBox from './SearchBox'

@Form.create() 
export default class USer extends React.Component {
  constructor(props){
    super(props);
    this.state={
      addFlag:false,
      title:"",
      selectRow:{},
      formData:[],        //列表数据
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
    query(_params,authority.query,(data)=>{
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
        if(Object.keys(selectRow).length==0){
          create(formData, authority.create, () => {
            this.indexQuery()
            this.setState({
              addFlag:false,
              selectedRowKeys: []
            })
          })
        }else{
          formData.id=selectRow.id
          update(formData, authority.update, () => {
            this.indexQuery()
            this.setState({
              selectRow:{},
              addFlag:false,
              selectedRowKeys: [],
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
    this.setState({addFlag:!this.state.addFlag,title:'新增'});
    
  }

  //显示修改模拟框
  showUpdateModal=(record)=>{
    this.setState({
      addFlag:!this.state.addFlag,
      selectRow:record,
      title:'修改'
    })
  }

  //删除
  handleDelete=(record)=>{
    let _this=this;
    Modal.confirm({
      title: '提示',
      content: '确认要删除吗？',
      onOk() {
        del([record.id], authority.delete, () => {
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

  render() {
    const { getFieldDecorator } = this.props.form;
    const formItemLayout = {
      labelCol: {
        span:4,
      },
      wrapperCol: {
        span:20,
      },
    };
    const columns = [
      {
        title: '序号',
        dataIndex: 'id',
        align: 'center',
        width:'10%',
        render: (text, record, index) => {
          return ((this.params.pageNo - 1) * this.params.pageSize + index + 1)
        },
      },
      {
        title: '用户名',
        dataIndex: 'username',
        align: 'center',
        width:'40%',
      },
      {
        title: '角色',
        dataIndex: 'role',
        align: 'center',
        width:'40%',
      },
      {
        title: '操作',
        dataIndex: 'operation',
        align: 'center',
        width:'10%',
        render: (text, record) => (
          <div>
            <a onClick={()=>this.showUpdateModal(record)}>修改</a>&nbsp;&nbsp;
            <a onClick={()=>this.handleDelete(record)}>删除</a>
          </div>
        ),
      }
    ]
    const {pageNo,pageSize,total}=this.params 
    return (
      <div>
        <CustomBreadcrumb arr={['代码规则']} />
        <Button type="primary" onClick={this.showAddModal} style={{ marginBottom: 10 }}>新增</Button>
        <SearchBox onSearch={this.onSearch}/>
        <Table
          bordered
          columns={columns}
          dataSource={this.state.formData}
          rowKey={row=>row.id}
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
          <Form ref={ele=>this.addMadoal=ele} onSubmit={this.handleSubmit}>
            <Form.Item {...formItemLayout} style={{ marginBottom: 0 }} label="用户名">
              {getFieldDecorator('username', {
                initialValue: this.state.selectRow.username,
                rules: [
                  {
                    required: true,
                    message: '必填',
                  },
                ],
              })(<Input placeholder="请输入用户名" />)}
            </Form.Item>
            <Form.Item {...formItemLayout} style={{ marginBottom: 0 }} label="密码">
              {getFieldDecorator('password', {
                initialValue: this.state.selectRow.password,
                rules: [
                  {
                    required: true,
                    message: '必填',
                  },
                ],
              })(<Input type="password" placeholder="请输入密码" />)}
            </Form.Item>
          </Form>
        </Modal>
      </div>
    )
  }
}