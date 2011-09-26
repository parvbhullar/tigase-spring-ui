function searchPassword() {
    document.accountInfo.action = "account.do?action=retrieveAccountPassword";
    document.accountInfo.submit();
}
function editPassword() {
    document.accountInfo.action = "account.do?action=updateAccountPassword";
    document.accountInfo.submit();
}

