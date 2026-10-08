const BASE_URL = "/api/sales";

export async function getAllSalesWithinLastMonth() {
    const response = await fetch(`${BASE_URL}`, {
        method: "GET"
    });
    return await response.json();
}

export async function getAllSalesFromSpecificMonth(year, month) {
    const response = await fetch(`${BASE_URL}/specific-month?year=${year}&month=${month}`, {
        method: "GET"
    });
    return await response.json();
}