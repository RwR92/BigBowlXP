async function fetchEmployees() {

    const response = await fetch('${/employees/display}');
    const employees = await response.json();

    return employees;
}

function displayEmployees(employees) {
    const tableBody = document.getElementById("employeeTableBody");
    const row = document.createElement("tr");
    row.setAttribute("data-id", employees.id);
    row.innerHTML = `

        <td>${employees.firstName}</td>
        <td>${employees.lastName}</td>
        <td>${employees.phoneNumber}</td>
        <td>${employees.role}</td>
        <td>${employees.employeeId}</td>

    `;
    tableBody.appendChild(row);
    console.log(fetchEmployees());
}

async function initApp() {
    const employees = await fetchEmployees();
    displayEmployees(employees);
}