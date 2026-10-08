

    const API_URL = "/api/admin/reservation";
    const OPEN_HOUR = 10;
    const CLOSE_HOUR = 23;

    const params = new URLSearchParams(window.location.search);
    document.getElementById("date").value = params.get("date") || todayString();
    document.addEventListener("DOMContentLoaded", loadDay);

    function todayString() {
    const now = new Date();
    const month = String(now.getMonth() + 1).padStart(2, "0");
    const day = String(now.getDate()).padStart(2, "0");
    return now.getFullYear() + "-" + month + "-" + day;
}

    async function loadDay() {

    const date = document.getElementById("date").value;

    if (!date) {
    alert("Vælg en dato.");
    return;
}

    try {
    const response = await fetch(`${API_URL}/day-overview?date=${date}`);

    if (!response.ok) {
    throw new Error("Kunne ikke hente dagsoversigten");
}

    const reservations = await response.json();

    displayTimeline(reservations, date);

} catch (error) {
    console.error("Fejl ved hentning af dagsoversigt:", error);
    alert("Der skete en fejl. Prøv igen.");
}
}

    function displayTimeline(reservations, date) {

    const rows = {};

    for (const reservation of reservations) {
    for (const activity of reservation.activities) {

    // En reservation kan have aktiviteter på andre dage
    if (activity.startTime.substring(0, 10) !== date) {
    continue;
}

    if (activity.lanes.length === 0) {
    addBlock(rows, "DINING", "Spisning", 2, 0, reservation, activity);
} else {
    for (const lane of activity.lanes) {
    const isAirhockey = lane.type === "AIRHOCKEY";
    const label = (isAirhockey ? "Airhockey " : "Bane ") + lane.laneNumber;
    const group = isAirhockey ? 1 : 0;
    addBlock(rows, lane.type + lane.laneNumber, label, group, lane.laneNumber, reservation, activity);
}
}
}
}

    const sortedRows = Object.values(rows);
    sortedRows.sort(function (a, b) {
    if (a.group !== b.group) {
    return a.group - b.group;
}
    return a.number - b.number;
});

    const timeline = document.getElementById("timeline");
    const message = document.getElementById("message");

    if (sortedRows.length === 0) {
    timeline.hidden = true;
    message.textContent = "Ingen reservationer på denne dato.";
    return;
}

    message.textContent = "";
    timeline.hidden = false;

    createHeader();

    const tableBody = document.getElementById("timelineBody");
    tableBody.innerHTML = "";

    for (const rowData of sortedRows) {
    tableBody.appendChild(createRow(rowData));
}
}

    function addBlock(rows, key, label, group, number, reservation, activity) {
    if (!rows[key]) {
    rows[key] = { label: label, group: group, number: number, blocks: [] };
}
    rows[key].blocks.push({ reservation: reservation, activity: activity });
}

    function createHeader() {
    const head = document.getElementById("timelineHead");
    head.innerHTML = "";

    const row = document.createElement("tr");

    const first = document.createElement("th");
    first.textContent = "Bane";
    row.appendChild(first);

    for (let hour = OPEN_HOUR; hour < CLOSE_HOUR; hour++) {
    const cell = document.createElement("th");
    cell.textContent = String(hour).padStart(2, "0") + ":00";
    row.appendChild(cell);
}

    head.appendChild(row);
}

    function createRow(rowData) {
    const row = document.createElement("tr");

    const label = document.createElement("th");
    label.textContent = rowData.label;
    row.appendChild(label);

    for (let hour = OPEN_HOUR; hour < CLOSE_HOUR; hour++) {
    const cell = document.createElement("td");

    for (const item of rowData.blocks) {
    if (overlapsHour(item.activity, hour)) {
    cell.appendChild(createLink(item.reservation));
}
}

    row.appendChild(cell);
}

    return row;
}

    // Dækker aktiviteten (helt eller delvist) timen fra hour til hour + 1?
    function overlapsHour(activity, hour) {
    const start = minutesOfDay(activity.startTime);
    const end = minutesOfDay(activity.endTime);
    return start < (hour + 1) * 60 && end > hour * 60;
}

    function minutesOfDay(isoDateTime) {
    const date = new Date(isoDateTime);
    return date.getHours() * 60 + date.getMinutes();
}

    function createLink(reservation) {
    const link = document.createElement("a");
    link.href = "/reservation/reservation-details.html?id=" + reservation.id;
        link.textContent = reservation.name;
    return link;
}
