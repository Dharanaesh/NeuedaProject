import React from 'react'
import { NavLink } from 'react-router-dom'
import './Transaction.css'

export default function Transaction() {
  return (
    <div class="container aos-init aos-animate" data-aos="fade-up" className='userDiv'>
        <header class="section-header">
          <p class='para'>Welcome to Transactions Section</p>
        </header>
        <div className='row gy-6'>
          <div class="col-lg-6 col-md-6 aos-init aos-animate" data-aos="fade-up" data-aos-delay="500">
            <NavLink className='nav-links' to='/allTransactions'>
              <h3>View All Transactions</h3>
              <p>Click to view the list of all transactions</p>
            </NavLink>
          </div>
          <div class="col-lg-6 col-md-6 aos-init aos-animate" data-aos="fade-up" data-aos-delay="500">
            <NavLink className='nav-links'  to='/transaction'>
              <h3>View State wise Transactions</h3>
              <p>Click to view</p>
            </NavLink>
          </div>
        </div>
        <div className='row gy-6'>
          <div class="col-lg-6 col-md-6 aos-init aos-animate" data-aos="fade-up" data-aos-delay="500">
            <NavLink className='nav-links'  to='/category'>
              <h3>View A State's Expenditure Summary</h3>
              <p>Click to View</p>
            </NavLink>
          </div><div class="col-lg-6 col-md-6 aos-init aos-animate" data-aos="fade-up" data-aos-delay="500">
            <NavLink className='nav-links'  to='/gender'>
              <h3>View A Gender's Expenditure Summary</h3>
              <p>Click to View</p>
            </NavLink>
          </div>
          <div className='row gy-6'>
          <div class="col-lg-6 col-md-6 aos-init aos-animate" data-aos="fade-up" data-aos-delay="500">
            <NavLink className='nav-links'  to='/merchant'>
              <h3>View A Merchant Transactions Summary</h3>
              <p>Click to View</p>
            </NavLink>
          </div></div>
        </div>
      </div>
  )
}