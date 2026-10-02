document.addEventListener("DOMContentLoaded", initApp);

import { deleteWorkShift, fetchWorkshifts, addWorkShift } from "./workingShiftAPI.js";

const table = document.querySelector("#workShift-list");
const createBtn = document.querySelector("#create-btn");
const popupContainer = document.querySelector(".popup-container");
const popupAdd = document.querySelector(".popup-add");
const activityMessagePopup = document.querySelector("#activity-message-popup");

async function initApp() {
    createBtn.addEventListener("click", openWorkShiftCreator);
    table.addEventListener("click", handleTableClick);
    popupContainer.addEventListener("click", event => {
        if (event.target === popupContainer) {
            popupContainer.classList.toggle("hidden")
        }
    })
    popupAdd.addEventListener("submit", handlePopupAddSubmit);

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
    popupContainer.classList.toggle("hidden");
}

async function handleTableClick(event) {
    const action = event.target.getAttribute("data-action");
    const row = event.target.closest("tr");
    console.log("click");
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

async function handlePopupAddSubmit(event) {
    event.preventDefault();
    console.log("click submit")
    const formData = new FormData(event.target);
    const startTime = formData.get("start-time");
    const endTime = formData.get("end-time");
    const date = formData.get("date");
    const employeeId = formData.get("employee-id");
    const workShiftData = {
        startTime,
        endTime,
        date,
        employeeId
    }
    console.log(workShiftData);
    try {
        await addWorkShift(workShiftData);
        popupContainer.classList.add("hidden");
    } catch (error) {
        activityMessagePopup.textContent = error.message;
    }

}


