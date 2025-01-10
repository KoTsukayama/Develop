import React, { useEffect, useState } from "react";
import { getHello } from "./services/api";

function App() {
    const [data, setData] = useState([]);

    useEffect(() => {
        getHello().then(response => setData(response));
    }, []);

    return (
        <div>
            <h1>ASP.NET + React App</h1>
            <ul>
                {data.map((item, index) => (
                    <li key={index}>{item.summary}</li>
                ))}
            </ul>
        </div>
    );
}

export default App;
