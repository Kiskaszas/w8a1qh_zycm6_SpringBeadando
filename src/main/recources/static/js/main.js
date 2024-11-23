function showNotification(message, isError = false) {
    const notification = document.getElementById('notification');
    const notificationMessage = document.getElementById('notification-message');
    const closeButton = document.getElementById('notification-close-btn');

    notificationMessage.textContent = message;

    if (isError) {
        notification.classList.add('error');
    } else {
        notification.classList.remove('error');
    }

    // Az értesítés megjelenítése
    notification.classList.remove('hidden');
    notification.classList.add('visible');

    // Automatikus eltűnés 3 másodperc múlva, ha az egér nincs fölötte
    let hideTimeout = setTimeout(function() {
        notification.classList.remove('visible');
    }, 500000);

    // Ha az egér fölé kerül, ne tűnjön el automatikusan
    notification.addEventListener('mouseenter', function() {
        clearTimeout(hideTimeout);
    });

    // Ha az egér elhagyja az értesítést, ismét beállítjuk a 3 másodperces eltűnési időt
    notification.addEventListener('mouseleave', function() {
        hideTimeout = setTimeout(function() {
            notification.classList.remove('visible');
        }, 500000);
    });

    // Gombra kattintva az értesítés bezárása
    closeButton.addEventListener('click', function() {
        notification.classList.remove('visible');
    });
}
