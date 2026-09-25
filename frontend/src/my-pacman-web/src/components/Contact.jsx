import React from 'react';
import './Contact.css';

export default function Contact() {
  return (
    <section id="contact" className="section-padding bg-alt">
      <div className="container contact-container">
        <div className="contact-info card">
          <h2>Contact Us</h2>
          <ul className="contact-list">
            <li><strong>Email:</strong> hello@reallygreatsite.com[cite: 1]</li>
            <li><strong>Phone:</strong> +123-456-7890[cite: 1]</li>
            <li><strong>Website:</strong> www.reallygreatsite.com[cite: 1]</li>
            <li><strong>Social Media:</strong> @reallygreatsite[cite: 1]</li>
          </ul>
        </div>
        <div className="contact-form card">
          <h2>Gửi tin nhắn cho chúng tôi</h2>
          <form onSubmit={(e) => e.preventDefault()}>
            <div className="form-group">
              <input type="text" placeholder="Họ và tên" required />
            </div>
            <div className="form-group">
              <input type="email" placeholder="Email của bạn" required />
            </div>
            <div className="form-group">
              <textarea placeholder="Nội dung lời nhắn..." rows="4" required></textarea>
            </div>
            <button type="submit" className="btn btn-primary">Gửi đi</button>
          </form>
        </div>
      </div>
    </section>
  );
}