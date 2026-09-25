// auth.js — module xác thực dùng chung cho mọi engine (Phaser, Unity WebGL, React, v.v.)
// Đã được cập nhật để bắt chính xác định dạng lỗi JSON từ Spring Boot.

const AUTH_API_BASE = "http://localhost:9090/api/auth"; // đổi khi deploy
const TOKEN_KEY = "pacman_jwt";

/**
 * Đăng ký tài khoản mới.
 * @returns {Promise<string>} token JWT nếu thành công
 */
async function registerUser(username, password) {
  const res = await fetch(`${AUTH_API_BASE}/register`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ username, password }),
  });
  const data = await res.json();
  
  // Sửa ở đây: Spring Boot mặc định trả về thông báo lỗi qua trường 'message'
  if (!res.ok) {
    throw new Error(data.message || data.error || "Đăng ký thất bại");
  }
  
  saveToken(data.token);
  return data.token;
}

/**
 * Đăng nhập.
 * @returns {Promise<string>} token JWT nếu thành công
 */
async function loginUser(username, password) {
  const res = await fetch(`${AUTH_API_BASE}/login`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ username, password }),
  });
  const data = await res.json();
  
  // Sửa ở đây: Hứng cả 'message' từ RuntimeException của Java
  if (!res.ok) {
    throw new Error(data.message || data.error || "Sai tài khoản hoặc mật khẩu");
  }
  
  saveToken(data.token);
  return data.token;
}

function saveToken(token) {
  localStorage.setItem(TOKEN_KEY, token);
}

function getToken() {
  return localStorage.getItem(TOKEN_KEY);
}

function isLoggedIn() {
  return !!getToken();
}

function logout() {
  localStorage.removeItem(TOKEN_KEY);
}

/**
 * Helper để gọi các API game cần xác thực sau này.
 * Tự động gắn header Authorization: Bearer <token>.
 */
async function authFetch(url, options = {}) {
  const token = getToken();
  const headers = { ...(options.headers || {}), Authorization: `Bearer ${token}` };
  const res = await fetch(url, { ...options, headers });
  if (res.status === 401) {
    logout();
    window.location.href = "login.html";
  }
  return res;
}