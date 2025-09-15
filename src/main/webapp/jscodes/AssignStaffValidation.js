// Validation + Server-side message hide
window.addEventListener("DOMContentLoaded", function () {
  const form = document.getElementById("assignSubjectForm");
  const staffSelect = document.getElementById("staff");

  // Custom validation
  form.addEventListener("submit", function (e) {
    let valid = true;

    clearError(staffSelect);

    if (staffSelect.value.trim() === "") {
      showError(staffSelect, "Please select a staff member");
      valid = false;
    }

    if (!valid) {
      e.preventDefault(); // stop form submission
    }
  });

  // Remove error when user selects a value
  staffSelect.addEventListener("change", function () {
    if (staffSelect.value.trim() !== "") {
      clearError(staffSelect);
    }
  });

  function showError(input, message) {
    if (!input.nextElementSibling || !input.nextElementSibling.classList.contains("input-error")) {
      let error = document.createElement("div");
      error.className = "input-error";
      error.innerText = message;
      input.after(error);
    }
    input.classList.add("error-border");
  }

  function clearError(input) {
    if (input.nextElementSibling && input.nextElementSibling.classList.contains("input-error")) {
      input.nextElementSibling.remove();
    }
    input.classList.remove("error-border");
  }
 
  // Auto-hide server-side messages
  const serverMsg = document.querySelector(".success-msg, .error-msg");
  if (serverMsg) {
    setTimeout(() => {
      serverMsg.style.transition = "opacity 0.6s ease";
      serverMsg.style.opacity = "0";
      setTimeout(() => serverMsg.remove(), 600);
    }, 3000);
  }
});
