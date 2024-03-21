document.addEventListener('DOMContentLoaded', function() {
    const slides = document.querySelectorAll('.slide');
    let currentSlide = 0;

    function showSlide(n) {
        slides[currentSlide].style.transform = "translateX(-100%)";
        currentSlide = (n + slides.length) % slides.length;
        slides[currentSlide].style.transform = "translateX(0)";
    }

    document.querySelector('.next').addEventListener('click', function() {
        showSlide(currentSlide + 1);
    });

    document.querySelector('.prev').addEventListener('click', function() {
        showSlide(currentSlide - 1);
    });
});
