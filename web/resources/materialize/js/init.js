document.addEventListener('DOMContentLoaded', function() {
    var elemsSidenav = document.querySelectorAll('.sidenav');
    M.Sidenav.init(elemsSidenav);

    var elemsModal = document.querySelectorAll('.modal');
    M.Modal.init(elemsModal);

    var elemsMaterialboxed = document.querySelectorAll('.materialboxed');
    M.Materialbox.init(elemsMaterialboxed);

    var elemsCollapsible = document.querySelectorAll('.collapsible');
    M.Collapsible.init(elemsCollapsible);

    var elemsDatepicker = document.querySelectorAll('.datepicker');
    M.Datepicker.init(elemsDatepicker, {
        format: 'yyyy-mm-dd'
    });

    var elems = document.querySelectorAll('.fixed-action-btn');
    M.FloatingActionButton.init(elems);
});
