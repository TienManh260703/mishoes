import * as request from '../utils/request';
import * as tokenService from './localStorage';

export const auth = async (username, password) => {
    try {
        const response = await request.post('auth/token', {
            username,
            password
        })
        // localStorage.setItem("token", response.token)
        tokenService.setToken(response.token)
        return response;
    } catch (error) {
        console.log(error);
    }
}

export const logOut = () => {
    tokenService.removeToken();
}