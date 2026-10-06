const BASE_SALE_URL = "/api/sales";
const BASE_PRODUCTS_URL = "/api/products";

export async function createSale(sale) {
    try {
        const response = await fetch(`${BASE_SALE_URL}`, {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(sale)
        });
        if (!response.ok) {
            const error = await response.json();
            throw new Error(error.detail);
        }
        return await response.json();
    } catch (error) {
        console.error(error);
    }

}

export async function getProducts() {
    try{
        const response = await fetch(`${BASE_PRODUCTS_URL}`, {
            method: "GET",
        })
        if(!response.ok) {
            const error = await response.json();
            throw new Error(error.detail);
        }
        return await response.json();
    }catch (error) {
        console.error(error);
    }
}