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

    const response = await tryLogin(formData);
    console.log(response)
    console.log(response.userType);
    if(response.userType === "admin"){
        window.location.href = "test-user-page.html";
    } else if (response === "employee"){
        window.location.href = "login.html";
    }
    console.log(response+" "+ response.userType);
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