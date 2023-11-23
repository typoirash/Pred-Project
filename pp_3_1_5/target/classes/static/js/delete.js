




/*fetch(`${BASE_URL}all`)
    .then(resp=>resp.json())
    .then(data=>console.log('users', data))*/

//$('#deleteBtn').click(start());


async function delUser(id) {
    const BASE_URL = 'http://localhost:8080/'
    let deleteID = document.querySelector('#deleteId')
    let deleteNAME = document.querySelector('#deleteName')
    let deleteAGE = document.querySelector('#deleteAge')
    let deletePASS = document.querySelector('#deletePassword')
    let deleteRole = document.querySelector('#deleteRole')
    let userBtnDelete = document.querySelector('#deleteBtn')

    const resp = await fetch(`${BASE_URL}all`)
    const users = await resp.json()

    let userById = users.find(x => x.id === id)
    console.log(userById)

    deleteID.value = userById.id
    deleteNAME.value = userById.name
    deleteAGE.value = userById.age
    deletePASS.value = userById.password

    userBtnDelete.addEventListener('click', () => deleteUser())

    const deleteUser = async () =>{
        try {
            await fetch(`${BASE_URL}admin/del/${id}`, {
                method: 'DELETE',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(id)
            })
            $('#nameUsers').html(start())
            document.getElementById('delClose').click();
        }  catch (error) {
            console.error(error)
        }
    }
}

