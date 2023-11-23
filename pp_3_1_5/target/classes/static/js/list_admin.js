
    const list = document.querySelector('#nameUsers')

    async function start() {
        const BASE_URL = 'http://localhost:8080/'
        try {
            const resp = await fetch(`${BASE_URL}all`)
            const users = await resp.json()

            let k = '<tbody>'
            for (i = 0; i < users.length; i++) {
                k += '<tr class="td" align="center">'
                k += '<td>' + users[i].id + '</td>';
                k += '<td>' + users[i].name + '</td>';
                k += '<td>' + users[i].age + '</td>';
                k += '<td>' + users[i].userRole + '</td>';
                k += '<td><button type="button" onclick="editUser('+ users[i].id + ')" ' +
                    'class="btn btn-primary" data-target="#exampleModal" data-toggle="modal" id="btnEdit" >Edit</button></td>';
                /*k += '<td><button type="button"  class="btn btn-primary" >Edit</button></td>';*/
                k += '<td><button type="button"' +
                    ' onclick="delUser(' + users[i].id + ')" ' +
                    'class="btn btn-danger"  data-target="#exModal" data-toggle="modal" id="btnDelete">Delete</button></td>';
                k += '</tr>';
            }
            k+='</tbody>';
            document.getElementById('nameUsers').innerHTML = k;
        } catch (err) {
            list.styles.color = 'red'
            list.innerHTML = 'error'
        }
    }
    start()
