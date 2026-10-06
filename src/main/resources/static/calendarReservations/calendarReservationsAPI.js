const BASE_URL = "/api/calendar";

export async function fetchActivities(from, to) {

    const url =
        `${BASE_URL}?from=${encodeURIComponent(from)}&to=${encodeURIComponent(to)}`;

    const response = await fetch(url, {
        method: "GET"
    });

    if (!response.ok) {
        throw new Error("Kunne ikke hente aktiviteter");
    }

    return await response.json();
}
