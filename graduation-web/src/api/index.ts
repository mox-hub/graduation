import request from '../utils/request'

export const text2img = (url: string, data: any) => {
    return request({
        url: url,
        method: 'post',
        data: data
    })
}

export const insertData = (data: any, path: String) => {
    return request({
        url: 'http://localhost:8080' + path,
        method: 'post',
        data: data
    })
}

export const deleteData = (data: any, path: String) => {
    return request({
        url: 'http://localhost:8080' + path,
        method: 'post',
        data: data
    })
}

export const updateData = (data: any, path: String) => {
    return request({
        url: 'http://localhost:8080' + path,
        method: 'post',
        data: data
    })
}

export const getData = (data: any, path: String) => {
    return request({
        url: 'http://localhost:8080' + path,
        method: 'post',
        data: data
    })
}

export const getDataNoParam = (path: String) => {
    return request({
        url: 'http://localhost:8080' + path,
        method: 'post',
        params: ""
    })
}
