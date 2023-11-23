
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
