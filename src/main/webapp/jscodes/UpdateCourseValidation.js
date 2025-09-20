document.addEventListener("DOMContentLoaded", function () {
  // === Utility functions ===
  function showError(el, message) {
    let errorEl = el.parentNode.querySelector(".error-text");
    if (errorEl) {
      errorEl.textContent = message;
      errorEl.style.display = "block";
    }
    el.classList.add("input-error");
    el.classList.remove("input-valid");
  }

  function showValid(el) {
    let errorEl = el.parentNode.querySelector(".error-text");
    if (errorEl) {
      errorEl.textContent = "";
      errorEl.style.display = "none";
    }
    el.classList.remove("input-error");
    el.classList.add("input-valid");
  }

  function clearError(el) {
    let errorEl = el.parentNode.querySelector(".error-text");
    if (errorEl) {
      errorEl.textContent = "";
      errorEl.style.display = "none";
    }
    el.classList.remove("input-error");
    el.classList.remove("input-valid");
  }

  // === Attach validation to a form ===
  function attachValidation(formId) {
    const form = document.getElementById(formId);
    if (!form) return;

    const inputs = form.querySelectorAll("input[required], select[required]");

    inputs.forEach(input => {
      input.addEventListener("input", () => clearError(input));
      input.addEventListener("change", () => clearError(input));
    });

    form.addEventListener("submit", function (e) {
      let valid = true;

      inputs.forEach(input => {
        if (!input.value.trim()) {
          showError(input, "This field is required");
          valid = false;
        }
      });

      if (!valid) {
        e.preventDefault();
      }
    });
  }

  // === Apply validation to both forms ===
  attachValidation("fetchcourseForm");
  attachValidation("updatecourseForm");

  // === Special live validation for Course Code ===
  const codeInput = document.getElementById("code");
  if (codeInput) {
    const pattern = /^[A-Z0-9]{6,}$/;
    codeInput.addEventListener("input", function () {
      if (!this.value.trim()) {
        clearError(this);
      } else if (!pattern.test(this.value)) {
        showError(this, "Course code must be at least 6 characters long, only capital letters and numbers allowed");
      } else {
        showValid(this);
      }
    });
  }

  // === Auto-hide server messages after 3 seconds ===
  const messages = document.querySelectorAll(".msg-box");
  if (messages.length > 0) {
    setTimeout(() => {
      messages.forEach(msg => {
        msg.style.transition = "opacity 0.5s ease";
        msg.style.opacity = "0";
        setTimeout(() => msg.remove(), 500);
      });
    }, 3000);
  }
});
