import request from '@/utils/request'
export const getOverview = () => request.get('/dashboard/overview')
