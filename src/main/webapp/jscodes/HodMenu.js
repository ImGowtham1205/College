// Sidebar toggle
function toggleSidebar() {
  const sidebar = document.getElementById("sidebar");
  const body = document.body;
  sidebar.classList.toggle("active");
  body.classList.toggle("sidebar-open");
}

// Close sidebar when clicking overlay
document.addEventListener("click", function (e) {
  const sidebar = document.getElementById("sidebar");
  if (!sidebar.contains(e.target) && document.body.classList.contains("sidebar-open") && e.target !== document.querySelector(".hamburger")) {
    sidebar.classList.remove("active");
    document.body.classList.remove("sidebar-open");
  }
});