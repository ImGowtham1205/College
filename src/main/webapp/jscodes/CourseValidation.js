document.addEventListener("DOMContentLoaded", function () {
  const form = document.getElementById("CourseForm");

  // === Validation rules ===
  const codeInput = document.getElementById("code");
  const codePattern = /^[A-Z0-9]{6,}$/;

  function validateField(input) {
    const value = input.value.trim();
    const errorText = input.parentNode.querySelector(".error-text");

    // Special rule for Course Code
    if (input.id === "code") {
      if (!codePattern.test(value)) {
        input.classList.add("input-error");
        input.classList.remove("input-success");
        errorText.textContent =
          "Course code must be at least 6 characters long, only capital letters and numbers allowed";
        return false;
      } else {
        input.classList.remove("input-error");
        input.classList.add("input-success");
        errorText.textContent = "";
        return true;
      }
    }

    // For all other required fields (subject, selects, etc.)
    if (value === "") {
      input.classList.add("input-error");
      input.classList.remove("input-success");
      errorText.textContent = "This field is required";
      return false;
    } else {
      input.classList.remove("input-error");
      input.classList.add("input-success");
      errorText.textContent = "";
      return true;
    }
  }

  // === Attach validation to all inputs/selects ===
  const inputs = form.querySelectorAll("input[required], select[required], textarea[required]");
  inputs.forEach((input) => {
    input.addEventListener("input", () => validateField(input));
    input.addEventListener("change", () => validateField(input)); // for selects
  });

  // === On form submit check all ===
  form.addEventListener("submit", function (e) {
    let isValid = true;
    inputs.forEach((input) => {
      if (!validateField(input)) {
        isValid = false;
      }
    });

    if (!isValid) {
      e.preventDefault(); // stop submission
    }
  });

  // === Hide server-side messages after 3 seconds ===
  const messages = document.querySelectorAll(".msg-box");
  if (messages.length > 0) {
    setTimeout(() => {
      messages.forEach((msg) => {
        msg.style.transition = "opacity 0.5s ease";
        msg.style.opacity = "0";
        setTimeout(() => msg.remove(), 500);
      });
    }, 3000);
  }
});
