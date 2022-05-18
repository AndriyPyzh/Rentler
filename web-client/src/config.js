console.log(process.env);

export default {
    authName: 'web-client',
    authPassword: 'password',
    apiUrl: process.env.REACT_APP_API_URL || 'http://localhost:8765'
};
