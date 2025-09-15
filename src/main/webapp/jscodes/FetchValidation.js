document.addEventListener("DOMContentLoaded", function () {
  const form = document.getElementById("hodForm");
  if (!form) return;

  const year = document.getElementById("year");
  const sem = document.getElementById("sem");

  const yearError = document.getElementById("yearError");
  const semError = document.getElementById("semError");

  function showError(elInput, elError, message) {
    elError.textContent = message;
    elError.classList.add("active");
    elInput.classList.add("invalid");
  }

  function clearError(elInput, elError) {
    elError.textContent = "";
    elError.classList.remove("active");
    elInput.classList.remove("invalid");
  }

  year.addEventListener("change", () => clearError(year, yearError));
  sem.addEventListener("change", () => clearError(sem, semError));

  form.addEventListener("submit", function (e) {
    e.preventDefault();
    let valid = true;

    clearError(year, yearError);
    clearError(sem, semError);

    if (!year.value || year.value.trim() === "") {
      showError(year, yearError, "Year is required");
      valid = false;
    }
    if (!sem.value || sem.value.trim() === "") {
      showError(sem, semError, "Semester is required");
      valid = false;
    }

    if (!valid) return;

    form.submit();
  });

  // Auto-hide server message (if present)
  const serverMsg = document.getElementById("serverMsg");
  if (serverMsg) {
    setTimeout(() => {
      serverMsg.style.transition = "opacity 0.4s";
      serverMsg.style.opacity = "0";
      setTimeout(() => serverMsg.remove(), 400);
    }, 3000);
  }
});
