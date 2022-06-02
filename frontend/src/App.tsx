import React from 'react';
import './App.css';
import Header from "./components/Header";
import {Route, Routes} from "react-router-dom";
import TableItemsOverview from "./components/TableItemsOverview";
import LoginPage from "./pages/LoginPage";
import {ToastContainer} from "react-toastify";
import RequireAuth from "./routing/RequireAuth";

export default function App() {

  return (
    <>
        <ToastContainer />
        <Header />
        <Routes>
            <Route element={<RequireAuth />}>
                <Route path="/"
                       element={<TableItemsOverview />}
                />
            </Route>
            <Route path={"/login"} element={<LoginPage />} />
        </Routes>
    </>
  );
}
