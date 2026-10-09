export function appendChild(child, headerId){
    //console.log("Venstre side: "+"window.location.href='"+window.location.pathname+"'"+child.getAttribute("onclick"))
    if("window.location.href='"+window.location.pathname+"'" === child.getAttribute("onclick")){
        child.setAttribute("disabled", "");
    }
    child.setAttribute("class", "header-btn");
    const header = document.querySelector(`#${headerId}`);
    header.appendChild(child);
}