import { apiUrl } from "../config.json";

const apiEndpoint = apiUrl + '/apartments';

export function getApartments() {
    const apartments = [];
    for (let i = 0; i < 20; i++) {
        apartments.push(
            {
                "id": 1,
                "name": "My Apartment",
                "price": 10000,
                "owner": "andriy",
                "address": {
                    "id": 2,
                    "city": "Lviv",
                    "street": "Shevchenka",
                    "houseNumber": 80
                },
                "type": "Apartment",
                "beds": 2,
                "bath": 2,
                "floor": 4,
                "squareMeters": 55.5,
                "petPolicy": "No pets",
                "description": "Some description...",
                "amenities": [
                    "Gym",
                    "Parking Spot"
                ],
                "availableFrom": "2021-04-25",
                "creationDate": "2021-04-25"
            }
        );
    }
    return apartments;
}


export default {
    getApartments
};
