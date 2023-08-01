import React from "react";
import { Route, Routes } from "react-router-dom";
import Home from "../pages/Home/Home";
import ProductsList from "../pages/Products/ProductsList";
import ProductsDetails from "../pages/ProductsDetails";
import Login from "../pages/Login";
import Register from "../pages/Register";
import CartPage from "../pages/Cart/CartPage";
import CustomerInfo from "../pages/Dashboard/CustomerInfo";
import CreateUserForm from "../pages/Dashboard/CreateUserForm";

const AllRoutes = () => {
  return (
    <>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/products" element={<ProductsList />} />
        <Route path="/products/:id" element={<ProductsDetails />} />
        <Route path="login" element={<Login />} />
        <Route path="register" element={<Register />} />
        <Route path="/dashboard" element={<CustomerInfo />} />
        <Route path="/create-user" element={<CreateUserForm />} />
        <Route path="cart" element={<CartPage />} />
      </Routes>
    </>
  );
};

export default AllRoutes;
