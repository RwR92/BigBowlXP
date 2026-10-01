const API_URL = "/api/lanes"


export async function initAdminPage() {
    const lanes = await getBowlingLanes()
    renderLaneRow(lanes);
}

async function getBowlingLanes() {
    try {
        const response = await fetch(API_URL);
        if (!response.ok) {
            throw new Error("Could not get lanes: " + response.status);
        }
        return await response.json();
    } catch (error) {
        console.error("Error loading bowling lanes:", error);
        return [];
    }
}

function createLaneRow(lane) {
    const row = document.createElement("tr");
    row.dataset.id = lane.id;

    const numberCell = document.createElement("td");
    numberCell.textContent = lane.laneNumber;

    const childCell = document.createElement("td");
    childCell.textContent = lane.childFriendly ? "Yes" : "No";

    const typeCell = document.createElement("td");
    typeCell.textContent = lane.type;

    row.append(numberCell, childCell, typeCell);
    return row;
}

function renderLaneRow(lanes) {
    const tBody = document.querySelector("#bowlingLaneTableBody");
    tBody.textContent = "";
    for (const lane of lanes) {
        tBody.appendChild(createLaneRow(lane));
    }
}