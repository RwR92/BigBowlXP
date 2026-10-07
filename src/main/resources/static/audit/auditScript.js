document.addEventListener("DOMContentLoaded", initApp);
let list = [];

async function initApp(){
    await getData()
    refresh();
}

async function getData() {
    try {
        list = await fetch("/api/admin/audit");

    } catch (error) {
        console.log("Error Could Not Fetch!!! ", error);
    }
    console.log("getData")
    console.log(list)
}

function createElement(auditLog) {
    const createdElement = document.createElement("row");
    createdElement.innerHTML = `
<tb>${auditLog.user}</tb>
<tb>${auditLog.action}</tb>
<tb>${auditLog.timeStamp}</tb>
<tb>${auditLog.description}</tb>
`
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
