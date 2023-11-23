

async function allUser() {
    const BASE_URL = 'http://localhost:8080/'
    let a = ''
    const table = document.querySelector('#allUser')
    fetch('https://localhost:8080/ad')
        .then(response => response.json())
        .then(user => {
            a = `
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.age}</td>
                <td>${user.read}</td>
            <tr>`
        }
    );
}
