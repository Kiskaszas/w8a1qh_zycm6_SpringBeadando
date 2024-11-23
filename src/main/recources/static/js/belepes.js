document.getElementById('searchInput').addEventListener('input', function() {
    let filter = this.value.toLowerCase();
    let rows = document.querySelectorAll('#belepesTable tr');
    rows.forEach(row => {
        let text = row.textContent.toLowerCase();
        row.style.display = text.includes(filter) ? '' : 'none';
    });
});
// Modal adat kitöltése módosításhoz
/*document.getElementById('updateModal').addEventListener('show.bs.modal', function (event) {
    var button = event.relatedTarget;
    var belepesId = button.getAttribute('data-id');

    jQuery.ajax({
        url: '/football/belepes/' + belepesId,
        type: 'GET',
        success: function(response) {
            $('#updateBelepesId').val(response.id);
            $('#nezo').val(response.nezo.id);
            $('#meccs').val(response.meccs.id);
            $('#idopont').val(response.idopont);
            window.location.href = "/";
        },
        error: function(xhr, status, error) {
            showNotification("Hiba történt a bejelentkezés során: " + error, true);
            console.error("Hiba történt az adatok betöltése során: " + error);
        }
    });
});*/

jQuery('#updateModalForm').on('submit', function (e) {
    e.preventDefault();

    var json = JSON.stringify({
        id: $('#updateBelepesId').val(),
        nezo: { id: $('#nezo').val() },
        meccs: { id: $('#meccs').val() },
        idopont: $('#idopont').val()
    });

    jQuery.ajax({
        url: '/football/belepes/' + $('#updateBelepesId').val(),
        type: 'PUT',
        data: json,
        contentType: "application/json",
        success: function() {
            alert("Belépés sikeresen frissítve!");
            window.location.href = "/belepesek";
        },
        error: function(xhr, status, error){
            console.error("Hiba történt a frissítés során: " + error);
        }
    });
});

// Törlés Modal beállítása
document.getElementById('deleteModal').addEventListener(
    'show.bs.modal', function (event) {
    var button = event.relatedTarget;
    var belepesId = button.getAttribute('data-id');

    $('#deleteBelepesId').val(belepesId);
    jQuery('#deleteModalForm').on('submit', function (e) {
        e.preventDefault();

        jQuery.ajax({
            url: '/football/belepes/' + belepesId,
            type: 'DELETE',
            success: function() {
                alert("Belépés sikeresen törölve!");
                window.location.href = "/belepes";
            },
            error: function(xhr, status, error) {
                console.error("Hiba történt a törlés során: " + error);
            }
        });
    });
});