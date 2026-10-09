document.addEventListener("DOMContentLoaded", initSaleOverviewApp);

import {getAllSalesFromSpecificMonth, getAllSalesWithinLastMonth} from "./salesOverviewAPI.js";
import {initApp} from "../app.js"

const salesUl = document.querySelector(".sales-list");
const monthForm = document.querySelector(".specific-month-form");

/* dynamic GOATED stuff (ts is so beautiful) *queue tears* */
const salesDrawerContainer = document.querySelector(".sales-drawer");
const drawerCloseButton = document.querySelector(".drawer-close");
const drawerSales = document.querySelector(".drawer-sales");


let salesGroupedByDate = {};

async function initSaleOverviewApp() {
    await initApp();
    monthForm.addEventListener("submit", displaySpecificMonthsSale)
    salesUl.addEventListener("click", handleListClick);
    drawerCloseButton.addEventListener("click", closeDrawer);
    await displaySales();
}

async function displaySales() {
    const salesList = await getAllSalesWithinLastMonth();

    salesUl.innerHTML = "";
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
    salesGroupedByDate = {};

    salesList.forEach(sale => {
        if (!salesGroupedByDate[sale.saleDate]) {
            salesGroupedByDate[sale.saleDate] = [sale];
        } else {
            salesGroupedByDate[sale.saleDate].push(sale);
        }
    });
    Object.keys(salesGroupedByDate).forEach(date => {
        const li = document.createElement("li");
        li.setAttribute("data-date", date);
        const [year, month, day] = date.split("-");
        li.textContent = `${day}/${month}-${year} · ${salesGroupedByDate[date].length} salg`
        salesUl.appendChild(li);
    })
}

function handleListClick(event) {
    const liElement = event.target.closest("li");
    const date = liElement.getAttribute("data-date");
    const [year, month, day] = date.split("-")
    const sales = salesGroupedByDate[date];

    document.querySelector(".drawer-date").textContent = `${day}/${month}-${year}`;

    drawerSales.innerHTML = "";
    console.log(drawerSales);
    sales.forEach(sale => {
        const saleDiv = document.createElement("div");
        const header3Div = document.createElement("div");
        const h3 = document.createElement("h3");

        saleDiv.classList.add("sale-container");
        header3Div.classList.add("sale-header-container");

        h3.textContent = `Salg #${sale.id}`;

        header3Div.appendChild(h3);
        saleDiv.appendChild(header3Div);

        const tableDiv = document.createElement("div");
        const table = document.createElement("table");
        const thead = document.createElement("thead");
        const theadRow = document.createElement("tr");
        const theadTh1 = document.createElement("th");
        const theadTh2 = document.createElement("th");
        const theadTh3 = document.createElement("th");
        const theadTh4 = document.createElement("th");

        tableDiv.classList.add("sale-table-container");

        theadTh1.textContent = "Produkt";
        theadTh2.textContent = "Antal";
        theadTh3.textContent = "Pris";
        theadTh4.textContent = "Total";

        theadRow.appendChild(theadTh1);
        theadRow.appendChild(theadTh2);
        theadRow.appendChild(theadTh3);
        theadRow.appendChild(theadTh4);
        thead.appendChild(theadRow);
        table.appendChild(thead);

        const tbody = document.createElement("tbody");
        let totalPrice = 0;
        sale.items.forEach(product => {
            const row = document.createElement("tr");
            const productTd1 = document.createElement("td");
            const productTd2 = document.createElement("td");
            const productTd3 = document.createElement("td");
            const productTd4 = document.createElement("td");

            productTd1.textContent = product.name;
            productTd2.textContent = product.quantity;
            productTd3.textContent = `${product.price} kr`;
            const total = product.price * product.quantity;
            productTd4.textContent = `${total} kr`;
            totalPrice += total;

            row.appendChild(productTd1);
            row.appendChild(productTd2);
            row.appendChild(productTd3);
            row.appendChild(productTd4);
            tbody.appendChild(row);
        })

        const totalPriceDiv = document.createElement("div");
        const totalPriceSpan = document.createElement("span");
        totalPriceDiv.classList.add("total-price-container");
        totalPriceSpan.textContent = `Samlet pris: ${totalPrice} kr`;
        totalPriceDiv.appendChild(totalPriceSpan);


        table.appendChild(tbody);
        tableDiv.appendChild(table);
        saleDiv.appendChild(tableDiv);
        saleDiv.appendChild(totalPriceDiv);
        drawerSales.appendChild(saleDiv);

    });
    salesDrawerContainer.classList.remove("hidden");
}

function closeDrawer() {
    salesDrawerContainer.classList.add("hidden");
}