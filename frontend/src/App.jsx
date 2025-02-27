import React from "react";
import { BrowserRouter, Routes, Route } from "react-router";
import Home from "./pages/auth/User/Home";
import RegisterUser from "./pages/auth/RegisterUser";

const App = () => {
  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/api/v1/auth/register" element={<RegisterUser />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
};

export default App;
