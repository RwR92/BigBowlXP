document.addEventListener("DOMContentLoaded", initApp);

async function initApp(){
    await refresh();
}

async function getData(){
const response = await fetch("/api/admin/audit");
    return response;

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
