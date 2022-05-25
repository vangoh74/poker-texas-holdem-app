import React from 'react';
import './App.css';
import Header from "./components/Header";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import TableItemsOverview from "./components/TableItemsOverview";
import useTableItems from "./hooks/UseTableItems";
import LoginPage from "./pages/LoginPage";

export default function App() {
    const tableItems = useTableItems();
  return (
    <BrowserRouter>
        <Header />
        <Routes>
            <Route path="/"
                element={<TableItemsOverview
                    tableItems={tableItems}/>} />
            <Route path={"/login"} element={<LoginPage />} />
        </Routes>
    </BrowserRouter>
  );
}


