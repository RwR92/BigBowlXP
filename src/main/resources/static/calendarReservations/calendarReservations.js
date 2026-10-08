import { fetchActivities } from "./calendarReservationsAPI.js";

document.addEventListener("DOMContentLoaded", initApp);

const calendar = document.querySelector("#calendar");
const calendarTitle = document.querySelector("#calendar-title");

const previousBtn = document.querySelector("#previous-btn");
const nextBtn = document.querySelector("#next-btn");
const todayBtn = document.querySelector("#today-btn");

const viewButtons = document.querySelectorAll(".view-btn");

let currentDate = new Date();
let currentView = "month";


async function initApp() {

    previousBtn.addEventListener("click", async () => {
        moveCalendar(-1);
        await renderCalendar();
    });

    nextBtn.addEventListener("click", async () => {
        moveCalendar(1);
        await renderCalendar();
    });

    todayBtn.addEventListener("click", async () => {
        currentDate = new Date();
        await renderCalendar();
    });

    viewButtons.forEach(button => {
        button.addEventListener("click", async () => {

            currentView = button.dataset.view;

            viewButtons.forEach(btn => {
                btn.classList.remove("active");
            });

            button.classList.add("active");

            await renderCalendar();
        });
    });

    await renderCalendar();
}


/*
 når man trykker på < > knapperne rykker den med det valgte interval frem eller tilbage.
 
 Dag      ->     +/- 1 dag
 Uge      ->     +/- 1 uge
 Månede   ->     +/- 1 månede
 */
function moveCalendar(direction) {

    if (currentView === "day") {
        currentDate.setDate(
            currentDate.getDate() + direction
        );
    }

    if (currentView === "week") {
        currentDate.setDate(
            currentDate.getDate() + direction * 7
        );
    }

    if (currentView === "month") {
        currentDate.setMonth(
            currentDate.getMonth() + direction
        );
    }
}

async function renderCalendar() {

    calendar.innerHTML = "";

    let from;
    let to;

    if (currentView === "day") {

        from = startOfDay(currentDate);
        to = addDays(from, 1);

    } else if (currentView === "week") {

        from = startOfWeek(currentDate);
        to = addDays(from, 7);

    } else {

        from = startOfMonth(currentDate);
        to = startOfMonth(addMonths(currentDate, 1));
    }

    calendarTitle.textContent = getTitle(from, to);

    try {

        const activities = await fetchActivities(
            toLocalDateTimeString(from),
            toLocalDateTimeString(to)
        );

        if (currentView === "day") {
            renderDay(from, activities);
        }

        if (currentView === "week") {
            renderWeek(from, activities);
        }

        if (currentView === "month") {
            renderMonth(from, activities);
        }

    } catch (error) {
        console.error(error);

        calendar.textContent = "Kunne ikke hente kalenderdata.";
    }
}



// View for dag ----------------------------------------------------------------------------------------------------


function renderDay(date, activities) {

    const container = document.createElement("div");
    container.classList.add("day-calendar");

    const header = document.createElement("div");
    header.classList.add("day-header");

    header.textContent = formatDate(date);

    container.appendChild(header);

    const body = document.createElement("div");
    body.classList.add("day-body");

    
    // loop til at lave 24 timers kalender blokke for 1 dag
    
    for (let hour = 0; hour < 24; hour++) {

        const slot = document.createElement("div");
        slot.classList.add("time-slot");

        slot.style.top = `${hour * 60}px`;

        const label = document.createElement("div");
        label.classList.add("time-label");

        label.textContent = `${String(hour).padStart(2, "0")}:00`;

        slot.appendChild(label);

        body.appendChild(slot);
    }

    const activityContainer = document.createElement("div");
    activityContainer.classList.add("day-activities");

    activities.forEach(activity => {

        const start = new Date(activity.startTime);
        const end = new Date(activity.endTime);

        const startMinutes =
            start.getHours() * 60 + start.getMinutes();

        const endMinutes =
            end.getHours() * 60 + end.getMinutes();

        const duration = endMinutes - startMinutes;

        const element = document.createElement("div");

        element.classList.add("activity");

        element.style.top = `${startMinutes}px`;
        element.style.height = `${Math.max(duration, 30)}px`;

        element.textContent =
            `${activity.type} ${formatTime(start)} - ${formatTime(end)}`;

        activityContainer.appendChild(element);
    });

    body.appendChild(activityContainer);

    container.appendChild(body);

    calendar.appendChild(container);
}


// View for uge ---------------------------------------------------------------------------------

function renderWeek(weekStart, activities) {

    const container = document.createElement("div");

    container.classList.add("week-calendar");

    // den toppe plads vedsiden af mandag i uge kalender
    const empty = document.createElement("div");
    container.appendChild(empty);

    // Loop til at lave dagene i toppen af uge kalenderen
    for (let i = 0; i < 7; i++) {

        const date = addDays(weekStart, i);

        const day = document.createElement("div");

        day.classList.add("week-day-header");

        day.textContent = formatShortDate(date);

        container.appendChild(day);
    }

    const timeColumn = document.createElement("div");

    timeColumn.classList.add("week-time-column");


    for (let hour = 0; hour < 24; hour++) {

        const label = document.createElement("div");

        label.classList.add("week-time-label");

        label.style.top = `${hour * 60}px`;

        label.textContent =
            `${String(hour).padStart(2, "0")}:00`;

        timeColumn.appendChild(label);
    }

    container.appendChild(timeColumn);


    // Laver tidskolonnen i venstre side af uge kalenderen
    for (let dayIndex = 0; dayIndex < 7; dayIndex++) {

        const date = addDays(weekStart, dayIndex);

        const dayColumn = document.createElement("div");

        dayColumn.classList.add("week-day");

        const body = document.createElement("div");

        body.classList.add("week-day-body");

        // Laver de vandrette linjer for hver time i kalenderen
        for (let hour = 0; hour < 24; hour++) {

            const line = document.createElement("div");

            line.classList.add("week-hour-line");

            line.style.top = `${hour * 60}px`;

            body.appendChild(line);
        }


        activities.forEach(activity => {

            const start = new Date(activity.startTime);
            const end = new Date(activity.endTime);

            if (!isSameDay(start, date)) {
                return;
            }

            const startMinutes =
                start.getHours() * 60 + start.getMinutes();

            const endMinutes =
                end.getHours() * 60 + end.getMinutes();

            const duration = endMinutes - startMinutes;

            const element = document.createElement("div");

            element.classList.add("activity");

            element.style.top = `${startMinutes}px`;
            element.style.height =
                `${Math.max(duration, 30)}px`;

            element.style.left = "2px";
            element.style.right = "2px";

            element.textContent =
                `${activity.type} ${formatTime(start)} - ${formatTime(end)}`;

            body.appendChild(element);
        });

        dayColumn.appendChild(body);

        container.appendChild(dayColumn);
    }

    calendar.appendChild(container);
}


// View for månede -----------------------------------------------------------------------------

function renderMonth(monthStart, activities) {

    const container = document.createElement("div");

    container.classList.add("month-calendar");

    const dayNames = [
        "Man",
        "Tir",
        "Ons",
        "Tor",
        "Fre",
        "Lør",
        "Søn"
    ];


    dayNames.forEach(dayName => {

        const element = document.createElement("div");

        element.classList.add("month-day-name");

        element.textContent = dayName;

        container.appendChild(element);
    });

    // Finder mandagen i den uge hvor måneden starter
    const firstDay = startOfWeek(monthStart);

    // Finder søndagen i den uge hvor måneden slutter
    const lastDay = endOfWeek(
        addDays(monthStart, getDaysInMonth(monthStart) - 1)
    );

    let date = new Date(firstDay);

    while (date <= lastDay) {

        const cell = document.createElement("div");

        cell.classList.add("month-cell");

        if (date.getMonth() !== monthStart.getMonth()) {
            cell.classList.add("other-month");
        }

        const dateElement = document.createElement("div");

        dateElement.classList.add("month-date");

        dateElement.textContent = date.getDate();

        cell.appendChild(dateElement);

        const dateString = toLocalDateTimeString(date).substring(0, 10);

        cell.classList.add("clickable");

        cell.addEventListener("click", () => {
            window.location.href = "/reservation/day-overview.html?date=" + dateString;
        });


        activities.forEach(activity => {

            const start = new Date(activity.startTime);

            if (!isSameDay(start, date)) {
                return;
            }

            const activityElement =
                document.createElement("div");

            activityElement.classList.add("month-activity");

            activityElement.textContent =
                `${formatTime(start)} ${activity.type}`;

            cell.appendChild(activityElement);
        });

        container.appendChild(cell);

        date = addDays(date, 1);
    }

    calendar.appendChild(container);
}


function startOfDay(date) {

    const result = new Date(date);

    result.setHours(0, 0, 0, 0);

    return result;
}


function startOfWeek(date) {

    const result = startOfDay(date);

    const day = result.getDay();

    // mandag = 1 og søndag = 0
    const difference = day === 0 ? 6 : day - 1;

    result.setDate(result.getDate() - difference);

    return result;
}


function endOfWeek(date) {

    return addDays(startOfWeek(date), 6);
}


function startOfMonth(date) {

    const result = new Date(date);

    result.setDate(1);
    result.setHours(0, 0, 0, 0);

    return result;
}


function addDays(date, amount) {

    const result = new Date(date);

    result.setDate(result.getDate() + amount);

    return result;
}


function addMonths(date, amount) {

    const result = new Date(date);

    result.setMonth(result.getMonth() + amount);

    return result;
}


function getDaysInMonth(date) {

    return new Date(
        date.getFullYear(),
        date.getMonth() + 1,
        0
    ).getDate();
}


function isSameDay(date1, date2) {

    return date1.getFullYear() === date2.getFullYear()
        && date1.getMonth() === date2.getMonth()
        && date1.getDate() === date2.getDate();
}


function formatDate(date) {

    return date.toLocaleDateString("da-DK", {
        weekday: "long",
        day: "numeric",
        month: "long",
        year: "numeric"
    });
}


function formatShortDate(date) {

    return date.toLocaleDateString("da-DK", {
        weekday: "short",
        day: "numeric",
        month: "numeric"
    });
}


function formatTime(date) {

    return date.toLocaleTimeString("da-DK", {
        hour: "2-digit",
        minute: "2-digit"
    });
}


function getTitle(from, to) {

    if (currentView === "day") {
        return formatDate(from);
    }

    if (currentView === "week") {

        const weekEnd = addDays(from, 6);

        return `${formatShortDate(from)} - ${formatShortDate(weekEnd)}`;
    }

    return from.toLocaleDateString("da-DK", {
        month: "long",
        year: "numeric"
    });
}


// Konverterer en JavaScript Date til det format YYYY-MM-DDTHH:mm:ss
function toLocalDateTimeString(date) {

    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, "0");
    const day = String(date.getDate()).padStart(2, "0");

    const hours = String(date.getHours()).padStart(2, "0");
    const minutes = String(date.getMinutes()).padStart(2, "0");
    const seconds = String(date.getSeconds()).padStart(2, "0");

    return `${year}-${month}-${day}T${hours}:${minutes}:${seconds}`;
}
