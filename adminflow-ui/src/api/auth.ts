import request from '@/utils/request'
export interface LoginPayload { username: string; password: string }
export const login = (data: LoginPayload) => request.post('/auth/login', data)
export const getMe = () => request.get('/auth/me')
