import React, { useEffect,useState } from 'react'
import { deleteUser, GetUserById } from '../services/GetAllUsers'
import { Navigate, useNavigate } from 'react-router-dom';
import './DeleteUser.css'

export default function DeleteUser() {
    let navigate = useNavigate();
    const [user, setUser] = useState({})
    const [id,setid] = useState({})
    const [showAlert, setShowAlert] = useState(false);
    const handleChange = (event)=>{
        setid(event.target.value)
    }

    const deleUser = () => {
        deleteUser(Number(id))
          .then(res => {
            setShowAlert(true);
            setTimeout(() => {
                setShowAlert(false);
                navigate('/usersList');
              }, 6000);
            // alert('User with customer ID ' + id.toString() + ' deleted.');
            // navigate('/usersList'); // Navigate after the user is deleted
          })
          .catch(error => {
            console.error('Error deleting user:', error);
            // Handle error and potentially show a message to the user
          });
      };

    const fetchUser = () => {
        GetUserById(Number(id))
          .then((userDataArray) => {
            if (Array.isArray(userDataArray) && userDataArray.length > 0) {
              setUser(userDataArray[0]);
            } else {
              setUser(null); // Set user to null when no user data is found
            }
          })
          .catch((error) => {
            console.error('Error fetching user data:', error);
          });
      };
    
      const formatDate = (dateString) => {
        const date = new Date(dateString);
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const day = String(date.getDate()).padStart(2, '0');
        return `${year}-${month}-${day}`;
      };
  return (
    <div class='deleteDiv'>
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
            <td className='td'>
                <div className="col-12">
                    <button type="reset" className="btn btn-danger" onClick={deleUser}>Delete</button>
                </div>
            </td>
            </tr>
        </table>
        {user !== null ? (
  user.dob ? (
    <div className='user-info'>
      <div>
        <div>
          <p>
            <span className="info-label">Customer-Id:</span>
            <span className="data-label">{user.customerId}</span>
          </p>
          <p>
            <span className="info-label">First Name:</span>
            <span className="data-label">{user.first}</span>
          </p>
          <p>
            <span className="info-label">Last Name:</span>
            <span className="data-label">{user.last}</span>
          </p>
          <p>
            <span className="info-label">Gender:</span>
            <span className="data-label">{user.gender}</span>
          </p>
          <p>
            <span className="info-label">Job Details:</span>
            <span className="data-label">{user.job}</span>
          </p>
          <p>
            <span className="info-label">Date of Birth:</span>
            <span className="data-label">{formatDate(user.dob)}</span>
          </p>
        </div>
      </div>
    </div>
  ): (
    <p>No Input Found</p>
  )
) : id ? (
  <p style={{ color: 'red', fontWeight: 'bold' }}>No user with such ID found</p>

) : null}
{showAlert && (
<div className="alert-modal">
    <p>Your request for Deletion is Accepted and sent for approval. Thank you..</p>
  </div>)}
    </div>
  )
}
