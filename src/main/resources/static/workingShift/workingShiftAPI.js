const BASE_URL = "/api/working-shifts";

export async function fetchWorkshifts() {
    const workShiftList = await fetch(`${BASE_URL}`, {
        method: "GET"
    });
    return await workShiftList.json();
}

export async function deleteWorkShift(id) {
    await fetch(`${BASE_URL}/${id}`, {
        method: "Delete"
    });
}

export async function addWorkShift(workShift) {
    const response = await fetch(`${BASE_URL}`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(workShift)
    });

    if (!response.ok) {
        const error = await response.json();
        throw new Error(error.detail);
    }

    return await response.json();
}