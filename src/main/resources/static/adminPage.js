const API_URL = "api/lanes"
document.addEventListener("DOMContentLoaded", initApp);


export async function initApp() {
    await getBowlingLanes();
}

async function getBowlingLanes() {

    try {
        const response = await fetch(API_URL);

        if (!response.ok) {
            throw new Error("Could not get lanes, error: " + response.statusText);
        }
        const lanes = await response.json();
        await createBowlingLanes(lanes);
    } catch (error) {
        console.error("Error loading bowling lanes: ", error);
        return [];
    }
}

async function createBowlingLanes(lanes) {
    const tBody = document.getElementById("#bowlingLaneTableBody");
    tBody.textContent = "";
    for (const lane of lanes) {
        await renderLaneRow(lane);
    }
}

async function renderLaneRow(lane) {
    const tBody = document.querySelector("#bowlingLaneTableBody");

    const row = document.createElement("tr");
    row.setAttribute("data-id", lane.id);

    const laneNumberCell = document.createElement("td");
    laneNumberCell.textContent = lane.laneNumber;

    const laneChildFriendlyCell = document.createElement("td");
    laneChildFriendlyCell.textContent = lane.childFriendly ? "Yes" : "No";

    const laneTypeCell = document.createElement("td");
    laneTypeCell.textContent = lane.type;

    row.append(laneNumberCell, laneChildFriendlyCell, laneTypeCell);
    tBody.appendChild(row);

}