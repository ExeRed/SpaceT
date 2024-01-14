// opening in full screen
function openFullscreen(img) {
    var modal = document.getElementById("myModal");
    var modalImg = document.getElementById("fullScreenImage");

    modal.style.display = "block";
    modalImg.src = img.src;
}

// closing image
function closeFullscreen() {
    var modal = document.getElementById("myModal");
    modal.style.display = "none";
}

//"X" (close button)
document.querySelector(".close").addEventListener("click", closeFullscreen);

// close clicking anywhere
window.addEventListener("click", function(event) {
    var modal = document.getElementById("myModal");
    if (event.target === modal) {
        closeFullscreen();
    }
});