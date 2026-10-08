document.addEventListener("DOMContentLoaded", initApp);
let list = [];

async function initApp(){
    await getData()
    refresh();
}

async function getData() {
    try {
       const response = await fetch("/api/admin/audit");
        list = await response.json();
    } catch (error) {
        console.log("Error Could Not Fetch!!! ", error);
    }
    console.log("getData")
    console.log(list)
}

function createElement(auditLog) {
    const createdElement = document.createElement("tr");
    const date = new Date(auditLog.timeStamp).toLocaleString("da-DK", {
        dateStyle: "short",
        timeStyle: "short"
    });
    createdElement.innerHTML = `
<td>${auditLog.user}</td>
<td>${auditLog.action}</td>
<td>${date}</td>
<td>${auditLog.description}</td>
`
    createdElement.className = "auditLog";

    console.log("createElement")
    const tableBody = document.getElementById("tbody");
    tableBody.appendChild(createdElement);
}

function forEachElement(list) {
    for(const auditLog of list) {
        createElement(auditLog);
    }
    console.log("forEachElement")
}

function refresh() {
    forEachElement(list);
    console.log("refresh")
}
