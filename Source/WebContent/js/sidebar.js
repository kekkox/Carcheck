document.addEventListener('DOMContentLoaded', function() {
    
    btn = document.querySelector(".hamburger");
    btn.addEventListener('click', toggleSideBar);
    
}, false);

let toggleSideBar = () => {
	btn.classList.toggle('active');
	
	if(btn.classList.contains("active")) {
		showSideBar();
	}
	else
		hideSideBar();
}

function showSideBar() {
	const sideBar = document.querySelector(".sidebar");
	const content = document.querySelector(".content");
	sideBar.style.marginLeft = "0";
	content.style.marginRight = "-250px";
}

function hideSideBar() {
	const sideBar = document.querySelector(".sidebar");
	const content = document.querySelector(".content");
	sideBar.style.marginLeft = "-250px";
	content.style.marginRight = "0";
}

let btn;