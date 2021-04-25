import { apiUrl } from "../config.json";
import http from "./httpService";

const apiEndpoint = apiUrl + '/apartments/';

export function getApartments(page = 0, size = 9, sort = 'id') {
    return http.get(apiEndpoint, { params: { page, size, sort } });
}

export function getById(id) {
    return http.get(`${apiEndpoint + id}`);
}

export default {
    getApartments,
    getById
};
