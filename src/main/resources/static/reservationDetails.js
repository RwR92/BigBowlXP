const API_URL = "/api/admin/reservation";
const TYPE_NAMES = { BOWLING: "Bowling", AIRHOCKEY: "Airhockey", DINING: "Spisning" };

const params = new URLSearchParams(window.location.search);
const reservationId = params.get("id");

document.addEventListener("DOMContentLoaded", loadReservation);

async function loadReservation() {

    const message = document.getElementById("message");

    if (!reservationId) {
        message.textContent = "Ingen reservation valgt.";
        return;
    }

    try {
        const response = await fetch(`${API_URL}/${reservationId}`);

        if (response.status === 404) {
            message.textContent = "Reservationen findes ikke.";
            return;
        }

        if (!response.ok) {
            throw new Error("Kunne ikke hente reservationen");
        }

        const reservation = await response.json();

        displayReservation(reservation);

    } catch (error) {
        console.error("Fejl ved hentning af reservation:", error);
        message.textContent = "Der skete en fejl. Prøv igen.";
    }
}

function displayReservation(reservation) {

    document.getElementById("message").hidden = true;
    document.getElementById("info").hidden = false;

    document.getElementById("number").textContent = reservation.reservationNumber;
    document.getElementById("created").textContent =
        new Date(reservation.createdAt).toLocaleString("da-DK");

    if (reservation.customer) {
        const customer = reservation.customer;
        document.getElementById("customer").textContent =
            customer.firstName + " " + customer.lastName
            + " (" + customer.email + ", " + customer.number + ")";
    } else {
        document.getElementById("customer").textContent = "Ingen kunde";
    }

    const activities = reservation.activities.slice();
    activities.sort(function (a, b) {
        if (a.startTime < b.startTime) return -1;
        if (a.startTime > b.startTime) return 1;
        return 0;
    });

    const tableBody = document.getElementById("activityBody");
    tableBody.innerHTML = "";

    for (const activity of activities) {
        tableBody.appendChild(createActivityRow(activity));
    }
}

function createActivityRow(activity) {

    const row = document.createElement("tr");

    const laneNames = [];
    for (const lane of activity.lanes) {
        laneNames.push((lane.type === "AIRHOCKEY" ? "Airhockey " : "Bane ") + lane.laneNumber);
    }

    const cells = [
        TYPE_NAMES[activity.type],
        formatPeriod(activity.startTime, activity.endTime),
        laneNames.length > 0 ? laneNames.join(", ") : "-",
        activity.guests !== null ? String(activity.guests) : "-"
    ];

    for (const text of cells) {
        const cell = document.createElement("td");
        cell.textContent = text;
        row.appendChild(cell);
    }

    return row;
}

function formatPeriod(startIso, endIso) {
    const start = new Date(startIso);
    const end = new Date(endIso);
    const options = { hour: "2-digit", minute: "2-digit" };

    return start.toLocaleDateString("da-DK") + " kl. "
        + start.toLocaleTimeString("da-DK", options) + " - "
        + end.toLocaleTimeString("da-DK", options);
}

async function cancelReservation() {

    if (!confirm("Er du sikker på, at du vil annullere hele reservationen?")) {
        return;
    }

    try {
        const response = await fetch(`${API_URL}/${reservationId}`, { method: "DELETE" });

        if (!response.ok) {
            throw new Error("Kunne ikke annullere reservationen");
        }

        alert("Reservationen er annulleret.");
        window.location.href = "day-overview.html";

    } catch (error) {
        console.error("Fejl ved annullering:", error);
        alert("Der skete en fejl. Prøv igen.");
    }
}