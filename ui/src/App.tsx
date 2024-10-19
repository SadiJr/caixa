import { useState } from 'react'
import reactLogo from './assets/react.svg'
import './App.css'
import {
  BrowserRouter as Router,
  Route,
  browserHistory,
  Routes
} from 'react-router-dom';
import Login from './components/Login'
import Register from './components/Register'
import Cash from './components/Cash';

function App() {

  return (
    <>
    <Router>
      <Routes>
        <Route path="/" element={<Register />} />
        <Route path="/register" element={<Register />} />
        <Route path="/login" element={<Login />} />
        <Route path="/cash" element={<Cash />} />
      </Routes>
    </Router>
    </>
  );
}

export default App
