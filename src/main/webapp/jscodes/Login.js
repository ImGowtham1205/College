// Get the form and required inputs
const form = document.getElementById("loginForm");
const inputs = form.querySelectorAll("input[required]");

// On form submit
form.addEventListener("submit", function (e) {
  let valid = true;

  inputs.forEach(input => {
    const errorText = input.parentElement.querySelector(".error-text");

    if (!input.value.trim()) {
      // Show custom error messages
      if (input.id === "id") {
        errorText.textContent = "Please enter your Student ID";
      } else if (input.id === "password") {
        errorText.textContent = "Please enter your Password";
      } else {
        errorText.textContent = "This field is required";
      }

      input.classList.add("invalid", "shake");
      valid = false;

      // Remove shake class after animation ends
      setTimeout(() => input.classList.remove("shake"), 400);

    } else {
      errorText.textContent = "";
      input.classList.remove("invalid");
    }
  });

  if (!valid) {
    e.preventDefault(); // Stop form submission
  }
});

// Live validation while typing
inputs.forEach(input => {
  input.addEventListener("input", function () {
    const errorText = this.parentElement.querySelector(".error-text");
    if (this.value.trim()) {
      errorText.textContent = "";
      this.classList.remove("invalid");
    }
  });
});
