import React from 'react'
import CustomBreadcrumb from '../../../components/CustomBreadcrumb/index'
import { Table, Button, Modal,Form,Input,Select,Upload,Icon, message} from 'antd'
import {pageNo,pageSize, pageSizeOptions, total} from '../../../config/common'
import { query,create,update,del,queryJxjmc,updateStatus} from './service'
import authority from "./authority"
import { isAuthenticated } from '../../../utils/Session'
import axios from 'axios'
@Form.create() 
export default class Apply extends React.Component {
  constructor(props){
    super(props);
    this.state={
      addFlag:false,
      isUpdate:false,
      nameList:[],          //奖学金名称列表
      formData:[],          //列表数据
      selectRow:{},
      selectedRowKeys:[],   //选中行
      selectedRows:[],      //选中行数据列表
      info:null,
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

  //提交表单
  handleSubmit=()=>{
    const {selectRow,info} = this.state
    
    this.props.form.validateFields((err, formData) => {
      if (!err) {
        if(Object.keys(selectRow).length==0){
          formData.sqr=isAuthenticated()
          formData.sqcl=info.file.name
          const fileData = new FormData();
          fileData.append('avatar', info.file);//名字和后端接口名字对应
          axios
            .post('http://localhost:8001/upload/uploadFile', fileData)
            .then((response) => {
              message.success(`${info.file.name}上传成功！`);
              formData.path=response.data.data.fileUrl
              create(formData, authority.create, (data) => {
                this.indexQuery()
                this.setState({
                  addFlag:false,
                })
              })
            })
            .catch(()=>{
              message.error(`${info.file.name}上传失败！`);
            });
        }else{
          formData.sqid=selectRow.sqid
          delete formData.fbid  //修改申请信息时不能修改奖学金名称
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

  //显示申请模拟框
  showAddModal = () => {
    //查询奖学金名称列表
    queryJxjmc((data)=>{
      this.setState({
        addFlag:!this.state.addFlag,
        isUpdate:false,
        title:"新增",
        nameList: data
      })
    })
  }

  //显示修改模拟框
  showUpdateModal=(record)=>{
    if(record.spzt===0 || record.spzt===5){
      queryJxjmc((data)=>{
        this.setState({
          addFlag:!this.state.addFlag,
          isUpdate:true,
          selectRow:record,
          nameList: data
        })
      })
    }else{
      message.warn("只能修改审批状态为“暂存”和“驳回”的申请")
    }
  }

  //送审
  onSubmit=()=>{
    let _this=this;
    const {selectedRowKeys,selectedRows}=this.state
    const sqidList=[]
    if(selectedRowKeys.length>0){
      for(const val of selectedRows){
        if(val.spzt!==0 && val.spzt!==5){ //0为暂存，5为驳回
          message.warn("只能送审审批状态为“暂存”和“驳回”的申请")
          break
        }else{
          sqidList.push(val.sqid)
        }
      }
      const flag=sqidList.length===0?false:true   //flag:是否执行下面代码的标志
      flag&&Modal.confirm({
        title: '提示',
        content: '确认要送审吗？',
        onOk() {
          updateStatus(sqidList, authority.delete, () => {
            _this.indexQuery()
            _this.setState({
              selectedRowKeys:[],   //选中行
            })
          })
        }
      })
    }else{
      message.warn("你还没有选中数据")
    }
  }

  //删除
  handleDelete=(record)=>{
    if(record.spzt===0 || record.spzt===5){
      let _this=this;
      Modal.confirm({
        title: '提示',
        content: '确认要删除吗？',
        onOk() {
          del([record.sqid], authority.delete, () => {
            _this.indexQuery()
          })
        }
      })
    }else{
      message.warn("只能删除审批状态为“暂存”和“驳回”的申请")
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
        width: '30%',
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
    const {jxjmc,sqms,sqcl}=this.state.selectRow
    const { selectedRowKeys } = this.state;
    const rowSelection = {
      columnWidth: '5%',
      selectedRowKeys,
      onChange:this.onSelectChange,
    }
    const { fileList} = this.state
    const props = {
      name: "avatar",
      customRequest: info => {//手动上传
        const formData = new FormData();
        formData.append('avatar', info.file);//名字和后端接口名字对应
        info.onSuccess(info,info.file);
        // axios
        //   .post('http://localhost:8001/upload/uploadFile', formData, {
        //     onUploadProgress: ({ total, loaded }) => {
        //       info.onProgress({ percent: Math.round((loaded / total) * 100).toFixed(2) }, info.file);
        //     },
        //   })
        //   .then(({ data: response }) => {
        //     info.onSuccess(response, info.file);
        //     message.success(`${info.file.name}上传成功！`);
        //   })
        //   .catch(()=>{
        //     message.error(`${info.file.name}上传失败！`);
        //     info.onError()
        //   });
      },
      onSuccess:(info,file)=>{
        const newFileLIst=[file]  //只上传一个文件
        this.setState({fileList:newFileLIst,info:info})
      },
      onRemove:()=>{
        this.setState({fileList:null})
      },
      fileList: fileList,
      listType:"picture",
      className: "avatar-uploader",
    }

    const {pageNo,pageSize,total}=this.params 
    return (
      <div>
        <CustomBreadcrumb arr={['评奖工作管理','奖学金申请']} />
        <Button type="primary" onClick={this.showAddModal} style={{ marginBottom: 10 }}>申请</Button>&nbsp;&nbsp;
        <Button type="primary" onClick={this.onSubmit} style={{ marginBottom: 10 }}>送审</Button>
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
        <Modal title="申请" width={600} visible={this.state.addFlag} onOk={this.handleSubmit} onCancel={this.handleCancel}>
          <Form onSubmit={this.handleSubmit}>
            <Form.Item {...formItemLayout} style={{ marginBottom: 0 }} label="奖学金名称">
              {getFieldDecorator('fbid', {
                initialValue: jxjmc,
                rules: [
                  {
                    required: true,
                    message: '必填',
                  },
                ],
              })(
                <Select placeholder="请选择" disabled={!!jxjmc}>
                  {this.state.nameList.map((val)=>(
                    <Select.Option key={val.fbid} value={val.fbid}>{val.jxjmc}</Select.Option>
                  ))}
                </Select>
              )}
            </Form.Item>
            <Form.Item {...formItemLayout} id='remark' style={{ marginBottom: 0 }} label="申请描述">
              {getFieldDecorator('sqms', {
                initialValue: sqms,
                rules: [
                  {
                    required: true,
                    message: '必填',
                  },
                ],})
                (<Input.TextArea rows={4} />)}
            </Form.Item>
            {!this.state.isUpdate&&<Form.Item {...formItemLayout} style={{ marginBottom: 0 }} id='attachment' label="申请材料">
            {this.state.addFlag&&getFieldDecorator('sqcl', {
                initialValue: sqcl,
                rules: [
                  {
                    required: true,
                    message: '必填',
                  },
                ],})(
              <Upload {...props}>
                <Button>
                  <Icon type="upload" />点击上传
                </Button>
                文件不得大于10M
              </Upload>)}
            </Form.Item>}
          </Form>
        </Modal>
      </div>
    )
  }
}