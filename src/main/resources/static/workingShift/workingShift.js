document.addEventListener("DOMContentLoaded", initApp);

import {deleteWorkShift, fetchWorkshifts, addWorkShift, editWorkShift, getPersonById} from "./workingShiftAPI.js";

const table = document.querySelector("#workShift-list");
const createBtn = document.querySelector("#create-btn");
const popupContainer = document.querySelector(".popup-container");
const popupForm = document.querySelector(".popup-form");
const activityMessagePopup = document.querySelector("#activity-message-popup");
const h3Form = document.querySelector("#h3-form");

// popup data
const startTime = document.querySelector("#start-time");
const endTime = document.querySelector("#end-time");
const date = document.querySelector("#date");
const employeeId = document.querySelector("#employee-id");

async function initApp() {
    createBtn.addEventListener("click", () => {
        resetPopup();
        popupContainer.classList.remove("hidden");
    });
    table.addEventListener("click", handleTableClick);
    popupContainer.addEventListener("click", event => {
        if (event.target === popupContainer) {
            const confirmed = confirm("Er du sikker på at du vil annullere indholdet?")
            if (confirmed) {
                resetPopup();
                popupContainer.classList.add("hidden")
            }
        }
    })
    popupForm.addEventListener("submit", handlePopupFormSubmit);

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

function renderUpdatedWorkShift(workShift) {
    const row = table.querySelector(`tr[data-id="${workShift.id}"]`);

    row.children[0].textContent = workShift.id;
    row.children[1].textContent = workShift.startTime;
    row.children[2].textContent = workShift.endTime;
    row.children[3].textContent = workShift.date;
    row.children[4].textContent = workShift.employeeDTO.id;
    row.children[5].textContent = `${workShift.employeeDTO.firstName} ${workShift.employeeDTO.lastName}`;
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

        const success = await deleteWorkShift(id);
        if (success) {
            row.remove();
        }
    } else if (action === "edit") {
        await showEditForm(id);
    }
}

async function showEditForm(id) {
    popupForm.setAttribute("data-acton", "edit");
    popupForm.setAttribute("data-id", id);
    h3Form.textContent = "Rediger eksisterende vagt";

    try {
        const workShift = await getPersonById(id);

        startTime.value = workShift.startTime;
        endTime.value = workShift.endTime;
        date.value = workShift.date;
        employeeId.value = workShift.employeeDTO.id;

        popupContainer.classList.remove("hidden");
    } catch (error) {
        console.log(error.message);
    }
}

async function handlePopupFormSubmit(event) {
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

    const formType = popupForm.getAttribute("data-action");
    if (formType === "create") {
        try {
            await addWorkShift(workShiftData);
            popupContainer.classList.add("hidden");
        } catch (error) {
            activityTextMessage(error.message);
        }
    } else if (formType === "edit") {
        const id = popupForm.getAttribute("data-id");

        try {
            const editedWorkShift = await editWorkShift(id, workShiftData);

            renderUpdatedWorkShift(editedWorkShift);

            popupContainer.classList.add("hidden");
        } catch (error) {
            activityTextMessage(error.message);
        }
    }
}

function resetPopup() {
    popupForm.reset();
    popupForm.setAttribute("data-action", "create");
    h3Form.textContent = "Opret ny vagt";

}

 // message disappears after five seconds
function activityTextMessage(message) {
    activityMessagePopup.textContent = message;
    activityMessagePopup.classList.remove("hidden");
    setTimeout(() => {
        activityMessagePopup.textContent = "";
        activityMessagePopup.classList.add("hidden");
    }, 5000);
}

