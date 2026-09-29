document.addEventListener("DOMContentLoaded", initApp);

async function initApp() {
    await handleRoute();
    console.log(sessionStorage.getItem("user"))
}

async function handleRoute(){
   const response = await fetch("/api/auth", {
       method: "GET",
       headers: {
           "Content-type": "application/json"
       },
       body: JSON.stringify()
   })

    const user = await response.json();
   console.log(user);

    if(response.ok){
        if(user.userType === "admin"){
            window.location.href = "test-user-page.html";
        } else if (user.userType === "employee"){
            window.location.href = "login.html";
        } else {
            window.location.href = "login.html";
        }
    }

}
