// Hide server-side error after 3 seconds
window.addEventListener("DOMContentLoaded", function () {
  const errorBox = document.getElementById("errorMsg");
  if (errorBox) {
    setTimeout(() => {
      errorBox.style.opacity = "0";
      setTimeout(() => errorBox.remove(), 500);
    }, 3000);
  }
});

// Form validation
const form = document.getElementById("PassForm");
const inputs = form.querySelectorAll("input[required]");

form.addEventListener("submit", function (e) {
  let valid = true;

  inputs.forEach(input => {
    const errorText = input.nextElementSibling; 

    if (!input.value.trim()) {
      if (input.id === "currentPassword") {
        errorText.textContent = "Please enter your Current Password";
      } else if (input.id === "newPassword") {
        errorText.textContent = "Please enter your New Password";
      } else if (input.id === "confirmPassword") {
        errorText.textContent = "Please enter your Confirm Password";
      }

      input.classList.add("invalid", "shake");
      valid = false;

      setTimeout(() => input.classList.remove("shake"), 400);
    } else {
      errorText.textContent = "";
      input.classList.remove("invalid");
    }
  });

  if (!valid) {
    e.preventDefault();
  }
});

// Live validation while typing
inputs.forEach(input => {
  input.addEventListener("input", function () {
    const errorText = this.nextElementSibling;
    if (this.value.trim()) {
      errorText.textContent = "";
      this.classList.remove("invalid");
    }
  });
});
