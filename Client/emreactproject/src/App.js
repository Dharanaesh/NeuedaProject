import './App.css';
import {Route, Routes} from 'react-router-dom';
import About from './component/About';
import NavBar from './component/NavBar';
import Home from './component/Home';
import GenderTransaction from './component/genderTransaction';
import User from './component/User';
import UserId from './component/UserId'
import DeleteUser from './component/DeleteUser';
import Transaction from './component/Transaction';
import MerchantDashboard from './component/MerchantTransaction';
import CategoryTransaction from './component/TransactionCategory';
import TransactionDashboard from './component/TransactionState';
import UsersList from './component/UsersList';
import AddUser from './component/AddUser';
import TransactionsList from './component/TransactionsList';
import UpdateUser from './component/UpdateUser';

function App() {
  return (
    <div className="container">
      <NavBar/>
      <Routes>
        <Route path='/' element={<Home/>}/>
        <Route path='/about' element={<About/>}/>
        <Route path='/user' element={<User/>}/>
        <Route path='/usersList' element={<UsersList/>}/>
        <Route path='/userId' element={<UserId/>}/>
        <Route path='/addUser' element={<AddUser/>}/>
        <Route path='/deleteUser' element={<DeleteUser/>}/>
        <Route path='/transactions' element={<Transaction/>}/>
        <Route path='/allTransactions' element={<TransactionsList/>}/>
        <Route path='/updateUser' element={<UpdateUser/>}/>
        <Route path='/transaction' element={<TransactionDashboard/>}/>
        <Route path='/category' element={<CategoryTransaction/>}/>
        <Route path='/gender' element={<GenderTransaction/>}/>
        <Route path='/merchant' element={<MerchantDashboard/>}/>
      </Routes>
    </div>
  );
}
export default App;
