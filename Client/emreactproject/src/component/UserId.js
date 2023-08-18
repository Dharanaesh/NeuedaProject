import React, { useState } from 'react';
import './UserId.css';
import { GetUserById } from '../services/GetAllUsers';

export default function UserId() {
  const [user, setUser] = useState({});
  const [id, setId] = useState('');

  const handleChange = (event) => {
    setId(event.target.value);
  };
  const handleReset = () =>{
    setUser({});
  setId('');
  }
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
    <div className='idDiv'>
      <form>
        <table>
          <tr>
            <td className='td'>
              <h4>
                <b>Enter a valid Customer ID</b>
              </h4>
            </td>
            <td className='td'>
              <div className='col-12'>
                <input
                  type='number'
                  className='form-control'
                  value={id}
                  onChange={handleChange}
                  placeholder='Enter Customer ID'
                />
              </div>
            </td>
            <td className='td'>
              <div className='col-12'>
                <button type='button' className='button'  onClick={fetchUser}>
                  Search
                </button>
                <button type='button' className='button' style={{ backgroundColor: 'red', color: 'white' }}onClick={handleReset}>
                  Reset
                </button>
              </div>
            </td>
          </tr>
        </table>
      </form>

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
    </div>
  );
}