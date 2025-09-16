document.addEventListener("DOMContentLoaded", function () {
  // Match the form id in JSP
  var form = document.getElementById("timeForm");
  if (!form) return;

  var selects = form.querySelectorAll("select");

  // === Show error below dropdown ===
  function showError(select, message) {
    var errorEl = select.parentNode.querySelector(".field-error");
    if (!errorEl) {
      errorEl = document.createElement("div");
      errorEl.className = "field-error";
      select.parentNode.appendChild(errorEl);
    }
    errorEl.textContent = message;
    select.classList.add("invalid");
  }

  // === Clear error ===
  function clearError(select) {
    var errorEl = select.parentNode.querySelector(".field-error");
    if (errorEl) {
      errorEl.textContent = "";
    }
    select.classList.remove("invalid");
  }

  // === On form submit ===
  form.addEventListener("submit", function (e) {
    var valid = true;

    Array.prototype.forEach.call(selects, function (sel) {
      var val = (sel.value || "").trim();
      if (!val) {
        var label = "this field";
        var labParent = sel.closest(".form-group");
        if (labParent) {
          var lab = labParent.querySelector("label");
          if (lab && lab.textContent) {
            label = lab.textContent.replace(":", "").trim().toLowerCase();
          }
        }
        showError(sel, "Please select " + label + ".");
        valid = false;
      } else {
        clearError(sel);
      }
    });

    if (!valid) {
      e.preventDefault();
      // focus first invalid dropdown
      var firstInvalid = null;
      Array.prototype.forEach.call(selects, function (s) {
        if (!firstInvalid && s.classList.contains("invalid")) {
          firstInvalid = s;
        }
      });
      if (firstInvalid) firstInvalid.focus();
    }
  });

  // === Clear error when user interacts ===
  Array.prototype.forEach.call(selects, function (sel) {
    sel.addEventListener("change", function () {
      clearError(sel);
    });
    sel.addEventListener("mousedown", function () {
      clearError(sel);
    }); // desktop
    sel.addEventListener("touchstart", function () {
      clearError(sel);
    }); // mobile
  });

  // === Hide server-side messages after 3s ===
  var messages = document.querySelectorAll(".error-msg, .success-msg");
  if (messages.length > 0) {
    setTimeout(function () {
      Array.prototype.forEach.call(messages, function (msg) {
        msg.style.display = "none";
      });
    }, 3000);
  }
});
