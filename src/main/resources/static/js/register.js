// Call the dataTables jQuery plugin
$(document).ready(function() {
});

async function registerUser() {
    let data = {};
    data.name = document.getElementById('inputName').value;
    data.surname = document.getElementById('inputSurname').value;
    data.email = document.getElementById('inputEmail').value;
    data.phone = document.getElementById('inputPhone').value;
    data.password = document.getElementById('inputPassword').value;

    let repeatPassword = document.getElementById('repeatPassword').value;

    if (repeatPassword != data.password) {
        alert('Password does not match, please try again');
        return;
    }

    const request = await fetch ('api/users', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });
    alert("Account successfully created!")
    window.location.href = 'users.html'
}

