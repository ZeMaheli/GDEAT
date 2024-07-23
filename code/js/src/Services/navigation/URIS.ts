
export const URI_PREFIX = "http://localhost:8080/";

//User
export const REGISTER = URI_PREFIX + "users/register";
export const LOGIN = URI_PREFIX + "users/login";
export const LOGOUT = URI_PREFIX + "users/logout";
export const REFRESH_TOKEN = URI_PREFIX + "users/refresh-token";

//Diagram
export const CREATE_DIAGRAM = URI_PREFIX + "diagrams/create";
export const DELETE_DIAGRAM = URI_PREFIX + "diagrams/delete";
export const GET_DIAGRAM = URI_PREFIX + "diagrams/{name}";
export const DIAGRAM_LIST = URI_PREFIX + "diagrams";
export const SAVE_DIAGRAM = URI_PREFIX + "diagrams/save";
