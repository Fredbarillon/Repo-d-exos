import Description from './Description.js';

const descriptions = [
    new Description("Un aventurier mystérieux", 1),
    new Description("Une exploratrice aDORAble", 2),
    new Description("Un mage excentrique", 3)
];

// random description on page load
document.addEventListener("DOMContentLoaded", () => {
    const firstDescription = getRandomDescription(descriptions);
    firstDescription.showDescription();
    document.querySelector("h2").innerText = firstDescription.content;
});



// random description on button click
function getRandomNumber() {
    return Math.floor(Math.random() * 3) + 1; 
}

function getRandomDescription(list) {
    const randNum = getRandomNumber();
    return list.find(description => description.number === randNum);
}

document.getElementById("generateDescription").addEventListener("click", () => {
    const description = getRandomDescription(descriptions);
    description.showDescription(); 
    document.querySelector("h2").innerText = description.content; 
});

// theme mode toggle
(function () {
  const root = document.documentElement;

  document.addEventListener("DOMContentLoaded", function () {
    const togglers = document.querySelectorAll("[data-theme-toggler]");
    togglers.forEach((toggler) => {
      toggler.addEventListener("click", toggleDarkMode);
    });
  });

  function toggleDarkMode() {
    const currentTheme = root.getAttribute("data-theme");
    const newTheme = currentTheme === "dark" ? "light" : "dark";
    root.setAttribute("data-theme", newTheme);
  }
})();



// profile generator
const pseudoInput = document.getElementById("pseudoInput");
const avatars = document.querySelectorAll(".avatars img");
const generateBtn = document.getElementById("generateProfile");

let pseudoOk = false;
let avatarOk = false;


function checkInput() {
  generateBtn.disabled = !(pseudoOk && avatarOk);
}

pseudoInput.addEventListener("input", () => {
  const regex = /^[a-zA-Z]+$/;
  pseudoOk = regex.test(pseudoInput.value);
  checkInput();
});

avatars.forEach(img => {
  img.addEventListener("click", () => {
    avatars.forEach(a => a.classList.remove("selected"));
    img.classList.add("selected");
    avatarOk = true;
    checkInput();
  });
});

// card generator
const formulaire = document.getElementById("form");
const profileCard = document.getElementById("profileCard");

const cardPseudo = document.getElementById("cardPseudo");
const cardDescription = document.getElementById("cardDescription");
const cardAvatar = document.getElementById("cardAvatar");
const resetBtn = document.getElementById("resetForm");

document.getElementById("generateProfile").addEventListener("click", () => {
  const pseudo = document.getElementById("pseudoInput").value.trim();
  const description = document.querySelector("h2").innerText; 
  const avatarImg = document.querySelector(".avatars img.selected");
  const avatarSrc = avatarImg ? avatarImg.src : "";

  cardPseudo.innerText = pseudo;
  cardDescription.innerText = description;
  cardAvatar.src = avatarSrc;

  formulaire.style.display = "none";
  profileCard.style.display = "block";
});


resetBtn.addEventListener("click", () => {
  profileCard.style.display = "none";
  formulaire.style.display = "block";
});