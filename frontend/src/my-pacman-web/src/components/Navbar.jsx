import React from 'react';
import './Navbar.css';

export default function Navbar() {
  return (
    <header className="navbar-header">
      <div className="container navbar-container">
        <div className="logo">
          <h3>DSA Project</h3>
          <span>IUers' Pacman</span>
        </div>
        <nav className="nav-links">
          <a href="#home" className="nav-item active">Home</a>
          <a href="#about" className="nav-item">About Us</a>
          <a href="#gameplay" className="nav-item">Gameplay</a>
          <a href="#contact" className="nav-item">Contact Us</a>
        </nav>
      </div>
    </header>
  );
}