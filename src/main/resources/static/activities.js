 // ========================================
// API
// ========================================

const API_URL = "/api/reservation";


import { authUser } from "./script.js";


// ========================================
// PAGE LOAD
// ========================================

document.addEventListener(
    "DOMContentLoaded",
    initReservations
);


async function initReservations() {

    await authUser(
        document.body.dataset.role
    );

    await getReservations();

    // Add first activity automatically
    addActivityForm();
}



// ========================================
// GET RESERVATIONS
// ========================================

async function getReservations() {

    try {

        const response =
            await fetch(API_URL);


        if (!response.ok) {

            throw new Error(
                "Could not get reservations"
            );

        }


        const reservations =
            await response.json();


        displayReservations(
            reservations
        );


    } catch (error) {

        console.error(
            "Error loading reservations:",
            error
        );

    }

}



// ========================================
// DISPLAY RESERVATIONS
// ========================================

function displayReservations(
    reservations
) {

    const tableBody =
        document.getElementById(
            "reservationTableBody"
        );


    tableBody.innerHTML = "";


    // No reservations

    if (reservations.length === 0) {

        const row =
            document.createElement("tr");


        row.innerHTML = `
            <td colspan="4">
                No reservations found.
            </td>
        `;


        tableBody.appendChild(row);

        return;

    }



    // One row per reservation

    reservations.forEach(
        reservation => {

            const row =
                document.createElement("tr");


            // Create activity HTML

            const activitiesHTML =
                reservation.activities
                    .map(activity => {

                        const lanes =
                            activity.lanes
                                .map(
                                    lane =>
                                        lane.laneNumber
                                )
                                .join(", ");


                        return `
                            <div class="activity">

                                <strong>
                                    ${activity.type}
                                </strong>

                                <br>

                                ${activity.startTime}
                                -
                                ${activity.endTime}

                                <br>

                                Guests:
                                ${activity.guests}

                                <br>

                                Lane:
                                ${lanes || "None"}

                            </div>

                            <hr>
                        `;

                    })
                    .join("");


            row.innerHTML = `

                <td>
                    ${reservation.id}
                </td>


                <td>
                    ${reservation.name}
                </td>


                <td>
                    ${activitiesHTML}
                </td>


                <td>

                    <button
                            class="delete-btn">

                        Delete

                    </button>

                </td>

            `;


            tableBody.appendChild(row);


            // Delete button

            const deleteButton =
                row.querySelector(
                    ".delete-btn"
                );


            deleteButton.addEventListener(
                "click",
                () => {

                    deleteReservation(
                        reservation.id
                    );

                }
            );

        }
    );

}



// ========================================
// ADD ACTIVITY FORM
// ========================================

let activityNumber = 0;


function addActivityForm() {

    activityNumber++;


    const container =
        document.getElementById(
            "activitiesContainer"
        );


    const activityDiv =
        document.createElement("div");


    activityDiv.classList.add(
        "activity-form"
    );


    activityDiv.dataset.activityNumber =
        activityNumber;


    activityDiv.innerHTML = `

        <h4>
            Activity ${activityNumber}
        </h4>


        <div>

            <label>
                Start Tid
            </label>

            <input
                    type="datetime-local"
                    class="activity-start"
                    required>

        </div>


        <div>

            <label>
                Slut Tid
            </label>

            <input
                    type="datetime-local"
                    class="activity-end"
                    required>

        </div>


        <div>

            <label>
                Antal Gæster
            </label>

            <input
                    type="number"
                    class="activity-guests"
                    min="1"
                    required>

        </div>


        <div>

            <label>
                Aktivitetstype
              
            </label>

            <select
                    class="activity-type"
                    required>

                <option value="">
                    Choose type
                </option>

                <option value="BOWLING">
                    Bowling
                </option>

                <option value="AIRHOCKEY">
                    Airhockey
                </option>

                <option value="DINING">
                    Spisning
                </option>

            </select>

        </div>


        <div>

            <label>
                Bane
            </label>

            <input
                    type="number"
                    class="activity-lane"
                    min="1">

        </div>


        <button
                type="button"
                class="remove-activity">

            Slet aktivitet
           

        </button>


        <hr>    

    `;


    container.appendChild(
        activityDiv
    );


    // Remove activity button

    const removeButton =
        activityDiv.querySelector(
            ".remove-activity"
        );


    removeButton.addEventListener(
        "click",
        () => {

            activityDiv.remove();

        }
    );

}



// ========================================
// ADD ACTIVITY BUTTON
// ========================================

document
    .getElementById(
        "addActivityButton"
    )
    .addEventListener(
        "click",
        addActivityForm
    );



// ========================================
// CREATE RESERVATION
// ========================================

document
    .getElementById(
        "reservationForm"
    )
    .addEventListener(
        "submit",
        addReservation
    );



async function addReservation(
    event
) {

    event.preventDefault();


    // Reservation name

    const reservationName =
        document.getElementById(
            "reservationName"
        ).value;



    // Find all activity forms

    const activityForms =
        document.querySelectorAll(
            ".activity-form"
        );


    const activities = [];



    // Convert every form into an ActivityDTO

    activityForms.forEach(
        activityForm => {


            const startTime =
                activityForm
                    .querySelector(
                        ".activity-start"
                    )
                    .value;


            const endTime =
                activityForm
                    .querySelector(
                        ".activity-end"
                    )
                    .value;


            const guests =
                activityForm
                    .querySelector(
                        ".activity-guests"
                    )
                    .value;


            const type =
                activityForm
                    .querySelector(
                        ".activity-type"
                    )
                    .value;


            const laneId =
                activityForm
                    .querySelector(
                        ".activity-lane"
                    )
                    .value;



            // Create ActivityDTO

            const activity = {

                type: type,

                startTime: startTime,

                endTime: endTime,

                guests: Number(
                    guests
                ),

                lanes: laneId
                    ? [
                        {
                            id: Number(
                                laneId
                            )
                        }
                    ]
                    : []

            };


            activities.push(
                activity
            );

        }
    );



    // Make sure there is at least
    // one activity

    if (activities.length === 0) {

        alert(
            "Add at least one activity."
        );

        return;

    }



    // Create ReservationDTO

    const reservation = {

        name: reservationName,

        activities: activities

    };


    console.log(
        "Sending reservation:",
        reservation
    );



    // ====================================
    // SEND TO BACKEND
    // ====================================

    try {

        const response =
            await fetch(
                API_URL,
                {

                    method: "POST",

                    headers: {

                        "Content-Type":
                            "application/json"

                    },

                    body:
                        JSON.stringify(
                            reservation
                        )

                }
            );



        if (!response.ok) {

            const errorText =
                await response.text();


            console.error(
                "Server returned:",
                response.status,
                errorText
            );

            alert("Kunne ikke oprette reservation: " + errorText);


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



        // Reset form

        document
            .getElementById(
                "reservationForm"
            )
            .reset();


        // Remove activity forms

        document
            .getElementById(
                "activitiesContainer"
            )
            .innerHTML = "";


        // Reset activity counter

        activityNumber = 0;


        // Add a new empty activity

        addActivityForm();


        // Reload reservations

        await getReservations();


    } catch (error) {

        console.error(
            "Error creating reservation:",
            error
        );

    }

}



// ========================================
// DELETE RESERVATION
// ========================================

async function deleteReservation(
    id
) {

    try {

        const response =
            await fetch(
                `${API_URL}/${id}`,
                {
                    method: "DELETE"
                }
            );



        if (!response.ok) {

            throw new Error(
                "Could not delete reservation"
            );

        }



        await getReservations();


    } catch (error) {

        console.error(
            "Error deleting reservation:",
            error
        );

    }

}