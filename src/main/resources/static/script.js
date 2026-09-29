document.addEventListener("DOMContentLoaded", initApp);

async function initApp() {
    await handleRoute();
    console.log(sessionStorage.getItem("user"))
}

async function handleRoute(){
   const response = await fetch("api/auth", {
       method: "GET",
       headers: {
           "Content-type": "application/json"
       },
       body: JSON.stringify()
   })
    window.location.href = "login.html";
}
