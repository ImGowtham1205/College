// Sidebar toggle
function toggleSidebar() {
  const sidebar = document.getElementById("sidebar");
  const body = document.body;
  sidebar.classList.toggle("active");
  body.classList.toggle("sidebar-open");
}