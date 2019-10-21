import axios from 'axios'
import QS from 'qs'
// 配置基本地址
axios.defaults.baseURL = "http://192.168.0.129:8081";

export default{
    get( url,params={} ){
        return new Promise((resolve,rejects)=>{
            axios.get(url,{params})
                 .then( response =>{
                    resolve(response.data);
                 })
                 .catch(err=>{
                     rejects(err);
                 })
        })
    },
    post( url,params={} ){
        return new Promise((resolve,rejects)=>{
            axios.post(url,QS.parse(params))
                 .then( response =>{
                    resolve(response.data);
                 })
                 .catch(err=>{
                     rejects(err);
                 })
        })
    }
}
