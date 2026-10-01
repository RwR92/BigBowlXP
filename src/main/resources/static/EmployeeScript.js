const API_DATABASE = "http://localhost:8080/api/employees";
let employees = []

document.addEventListener("DOMContentLoaded", initApp);

async function initApp() {

    try {
        employees = await fetchEmployees();
        displayEmployees(employees);

    } catch (error) {
        console.error("Could not load employees", error);

    }
    document.querySelector("#employeeTableHeader").addEventListener("click", handleClick);

    document.querySelector("#filterByRole").addEventListener("change", handleDropdown);

}

async function fetchEmployees() {

    const response = await fetch(`${API_DATABASE}`);

    if (!response.ok) {
        throw new Error('Failed to fetch employees: ${response.status}');
    }
    console.log("Fired");
    return await response.json();

}

function displayEmployees(employees) {

    const tableBody = document.getElementById("employeeTableBody");
    tableBody.innerHTML = ""; // ryder eksisterende rækker

    employees.forEach(employee => {
        const row = document.createElement("tr");
        row.setAttribute("data-id", employee.employeeId);
        row.innerHTML = `
        <td>${employee.firstName}</td>
        <td>${employee.lastName}</td>
        <td>${employee.phoneNumber}</td>
        <td>${employee.role}</td>
        `;

        // populater min DOM for visning
        tableBody.appendChild(row);

    });
}

function handleClick(employee) {

    const tableBody = employee.target.closest("th");

    const key = tableBody.getAttribute("data-sort-key");

    if (key === "role") {
        console.log(key);
        filterByRole(employees);
    } else {
        console.log("not a role");
    }

}

function handleDropdown(event) {
    const select = event.target;
    const key = select.value;

    if (key !== "all") {

        refreshEmployeesAfterSort(checkByRole(employees, key));
    } else {

        displayEmployees(employees);
    }
}

function refreshEmployeesAfterSort(employees) {
    displayEmployees(employees);
}


function checkByRole(employees, role) {

    const employeeFilterList = [];

    for (const employee of employees) {
        if (employee.role === role) {
            employeeFilterList.push(employee);


        }
    }
    return employeeFilterList;
}