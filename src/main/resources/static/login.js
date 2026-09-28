document.addEventListener("DOMContentLoaded", initApp);
const BASE_URL_LOGIN = "/api/login";

const formData = [];

async function initApp() {
    document.querySelector("#loginForm").addEventListener("submit", handleLoginSubmit)
}

async function handleLoginSubmit(e) {
    e.preventDefault();
    const form = new FormData(e.target);
    const username = form.get("username");
    const password = form.get("password");

    const formData = {username, password};

    const userType = tryLogin(formData);
    if(userType.toString() === "admin"){
        window.location.href = "test-user-page.html";
    } else if (userType.toString() === "employee"){
        window.location.href = "login.html";
    }
}

async function tryLogin(loginInfo){
    const response= await fetch(BASE_URL_LOGIN, {
        method: "GET",
        headers: {
            "Content-type": "application/json"
        },
        body:   JSON.stringify(loginInfo)
    });
    return await response.json();

}