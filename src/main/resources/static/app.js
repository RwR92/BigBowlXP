const BASE_URL_LOGOUT = "api/logout";
import {handleRoute} from "./script.js"
document.addEventListener("DOMContentLoaded", initApp);

async function initApp() {
    document.querySelector("#logout-btn").addEventListener("click", logout)
    await handleRoute(document.body.dataset.role);
}

export async function logout() {
    await fetch(BASE_URL_LOGOUT, {method: "POST"});
    return window.location.href = "login.html";
}
