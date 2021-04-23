import axios from "axios";
import jwtDecode from "jwt-decode";
import { apiUrl, authName, authPassword } from "../config.json";

const apiEndpoint = apiUrl + '/auth';
const tokenKey = 'token';

export async function login(username, password) {
    const bodyFormData = new FormData();

    bodyFormData.append('username', username);
    bodyFormData.append('password', password);
    bodyFormData.append('grant_type', 'password');

    const { data } = await axios({
        method: "post",
        url: `${ apiEndpoint }/oauth/token`,
        data: bodyFormData,
        headers: { "Content-Type": "multipart/form-data" },
        auth: {
            username: authName,
            password: authPassword
        }
    });

    localStorage.setItem(tokenKey, data.access_token);
}


export function logout() {
    localStorage.removeItem(tokenKey);
}

export function getCurrentUser() {
    try {
        const jwt = localStorage.getItem(tokenKey);
        return jwtDecode(jwt);
    } catch (ex) {
        return null;
    }
}

export function getJwt() {
    return localStorage.getItem(tokenKey);
}

export default {
    login,
    logout,
    getCurrentUser,
    getJwt
};
