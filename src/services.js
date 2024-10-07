const slides = [
    {
        title: "Maternity Photography",
        description: "Maternity photoshoot is recommended to be done between 28 to 35 weeks",
        image: "Images/maternity.jpg"
    },
    {
        title: "Family Photography",
        description: "Family photoshoots are a great way to create lasting memories.",
        image: "Images/family.jpg"
    }
];

let currentSlide = 0;

const titleElement = document.getElementById('slide-title');
const descriptionElement = document.getElementById('slide-description');
const middleContainer = document.querySelector('.middlecontainer');

document.getElementById('prev').addEventListener('click', () => {
    currentSlide = (currentSlide === 0) ? slides.length - 1 : currentSlide - 1;
    updateSlide();
});

document.getElementById('next').addEventListener('click', () => {
    currentSlide = (currentSlide === slides.length - 1) ? 0 : currentSlide + 1;
    updateSlide();
});

function updateSlide() {
    titleElement.textContent = slides[currentSlide].title;
    descriptionElement.textContent = slides[currentSlide].description;
    middleContainer.style.backgroundImage = `url('${slides[currentSlide].image}')`;
}

// Initialize the first slide
updateSlide();
