//Disapper Server Side Message in 3 Seconds
document.addEventListener("DOMContentLoaded", function () {
  const msgBox = document.querySelector(".msg-box");
  if (msgBox) {
    setTimeout(() => {
      msgBox.classList.add("fade-out");

      // remove from DOM after transition
      msgBox.addEventListener("transitionend", () => {
        msgBox.remove();
      });
    }, 3000); // wait 3 sec
  }
});