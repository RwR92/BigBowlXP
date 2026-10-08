document.addEventListener("DOMContentLoaded", initApp);
let count = 0;
async function initApp() {
     await authUser(document.body.dataset.role);
    console.log(sessionStorage.getItem("user"))
}
export async function authUser(dataRole){
    const response = await fetch("/api/auth", {
        method: "GET"
    })
    let userType = null;
    if (response.ok) {
        const user = await response.json();
        userType = user.userType;
    }

    if(userType !== dataRole){
       await handleRoute(dataRole,userType);
    }
}
async function handleRoute(dataRole,userType) {
    count ++;
    console.log("Vi kom ind i handleRoute: "+count+" gange");

    if (!userType) {
        if (dataRole) {
            window.location.replace("/login.html");
        }
    }

    if (userType !== dataRole) {
        if (userType === "admin") {
            window.location.replace("admin-home-page.html");
        } else if (userType === "employee") {
            window.location.replace("employee-home-page.html");
        }
    }
}