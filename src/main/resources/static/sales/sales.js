document.addEventListener("DOMContentLoaded", initSaleApp);
//import {initApp} from "../app"
import {createSale, getProducts} from "./salesAPI.js"

let products = [];

async function initSaleApp(){
    document.querySelector("#saleForm").addEventListener("submit", handleSubmit)
    await displayProducts();
}

async function displayProducts(){
    const list = document.querySelector("#productList");
    list.innerHTML = "";
    try{
        products = await getProducts();
        for(const product of products){
            await list.appendChild(await renderProducts(product));
        }
    }catch (error){
        console.log(error);
    }

}

async function renderProducts(product){
    const li = document.createElement("li");
    li.dataset.id = product.id;

    const label = document.createElement("span");
    label.textContent = `${product.name} (${Number(product.price).toFixed(2)} kr.)`;

    const input = document.createElement("input");
    input.type = "number";
    input.min = "0";
    input.value = "0";
    // input.addEventListener("input");

    li.append(label, input);
    return li;
}

async function handleSubmit(e){
    e.preventDefault();
    const selectedProducts = await getSelectedProducts();
    try{
        await createSale(selectedProducts);
    } catch(error){
        console.log(error);
    }
}

async function getSelectedProducts(){
    let products = [];

    for(const li of document.querySelectorAll("#productList li")){
        const quantity = parseInt(li.querySelector("input").value, 10);
        if(quantity > 0){
            products.push({productId: Number(li.dataset.id), quantity: quantity});
        }
    }
    return products;
}
