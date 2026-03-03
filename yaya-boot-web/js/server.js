// 业务服务器代理地址
const server_url='/api';
// 文件服务器代理地址
const file_url='/api';


const yaya = layui.sessionData("yaya");
//获取token
const token = yaya ? yaya.token : '';
//获取用户信息
const user = yaya ? yaya.user : {};