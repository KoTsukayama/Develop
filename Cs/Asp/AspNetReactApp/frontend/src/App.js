import React, { useEffect, useState } from "react";
import { getHello } from "./services/api";

function App() {
  const [data, setData] = useState([]);
  const [error, setError] = useState(null);  // エラーを管理する state

  useEffect(() => {
      getHello()
          .then(response => setData(response))
          .catch(error => setError("Failed to fetch data from API"));
  }, []);

  return (
    <div>
        <h1>ASP.NET + React App</h1>
        {error && <p style={{ color: 'red' }}>{error}</p>}  {/* エラーがあればメッセージを表示 */}
        <ul>
            {data.map((item, index) => (
                <li key={index}>{item.summary}</li>
            ))}
        </ul>
    </div>
  );
}

export default App;
