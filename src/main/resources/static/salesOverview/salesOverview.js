document.addEventListener("DOMContentLoaded", initApp);

import { getAllSalesFromSpecificMonth, getAllSalesWithinLastMonth } from "./salesOverviewAPI.js";

const table = document.querySelector(".sales-table");
const monthForm = document.querySelector(".specific-month-form");

async function initApp() {
    monthForm.addEventListener("submit", displaySpecificMonthsSale)

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
    event.preventDefault();

    const formData = new FormData(event.target);
    const [year, month] = formData.get("year-month").split("-");

    const monthsSaleList = await getAllSalesFromSpecificMonth(year, month);

    table.innerHTML = "";
    monthsSaleList.forEach(sale => {
        renderSalesInformation(sale)
    });
}

function renderSalesInformation(sale) {
    const row = document.createElement("tr");

    const td1 = document.createElement("td");
    td1.textContent = sale.id;
    const td2 = document.createElement("td");
    td2.textContent = sale.saleDate;

    row.appendChild(td1);
    row.appendChild(td2);

    const td3 = document.createElement("td");
    const tableInTd = document.createElement("table");


    for (const s of sale.items) {
        const tableRow = document.createElement("tr");
        const tableData1 = document.createElement("td");
        tableData1.textContent = s.name;
        const tableData2 = document.createElement("td");
        tableData2.textContent = s.quantity;
        const tableData3 = document.createElement("td");
        tableData3.textContent = s.price;

        tableRow.appendChild(tableData1);
        tableRow.appendChild(tableData2);
        tableRow.appendChild(tableData3);
        tableInTd.appendChild(tableRow);
    }

    td3.appendChild(tableInTd);
    row.appendChild(td3);
    table.appendChild(row);
}