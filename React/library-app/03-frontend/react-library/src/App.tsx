import React from 'react';
import './App.css';
import { Navbar } from './layouts/NabbarAndFooter/Navbar';
import { Footer } from './layouts/NabbarAndFooter/Footer';
import { HomePage } from './layouts/HomePage/HomePage';

export const App = () => {
  return (
    <div>
      <Navbar />
      <HomePage />
      <Footer />
    </div>
  );
}
