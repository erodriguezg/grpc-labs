$(function () {
    addCSRFListener();
});

function addCSRFListener() {
    document.body.addEventListener('htmx:configRequest', function (event) {
        var token = document.querySelector('meta[name="_csrf"]').getAttribute('content');
        var header = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');
        event.detail.headers[header] = token;
    });
}

function showLoading() {
    $('#globalLoading').addClass('is-visible');
}

function hideLoading() {
    $('#globalLoading').removeClass('is-visible');
}