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