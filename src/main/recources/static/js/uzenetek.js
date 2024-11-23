$(document).ready(function () {
    $('#deleteOptionBtn').click(function () {
        let uzenetId = $(this).data('id');
        $('#deleteUzenetId').val(uzenetId);
    });

    $('#deleteModalForm').submit(function (event) {
        event.preventDefault();
        let uzenetId = $('#deleteUzenetId').val();

        $.ajax({
            url: '/uzenetek/' + uzenetId,
            type: 'DELETE',
            success: function (result) {
                $('#uzenetTable').find('[data-id="' + uzenetId + '"]').closest('tr').remove();

                showNotification('Az üzenet sikeresen törölve lett.');

                $('#deleteModal').modal('hide');
            },
            error: function (xhr, status, error) {
                showNotification('Hiba történt az üzenet törlése során.');
            }
        });
    });

    function showNotification(message) {
        $('#notification-message').text(message);
        $('#notification').removeClass('hidden');

        setTimeout(function () {
            $('#notification').addClass('hidden');
        }, 3000);
    }

    $('#notification-close-btn').click(function () {
        $('#notification').addClass('hidden');
    });
});