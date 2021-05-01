import { apiUrl } from "../config.json";
import http from "./httpService";

const apiEndpoint = apiUrl + '/apartments/';

export function getApartments(page = 0, size = 9, sort = 'id') {
    return http.get(apiEndpoint, { params: { page, size, sort } });
}

export function getById(id) {
    return http.get(`${ apiEndpoint + id }`);
}

export function getByUsername(username) {
    return http.get(`${ apiEndpoint }search`, { params: { owner: username } });
}

export function getAmenities() {
    return http.get(`${ apiEndpoint }amenities`);
}

export function getTypes() {
    return http.get(`${ apiEndpoint }types`);
}

export function addApartment(apartment) {
    return http.post(`${ apiEndpoint }`, {
        ...apartment
    });
}

export function updateApartment(apartment) {
    return http.put(`${ apiEndpoint }`, {
        ...apartment
    });
}

export default {
    getApartments,
    getById,
    getByUsername,
    getAmenities,
    getTypes,
    addApartment,
    updateApartment
};
