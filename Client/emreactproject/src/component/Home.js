import React from 'react'
import { Link } from 'react-router-dom';
import './Home.css'
import img1 from './imgsrc.jpg';


export default function Home() {
  return (

    <div className='homeDiv'>
     <div className="homepage-container">
                <h1 className="header">Neueda Credit Cards</h1>
                <div className="img-container">
                    <div class="inner-container">
                    <div class="jumbotron"><h2>A Card With No Limits</h2>
                    <p><q>Enjoy the power of Instant Money</q></p>
                    <p className="para">A credit card allows you to buy products and services on low-cost EMIs. Another option that has become popular is the Buy Now, Pay Later, which is ideal for salaried borrowers with fixed monthly incomes. Credit cards are the best alternative to cash, as it eliminates the need for carrying cash.</p>
                    <Link className="button-29" to='/addUser'>Join with us..</Link>
                    </div>
                  
                    </div>
                    <img src={img1} alt="Credit-card" className="img-responsive"/>
                    
                </div>
            </div>
    </div>
  )
}
