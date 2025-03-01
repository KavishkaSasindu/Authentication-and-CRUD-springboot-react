import React from "react";
import { BrowserRouter, Routes, Route } from "react-router";
import Home from "./pages/Home";
import RegisterUser from "./pages/auth/RegisterUser";
import LogInUser from "./pages/auth/LogInUser";
import AllUsers from "./pages/User/AllUsers";

const App = () => {
  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/api/v1/auth/register" element={<RegisterUser />} />
          <Route path="/api/v1/auth/login" element={<LogInUser />} />
          <Route path="/api/v1/user/allUsers" element={<AllUsers />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
};

export default App;
