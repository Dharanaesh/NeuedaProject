import React, { useEffect, useState } from 'react';
import { getAllTransactions } from '../services/transactionService';
import './TransactionsList.css';

export default function TransactionsList() {
  const [transactions, setTransactions] = useState([]);
  const [filteredTransactions, setFilteredTransactions] = useState([]);
  const [filterValues, setFilterValues] = useState({
    minAmt: "",
    maxAmt: ""
  });
  const [currentPage, setCurrentPage] = useState(0);
    const [pageSize, setPageSize] = useState(20);
  useEffect(() => {
    getAllTransactions()
      .then(transactions => {
        setTransactions(transactions);
        setFilteredTransactions(transactions);
      });
  }, []);
  const totalPages = Math.ceil(filteredTransactions.length / pageSize);
    const startIndex = currentPage * pageSize;
    const endIndex = startIndex + pageSize;
    const displayedUsers = filteredTransactions.slice(startIndex, endIndex);
    
  const handleFilterApply = () => {
    let filtered = transactions;

    if (filterValues.minAmt) {
      filtered = filtered.filter(transaction => transaction.amt > parseFloat(filterValues.minAmt));
    }

    if (filterValues.maxAmt) {
      filtered = filtered.filter(transaction => transaction.amt < parseFloat(filterValues.maxAmt));
    }

    setFilteredTransactions(filtered);
    setCurrentPage(0);
  };

  const handleResetFilters = () => {
    setFilterValues({
      minAmt: "",
      maxAmt: ""
    });
    setFilteredTransactions(transactions);
    setCurrentPage(0);
  };
  const formatDate = (dateString) => {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
  };
  return (
    <div className='listDiv'>
      <p><b>Total number of transactions found <i>{filteredTransactions.length}</i>.</b></p>
      <div className="filter-controls">
        <input
          type="number"
          placeholder="Min Amount"
          className="filter-input"
          value={filterValues.minAmt}
          onChange={e => setFilterValues({ ...filterValues, minAmt: e.target.value })}
        />
        <input
          type="number"
          placeholder="Max Amount"
          className="filter-input"
          value={filterValues.maxAmt}
          onChange={e => setFilterValues({ ...filterValues, maxAmt: e.target.value })}
        />
        <button className="button" onClick={handleFilterApply}>Apply Filters</button>
        <button className="button" onClick={handleResetFilters}>Reset Filters</button>
      </div>
      {displayedUsers.length === 0 ? <p>No Transactions found.</p> :
        <table>
          <thead>
          <tr className='tableHead'>
                    <th className='th'>Transaction Number</th>
                    <th className='th'>Transaction Date</th>
                    <th className='th'>Transaction Time</th>
                    <th className='th'>Amount</th>
                    <th className='th'>City</th>
                    <th className='th'>Merchant</th>
                    <th className='th'>Category</th>
                    <th className='th'>Customer ID</th>
                    <th className='th'>First Name</th>
                    <th className='th'>Last Name</th>
                    <th className='th'>Gender</th>
                    <th className='th'>Job</th>
                    <th className='th'>Date of Birth (YYYY-MM-DD)</th>
                    <th className='th'>Status</th>
                </tr>
          </thead>
          <tbody>
            {displayedUsers.map((u, index) => (
              <tr key={index}>
              <td className='tb'>{u.transNum}</td>
              <td className='tb'>{u.transDateTransTime.substring(0,10)}</td>
              <td className='tb'>{u.transDateTransTime.substring(11,16)}</td>
              <td className='tb'>{u.amt}</td>
              <td className='tb'>{u.city}</td>
              <td className='tb'>{u.merchant}</td>
              <td className='tb'>{u.category}</td>
              <td className='tb'>{u.customer_id}</td>
              <td className='tb'>{u.first}</td>
              <td className='tb'>{u.last}</td>
              <td className='tb'>{u.gender}</td>
              <td className='tb'>{u.job}</td>
              <td className='tb'>{formatDate(u.dob)}</td>
              <td className='td'>{u.state}</td>
          </tr>
            ))}
          </tbody>
        </table>
      }
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