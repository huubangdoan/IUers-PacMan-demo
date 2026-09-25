import React from 'react';
import './Hero.css';

export default function Hero() {
  return (
    <section id="home" className="hero-section flex-center">
      <div className="hero-content text-center">
        <span className="badge">DSA Project</span>
        <h1>IUers' Pacman</h1>
        <p>Giới thiệu game ngắn gọn: Trải nghiệm tựa game Pacman phiên bản sinh viên Quốc tế đầy thú vị và thử thách thuật toán cấu trúc dữ liệu.</p>
        <a href="#gameplay" className="btn btn-primary">PLAY NOW</a>
      </div>
    </section>
  );
}