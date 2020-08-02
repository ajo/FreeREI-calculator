document.addEventListener("DOMContentLoaded", function (event) {
    if ($("input[id='usingLoan']").is(':checked')) {
        $('#loanInfo').css('display', 'block');
        $('#noLoanInfo').css('display', 'none');
    } else {
        $('#loanInfo').css('display', 'none');
        $('#noLoanInfo').css('display', 'block');
    }

    if ($("input[id='needsRepairs']").is(':checked')) {
        $('#repairInfo').css('display', 'block');
    } else {
        $('#repairInfo').css('display', 'none');
    }
});

$("input[name='usingLoan']").click(function () {
    $('#loanInfo').css('display', ($(this).val() === 'true') ? 'block' : 'none');
    $('#noLoanInfo').css('display', ($(this).val() === 'true') ? 'none' : 'block');
});

$("input[name='needsRepairs']").click(function () {
    $('#repairInfo').css('display', ($(this).val() === 'true') ? 'block' : 'none');
});