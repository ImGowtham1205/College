function toggleMenu() {
    const sidebar = document.getElementById('sidebarMenu');
    const container = document.querySelector('.container');
    const overlay = document.getElementById('overlay');

    sidebar.classList.toggle('open');
    container.classList.toggle('shifted');

    // Show or hide overlay
    if (sidebar.classList.contains('open')) {
        overlay.style.display = 'block';
    } else {
        overlay.style.display = 'none';
    }
}

// Close sidebar when clicking on overlay
function closeSidebar() {
    const sidebar = document.getElementById('sidebarMenu');
    const container = document.querySelector('.container');
    const overlay = document.getElementById('overlay');

    sidebar.classList.remove('open');
    container.classList.remove('shifted');
    overlay.style.display = 'none';
}

// Highlight clicked course card
document.querySelectorAll('.info-item').forEach(item => {
    item.addEventListener('click', function() {
        document.querySelectorAll('.info-item').forEach(i => i.classList.remove('active'));
        this.classList.add('active');
    });
});
