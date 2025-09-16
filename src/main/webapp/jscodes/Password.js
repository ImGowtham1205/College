// Hide server-side messages after 3 seconds
window.addEventListener("DOMContentLoaded", function () {
  const errorBox = document.getElementById("errorMsg");
  if (errorBox) {
    setTimeout(() => {
      errorBox.style.opacity = "0";
      setTimeout(() => errorBox.remove(), 500);
    }, 3000);
  }
});

// Password pattern: at least 1 uppercase, 1 number, 1 special char, minimum 7 chars
const passwordPattern = /^(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9]).{7,}$/;

// Form and inputs
const form = document.getElementById("PassForm");
const inputs = form.querySelectorAll("input[required]");

// Form submit validation
form.addEventListener("submit", function (e) {
  let valid = true;

  inputs.forEach(input => {
    const errorText = input.nextElementSibling;

    // Required field check
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
    } 
    // Password strength check
    else if ((input.id === "newPassword" || input.id === "confirmPassword") 
             && !passwordPattern.test(input.value)) {
      errorText.textContent = "Password must have 1 uppercase, 1 number, 1 special char, min 7 chars";
      input.classList.add("invalid", "shake");
      valid = false;
      setTimeout(() => input.classList.remove("shake"), 400);
    } 
    else {
      errorText.textContent = "";
      input.classList.remove("invalid");
    }
  });

  if (!valid) {
    e.preventDefault();
  }
});

// Live input validation
inputs.forEach(input => {
  input.addEventListener("input", function () {
    const errorText = this.nextElementSibling;

    if (this.value.trim()) {
      if ((this.id === "newPassword" || this.id === "confirmPassword") 
          && !passwordPattern.test(this.value)) {
        errorText.textContent = "Password must have 1 uppercase, 1 number, 1 special char, min 7 chars";
        this.classList.add("invalid");
      } else {
        errorText.textContent = "";
        this.classList.remove("invalid");
      }
    } else {
      errorText.textContent = "";
      this.classList.remove("invalid");
    }
  });
});
