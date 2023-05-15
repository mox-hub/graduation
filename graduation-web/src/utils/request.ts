import axios from 'axios'

const service = axios.create({
    timeout: 1000000,
    baseURL: 'http://localhost:8080',
    headers: {
        'Content-Type': 'application/json',
    }

})

service.interceptors.request.use(
    config => {
        return config
    },
    error => {
        console.log(error)
        return Promise.reject()
    }
)

service.interceptors.response.use(
    response => {
        if (response.status === 200) {
            return response.data
        } else {
            Promise.reject()
        }
    },
    error => {
        console.log(error)
        return Promise.reject()
    }
)

export default service
