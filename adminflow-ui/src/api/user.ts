import request from '@/utils/request'
export const getUsers = (params: {page:number;size:number;keyword?:string}) => request.get('/system/users',{params})
