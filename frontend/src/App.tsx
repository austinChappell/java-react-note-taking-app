import React, { useEffect } from 'react';
import logo from './logo.svg';
import './App.css';

async function getNotes() {
  try {
    const response = await fetch('http://localhost:8080/api/v1/notes');

    const data = await response.json();

    console.log('the data: ', data);
  } catch (error) {
    alert(error.message)
  }
}

function App() {
  useEffect(() => {
  getNotes();    
  }, []);

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.tsx</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;
