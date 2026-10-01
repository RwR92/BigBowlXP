document.addEventListener("DOMContentLoaded", initApp);
let count = 0;
async function initApp() {
     await handleRoute(document.body.dataset.role);
    console.log(sessionStorage.getItem("user"))
}

export async function handleRoute(dataRole) {
    count ++;
    const response = await fetch("/api/auth", {
        method: "GET",
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify()
    })

    let userType = null;
    if (response.ok) {
        const user = await response.json();
        userType = user.userType;
    }

    if (!userType) {
        if (dataRole) {
            window.location.replace("login.html");
        }
       // return;
    }

    if (userType !== dataRole) {
        if (userType === "admin") {
            window.location.replace("test-admin-page.html");
        } else if (userType === "employee") {
            window.location.replace("test-employee-page.html");
        }
    }
    console.log(count);
}