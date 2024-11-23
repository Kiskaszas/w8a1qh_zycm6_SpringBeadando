document.getElementById('searchInput').addEventListener('input', function() {
    let filter = this.value.toLowerCase();
    let rows = document.querySelectorAll('#meccsTable tr');
    rows.forEach(row => {
        let text = row.textContent.toLowerCase();
        row.style.display = text.includes(filter) ? '' : 'none';
    });
});
// Meccs Modal form adat kitöltése
document.getElementById('updateModal').addEventListener('show.bs.modal', function (event) {
    var button = event.relatedTarget; // A modal-t megnyitó gomb
    var matchId = button.getAttribute('data-id'); // A gomb adat-id attribútuma, amely tartalmazza a meccs ID-t

    jQuery.ajax({
        url: '/football/meccs/' + matchId,
        type: 'GET',
        success: function(response) {
            // Töltsük fel a modal mezőit a válaszból kapott adatokkal
            $('#updateMeccsId').val(response.id);
            $('#datum').val(response.datum);
            $('#kezdes').val(response.kezdes);
            $('#belepo').val(response.belepo);
            $('#tipus').val(response.tipus);
        },
        error: function(xhr, status, error) {
            console.error("Hiba történt az adatok betöltése során: " + error);
        }
    });
});
document.getElementById('updateModal').addEventListener('show.bs.modal', function (event) {
    var button = event.relatedTarget;
    var id = button.getAttribute('data-id');
    var modal = this;
    modal.querySelector('#updateMeccsId').value = id;

    // AJAX kérés a módosításhoz
    jQuery('#updateModalForm').on('submit', function (e) {
        e.preventDefault();

        var json = JSON.stringify({
            id: $('#updateMeccsId').val(),
            datum: $('#datum').val(),
            kezdes: $('#kezdes').val(),
            belepo: $('#belepo').val(),
            tipus: $('#tipus').val()
        });

        jQuery.ajax({
            url: '/football/meccs/' + $('#updateMeccsId').val(),
            type: 'PUT',
            data: json,
            contentType: "application/json",
            success: function (){
                showNotification("Meccs sikeresen frissítve!");
                window.location.href = "/meccsek";
            },
            error: function(xhr, status, error){
                showNotification("Hiba történt a frissítés során: " + error, true);
                console.log(xhr.responseText);
            }
        });
    });
});

// Törlés Modal
document.getElementById('deleteModal').addEventListener(
    'show.bs.modal', function (event) {
    var button = event.relatedTarget; // A modal-t megnyitó gomb
    var matchId = button.getAttribute('data-id');

    var modal = this;
    modal.querySelector('#deleteMeccsId').value = matchId;

    // Frissítjük a form action URL-t, hogy tartalmazza az ID-t
    modal.querySelector('form').action = '/football/meccs/' + matchId;

    // AJAX kérés a törléshez
    jQuery('#deleteModalForm').on('submit', function () {
        jQuery.ajax({
            url: '/football/meccs/' + matchId,
            type: 'DELETE',
            success: function() {
                showNotification("Meccs sikeresen törölve!");
                window.location.href = "/meccsek";
            },
            error: function(xhr, status, error) {
                showNotification("Hiba történt a törlés során: " + error, true);
            }
        });
    })
});