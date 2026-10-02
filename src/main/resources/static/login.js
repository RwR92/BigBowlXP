document.addEventListener("DOMContentLoaded", initApp);
const BASE_URL_LOGIN = "/api/login";
const formData = [];
import {authUser} from "./script.js"
async function initApp() {
    document.querySelector("#loginForm").addEventListener("submit", handleLoginSubmit)
}

async function handleLoginSubmit(e) {
    console.log("Vi er her handleloginsubmit");
    e.preventDefault();
    const form = new FormData(e.target);
    const username = form.get("username");
    const password = form.get("password");

    const formData = {username, password};

    const response = await tryLogin(formData);
    if(response.userType === "admin"){
        window.location.href = "admin-home-page.html";
    } else if (response.userType === "employee"){
        window.location.href = "employee-home-page.html";
    }
}

async function tryLogin(loginInfo) {
    const response = await fetch(BASE_URL_LOGIN, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(loginInfo)
    });
    const text = await response.text();
    return text ? JSON.parse(text) : null;
}