import React from 'react';
import './App.css';
import Header from "./components/Header";
import {Route, Routes} from "react-router-dom";
import TableItemsOverview from "./components/TableItemsOverview";
import UseTableItems from "./hooks/useTableItems";
import LoginPage from "./pages/LoginPage";
import {ToastContainer} from "react-toastify";

export default function App() {
    const tableItems = UseTableItems();

  return (
    <>
        <ToastContainer />
        <Header />
        <Routes>
            <Route path="/"
                   element={<TableItemsOverview
                       tableItems={tableItems} />}
            />
            <Route path={"/login"} element={<LoginPage />} />
        </Routes>
    </>
  );
}
