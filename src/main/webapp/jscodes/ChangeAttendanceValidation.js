document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("attendanceForm");
    if (!form) return;

    // Form fields
    const date = document.getElementById("date");
    const beginTime = document.getElementById("beginTime");
    const endTime = document.getElementById("endTime");
    const student = document.getElementById("student");
    const radios = document.getElementsByName("attendance");
    const attendanceGroup = document.getElementById("attendanceGroup");

    // Error elements
    const dateError = document.getElementById("dateError");
    const beginTimeError = document.getElementById("beginTimeError");
    const endTimeError = document.getElementById("endTimeError");
    const studentError = document.getElementById("studentError");
    const attendanceError = document.getElementById("attendanceError");

    // Show/clear error functions
    function showError(input, errorEl, msg) {
        if (errorEl) errorEl.textContent = msg;
        if (input) input.classList.add("input-error");
    }

    function clearError(input, errorEl) {
        if (errorEl) errorEl.textContent = "";
        if (input) input.classList.remove("input-error");
    }

    function showRadioError(group, errorEl, msg) {
        if (errorEl) errorEl.textContent = msg;
        if (group) group.classList.add("radio-error");
    }

    function clearRadioError(group, errorEl) {
        if (errorEl) errorEl.textContent = "";
        if (group) group.classList.remove("radio-error");
    }

    // Form submit validation
    form.addEventListener("submit", function (e) {
        let valid = true;

        if (date && !date.value) {
            showError(date, dateError, "Please select a date");
            valid = false;
        } else if (date) clearError(date, dateError);

        if (beginTime && !beginTime.value) {
            showError(beginTime, beginTimeError, "Please select begin time");
            valid = false;
        } else if (beginTime) clearError(beginTime, beginTimeError);

        if (endTime && !endTime.value) {
            showError(endTime, endTimeError, "Please select end time");
            valid = false;
        } else if (endTime) clearError(endTime, endTimeError);

        if (student && !student.value) {
            showError(student, studentError, "Please select a student");
            valid = false;
        } else if (student) clearError(student, studentError);

        // Check radio buttons
        let checked = false;
        if (radios && radios.length > 0) {
            radios.forEach(r => {
                if (r.checked) checked = true;
            });
        }
        if (!checked) {
            showRadioError(attendanceGroup, attendanceError, "Please select attendance status");
            valid = false;
        } else clearRadioError(attendanceGroup, attendanceError);

        if (!valid) e.preventDefault();
    });

    // Hide server-side messages after 3 seconds
    const msg = document.getElementById("serverMsg");
    if (msg) {
        setTimeout(() => {
            msg.style.display = "none";
        }, 3000);
    }

    // Clear errors on user interaction
    [date, beginTime, endTime, student].forEach(el => {
        if (el) {
            const errorEl = document.getElementById(el.id + "Error");
            el.addEventListener("change", () => clearError(el, errorEl));
        }
    });

    if (radios && radios.length > 0) {
        radios.forEach(radio => {
            radio.addEventListener("change", () => clearRadioError(attendanceGroup, attendanceError));
        });
    }
});
