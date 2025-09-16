// ==========================
// Select All Attendance Logic
// ==========================
function setAll(status) {
  const rollNumbersField = document.getElementById("rollNumbers");
  if (!rollNumbersField) return;

  const rollNumbers = rollNumbersField.value.split(",");
  rollNumbers.forEach(roll => {
    const radios = document.getElementsByName("status" + roll);
    radios.forEach(radio => {
      if (radio.value === status) {
        radio.checked = true;
      }
    });
  });
}

// ==========================
// Responsive Table Labels
// ==========================
document.addEventListener("DOMContentLoaded", function () {
  const table = document.querySelector("table");
  if (!table) return;

  const headers = Array.from(table.querySelectorAll("thead th"));
  const rows = table.querySelectorAll("tbody tr");

  rows.forEach(row => {
    const cells = row.querySelectorAll("td");
    cells.forEach((cell, i) => {
      if (headers[i]) {
        cell.setAttribute("data-label", headers[i].innerText.trim());
      }
    });
  });
});
