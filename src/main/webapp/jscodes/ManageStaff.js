document.addEventListener("DOMContentLoaded", () => {
  const fetchForm = document.getElementById("fetchStaffForm");
  const updateForm = document.getElementById("updateStaffForm");

  // ====== Fade out messages ======
    const msgBox = document.querySelector(".msg-box");
    if (msgBox) {
      setTimeout(() => {
        msgBox.classList.add("fade-out");
      }, 3000); // 3 seconds
    }
	
	// ====== Disable Update button initially ======
	  if (updateBtn && updateBtn.disabled) {
	    updateBtn.classList.add("disabled-btn");
	  }
	  
  // Utility function to show error
  function showError(input, message) {
    const small = input.nextElementSibling;
    input.classList.add("error-border");
    input.classList.remove("success-border");
    if (small) {
      small.innerText = message;
    }
  }

  // Utility function to show success
  function showSuccess(input) {
    const small = input.nextElementSibling;
    input.classList.remove("error-border");
    input.classList.add("success-border");
    if (small) {
      small.innerText = "";
    }
  }

  // Required field check
  function checkRequired(input, message) {
    if (input.value.trim() === "" || input.value === null) {
      showError(input, message);
      return false;
    } else {
      showSuccess(input);
      return true;
    }
  }

  // Email validation
  function checkEmail(input) {
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!re.test(input.value.trim())) {
      showError(input, "Enter a valid email address");
      return false;
    }
    showSuccess(input);
    return true;
  }

  // Phone validation
  function checkPhone(input) {
    const re = /^[0-9]{10}$/;
    if (!re.test(input.value.trim())) {
      showError(input, "Enter a valid 10-digit phone number");
      return false;
    }
    showSuccess(input);
    return true;
  }

  /* ============================
     Validation for Fetch Form
  ============================ */
  fetchForm.addEventListener("submit", (e) => {
    let isValid = true;
    const staffId = document.getElementById("staffId");

    if (!checkRequired(staffId, "Staff ID is required")) {
      isValid = false;
    }

    if (!isValid) e.preventDefault();
  });

  /*  Validation for Update Form  */
  updateForm.addEventListener("submit", (e) => {
    let isValid = true;

    const name = document.getElementById("staffName");
    const dno = document.getElementById("dno");
    const email = document.getElementById("staffEmail");
    const phone = document.getElementById("phone");
    const desigination = document.getElementById("desigination");

    if (!checkRequired(name, "Name is required")) isValid = false;
    if (!checkRequired(dno, "Department number is required")) isValid = false;
    if (!checkRequired(email, "Email is required") || !checkEmail(email)) isValid = false;
    if (!checkRequired(phone, "Phone number is required") || !checkPhone(phone)) isValid = false;
    if (!checkRequired(desigination, "Designation is required")) isValid = false;

    if (!isValid) e.preventDefault();
  });

  /* Live validation on input */
  document.querySelectorAll("#updateStaffForm input, #updateStaffForm select").forEach((input) => {
    input.addEventListener("input", () => {
      if (input.id === "staffEmail") checkEmail(input);
      else if (input.id === "phone") checkPhone(input);
      else checkRequired(input, input.name + " is required");
    });
  });
});