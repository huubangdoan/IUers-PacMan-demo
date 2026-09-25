import React from 'react';
import './About.css';

export default function About() {
  return (
    <section id="about" className="section-padding container">
      <div className="about-grid">
        <div className="about-text-box card">
          <h2>Introduction</h2>
          <p className="quote-text">"Designed to capture modern sophistication with timeless allure."</p>
          <p>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
          </p>
          <p>
            Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
          </p>
        </div>
        <div className="about-image-placeholder card flex-center">
          <div className="cloud-landscape-mock"></div>
        </div>
      </div>
    </section>
  );
}