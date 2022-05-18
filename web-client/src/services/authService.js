import axios from "axios";
import jwtDecode from "jwt-decode";
import config from "../config.js";
import httpService from "./httpService";
const apiEndpoint = config.apiUrl + '/auth';
const tokenKey = 'token';

httpService.setToken(getToken());

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
            username: config.authName,
            password: config.authPassword
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

export function getToken() {
    return localStorage.getItem(tokenKey);
}

export default {
    login,
    logout,
    getCurrentUser,
    getToken
};
