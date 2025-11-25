import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../../context/AuthContext';

const Logout = () => {
  const { logout } = useAuth();
  const navigate = useNavigate();

  useEffect(() => {
    logout(); // clear token & user
    navigate('/login'); // redirect
  }, [logout, navigate]);

  return <p>Logging out...</p>;
};

export default Logout;