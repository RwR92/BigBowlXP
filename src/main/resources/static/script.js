const API_DATABASE = "http://localhost:8080";

async function fetchEmployees() {

    const response = await fetch(`${API_BASE}/employees/display`);

    if (!response.ok){
        throw new Error('Failed to fetch employees: ${response.status}');
    }

return await response.json();

}

function displayEmployees(employees) {

    const tableBody = document.getElementById("employeeTableBody");
    tableBody.innerHTML = ""; // ryder eksisterende rækker
    
    employees.forEach(employee => {
        const row = document.createElement("tr");
        row.setAttribute("data-id", employee.id);
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

async function initApp() {

    try {
    const employees = await fetchEmployees();
    displayEmployees(employees);

    } catch (error) {
    console.error("Could not load employees", error)
    }
}