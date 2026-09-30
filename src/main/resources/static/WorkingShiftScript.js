document.addEventListener("DOMContentLoaded" ,initApp);

const url = "http://localhost:8080/working-shift/display";
let workingShiftArray = [];

async function initApp() {
    await loadData();
    updateDisplay(workingShiftArray);
}

async function fetchWorkingShifts() {
    const response = await fetch(url);

    if(!response.ok) {
        throw new Error(`Failed To Fetch ${response.status}`) 
    }

    return await response.json();
}

async function loadData() {
    workingShiftArray = await fetchWorkingShifts();
}

function createElements(json) {   
    const row = document.createElement("tr");
    row.setAttribute("data-id", json.id);
    row.innerHTML = `
        <td>${json.date}</td>
        <td>${json.startTime}</td>
        <td>${json.endTime}</td>
        <td>${json.employee.firstName}</td>
    `;
    return row;
}

function display(array) {
    const tableBody = document.querySelector("#workingShiftTableBody");

    for (const shift of array) {
        tableBody.appendChild(createElements(shift));
    }
}

function updateDisplay(array) {
    const tablehead = document.querySelector("#workingShiftTableBody")
    tablehead.innerHTML = "";
    display(array);
}