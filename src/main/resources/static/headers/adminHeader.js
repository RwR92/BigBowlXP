import {appendChild} from "./header.js";

document.addEventListener("DOMContentLoaded", initApp);


async function initApp(){
    renderHeader();
}

function renderHeader(){
    let childArray = [];

    const logoutBtn = document.createElement("button");
    logoutBtn.textContent = "Logout";
    logoutBtn.setAttribute("id", "logout-btn");

    const homeBtn = document.createElement("button");
    homeBtn.textContent = "Hjem";
    homeBtn.setAttribute("id", "home-btn");
    homeBtn.setAttribute("onclick", "window.location.href='/admin-home-page.html'")

    const shiftBtn = document.createElement("button");
    shiftBtn.textContent = "Shifts";
    shiftBtn.setAttribute("id", "shift-btn");
    shiftBtn.setAttribute("onclick", "window.location.href='/workingShift/workingShift.html'")

    /*const reservationBtn = document.createElement("button");
    reservationBtn.textContent = "Reservationer";
    reservationBtn.setAttribute("id", "reservation-btn");
    reservationBtn.setAttribute("onclick", "window.location.href='/reservation/reservation.html'")*/

    childArray = [logoutBtn, homeBtn, shiftBtn];
    const headerId = document.querySelector("#employee-header").
    getAttribute("id");

    for (const child of childArray){
        appendChild(child, headerId);
    }
}


