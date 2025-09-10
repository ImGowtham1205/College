function toggleSidebar(menuToggle) {
  const body = document.body;
  const sidebar = document.querySelector(".sidebar");

  // Toggle sidebar
  sidebar.classList.toggle("show");
  body.classList.toggle("sidebar-open");

  // Animate hamburger
  menuToggle.classList.toggle("open");
}
