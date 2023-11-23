

/*const BASE_URL = 'http://localhost:8080/'

const  idFiled = document.querySelector('#editId')
const nameFiled = document.querySelector('#editName')
const ageFiled = document.querySelector('#editAge')
const passwordFiled = document.querySelector('#editPassword')
const roleFiled = document.querySelector('#editRole')
const userBtn = document.querySelector('#btnEdit')*/

/*const openModalBtn = document.querySelector('.btn-primary')
const closeModalBtn = document.querySelector('.close')
const exampleModal = document.querySelector('#exampleModal')[0]

openModalBtn.onclick = function () {
 exampleModal.styles.display = 'block'
}

closeModalBtn.onclick = function () {
 exampleModal.styles.display = 'none'
}*/

/*async function edit() {
    const modal = document.querySelector('#mod1');
    const btn = document.querySelector('#btnn');
    const span = document.getElementsByClassName('close')[0];




    btn.onclick = function() {
        modal.style.display = "block";
    }

    span.onclick = function() {
        modal.style.display = "none";
    }

    window.onclick = function(event) {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    }
}*/


/*const openModal = function () {
    exampleModal.classList.remove()
}*/

/*openModalBtn.addEventListener("click", openModal);*/

async function editUser(id) {
    //document.getElementById('editUser').innerHTML = ;
    let editID = document.querySelector('#editId')
    let editNAME = document.querySelector('#editName')
    let editAGE = document.querySelector('#editAge')
    let editPASS = document.querySelector('#editPassword')
    let editRole = document.querySelector('#editRole')
    let userBtnEdit = document.querySelector('#editBtn')

    const resp = await fetch(`${BASE_URL}all`)
    const users = await resp.json()
    let userById = users.find(x => x.id === id)

    editID.value = userById.id
    editNAME.value = userById.name
    editAGE.value = userById.age
    editPASS.value = userById.password

    userBtnEdit.addEventListener('click', () => editUserBy())

    const editUserBy = async () => {
        let roleListEdit = [
            {id: 1, role: "ADMIN"},
            {id: 2, role: "USER"}
        ]
        let arrayEdit = $("#editRole").val();

        let userRoleEdit = () => {
            let array2 = []
            if (arrayEdit.length === 2) {
                array2.push(roleListEdit[0])
                array2.push(roleListEdit[1])
            } else {
                switch (editRole.value) {
                    case '1':
                        array2.push(roleListEdit[0])
                        break;

                    case '2':
                        array2.push(roleListEdit[1])
                        break;

                    default:
                        array2.push()
                        break;
                }
            }console.log(array2)
            return array2
        }

        let user = {
            id: id,
            name: editNAME.value,
            age: editAGE.value,
            password: editPASS.value,
            roles: userRoleEdit()
        }

        try {
            await fetch(`${BASE_URL}admin/edit/${id}`, {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(user)
            })
            $('#nameUsers').html(start());
            document.getElementById('editClose').click();
        } catch (error) {
            console.error(error)
        }

    }
}
