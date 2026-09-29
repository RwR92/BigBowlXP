document.addEventListener("DOMContentLoaded", initApp);
const BASE_URL_LOGIN = "/api/login";
console.log(sessionStorage.getItem("user"))
const formData = [];

async function initApp() {
    sessionStorage.removeItem("user");
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
        window.location.href = "test-user-page.html";
    } else if (response === "employee"){
        window.location.href = "login.html";
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