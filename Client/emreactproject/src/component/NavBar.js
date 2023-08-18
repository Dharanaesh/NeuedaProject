import React from 'react'
import { Link, NavLink } from 'react-router-dom'
import './NavBar.css'
export default function NavBar() {
  return (
    <div>
        <nav className='navbar fixed-top navbar-expand-lg navbar-dark bg-dark'>
            <div className='container-fluid'>
                <NavLink className='navbar-brand' to='/'>Neueda</NavLink>
            </div>
            <div className='collapse navbar-collapse'>
                <ul className='navbar-nav me-auto mb-2 mb-lg-0'>
                    <li className='nav-item'><NavLink className='nav-link' to='/user'>Credit Card</NavLink></li>
                    <li className='nav-item'><NavLink className='nav-link' to='/transactions'>Transactions</NavLink></li>
                    <li className='nav-item'><NavLink className='nav-link' to='/about'>About Us</NavLink></li>
                </ul>
            </div>
        </nav>
    </div>
  )
}