document.addEventListener("DOMContentLoaded", initApp);

const BASE_URL_LOGOUT = "api/logout";
async function initApp(){
document.querySelector("#logout-btn").addEventListener("click", logout)
}

async function logout(){
const response = await fetch(BASE_URL_LOGOUT,{ method: "POST"});

return window.location.href = "login.html";
}
