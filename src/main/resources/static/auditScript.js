document.addEventListener("DOMContentLoaded", initApp);
let list = [];

async function initApp(){
    await refresh();
}

async function getData(){
    try {
        list = await fetch("/api/admin/audit");

    } catch (error) {
        console.log("Error Could Not Fetch!!! ", error);
    }
    return list;
}

function createElement(auditLog) {
    const createdElement = document.createElement("row");
    createdElement.innerHTML = `
<tb>${auditLog.user}</tb>
<tb>${auditLog.action}</tb>
<tb>${auditLog.timeStamp}</tb>
<tb>${auditLog.description}</tb>
`
    const tableBody = document.getElementById("tbody");
    tableBody.appendChild(createdElement);
}

function forEachElement(list) {
    for(const auditLog of list) {
        createElement(auditLog);
    }
}

function refresh() {
    forEachElement(getData());
}
