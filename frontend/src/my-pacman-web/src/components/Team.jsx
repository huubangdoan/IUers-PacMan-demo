import React from 'react';
import './Team.css';

const teamMembers = [
  { name: "Đoàn Hữu Bằng", role: "Developer", quote: "Code clean, think smart." },
  { name: "Thành viên 2", role: "UI/UX Designer", quote: "Design for better experience." },
  { name: "Thành viên 3", role: "Algorithm Specialist", quote: "Optimize every single loop." },
  { name: "Thành viên 4", role: "Tester & Report", quote: "Quality is priority." }
];

export default function Team() {
  return (
    <section className="section-padding container">
      <div className="section-title text-center" style={{ marginBottom: '3rem' }}>
        <h2>Team Members</h2>
        <p>Đội ngũ phát triển dự án IUers' Pacman</p>
      </div>
      <div className="team-grid">
        {teamMembers.map((member, index) => (
          <div className="card team-card" key={index}>
            <div className="avatar-placeholder"></div>
            <h3>{member.name}</h3>
            <span className="role">{member.role}</span>
            <p className="quote">"{member.quote}"</p>
          </div>
        ))}
      </div>
    </section>
  );
}