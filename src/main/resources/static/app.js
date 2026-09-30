import {handleRoute} from "./script.js"
import {initApp} from "./adminPage"
document.addEventListener("DOMContentLoaded", initApp);
const BASE_URL_LOGOUT = "api/logout";

async function initApp() {
    document.querySelector("#logout-btn").addEventListener("click", logout)
    await handleRoute(document.body.dataset.role)
    await initApp()
}

async function logout() {
    const response = await fetch(BASE_URL_LOGOUT, {method: "POST"});

    return window.location.href = "login.html";
}
