function toggleTheme() {
    var htmlElement = document.getElementsByTagName('html')[0];

    if (htmlElement.getAttribute('data-bs-theme') === 'dark') {
        htmlElement.setAttribute('data-bs-theme', 'light');
    } else {
        htmlElement.setAttribute('data-bs-theme', 'dark');
    }
}