import React, { useEffect, useState } from "react";
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { getHello } from "./services/api";
import Login from "./pages/Login"; 

function App() {
  const [data, setData] = useState([]);
  const [error, setError] = useState(null);  // エラーを管理する state

  useEffect(() => {
      getHello()
          .then(response => setData(response))
          .catch(error => setError("Failed to fetch data from API"));
  }, []);

  return (
    <Router>
      <Routes>
        <Route path="/" element={
          <div>
            <h1>ASP.NET + React App</h1>
            {error && <p style={{ color: "red" }}>{error}</p>} {/* エラーがあればメッセージを表示 */}
            <ul>
              {data.map((item, index) => (
                <li key={index}>{item.summary}</li>
              ))}
            </ul>
          </div>
        } />
        <Route path="/login" element={<Login />} />
      </Routes>
    </Router>
  );
}

export default App;
