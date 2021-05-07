let base = `/api`

// 奖学金信息管理
export const scholarshipDefine = {
    query: `${base}/scholarshipDefine/query`, // 查询
    create: `${base}/scholarshipDefine/create`, // 插入
    update: `${base}/scholarshipDefine/update`, // 更新
    delete: `${base}/scholarshipDefine/delete`, // 删除
    home: `${base}/scholarshipDefine/`, // home
}

// 奖学金信息发布管理
export const scholarshipPublish = {
    query: `${base}/scholarshipPublish/query`,      // 查询
    create: `${base}/scholarshipPublish/create`,    // 插入
    update: `${base}/scholarshipPublish/update`,    // 更新
    delete: `${base}/scholarshipPublish/delete`,    // 删除
    queryType: `${base}/scholarshipDefine/queryType`,    // 查询奖学金类型列表
    querySffdj: `${base}/scholarshipDefine/`,           // 根据主键查询是否分分级
}

// 奖学金申请
export const apply = {
    query: `${base}/apply/query`,      // 查询
    create: `${base}/apply/create`,    // 插入
    update: `${base}/apply/update`,    // 更新
    delete: `${base}/apply/delete`,    // 删除
    updateStatus: `${base}/apply/updateStatus`,             // 送审
    queryJxjmc: `${base}/scholarshipPublish/queryName`,     // 查询奖学金名称列表
}

// 奖学金审批
export const approval = {
    query: `${base}/approval/query`,      // 查询
    create: `${base}/approval/create`,    // 插入
    update: `${base}/approval/update`,    // 更新
    delete: `${base}/approval/delete`,    // 删除
}

// 获奖名单管理
export const scholarshipList = {
    query: `${base}/scholarshipList/query`,      // 查询
}


// 学院信息管理
export const department = {
    query: `${base}/department/query`,      // 查询
    create: `${base}/department/create`,    // 插入
    update: `${base}/department/update`,    // 更新
    delete: `${base}/department/delete`,    // 删除
}

// 专业信息管理
export const profession = {
    query: `${base}/profession/query`,      // 查询
    create: `${base}/profession/create`,    // 插入
    update: `${base}/profession/update`,    // 更新
    delete: `${base}/profession/delete`,    // 删除
}

// 班级信息管理
export const _class = {
    query: `${base}/class/query`,      // 查询
    create: `${base}/class/create`,    // 插入
    update: `${base}/class/update`,    // 更新
    delete: `${base}/class/delete`,    // 删除
}

// 学生信息管理
export const student = {
    query: `${base}/student/query`,      // 查询
    create: `${base}/student/create`,    // 插入
    update: `${base}/student/update`,    // 更新
    delete: `${base}/student/delete`,    // 删除
}

// 代码规则管理
export const rule = {
    query: `${base}/rule/query`,    // 查询
    create: `${base}/rule/create`,  // 插入
    update: `${base}/rule/update`,  // 更新
    delete: `${base}/rule/delete`,  // 删除
}
// 代码库管理
export const library = {
    query: `${base}/library/query`,     // 查询
    create: `${base}/library/create`,   // 插入
    update: `${base}/library/update`,   // 更新
    delete: `${base}/library/delete`,   // 删除
}

// 用户管理
export const user = {
    query: `${base}/user/query`,     // 查询
    create: `${base}/user/create`,   // 插入
    update: `${base}/user/update`,   // 更新
    delete: `${base}/user/delete`,   // 删除
    getUserByUserName: `${base}/user/getUserByUserName`,   //根据用户名查询用户数据
    updatePass: `${base}/user/updatePass`,   //更新密码
}

//列表查询
export const common = {
    queryDept: `${base}/common/queryDept`,      // 查询学院列表
    queryPro: `${base}/common/queryPro`,        // 查询专业列表
    queryClass: `${base}/common/queryClass`,    // 查询班级列表
    queryRule: `${base}/common/queryRule`,      // 查询代码规则列表
    queryLib: `${base}/common/queryLib/`,        // 查询代码映射列表

}