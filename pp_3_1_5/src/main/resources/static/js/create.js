
    const BASE_URL = 'http://localhost:8080/'

    let nameFiled = document.querySelector('#inputName')
    let ageFiled = document.querySelector('#inputAge')
    let passwordFiled = document.querySelector('#inputPassword')
    let roleId = document.querySelector('#inputRole')
    let userBtn = document.querySelector('#userBtn')

    userBtn.addEventListener('click', () => createUser())

    const createUser = async () => {

        let roleList = [
            {id: 1, role: "ADMIN"},
            {id: 2, role: "USER"}
        ]

        let array = $("#inputRole").val();

        let userRol = () => {
            let array1 = []
            if (array.length === 2) {
                  array1.push(roleList[0])
                  array1.push(roleList[1])
            } else {
                switch(roleId.value) {
                    case '1':
                        array1.push(roleList[0])
                        break;

                    case '2':
                        array1.push(roleList[1])
                        break;

                    default:
                        array1.push(roleList[1])
                        break;
                }
            }
            return array1
        }
        let user = {
            name: nameFiled.value,
            age: ageFiled.value,
            password: passwordFiled.value,
            roles: userRol()
        }

        try {
            await fetch(`${BASE_URL}admin/add`, {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(user)
            })
            $('#nameUsers').html(start());
            document.getElementById('nav-users-tab').click();

        } catch (error) {
            console.error(error)
        }

        console.log('obj', user)

    }


