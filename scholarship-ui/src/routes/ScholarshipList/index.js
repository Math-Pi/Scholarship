import React from 'react'
import CustomBreadcrumb from '../../components/CustomBreadcrumb/index'
import { Table, Button, Modal,Form} from 'antd'
import ExportJsonExcel from 'js-export-excel';
import {pageNo,pageSize, pageSizeOptions, total} from '../../config/common'
import { query} from './service'
@Form.create() 
export default class ScholarshipList extends React.Component {
  constructor(props){
    super(props);
    this.state={
      formData:[],          //列表数据
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

  //导出Excel表格
  downloadExcel = () => {
    const datas = this.state.formData
      var option={};
      let dataTable = [];
      datas.map(data=>{
        dataTable.push({
          'mdid': data.mdid,
          'jxjmc': data.jxjmc,
          'xsbh': data.xsbh,
          'xsxm': data.xsxm,
          'nj': data.nj,
          'bjmc': data.bjmc,
          'zymc': data.zymc,
          'xymc': data.xymc,
        })
      })
      // if (datas) {
      //   for (const data of datas) {
      //     if(data){
      //       let obj = {
      //         'mdid': data.mdid,
      //         'jxjmc': data.jxjmc,
      //         'xsbh': data.xsbh,
      //         'xsxm': data.xsxm,
      //         'nj': data.nj,
      //         'bjbh': data.bjmc,
      //         'zybh': data.zymc,
      //         'xybh': data.xymc,
      //       }
      //       dataTable.push(obj);
      //     }
      //   }
      // }
      option.fileName = '获奖名单'
      option.datas=[
        {
          sheetData:dataTable,
          sheetName:'获奖名单',
          sheetFilter:['mdid','jxjmc','xsbh','xsxm','nj','bjmc','zymc','xymc'],
          sheetHeader:['序号','奖学金名称','学号','姓名','年级','班级','专业','院系'],
        }
      ];
      var toExcel = new ExportJsonExcel(option); 
      toExcel.saveExcel();        
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
    
    const columns = [
      {
        title: '序号',
        dataIndex: 'mdid',
        width: '10%',
        align: 'center',
        render:(text,record,index)=>{
          return (index + 1)
        }
      },
      {
        title: '奖学金名称',
        dataIndex: 'jxjmc',
        align: 'center',
        width: '15%',
      },
      {
        title: '学号',
        dataIndex: 'xsbh',
        width: '10%',
        align: 'center',
      },
      {
        title: '姓名',
        dataIndex: 'xsxm',
        align: 'center',
        width: '10%'
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
        width: '10%',
      },
      {
        title: '专业',
        dataIndex: 'zymc',
        align: 'center',
        width: '20%',
      },
      {
        title: '院系',
        dataIndex: 'xymc',
        align: 'center',
        width: '20%',
      }
    ]
    const {pageNo,pageSize,total}=this.params
    return (
      <div>
        <CustomBreadcrumb arr={['获奖名单管理']} />
        <Button type="primary" onClick={this.downloadExcel} style={{ marginBottom: 10 }}>导出</Button>
        <Table
          bordered
          columns={columns}
          dataSource={this.state.formData}
          rowKey={row=>row.mdid}
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
      </div>
    )
  }
}