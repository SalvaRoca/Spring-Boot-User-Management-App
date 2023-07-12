// Call the dataTables jQuery plugin
$(document).ready(function() {
    loadUsers();
    $('#users').DataTable();
    updateUserEmail();
});

function updateUserEmail() {
    document.getElementById('userEmail').outerHTML = localStorage.email;
}

async function loadUsers() {
    const request = await fetch ('api/users', {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': localStorage.token
        }
    });
    const users = await request.json();



    let listHtml = '';

    for (let user of users) {
        let deleteButton = '<a href="#" onclick="deleteUser(' + user.id + ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>'
        let userHtml = '<tr><td>'+ user.id +'</td><td>' + user.name + ' ' + user.surname + '</td><td>'
                       + user.email + '</td><td>' + user.phone
                       + '</td><td>' + deleteButton + '</td></tr>';
        listHtml += userHtml;
    }

    console.log(users);

    document.querySelector('#users tbody').outerHTML = listHtml;

}

async function deleteUser(id) {

    if (!confirm('Delete this user?')) {
        return;
    }

    const request = await fetch ('api/user/' + id, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': localStorage.token
        }
    });

    location.reload();
}