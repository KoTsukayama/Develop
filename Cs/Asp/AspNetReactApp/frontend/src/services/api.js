const API_BASE_URL = "https://localhost:7026";

export async function getHello() {
    const response = await fetch(`${API_BASE_URL}/weatherforecast`);
    return await response.json();
}
