window.onload=()=>{
const photohome=document.getElementById("photohome");
(function init() {
		getLoggedInMemberID();
		renderPetList();
	})()

function renderPetList(petList) {
	let output = "";
	for (let pet of petList) {
	output += `<button type="button" class="btn btn-sm btn-outline-secondary" pID=${pet.pID}>`;
			if (pet.likedByMemberIds.includes(loggedInMemberID)) {
				output += `<i class="fa-solid fa-heart fa-xl" style="color: #e51515;"></i>`;
			} else {
				output += `<i class="fa-regular fa-heart fa-xl"></i>`;
			}
			output += `</button>`;
	
}


photohome.innerHTML = output;

}


photohome.addEventListener("click",function(e){
    if(e.target.tagName !=="I"){return;}
    let pid =e.target.parentElement.getAttribute("pid");
    let url = `${root}/likePhotos?pid=${pid}`;

    fetch(url).then(result=>result.text()).then(status=>{
        if(status=="liked"){
            e.target.classList.remove("fa-regular");
			e.target.classList.add("fa-solid");
			e.target.style.color = "#e51515";
        }
        if(status == "unLiked"){
            e.target.classList.remove("fa-solid");
			e.target.classList.add("fa-regular");
			e.target.style.color = "";


        }


    })
})
photohome.addEventListener("click",function(e){
    if(e.target.tagName !=="BUTTON"){
	return;
	}
    e.target.children[0].click();
})

}