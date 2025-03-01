import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router";

const AllUsers = () => {
  const navigate = useNavigate();

  const [users, setUsers] = useState([]);

  const fetchUsers = async () => {
    try {
      const token = localStorage.getItem("jwt_token");
      if (!token) {
        navigate("/api/v1/auth/login");
      }
      const response = await axios.get(
        "http://localhost:8080/api/v1/user/allUsers",
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("jwt_token")}`,
          },
        }
      );

      const data = await response.data;
      console.log(data);
      setUsers(data);
    } catch (error) {
      localStorage.removeItem("jwt_token");
      navigate("/api/v1/auth/login");
      console.log(error.message);
    }
  };

  useEffect(() => {
    fetchUsers();
  }, []);

  return (
    <div>
      <div>All Users</div>
      <div className="w-[100%] flex justify-center items-center ">
        <div className="w-[80%] flex justify-center items-center  ">
          <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
            {users.map((user, index) => (
              <div
                className="p-3  shadow-sm shadow-gray-200 space-y-5"
                key={index}
              >
                <div>{user.username}</div>
                <div className="flex space-x-2">
                  <button className="px-3 py-1 bg-blue-200  rounded-sm">
                    Update
                  </button>
                  <button className="px-3 py-1 bg-red-200  rounded-sm">
                    Delete
                  </button>
                </div>
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
};

export default AllUsers;
