import React from 'react'
import { NavLink } from 'react-router-dom'
import './User.css'

export default function User() {
  return (
    <div class="container aos-init aos-animate" data-aos="fade-up" className='userDiv'>
        <header class="section-header">
          <p class='para'>Welcome to Credit Card Section</p>
        </header>
        <div className='row gy-4'>
          <div class="col-lg-4 col-md-6 aos-init aos-animate" data-aos="fade-up" data-aos-delay="500">
            <NavLink className='nav-links' to='/usersList'>
              <h3>View All Users</h3>
              <p>Click to view the list of all credit card users</p>
            </NavLink>
          </div>
          <div class="col-lg-4 col-md-6 aos-init aos-animate" data-aos="fade-up" data-aos-delay="500">
            <NavLink className='nav-links'  to='/userId'>
              <h3>View an User</h3>
              <p>Click to view any credit card user</p>
            </NavLink>
          </div>
          <div class="col-lg-4 col-md-6 aos-init aos-animate" data-aos="fade-up" data-aos-delay="500">
            <NavLink className='nav-links'  to='/addUser'>
              <h3>Add an User</h3>
              <p>Click to enroll a new credit card user</p>
            </NavLink>
          </div>
        </div>
        <div className='row gy-6'>
          <div class="col-lg-6 col-md-6 aos-init aos-animate" data-aos="fade-up" data-aos-delay="500">
            <NavLink className='nav-links'  to='/updateUser'>
              <h3>Update an User</h3>
              <p>Click to update any credit card user</p>
            </NavLink>
          </div>
          <div class="col-lg-6 col-md-6 aos-init aos-animate" data-aos="fade-up" data-aos-delay="500">  
            <NavLink className='nav-links' to='/deleteUser'>
              <h3>Delete an User</h3>
              <p>Click to delete any credit card user</p>
            </NavLink>
          </div>

        </div>
      </div>
  )
}