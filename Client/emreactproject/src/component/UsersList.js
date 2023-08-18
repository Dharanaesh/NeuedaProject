import React, { useEffect, useState } from 'react';
import { GetAllUsers } from '../services/GetAllUsers';
import './UsersList.css';

export default function UsersLists() {
    const [users, setUsers] = useState([]);
    const [filteredUsers, setFilteredUsers] = useState([]);
    const [id, setid] = useState([]);
    const [filterValues, setFilterValues] = useState({
      gender: "",
      job: ""
    });
    const [currentPage, setCurrentPage] = useState(0);
    const [pageSize, setPageSize] = useState(20);
    const [filteredCount, setFilteredCount] = useState(0);

    useEffect(() => {
      GetAllUsers().then(users => {
        setUsers(users);
        setFilteredUsers(users);
        updateFilteredCount(users);
        
      });
    }, []);
    const totalPages = Math.ceil(filteredUsers.length / pageSize);
    const startIndex = currentPage * pageSize;
    const endIndex = startIndex + pageSize;
    const displayedUsers = filteredUsers.slice(startIndex, endIndex);
    const updateFilteredCount = (filteredData) => {
      setFilteredCount(filteredData.length);
    };
    const handleFilterApply = () => {
      let filtered = users;
  
      if (filterValues.gender) {
        filtered = filtered.filter(user => user.gender === filterValues.gender);
      }
      if (filterValues.job) {
        filtered = filtered.filter(user => user.job === filterValues.job);
      }
  
      setFilteredUsers(filtered);
      setFilteredCount(filtered.length);
      setid("");
      setCurrentPage(0);
    };
    
  const handleChange = (event)=>{
    setid(event.target.value)
}
const fetchUser = () => {
    if (id === "") {
      // If customer ID is empty, reset filteredUsers to show all users
      setFilteredUsers(users);
    } else {
      // Find the user with the matching customer ID
      const foundUser = users.find(user => user.customerId === parseInt(id));
      if (foundUser) {
        // If user is found, update filteredUsers to show only the found user
        setFilteredUsers([foundUser]);
        setFilteredCount(1);
      } else {
        // If user is not found, show "No Data Found"
        setFilteredUsers([]);
        setFilteredCount(0);
      }
      setCurrentPage(0);
    }};      
    const handleResetFilters = () => {
        setFilterValues({
          gender: "",
          job: ""
        });
        setFilteredUsers(users);
        setCurrentPage(0);
        setFilteredCount(users.length); // Reset filteredCount to the total count of users
      };
      
  return (
    <div className='listDiv'>
      <h2 className="text-center">Customer Database</h2>
      <div className='filtersDiv'>
      
        <label className="label" htmlFor="genderFilterSelect">Filter by Gender:</label>
        <select
          id="genderFilterSelect"
          className="select"
          value={filterValues.gender}
          onChange={e => setFilterValues({ ...filterValues, gender: e.target.value })}
        >
          <option value="">All</option>
          <option value="M">Male</option>
          <option value="F">Female</option>
        </select>
        <br/><br/>
        <label className="label" htmlFor="jobFilterSelect">Filter by Job:</label>
        <select
          id="jobFilterSelect"
          className="select"
          value={filterValues.job}
          onChange={e => setFilterValues({ ...filterValues, job: e.target.value })}
        >
          <option value="">All</option>
          <option value="Tourist information centre manager">Tourist information centre manager</option>
          <option value="Advertising account planner">Advertising account planner</option>
          <option value="Higher education careers adviser">Higher education careers adviser</option>
          <option value="Senior tax professional/tax inspector">Senior tax professional/tax inspector</option>
        </select>
        <br/><br/>
        <button className="button" onClick={handleFilterApply}>Apply Filters</button>
        <button className="button" onClick={handleResetFilters} style={{ backgroundColor: 'red', color: 'white' }}>Reset Filters</button>
        <br/><br/>
        <div  className="searchContainer">
            <input type="number" className="searchInput" name={id} value={id} onChange={handleChange} placeholder="Enter Customer ID" />
            <button type="submit" className="button btn-danger" onClick={fetchUser}>Search</button>       
        </div>
        <br/><br/>
        </div>

      <p>
        <b>Total number of Credit Card Users Found : <i className="boldText">{filteredCount}</i>.</b>
      </p>
      <table className="userTable">
        <thead>
          <tr className='tableHead'>
            <th className='th'>Customer ID</th>
            <th className='th'>First Name</th>
            <th className='th'>Last Name</th>
            <th className='th'>Gender</th>
            <th className='th'>Job</th>
            <th className='th'>Date of Birth (YYYY-MM-DD)</th>
          </tr>
        </thead>
         <tbody>
          {displayedUsers.length === 0 ? (
            <tr>
              <td colSpan="6">No Data Found</td>
            </tr>
          ) : (
            displayedUsers.map((u, index) => (
              <tr key={index}>
                <td className='tb'>{u.customerId}</td>
                <td className='tb'>{u.first}</td>
                <td className='tb'>{u.last}</td>
                <td className='tb'>{u.gender}</td>
                <td className='tb'>{u.job}</td>
                <td className='tb'>{u.dob.substring(0, 10)}</td>
              </tr>
            ))
          )}
        </tbody>
      </table>
      <div className="pagination">
  <button
    className={`paginationButton ${currentPage === 0 ? 'disabled' : ''}`}
    onClick={() => setCurrentPage(prevPage => Math.max(prevPage - 1, 0))}
    disabled={currentPage === 0}
  >
    Previous Page
  </button>
  <span className="pageNumber">
    Page {currentPage + 1} of {totalPages}
  </span>
  <button
    className={`paginationButton ${currentPage === totalPages - 1 ? 'disabled' : ''}`}
    onClick={() => setCurrentPage(prevPage => Math.min(prevPage + 1, totalPages - 1))}
    disabled={currentPage === totalPages - 1}
  >
    Next Page
  </button>
</div>

    </div>
  );
}
