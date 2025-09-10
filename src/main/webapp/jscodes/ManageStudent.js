document.addEventListener("DOMContentLoaded", () => {
  const fetchForm = document.getElementById("fetchStudentForm");
  const updateForm = document.getElementById("updateStudentForm");
  const updateBtn = document.getElementById("updateBtn");

  // ====== Disable Update button initially ======
  if (updateBtn && updateBtn.disabled) {
    updateBtn.classList.add("disabled-btn");
  }

  // ====== Helper: Find error <small> for field ======
  function getErrorElement(field) {
    let el = field.nextElementSibling;
    while (el && !el.classList.contains("error-text")) {
      el = el.nextElementSibling;
    }
    return el;
  }

  // ====== Validation function ======
  function validateField(field) {
    const errorText = getErrorElement(field);
    let valid = true;

    if (field.hasAttribute("required") && !field.value.trim()) {
      if (errorText) errorText.textContent = "This field is required";
      field.classList.add("error-border");
      field.classList.remove("success-border");
      valid = false;
    } else if (field.type === "number") {
      if (field.value.trim() === "" || isNaN(field.value)) {
        if (errorText) errorText.textContent = "Enter a valid number";
        field.classList.add("error-border");
        field.classList.remove("success-border");
        valid = false;
      } else {
        if (errorText) errorText.textContent = "";
        field.classList.remove("error-border");
        field.classList.add("success-border");
      }
    } else if (field.type === "email") {
      const emailPattern = /^[^ ]+@[^ ]+\.[a-z]{2,3}$/;
      if (!emailPattern.test(field.value.trim())) {
        if (errorText) errorText.textContent = "Enter a valid email";
        field.classList.add("error-border");
        field.classList.remove("success-border");
        valid = false;
      } else {
        if (errorText) errorText.textContent = "";
        field.classList.remove("error-border");
        field.classList.add("success-border");
      }
    } else if (field.type === "tel") {
      const phonePattern = /^[0-9]{10}$/;
      if (!phonePattern.test(field.value.trim())) {
        if (errorText) errorText.textContent = "Enter 10 digit number";
        field.classList.add("error-border");
        field.classList.remove("success-border");
        valid = false;
      } else {
        if (errorText) errorText.textContent = "";
        field.classList.remove("error-border");
        field.classList.add("success-border");
      }
    } else {
      if (errorText) errorText.textContent = "";
      field.classList.remove("error-border");
      field.classList.add("success-border");
    }

    return valid;
  }

  // ====== Attach validation to Fetch form ======
  if (fetchForm) {
    const rollInput = document.getElementById("studentId");

    // Add <small> if missing
    if (rollInput && !getErrorElement(rollInput)) {
      const small = document.createElement("small");
      small.classList.add("error-text");
      rollInput.insertAdjacentElement("afterend", small);
    }

    fetchForm.addEventListener("submit", (e) => {
      let formValid = true;
      fetchForm.querySelectorAll("input, select, textarea").forEach(field => {
        if (!validateField(field)) {
          formValid = false;
        }
      });

      if (!formValid) {
        e.preventDefault(); // stop form submission
      }
    });

    // Live validation
    rollInput.addEventListener("input", () => validateField(rollInput));
  }

  // ====== Attach validation to Update form ======
  if (updateForm) {
    updateForm.querySelectorAll("input, select, textarea").forEach(field => {
      field.addEventListener("input", () => validateField(field));
      field.addEventListener("change", () => validateField(field));
    });

    updateForm.addEventListener("submit", (e) => {
      let formValid = true;
      updateForm.querySelectorAll("input, select, textarea").forEach(field => {
        if (!validateField(field)) {
          formValid = false;
        }
      });

      if (!formValid) {
        e.preventDefault();
      }
    });
  }

  // ====== Fade out messages ======
  const msgBox = document.querySelector(".msg-box");
  if (msgBox) {
    setTimeout(() => {
      msgBox.classList.add("fade-out");
    }, 3000); // 3 seconds
  }
});
