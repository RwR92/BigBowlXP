// API URL
const API_URL = "/api/activities";


// Load reservations when page opens
document.addEventListener("DOMContentLoaded", getReservations);


// ------------------------------------
// GET ALL RESERVATIONS
// ------------------------------------

async function getReservations() {

    try {

        const response = await fetch(API_URL);

        if (!response.ok) {
            throw new Error("Could not get reservations");
        }

        const reservations = await response.json();

        displayReservations(reservations);

    } catch (error) {

        console.error("Error loading reservations:", error);

    }
}


// ------------------------------------
// DISPLAY RESERVATIONS
// ------------------------------------

function displayReservations(reservations) {

    const tableBody =
        document.getElementById("reservationTableBody");

    tableBody.innerHTML = "";


    // No reservations
    if (reservations.length === 0) {

        const row = document.createElement("tr");

        row.innerHTML = `
                <td colspan="6">
                    No reservations found.
                </td>
            `;

        tableBody.appendChild(row);

        return;
    }


    // Create a row for every reservation
    reservations.forEach(reservation => {

        const row = document.createElement("tr");

        row.innerHTML = `
                <td>${reservation.id}</td>
                <td>${reservation.startTime}</td>
                <td>${reservation.endTime}</td>
                <td>${reservation.guests}</td>
                <td>${reservation.type}</td>
                <td>
    ${reservation.lanes.map(lane => lane.laneNumber).join(", ")}
</td>

                <td>
                    <button onclick="deleteReservation(${reservation.id})">
                        Delete
                    </button>
                </td>
            `;

        tableBody.appendChild(row);

    });

}


// ------------------------------------
// CREATE RESERVATION
// ------------------------------------

document
    .getElementById("reservationForm")
    .addEventListener("submit", addReservation);

async function addReservation(event) {

    event.preventDefault();


    const startTime =
        document.getElementById("startTime").value;

    const endTime =
        document.getElementById("endTime").value;

    const guests =
        document.getElementById("guests").value;

    const type =
        document.getElementById("type").value;

    const laneId =
        document.getElementById("laneId").value;


    const reservation = {

        type: type,

        startTime: startTime,

        endTime: endTime,

        lanes: [
            {
                id: Number(laneId)
            }
        ],

        guests: Number(guests)

    };


    console.log("Sending reservation:", reservation);


    try {

        const response = await fetch(API_URL, {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify(reservation)

        });


        if (!response.ok) {

            const errorText = await response.text();

            console.error(
                "Server returned:",
                response.status,
                errorText
            );

            throw new Error(
                "Could not create reservation"
            );
        }


        const createdReservation =
            await response.json();

        console.log(
            "Reservation created:",
            createdReservation
        );

        document.getElementById("message").textContent = "Reservation " + createdReservation.id + " is created";


        document
            .getElementById("reservationForm")
            .reset();


        getReservations();


    } catch (error) {

        console.error(
            "Error creating reservation:",
            error
        );

    }

}


// ------------------------------------
// DELETE RESERVATION
// ------------------------------------

async function deleteReservation(id) {

    try {

        const response = await fetch(
            `${API_URL}/${id}`,
            {
                method: "DELETE"
            }
        );


        if (!response.ok) {
            throw new Error("Could not delete reservation");
        }


        // Reload reservations
        getReservations();


    } catch (error) {

        console.error(
            "Error deleting reservation:",
            error
        );

    }

}
