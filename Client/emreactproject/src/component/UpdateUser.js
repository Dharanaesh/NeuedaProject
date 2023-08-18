import React, { useState } from 'react'
import { deleteUser,addUser, GetUserById } from '../services/GetAllUsers'
import { useNavigate } from 'react-router-dom';
import './UpdateUser.css'

export default function UpdateUser() {

    let navigate = useNavigate();
    const [user, setuser] = useState({first:'',last:'',gender:'',job:'',dob:''})
    const [id,setid] = useState({})

    const handleChange = (event)=>{
        setid(event.target.value)
    }

    const handleChangeUser = (event)=>{
        let {name,value} = event.target;
        let newUser = {...user,[name]:value}
        setuser(newUser)
    }


    const nextStep = () =>{
        addUser(user)
        alert('User with customer ID '+id.toString()+' updated.')
        navigate('/usersList')
    }
    const updUser = () =>{
        deleteUser(Number(id))
        nextStep();
    }

    const fetchUser = () => {
        GetUserById(Number(id))
            .then(user => {
                setuser(user[0]);
                let date_value = user[0].dob.substring(0, 10);
                document.getElementById('dat').value = date_value;
    
                if (user[0].gender === 'M') {
                    document.getElementById('genderM').checked = true;
                } else {
                    document.getElementById('genderF').checked = true;
                }
            })
            .catch(error => {
                console.error("Error fetching user:", error);
            });
    }
    

  return (
    <div className='updateDiv'>
        <h5>Update User Details</h5>
        
        <table><tr>
            <td className='td'>
                <h4><b>Enter a valid Customer ID</b></h4>
            </td>
            <td className='td'>
                <div className="col-12">
                    <input type="number" className="form-control" 
                    name={id} onChange={handleChange} placeholder="Enter Customer ID" />
                </div>
            </td>
            <td className='td'>
                <div className="col-12">
                    <button type="submit" className="btn btn-primary" onClick={fetchUser}>Search</button>
                </div>
            </td>
            </tr>
        </table>
        <form onSubmit={updUser}>
        <div className="m-3">
        <div className="mb-4">
                <label className='lab'> First Name </label>
                <input type="text" className="form-control" 
                name='first' value={user.first} onChange={handleChangeUser}
                placeholder="First Name" />
            </div>
            <div className="mb-4">
                <label  className='lab'>Last Name</label>
                <input type="text" className="form-control" 
                name='last' value={user.last} onChange={handleChangeUser}
                placeholder="Last Name" />
            </div>
            <div className="mb-4">
                <label  className='lab'>Gender</label>
                <input id="genderM" type="radio" name='gender' style={{ marginRight: '5px' }} value={user.gender} onChange={handleChangeUser}/>
                <label id="male" for="genderM">Male</label>
                <input id="genderF" type="radio" name='gender' style={{ marginRight: '5px' }} value={user.gender} onChange={handleChangeUser}/>
                <label for="genderF">Female</label>
            </div>
            <div className="mb-4">
                <label  className='lab'>Job</label>
                <input type="text" className="form-control" 
                name='job' value={user.job} onChange={handleChangeUser}
                placeholder="Job" />
            </div>
            <div className="mb-4">
                <label  className='lab'>Date of Birth</label>
                <input id='dat' type="date"  required pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}" name='dob' onChange={handleChangeUser} />
            </div>
            <div className="col-12">
                <button type="submit" className="btn btn-primary" >Update</button>
            </div>
        </div>
        
        </form>
        <div id='output'></div>
    </div>
  )
}
