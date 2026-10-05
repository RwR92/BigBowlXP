document.addEventListener("DOMContentLoaded", initActivityCheck)
const API_URL = "/api/activities/availability";
import {initApp} from "./app.js"

async function initActivityCheck() {
    await initApp();
    document.querySelector("#loadAvailable").addEventListener("click", loadAvailability);
}
async function loadAvailability() {

    const type = document.getElementById("type").value;
    const date = document.getElementById("date").value;

    if (!type || !date) {
        alert("Vælg både aktivitet og dato.");
        return;
    }
    console.log(date);
    try {
        const response = await fetch(`${API_URL}?type=${type}&date=${date}`);

        if (!response.ok) {
            throw new Error("Kunne ikke hente ledige tider");
        }

        const timeSlots = await response.json();

        displayAvailability(timeSlots);

    } catch (error) {
        console.error("Fejl ved hentning af ledige tider:", error);
        alert("Der skete en fejl. Prøv igen.");
    }
}

function displayAvailability(timeSlots) {

    const tableBody = document.getElementById("availabilityTableBody");
    tableBody.innerHTML = "";

    timeSlots.forEach(slot => {

        const row = document.createElement("tr");

        const startTime = formatTime(slot.start);
        const endTime = formatTime(slot.end);
        const count = slot.availableLaneNumbers.length;

        row.innerHTML = `
        <td>${startTime} - ${endTime}</td>
        <td class="${count > 0 ? 'available' : 'unavailable'}">${count}</td>
        <td>${slot.availableLaneNumbers.join(", ") || "Ingen ledige"}</td>
      `;

        tableBody.appendChild(row);
    });
}

function formatTime(isoDateTime) {
    const date = new Date(isoDateTime);
    return date.toLocaleTimeString("da-DK", { hour: "2-digit", minute: "2-digit" });
}