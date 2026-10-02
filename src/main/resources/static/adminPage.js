const API_URL = "/api/lanes"
let lanes = [];
import {logout} from "./app.js"
async function initAdminPage() {
    await refreshLanes();
    document.querySelector("#bowlingLaneTableBody").addEventListener("click", handleTableClick);
    document.querySelector("#logout-btn").addEventListener("click", logout);
}

async function refreshLanes(){
    lanes = await fetchLanes()
    renderLaneRow(lanes);
}

async function fetchLanes() {
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
    typeCell.textContent = lane.type; // Navnet "type" bør nok ændres, da 'type' er et beskyttet navn.

    const isOpenCell = document.createElement("td");
    isOpenCell.textContent = lane.isOpen ? "Open" : "Closed";

    const actionCell = document.createElement("td");

    const setStatusButton = document.createElement("button");
    setStatusButton.setAttribute("data-action", "set-status");
    setStatusButton.textContent = lane.isOpen ? "Close" : "Open";

    actionCell.append(setStatusButton)
    row.append(numberCell, childCell, typeCell, isOpenCell, actionCell);
    return row;
}

function renderLaneRow(lanes) {
    const tBody = document.querySelector("#bowlingLaneTableBody");
    tBody.textContent = "";
    for (const lane of lanes) {
        tBody.appendChild(createLaneRow(lane));
    }
}
async function handleTableClick(e){
    const action = e.target.getAttribute("data-action");
    const row = e.target.closest("tr");
    const id = row.getAttribute("data-id");

    if(action === "set-status"){
        await setStatus(id);
        await refreshLanes();
    }
}
async function setStatus(id){
    let lane = null;
    for(const l of lanes){
        console.log("l.id: "+typeof l.id+" id: "+typeof id);
        if(l.id.toString() === id){
            lane = l.isOpen;
        }
    }
    let apiAppend="";
    if(!lane){
        apiAppend = "open";
    } else if(lane){
        apiAppend = "close";
    }
    await updateLane(id, apiAppend);
}

async function updateLane(id, apiAppend){
    try{
        const response = await fetch(`${API_URL}/${id}/${apiAppend}`, {
            method: "PATCH"
        });
        if(!response.ok){
            throw new Error(`Failed to connect: ${response}`);
        }
        return await response.json();
    } catch (error){
        console.log(error);
    }
}