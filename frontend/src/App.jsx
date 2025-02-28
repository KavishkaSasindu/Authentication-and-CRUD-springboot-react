import React from "react";
import { BrowserRouter, Routes, Route } from "react-router";
import Home from "./pages/Home";
import RegisterUser from "./pages/auth/User/RegisterUser";
import LogInUser from "./pages/auth/User/LogInUser";

const App = () => {
  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/api/v1/auth/register" element={<RegisterUser />} />
          <Route path="/api/v1/auth/login" element={<LogInUser />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
};

export default App;
