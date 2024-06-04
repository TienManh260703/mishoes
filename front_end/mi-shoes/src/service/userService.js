import * as request  from "../utils/request";


export const  getUser = async ()=>{
    try{
        const response = await request.get('users/myInfo');
        console.log(response);
        return response;
    }catch(error){
        console.log(error);
    }
}