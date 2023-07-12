// Call the dataTables jQuery plugin
$(document).ready(function() {
});

async function userLogin() {
    let data = {};
    data.email = document.getElementById('inputEmail').value;
    data.password = document.getElementById('inputPassword').value;

    const request = await fetch ('api/login', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });
    const response = await request.text();
    if (response != 'Fail') {
        localStorage.token = response;
        localStorage.email = data.email;
        window.location.href = 'users.html'
    } else {
        alert("Invalid credentials, please try again.")
    }
}

