import axios from "axios";
import { apiUrl, authName, authPassword } from "../config.json";

const apiEndpoint = apiUrl + '/auth';

export function login(username, password) {
    const bodyFormData = new FormData();

    bodyFormData.append('username', username);
    bodyFormData.append('password', password);
    bodyFormData.append('grant_type', 'password');

    return axios({
        method: "post",
        url: `${ apiEndpoint }/oauth/token`,
        data: bodyFormData,
        headers: { "Content-Type": "multipart/form-data" },
        auth: {
            username: authName,
            password: authPassword
        }
    });

    // return http.post(`${ apiEndpoint }/oauth/token`, {
    //         username,
    //         password,
    //         grant_type: "password"
    //     }, {
    //         auth: {
    //             username: authName,
    //             password: authPassword
    //         }
    //     }
    // );

}
