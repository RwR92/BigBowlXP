document.addEventListener("DOMContentLoaded", initApp);

import { getAllSalesFromSpecificMonth, getAllSalesWithinLastMonth } from "./salesOverviewAPI.js";

const salesUl = document.querySelector(".sales-list");
const monthForm = document.querySelector(".specific-month-form");

async function initApp() {
    monthForm.addEventListener("submit", displaySpecificMonthsSale)

    await displaySales();
}

async function displaySales() {
    const salesList = await getAllSalesWithinLastMonth();

    salesList.innerHTML = "";
    renderSalesInformation(salesList);
}

async function displaySpecificMonthsSale(event) {
    event.preventDefault();

    const formData = new FormData(event.target);
    const [year, month] = formData.get("year-month").split("-");

    const monthsSaleList = await getAllSalesFromSpecificMonth(year, month);

    salesUl.innerHTML = "";
    renderSalesInformation(monthsSaleList);
}

function renderSalesInformation(salesList) {
    const salesSortedByDate = {};
    salesList.forEach(sale => {
        if (!salesSortedByDate[sale.saleDate]) {
            salesSortedByDate[sale.saleDate] = [sale];
        } else {
            salesSortedByDate[sale.saleDate].push(sale);
        }
    });
    Object.keys(salesSortedByDate).forEach(date => {
        const li = document.createElement("li");
        li.setAttribute("data-date", date);
        const [year, month, day] = date.split("-");
        li.textContent = `${day}/${month}-${year} · ${salesSortedByDate[date].length} salg`
        salesUl.appendChild(li);
    })
}