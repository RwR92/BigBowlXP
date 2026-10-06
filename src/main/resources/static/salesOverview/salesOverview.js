document.addEventListener("DOMContentLoaded", initApp);

import {getAllSalesWithinLastMonth} from "./salesOverviewAPI.js";

const table = document.querySelector(".sales-table");

async function initApp() {
    await displaySales();
}

async function displaySales() {
    const salesList = await getAllSalesWithinLastMonth();
    table.innerHTML = "";
    console.log("sales:", salesList);
    salesList.forEach(sale => {
        renderSalesInformation(sale)
    });
}

function renderSalesInformation(sale) {
    const row = document.createElement("tr");
    row.innerHTML = `${sale.date}`;
    table.appendChild(row);
}