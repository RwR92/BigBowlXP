export function appendChild(child, headerId){
    //console.log("Venstre side: "+"window.location.href='"+window.location.pathname+"'"+child.getAttribute("onclick"))
    if("window.location.href='"+window.location.pathname+"'" === child.getAttribute("onclick")){
        return;
    }
    const header = document.querySelector(`#${headerId}`);
    header.appendChild(child);
}