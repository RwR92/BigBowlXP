document.addEventListener("DOMContentLoaded", initSaleApp);
import {initApp} from "../app"
import {createSale, getProducts} from "./salesAPI.js"


async function initSaleApp(){

}

async function displayProducts(){
    const products = await getProducts();
    products.innerHTML = "";
    try{
        for(const product of products){
            products.appendChild(renderProducts(product));
        }
    }catch (error){
        console.log(error)
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
    input.addEventListener("input");

    li.append(label, input);
    return li;
}