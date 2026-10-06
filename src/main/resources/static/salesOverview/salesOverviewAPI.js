const BASE_URL = "/api/sales";

export async function getAllSalesWithinLastMonth() {
    const response = await fetch(`${BASE_URL}`, {
        method: "GET"
    })
    console.log("status:", response.status);
    return await response.json();
}

