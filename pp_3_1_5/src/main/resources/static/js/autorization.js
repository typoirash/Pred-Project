
const userByUsername = async () =>{
    const BASE_URL = 'http://localhost:8080/'
    try {
        fetch(`${BASE_URL}user/user`)
            .then(resp=>resp.json())
            .then(data=>{
                let a = `<tbody>
                        <div >
                        <b><span >${data.name}</span></b>
                        with roles:
                        <span class="boldtext4">${data.userRole}</span>
                    </div>
                    </tbody>`
                let b = `
                <tr align="center" class="td">
                    <td  class="non-modifiable">${data.id}</td>
                    <td  class="changeable">${data.name}</td>
                    <td  class="changeable">${data.age}</td>
                    <td  class="changeable">${data.userRole}</td>
                </tr>
                `
                if (data.userRole ==='USER') {
                    document.getElementById('v-pills-user-tab').click();
                }
                
                document.getElementById('panel').innerHTML = a;
                document.getElementById('user-info').innerHTML = b;
            })

    } catch (error) {
    console.error(error)
    }
}
userByUsername()
