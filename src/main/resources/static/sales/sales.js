document.addEventListener("DOMContentLoaded", initApp);

import {getAllSalesFromSpecificMonth, getAllSalesWithinLastMonth} from "./salesAPI.js";

const table = document.querySelector(".sales-table");
const yearMonth = document.querySelector("#year-month")

async function initApp() {
    yearMonth.addEventListener("submit", displaySpecificMonthsSale)




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

async function displaySpecificMonthsSale(event) {
    const formData = new FormData(event.target);
    const date = formData.get("year-month");
    const year = date.getFullYear();
    const month = date.getMonth() + 1;
    const monthsSaleList = await getAllSalesFromSpecificMonth(year, month);
    table.innerHTML = "";
    monthsSaleList.forEach(sale => {
        renderSalesInformation(sale)
    });
}

function renderSalesInformation(sale) {
    const row = document.createElement("tr");
    row.innerHTML = `${sale.date}`;
    table.appendChild(row);
}