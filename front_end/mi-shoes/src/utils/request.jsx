import axios from "axios";

export const request = axios.create({
    baseURL: 'http://localhost:8080/api/v1/',
})

// làm thêm check token còn sd được ko và 
request.interceptors.request.use (
    (config)=>{
        const token = localStorage.getItem("token");
        if(token){
            config.headers.Authorization= `Bearer ${token}`
        }
        return config;
    },
    (error)=> Promise.reject(error)
)

export const get= async (path, options={})=>{
    const repoonse =await request.get(path, options);
    return repoonse.data;
}

export const post= async (path, options={})=>{
    const repoonse =await request.post(path, options);
    return repoonse.data;
}

export const deleted = async (path)=>{
    const response = await request.delete(path);
    return response.data;
}