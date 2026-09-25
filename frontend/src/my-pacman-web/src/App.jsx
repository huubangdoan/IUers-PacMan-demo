import React, { useState } from 'react';
import Navbar from './components/Navbar';
import Hero from './components/Hero';
import About from './components/About';
import Gameplay from './components/Gameplay';
import Team from './components/Team';
import Contact from './components/Contact';
import '../styles/global.css';

export default function App() {
  const [isPlaying, setIsPlaying] = useState(false);

  return (
    <div className="app">
      {!isPlaying ? (
        // Màn hình Intro mở đầu có nút PLAY NOW
        <div className="intro-screen flex-center" style={{ height: '100vh', background: 'linear-gradient(to bottom, #d4eaf7, #a3c963)' }}>
          <div className="text-center" style={{ padding: '2rem' }}>
            <span className="badge" style={{ background: '#658c1a', color: '#fff', padding: '0.5rem 1rem', borderRadius: '20px' }}>DSA Project</span>
            <h1 style={{ fontSize: '3rem', margin: '1rem 0', color: '#2c3e2d' }}>IUers' Pacman</h1>
            <p style={{ marginBottom: '2rem', color: '#4a5568' }}>Trò chơi Pacman phiên bản sinh viên quốc tế kết hợp cấu trúc dữ liệu.</p>
            <button onClick={() => setIsPlaying(true)} className="btn btn-primary" style={{ fontSize: '1.2rem', padding: '1rem 2.5rem' }}>
              ▶ PLAY NOW
            </button>
          </div>
        </div>
      ) : (
        // Sau khi bấm PLAY NOW sẽ vào trang chủ đầy đủ
        <>
          <Navbar />
          <Hero />
          <About />
          <Gameplay />
          <Team />
          <Contact />
          <footer className="footer text-center" style={{ padding: '2rem 0', background: '#2c3e2d', color: '#fff' }}>
            <p>&copy; 2026 DSA Project - IUers' Pacman. All rights reserved.</p>
          </footer>
        </>
      )}
    </div>
  );
}