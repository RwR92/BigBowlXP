document.addEventListener("DOMContentLoaded", initApp);

import { getAllSalesFromSpecificMonth, getAllSalesWithinLastMonth } from "./salesOverviewAPI.js";

const salesUl = document.querySelector(".sales-list");
const monthForm = document.querySelector(".specific-month-form");

/* dynamic GOATED stuff (ts is so beautiful) *queue tears* */
const salesDrawerDiv = document.querySelector(".sales-drawer");
const drawerCloseButton = document.querySelector(".drawer-close");


let salesGroupedByDate = {};

async function initApp() {
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
    const drawerSalesDiv = document.querySelector(".drawer-sales");
    drawerSalesDiv.innerHTML = "";
    sales.forEach(sale => {
        const ul = document.createElement("ul");
        const li1 = document.createElement("li");
        const li2 = document.createElement("li");

        li1.textContent = sale.id;
        li2.textContent = sale.saleDate;

        ul.appendChild(li1);
        ul.appendChild(li2);

        sale.items.forEach(product => {
            const productLi1 = document.createElement("li");
            const productLi2 = document.createElement("li");
            const productLi3 = document.createElement("li");

            productLi1.textContent = product.name;
            productLi2.textContent = product.quantity;
            productLi3.textContent = product.price;

            ul.appendChild(productLi1);
            ul.appendChild(productLi2);
            ul.appendChild(productLi3);
        })

        drawerSalesDiv.appendChild(ul);
    })
    salesDrawerDiv.classList.remove("hidden");
}

function closeDrawer() {
    salesDrawerDiv.classList.add("hidden");
}