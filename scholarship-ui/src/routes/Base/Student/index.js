import React from 'react'
import { Table, Button, Modal,Form,Input,Select,DatePicker,Radio,InputNumber} from 'antd'
import moment from "moment";
import { query,create,update,del,queryDept, queryPro,queryClass,queryLib} from './service'
import {pageNo,pageSize, pageSizeOptions, total} from '../../../config/common'
import authority from "./authority"
import CustomBreadcrumb from '../../../components/CustomBreadcrumb/index'
import SearchBox from './SearchBox'

@Form.create() 
export default class Student extends React.Component {
  constructor(props){
    super(props);
    this.state={
      addFlag:false,
      visible:false,
      title:"",
      selectRow:{},
      formData:[],          //列表数据
      deptList:[],          //学院列表
      proList:[],           //专业列表
      classList:[],         //班级列表
      list:[],         //
      gradeList:[],         //年级列表
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
        if(Object.keys(selectRow).length==0){
          create(formData, authority.create, () => {
            this.indexQuery()
            this.setState({
              addFlag:false,
            })
          })
        }else{
          formData.xsid=selectRow.xsid
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

  //新增
  showAddModal = () => {
    
    //查询学院列表
    queryDept((data)=>{
      this.setState({
        addFlag:!this.state.addFlag,
        title:'新增',
        isUpdate:false,
        deptList: data
      })
    })
  }

  //显示修改模拟框
  showUpdateModal=(record)=>{
    //查询学院列表
    queryDept((data)=>{
      this.setState({
        addFlag:!this.state.addFlag,
        title:'修改',
        isUpdate:true,
        selectRow:record,
        deptList: data
      })
    })
    //查询年级列表
    queryLib("NJ",(data)=>{
      this.setState({
        gradeList: data
      })
    })
  }

  //查询某学院下的专业列表
  handleDept=(xybh)=>{
    let params={'xybh':xybh}
    queryPro(params,(data)=>{
      this.setState({
        proList: data
      })
    })
  }

  //查询某专业下的年级列表
  handlePro=(zybh)=>{
    let params={'zybh':zybh}
    queryClass(params,(data)=>{
      this.setState({
        classList: data,
        list:data
      })
    })
    //查询年级列表
    queryLib("NJ",(data)=>{
      this.setState({
        gradeList: data
      })
    })
  }

  //查询某学院下的专业列表
  handleNJ=(nj)=>{
    const newClassList=this.state.list.filter(val=>{return val.nj===nj})
    this.setState({classList:newClassList})
  }

  //删除
  handleDelete=(record)=>{
    let _this=this;
    Modal.confirm({
      title: '提示',
      content: '确认要删除吗？',
      onOk() {
        del([record.xsid], authority.delete, () => {
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
        dataIndex: 'xsid',
        width: '10%',
        align: 'center',
        render: (text, record, index) => {
          return ((this.params.pageNo - 1) * this.params.pageSize + index + 1)
        },
      },
      {
        title: '学号',
        dataIndex: 'xsbh',
        width: '8%',
        align: 'center',
      },
      {
        title: '姓名',
        dataIndex: 'xsxm',
        align: 'center',
        width: '7%'
      },
      {
        title: '性别',
        dataIndex: 'xsxb',
        align: 'center',
        width: '5%'
      },
      {
        title: '年龄',
        dataIndex: 'xsnl',
        align: 'center',
        width: '5%'
      },
      {
        title: '年级',
        dataIndex: 'nj',
        align: 'center',
        width: '5%'
      },
      {
        title: '班级',
        dataIndex: 'bjmc',
        align: 'center',
        width: '10%'
      },
      {
        title: '专业',
        dataIndex: 'zymc',
        align: 'center',
        width: '13%'
      },
      {
        title: '院系',
        dataIndex: 'xymc',
        align: 'center',
        width: '17%'
      },
      {
        title: '联系电话',
        dataIndex: 'lxdh',
        align: 'center',
        width: '10%'
      },
      // {
      //   title: '地址',
      //   dataIndex: 'dz',
      //   align: 'center',
      //   width: '10%'
      // },
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
    const {xsbh,xsxm,xsxb,xsnl,nj,rxsj,lxdh,dz,bz,bjmc,zymc,xymc}=this.state.selectRow
    const {isUpdate,addFlag}=this.state
    const {pageNo,pageSize,total}=this.params
    return (
      <div>
        <CustomBreadcrumb arr={['学生信息管理']} />
        <Button type="primary" onClick={this.showAddModal} style={{ marginBottom: 10 }}>新增</Button>
        <a onClick={this.handleVisible} style={{float:'right',marginTop:-50}}>高级搜索</a>
        <SearchBox visible={this.state.visible} onSearch={this.onSearch}/>
        <Table
          bordered
          columns={columns}
          dataSource={this.state.formData}
          rowKey={row=>row.xsid}
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
            <Form.Item {...formItemLayout} style={{ marginBottom: 0 }} label="学号">
              {getFieldDecorator('xsbh', {
                initialValue: xsbh,
                rules: [
                  {
                    required: true,
                    message: '必填',
                  },
                  {max: 10, message: '学生编号长度必须小于10'}
                ],
              })(<Input disabled={isUpdate} placeholder="请输入学号" />)}
            </Form.Item>
            <Form.Item {...formItemLayout} style={{ marginBottom: 0 }} label="姓名">
              {getFieldDecorator('xsxm', {
                initialValue: xsxm,
                rules: [
                  {
                    required: true,
                    message: '必填',
                  },
                  {max: 10, message: '学生姓名长度必须小于10'}
                ],
              })(<Input placeholder="请输入姓名" />)}
            </Form.Item>
            <Form.Item {...formItemLayout} style={{ marginBottom: 0 }} label="性别">
              {getFieldDecorator('xsxb', {
                initialValue: xsxb,
                rules: [
                  {
                    required: true,
                    message: '必填',
                  },
                ],
              })(<Radio.Group onChange={()=>{}}>
                  <Radio value='男'>男</Radio>
                  <Radio value='女'>女</Radio>
                </Radio.Group>
              )}
            </Form.Item>
            <Form.Item {...formItemLayout} style={{ marginBottom: 0 }} label="年龄">
              {getFieldDecorator('xsnl', {
                initialValue: xsnl,
                rules: [
                  {
                    required: true,
                    message: '必填',
                  },
                  {pattern: /^(?:[1-9][0-9]?|1[01][0-9]|120)$/, message: '年龄是1-120之间有效'}
                ],
              })(<InputNumber placeholder="请输入年龄" />)}
            </Form.Item>
            {!isUpdate&&<Form.Item {...formItemLayout} style={{ marginBottom: 0 }} label="学院">
              {addFlag&&getFieldDecorator('xybh', {
                initialValue: xymc,
                rules: [
                  {
                    required: true,
                    message: '必填',
                  },
                ],
              })(
                <Select placeholder="请选择">
                  {this.state.deptList.map((val)=>(
                    <Select.Option key={val.xybh} onClick={()=>this.handleDept(val.xybh)}>{val.xymc}</Select.Option>
                  ))}
                </Select>
              )}
            </Form.Item>}
            {!isUpdate&&<Form.Item {...formItemLayout} style={{ marginBottom: 0 }} label="专业">
              {addFlag&&getFieldDecorator('zybh', {
                initialValue: zymc,
                rules: [
                  {
                    required: true,
                    message: '必填',
                  },
                ],
              })(
                <Select placeholder="请选择">
                  {this.state.proList.map((val)=>(
                    <Select.Option key={val.zybh} onClick={()=>this.handlePro(val.zybh)}>{val.zymc}</Select.Option>
                  ))}
                </Select>
              )}
            </Form.Item>}
            <Form.Item {...formItemLayout} style={{ marginBottom: 0 }} label="年级">
              {getFieldDecorator('nj', {
                initialValue: nj,
                rules: [
                  {
                    required: true,
                    message: '必填',
                  },
                ],
              })(
                <Select placeholder="请选择">
                  {this.state.gradeList.map((val)=>(
                    <Select.Option key={val.dmk} value={val.dmv} onClick={()=>this.handleNJ(val.dmv)}>{val.dmv}</Select.Option>
                  ))}
                </Select>
              )}
            </Form.Item>
            {!isUpdate&&<Form.Item {...formItemLayout} style={{ marginBottom: 0 }} label="班级">
              {addFlag&&getFieldDecorator('bjbh', {
                initialValue: bjmc,
                rules: [
                  {
                    required: true,
                    message: '必填',
                  },
                ],
              })(
                <Select placeholder="请选择">
                  {this.state.classList.map((val)=>(
                    <Select.Option key={val.bjbh}>{val.bjmc}</Select.Option>
                  ))}
                </Select>
              )}
            </Form.Item>}
            <Form.Item {...formItemLayout} label="入学时间:" style={{ marginBottom: 0 }}>
              {getFieldDecorator("rxsj", {
                initialValue: rxsj?moment(rxsj):undefined,
                rules: [{ required: true, message: "必填" }]
              })(
                <DatePicker
                  format="YYYY-MM-DD"
                  placeholder="入学日期"
                />
              )}
            </Form.Item>
            <Form.Item {...formItemLayout} style={{ marginBottom: 0 }} label="联系电话">
              {getFieldDecorator('lxdh', {
                initialValue: lxdh,
                rules: [
                  {
                    required: true,
                    message: '必填',
                  },
                  {pattern: /^1[34578]\d{9}$/, message: '请输入正确的手机号'}
                ],
              })(<Input placeholder="请输入联系电话" />)}
            </Form.Item>
            <Form.Item {...formItemLayout} style={{ marginBottom: 0 }} label="地址">
              {getFieldDecorator('dz', {
                initialValue: dz,
                rules: [
                  {
                    required: true,
                    message: '必填',
                  },
                ],
              })(<Input.TextArea rows={2} />)}
            </Form.Item>
            <Form.Item {...formItemLayout} style={{ marginBottom: 0 }} label="备注">
              {getFieldDecorator('bz', {
                initialValue: bz,
              })(<Input.TextArea rows={2} />)}
            </Form.Item>
          </Form>
        </Modal>
      </div>
    )
  }
}