import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import './App.css';

// Importy komponentów
import Camera from './components/Camera';
import ProductInfo from './components/ProductInfo';
import Recipes from './components/Recipes';
import FridgeContents from './components/FridgeContents';
import FileUpload from './components/FileUpload';
import VideoPlayer from './components/VideoPlayer';

function App() {
  return (
    <Router>
      <div className="App">
        <nav>
          <ul>
            <li><Link to="/">Strona Główna</Link></li>
            <li><Link to="/camera">Kamera</Link></li>
            <li><Link to="/product">Produkt</Link></li>
            <li><Link to="/recipes">Przepisy</Link></li>
            <li><Link to="/fridge">Zawartość Lodówki</Link></li>
            <li><Link to="/upload">Wgraj Wideo</Link></li>
          </ul>
        </nav>

        <Routes>
          <Route path="/" element={<h1>Witaj w Aplikacji</h1>} />
          <Route path="/camera" element={<Camera />} />
          <Route path="/product" element={<ProductInfo product={{
            name: 'Przykładowy Produkt', 
            category: 'Spożywczy', 
            quantity: 5 
          }} />} />
          <Route path="/recipes" element={<Recipes />} />
          <Route path="/fridge" element={<FridgeContents />} />
          <Route path="/upload" element={<FileUpload />} />
          <Route path="/video-player" element={<VideoPlayer />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;