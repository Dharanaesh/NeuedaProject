let url = 'http://localhost:8080/creditcard'


export async function GetAllUsers(){
    let response = await fetch(url);
    let users = await response.json();
    return users
}

export async function GetUserById(id){
    console.log(url+'/'+id.toString())
    let response = await fetch(url+'/'+id.toString())
    let user = await response.json()
    return user
}

export async function addUser(user) {
    console.log(JSON.stringify(user))
    console.log('we are here 1')
    let response = await fetch(url, {
      method: "POST",
      body: JSON.stringify(user),
      headers: {
        "Content-Type": "application/json",
      },
    });
    
    console.log(response)
    let newUser = await response.json();
    console.log(newUser);

    return newUser;
  }



export async function deleteUser(id){
  let response = await fetch(url+'/'+id.toString(),{
    method:"DELETE"
  });
  let data= await response.json();
  return data;
}

export async function updateUser(id){
    
}