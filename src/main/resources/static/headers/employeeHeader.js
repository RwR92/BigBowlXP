document.addEventListener("DOMContentLoaded", initApp);
import {appendChild} from "./header.js";

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
    homeBtn.setAttribute("onclick", "window.location.href='/employee-home-page.html'")

    const reservationBtn = document.createElement("button");
    reservationBtn.textContent = "Reservationer";
    reservationBtn.setAttribute("id", "reservation-btn");
    reservationBtn.setAttribute("onclick", "window.location.href='/reservation/reservation.html'")

    const saleOverviewBtn = document.createElement("button");
    saleOverviewBtn.textContent = "Se Salg";
    saleOverviewBtn.setAttribute("id", "sale-overview-btn");
    saleOverviewBtn.setAttribute("onclick", "window.location.href='/salesOverview/salesOverview.html'")

    const saleBtn = document.createElement("button");
    saleBtn.textContent = "Opret salg";
    saleBtn.setAttribute("id", "sale-btn");
    saleBtn.setAttribute("onclick", "window.location.href='/sales/sale.html'")

    childArray = [logoutBtn, homeBtn, reservationBtn, saleOverviewBtn, saleBtn];

    const headerId = document.querySelector("#employee-header").
    getAttribute("id");

    console.log("Header Id: "+headerId);
    for(const child of childArray){
        appendChild(child, headerId);
    }
}