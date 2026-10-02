document.addEventListener("DOMContentLoaded", initApp);

import { deleteWorkShift, fetchWorkshifts } from "./workingShiftAPI.js";

const table = document.querySelector("#workShift-list");
const createBtn = document.querySelector("#create-btn");

async function initApp() {
    createBtn.addEventListener("click", openWorkShiftCreator);
    table.addEventListener("click", handleTableClick);
    displayWorkShifts(await fetchWorkshifts());
}



function displayWorkShifts(workShiftList) {
    table.innerHTML = "";
    workShiftList.forEach(workShift => {
        renderWorkShift(workShift);
    })
}

function renderWorkShift(workShift) {
    const row = document.createElement("tr");
    row.setAttribute("data-id", workShift.id);
    row.innerHTML = `
        <td>${workShift.id}</td>
        <td>${workShift.date}</td>
        <td>${workShift.startTime}</td>
        <td>${workShift.startTime}</td>
        <td>${workShift.endTime}</td>
        <td>${workShift.employeeDTO.id}</td>
        <td>${workShift.employeeDTO.firstName} ${workShift.employeeDTO.lastName}</td>
        <td>
            <button class="btn btn-warning" data-action="edit">Edit</button>
            <button class="btn btn-danger" data-action="delete">Delete</button>
        </td>
    `;
    table.appendChild(row);
}

function openWorkShiftCreator() {

}

async function handleTableClick(event) {
    const action = event.target.getAttribute("data-action");
    const row = event.target.closest("tr");
    const id = row.getAttribute("data-id");
    if (action === "delete") {
        const confirmed = confirm("Er du sikker på du vil slette vagten?")

        if (!confirmed) {
            return;
        }

        await deleteWorkShift(id);
        row.remove();
    } else if (action === "edit") {
        console.log("edit clicked");
    }
}


