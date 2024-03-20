let hamb = document.querySelector(".hamb");
let navMenu = document.querySelector(".nav__menu");

hamb.addEventListener("click", mobileMenu);

function mobileMenu() {
    hamb.classList.toggle("active");
    navMenu.classList.toggle("active");
}

const navLink = document.querySelectorAll(".nav__link");

navLink.forEach(n => n.addEventListener("click", closeMenu));

function closeMenu() {
    hamb.classList.remove("active");
    navMenu.classList.remove("active");
}

function changeTextSize(action) {
    var currentSize = parseInt($("html").css("font-size"));

    if (action === 'increaseBy4') {
        $("html").css("font-size", currentSize + 4 + "px");
    } else if (action === 'increaseBy2') {
        $("html").css("font-size", currentSize + 2 + "px");
    } else if (action === 'reset') {
        $("html").css("font-size", "10px");
    }
}
