import React from 'react';
import './Gameplay.css';

export default function Gameplay() {
  return (
    <section id="gameplay" className="section-padding bg-alt">
      <div className="container">
        <div className="section-title text-center">
          <h2>What feature?</h2>
          <p>Khám phá các tính năng thuật toán và cơ chế độc đáo trong dự án.</p>
        </div>
        <div className="gameplay-grid">
          <div className="card">
            <h3>Pathfinding AI</h3>
            <p>Ứng dụng thuật toán tìm đường (BFS/DFS/A*) tối ưu hóa hành vi di chuyển của Ghost thông minh.</p>
          </div>
          <div className="card">
            <h3>Interactive UI</h3>
            <p>Giao diện tối giản, hiện đại lấy cảm hứng từ thiên nhiên, mượt mà trên mọi thiết bị.</p>
          </div>
          <div className="card">
            <h3>Score Tracking</h3>
            <p>Hệ thống lưu trữ điểm số thời gian thực giúp người chơi cạnh tranh thành tích học tập.</p>
          </div>
        </div>
      </div>
    </section>
  );
}