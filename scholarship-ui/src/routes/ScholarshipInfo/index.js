import React from 'react'
import { Table, Switch, Button, Modal,Form,Input,Select,Upload,Icon,message } from 'antd'
import axios from 'axios'
import authority from "./authority"
import { query,create,update,del,queryLib } from './service'
import {pageNo,pageSize, pageSizeOptions, total} from '../../config/common'
import CustomBreadcrumb from '../../components/CustomBreadcrumb/index'
import SearchBox from './SearchBox'

@Form.create() 
export default class ScholarshipInfo extends React.Component {
  constructor(props){
    super(props);
    this.state={
      addFlag:false,        //是否显示模拟框
      visible:false,
      title:"",             //模拟框标题
      level:[],             //奖励金等级
      source:[],            //资金来源
      selectRow:{},         //选中行
      formData:[],          //列表数据
      fileList : [],        //文件列表
      percent:0,            //百分比
      selectedRowKeys:[]    //选中行
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
    let _params={
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
      formData.sffdj=formData.sffdj?1:0  //true设为1，false设为0
      formData.sfqy=formData.sfqy?1:0
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
          formData.dyid=selectRow.dyid
          formData.jljbdm=formData.jljbdm==="国家级"?1:formData.jljbdm==="省级"?2:formData.jljbdm==="校级"?3:formData.jljbdm==="院级"?4:formData.jljbdm,
          formData.zjlydm=formData.zjlydm==="国家拨款"?1:formData.zjlydm==="企业拨款"?2:formData.zjlydm==="学校拨款"?3:formData.zjlydm==="其它"?4:formData.jljbdm,
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
    })
    // window.location.reload()
  }

  //是否启用改奖学金
  updateStatus = (checked, record) => {
    record.sfqy=checked?1:0
    update(record, authority.update, (data) => {
      this.setState({})
    })
  }

  //显示新增模拟框
  showAddModal = () => {
    this.setState({addFlag:!this.state.addFlag,title:"新增"});
    queryLib("JLJJB",(data)=>{
      this.setState({
        level: data
      })
    })
    queryLib("ZJLY",(data)=>{
      this.setState({
        source: data
      })
    })
  }

  //显示修改模拟框
  showUpdateModal=(record)=>{
    console.log(record)
    this.setState({
      addFlag:!this.state.addFlag,
      selectRow:record,
      title:'修改'
    })
    queryLib("JLJJB",(data)=>{
      this.setState({
        level: data
      })
    })
    queryLib("ZJLY",(data)=>{
      this.setState({
        source: data
      })
    })
  }

  //删除
  handleDelete=(pkid)=>{
    let _this=this;
    Modal.confirm({
      title: '提示',
      content: '确认要删除吗？',
      onOk() {
        del([pkid], authority.delete, () => {
          _this.indexQuery()
          _this.setState({
            selectedRowKeys: []
          })
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
        span:4,
      },
      wrapperCol: {
        span:20,
      },
    } 
    const columns = [
      {
        title: '序号',
        dataIndex: 'dyid',
        align: 'center',
        width:'10%',
        render: (text, record, index) => {
          return ((this.params.pageNo - 1) * this.params.pageSize + index + 1)
        },
      },
      {
        title: '奖学金类型',
        dataIndex: 'lx',
        align: 'center',
        width:'20%',
      },
      {
        title: '资金来源',
        dataIndex: 'zjlydm',
        align: 'center',
        width:'20%',
        render:(text,record)=>{
          return text===1?"国家拨款":text===2?"企业拨款":text===3?"学校拨款":text===4?"其他":""
        }
      },
      {
        title: '奖学金级别',
        dataIndex: 'jljbdm',
        align: 'center',
        width:'20%',
        render:(text,record)=>{
          return text===1?"国家级":text===2?"省级":text===3?"校级":text===4?"院级":""
        }
      },
      {
        title: '是否分等级',
        dataIndex: 'sffdj',
        align: 'center',
        width:'10%',
        render: (text, record) => {
          return record.sffdj=='1'?'是':'否';
        }
      },
      {
        title: '是否启用',
        dataIndex: 'sfqy',
        align: 'center',
        width:'10%',
        render: (text, record) => {
          return <Switch checkedChildren="是" unCheckedChildren="否" checked={record.sfqy===1} onChange={(checked) => this.updateStatus(checked, record)}
          />
        }
      },
      {
        title: '操作',
        dataIndex: 'operation',
        align: 'center',
        width:'10%',
        render: (text, record) => (
          <div>
            <a onClick={()=>this.showUpdateModal(record)}>修改</a>&nbsp;&nbsp;
            <a onClick={()=>this.handleDelete(record.dyid)}>删除</a>
          </div>
        ),
      }
    ]
    const {lx,zjlydm,jljbdm,sffdj,sfqy,jjms,}=this.state.selectRow
    const { fileList} = this.state
    
    const props = {
      name: "avatar",
      customRequest: info => {//手动上传
        const formData = new FormData();
        formData.append('avatar', info.file);//名字和后端接口名字对应
        axios
          .post('http://localhost:8001/upload/uploadFile', formData, {
            onUploadProgress: ({ total, loaded }) => {
              info.onProgress({ percent: Math.round((loaded / total) * 100).toFixed(2) }, info.file);
            },
          })
          .then(({ data: response }) => {
            info.onSuccess(response, info.file);
            message.success(`${info.file.name}上传成功！`);
          })
          .catch(()=>{
            message.error(`${info.file.name}上传失败！`);
            info.onError()
          });
      },
      onSuccess:(response,file)=>{
        const newFileLIst=[file]  //只上传一个文件
        this.setState({fileList:newFileLIst})
      },
      fileList: fileList,
      listType:"picture",
      className: "avatar-uploader",
    }
    const {pageNo,pageSize,total}=this.params
    return (
      <div>
        <CustomBreadcrumb arr={['奖学金信息管理']} />
        <Button type="primary" onClick={this.showAddModal} style={{ marginBottom: 10 }}>新增</Button>
        <a onClick={this.handleVisible} style={{float:'right',marginTop:-50}}>高级搜索</a>
        <SearchBox visible={this.state.visible} onSearch={this.onSearch}/>
        <Table
          bordered
          columns={columns}
          dataSource={this.state.formData}
          rowKey={row=>row.dyid}
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
            <Form.Item {...formItemLayout} style={{ marginBottom: 0 }} label="奖学金类型">
              {getFieldDecorator('lx', {
                initialValue: lx,
                rules: [
                  {
                    required: true,
                    message: '必填',
                  },
                ],
              })(<Input placeholder="请输入奖学金类型" />)}
            </Form.Item>
            <Form.Item {...formItemLayout} style={{ marginBottom: 0 }} label="资金来源">
              {getFieldDecorator('zjlydm', {
                initialValue: zjlydm===1?"国家拨款":zjlydm===2?"企业拨款":zjlydm===3?"学校拨款":zjlydm===4?"其它":zjlydm,
                // initialValue: zjlydm,
                rules: [
                  {
                    required: true,
                    message: '必填',
                  },
                ],
              })(
                <Select placeholder="请选择">
                  {this.state.source.map((val)=>(
                    <Select.Option key={val.dmk} value={val.dmv}>{val.dmv}</Select.Option>
                  ))}
                </Select>
              )}
            </Form.Item>
            <Form.Item {...formItemLayout} style={{ marginBottom: 0 }} label="奖学金等级">
              {getFieldDecorator('jljbdm', {
                initialValue: jljbdm===1?"国家级":jljbdm===2?"省级":jljbdm===3?"校级":jljbdm===4?"院级":jljbdm,
                // initialValue:jljbdm,
                rules: [
                  {
                    required: true,
                    message: '必填',
                  }
                ],
              })(
                <Select placeholder="请选择">
                  {this.state.level.map((val)=>(
                    <Select.Option key={val.dmk} value={val.dmv}>{val.dmv}</Select.Option>
                  ))}
                </Select>)}
            </Form.Item>
            <Form.Item {...formItemLayout} style={{ marginBottom: 0 }} label="是否分等级">
              {getFieldDecorator('sffdj', {
                initialValue: !sffdj&&0,
                rules: [
                  {
                    required: true,
                    message: '必填',
                  },
                ],
              })(<Switch checkedChildren="是" unCheckedChildren="否" key={sffdj===1} defaultChecked={sffdj===1}/>)}
                
            </Form.Item>
            
            <Form.Item {...formItemLayout} style={{ marginBottom: 0 }} label="是否启用">
              {getFieldDecorator('sfqy', {
                initialValue: sfqy?sfqy:0,    //没有初始值时设为0
                rules: [
                  {
                    required: true,
                    message: '必填',
                  },
                ],
              })(<Switch checkedChildren="是" unCheckedChildren="否" key={sfqy===1}  defaultChecked={sfqy===1}/>)}
            </Form.Item>
            <Form.Item {...formItemLayout} id='jjms' style={{ marginBottom: 0 }} label="简介说明">
            {getFieldDecorator('jjms', {
                initialValue: jjms
              })(<Input.TextArea rows={4} />)}
            </Form.Item>
            <Form.Item {...formItemLayout} style={{ marginBottom: 0 }} id='attachment' label="添加附件">
              <Upload {...props}>
                <Button>
                  <Icon type='upload'/>点击上传
                </Button>
              </Upload>
            </Form.Item>
          </Form>
        </Modal>
        
        </div>
    )
  }
}