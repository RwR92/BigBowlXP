document.addEventListener("DOMContentLoaded", initApp);
const BASE_URL_LOGIN = "/api/login";
const formData = [];
import {handleRoute} from "./script.js"
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
        window.location.href = "test-admin-page.html";
    } else if (response.userType === "employee"){
        window.location.href = "test-employee-page.html";
    }
}

async function tryLogin(loginInfo){
    const response= await fetch(BASE_URL_LOGIN, {
        method: "POST",
        headers: {
            "Content-type": "application/json"
        },
        body:   JSON.stringify(loginInfo)
    });
    return await response.json();
}