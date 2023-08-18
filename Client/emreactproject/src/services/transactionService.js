let url = 'http://localhost:8080/transactions'

export async function getAllTransactions(){
    let response = await fetch(url);
    console.log(response)
    let data = await response.json();
    return data;
}