
// ///
// function changeBg(){
//     var header = document.getElementById("header");
//     var scrollValue = window.scrollY;

//     console.log(scrollValue); 
        
//     if(scrollValue < 40){
//         header.classList.add("header-null");
//         header.classList.remove("header-scrolled");
        
//     } 

//     else{
//         header.classList.remove("header-null");
//         header.classList.add("header-scrolled");
//     } 

// }

// function slide(){
//     var scrollValue = window.scrollY;
//     var txt1 = document.getElementById("txt1");
//     var txt2 = document.getElementById("txt2");
//     var txt3 = document.getElementById("txt3");
//     var svg1 = document.getElementById("svg1");
//     var svg2 = document.getElementById("svg2");
//     var svg3 = document.getElementById("svg3");

//     if(scrollValue > 510){
//         txt1.classList.add("slide-in-active");
//         svg1.classList.add("slide-in-active");
//     }

//     if(scrollValue > 1418){
//         txt2.classList.add("slide-in-active");
//         svg2.classList.add("slide-in-active");
//     }

//     if(scrollValue > 2448){
//         txt3.classList.add("slide-in-active");
//         svg3.classList.add("slide-in-active");
//     }

// }

// window.addEventListener("scroll", changeBg);
// window.addEventListener("scroll", slide);

const header = document.querySelector("#header");
const banner = document.querySelector(".main-bg");
const txt2 = document.querySelector("#txt2");
const txt3 = document.querySelector("#txt3");
const svg1 = document.querySelector("#svg1");
const txt1 = document.querySelector("#txt1");
const svg2 = document.querySelector("#svg2");
const svg3 = document.querySelector("#svg3");


const bannerOptions = {
    rootMargin:"-120px 0px 0px 0px"
};

const bannerObserver = new IntersectionObserver(function(entries) {
    entries.forEach(entry => {
        console.log(entry.target);
        if(!entry.isIntersecting){
            header.classList.remove("header-null");
            header.classList.add("header-scrolled");

    } else if(entry.isIntersecting){
        header.classList.remove("header-scrolled");
        header.classList.add("header-null");
    }
})
}, bannerOptions);

bannerObserver.observe(banner);

const line1Observer = new IntersectionObserver(function(entries) {
    entries.forEach(entry => {
        if(entry.isIntersecting){
            txt1.classList.add("slide-in-active");
            svg1.classList.add("slide-in-active");

    } 
})
});

line1Observer.observe(txt1);

const line2Observer = new IntersectionObserver(function(entries) {
    entries.forEach(entry => {
        if(entry.isIntersecting){
            txt2.classList.add("slide-in-active");
            svg2.classList.add("slide-in-active");

    } 
})
});

line2Observer.observe(txt2);

const line3Observer = new IntersectionObserver(function(entries) {
    entries.forEach(entry => {
        if(entry.isIntersecting){
            txt3.classList.add("slide-in-active");
            svg3.classList.add("slide-in-active");

    }
})
});

line3Observer.observe(txt3);