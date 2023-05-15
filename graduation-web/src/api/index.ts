import request from '../utils/request'

export const text2img = (url: string, data: any) => {
    return request({
        url: url,
        method: 'post',
        data: data
    })
}

export const insertData = (data: JSON, path: String) => {
    return request({
        url: 'http://localhost:8080' + path,
        method: 'post',
        data: data
    })
}

export const insertDataParam = (data: JSON, path: String) => {
    return request({
        url: 'http://localhost:8080' + path,
        method: 'post',
        params: data
    })
}

export const deleteData = (data: JSON, path: String) => {
    return request({
        url: 'http://localhost:8080' + path,
        method: 'post',
        params: data
    })
}

export const updateData = (data: JSON, path: String) => {
    return request({
        url: 'http://localhost:8080' + path,
        method: 'post',
        data: data
    })
}

export const getDataParam = (query: String, path: String) => {
    return request({
        url: 'http://localhost:8080' + path,
        method: 'get',
        params: query
    })
}

export const getDataNoParam = (path: String) => {
    return request({
        url: 'http://localhost:8080' + path,
        method: 'get',
        params: ""
    })
}
