const contentInfo = [
    {
        image: '../static/images/space-nebula.jpeg',
        text: 'Space nebulae, vast clouds of dust and gases, are celestial tapestries adorned with vibrant hues.'
    },
    {
        image: '../static/images/blueandpurle.png',
        text: 'ТIn the cosmic expanse, amid clouds of blue and purple-hued ashes, stars emerge as celestial beacons.'
    },
];

let currentIndex = 0;
const imageElement = document.getElementById('image');
const textElement = document.getElementById('text');

// Функция для обновления контента
function updateContent() {
    imageElement.style.opacity = '0';
    textElement.style.opacity = '0';

    setTimeout(() => {
        imageElement.src = contentInfo[currentIndex].image;
        textElement.textContent = contentInfo[currentIndex].text;

        imageElement.style.opacity = '1';
        textElement.style.opacity = '1';

        currentIndex = (currentIndex + 1) % contentInfo.length;
    }, 1000);
}

setInterval(updateContent, 10000);