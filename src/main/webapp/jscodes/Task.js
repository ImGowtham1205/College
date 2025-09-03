// ===== TEXTAREA VALIDATION + CHARACTER COUNTER =====
const textarea = document.getElementById("request");
const errorMsg = document.getElementById("errorMsg");
const charCount = document.getElementById("charCount");

function validateRequest() {
    const value = textarea.value.trim();

    // Check if empty
    if (value.length === 0) {
        errorMsg.textContent = "Please enter your request.";
        showError();
        return false;
    }

    // Check max length
    if (value.length > 500) {
        errorMsg.textContent = "Request cannot exceed 500 characters.";
        showError();
        return false;
    }

    clearError();
    return true;
}

function showError() {
    textarea.classList.add("error");
    textarea.classList.add("shake");
    setTimeout(() => textarea.classList.remove("shake"), 500);
}

function clearError() {
    textarea.classList.remove("error");
    errorMsg.textContent = "";
}

// Live character counter
textarea.addEventListener("input", function () {
    const value = this.value;
    const length = value.length;
    charCount.textContent = length + " / 500 characters";

    if (length > 500) {
        charCount.classList.add("warning");
        errorMsg.textContent = "Request cannot exceed 500 characters.";
        textarea.classList.add("error");
    } else {
        charCount.classList.remove("warning");
        if (length > 0) {
            clearError();
        }
    }
});

// ===== SUCCESS MESSAGE AUTO HIDE =====
window.addEventListener("DOMContentLoaded", () => {
    const successMsg = document.getElementById("successMsg");
    if (successMsg) {
        setTimeout(() => {
            successMsg.style.display = "none";
        }, 3000); // hide after 3 seconds
    }
});
