import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function Login() {
  const [userID, setUserID] = useState("");
  const [password, setPassword] = useState("");
  const [message, setMessage] = useState("");
  const navigate = useNavigate();

  const handleLogin = async () => {
    const response = await fetch("http://localhost:5000/api/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ userID, password }),
    });

    const data = await response.json();
    if (response.ok) {
      setMessage("Login successful");
      setTimeout(() => navigate("/home"), 1000);
    } else {
      setMessage("Invalid credentials");
    }
  };

  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-gray-100">
      <div className="bg-white p-8 shadow-md rounded-md w-80">
        <h2 className="text-xl font-bold mb-4 text-center">Login</h2>
        <input
          type="text"
          placeholder="User ID"
          value={userID}
          onChange={(e) => setUserID(e.target.value)}
          className="w-full p-2 border rounded-md mb-2"
        />
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          className="w-full p-2 border rounded-md mb-4"
        />
        <button
          onClick={handleLogin}
          className="w-full bg-blue-500 text-white p-2 rounded-md"
        >
          Login
        </button>
        {message && <p className="mt-4 text-center">{message}</p>}
      </div>
    </div>
  );
}
