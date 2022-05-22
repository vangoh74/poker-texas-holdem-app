import React from 'react';
import './App.css';
import Header from "./pages/Header";
import {Route, Routes} from "react-router-dom";
import TableItemsOverview from "./components/TableItemsOverview";
import useTableItems from "./hooks/UseTableItems";

export default function App() {
    const tableItems = useTableItems();
  return (
    <div className="App">
        <Header />
        <Routes>
            <Route path="/"
                element={<TableItemsOverview
                    tableItems={tableItems}/>} />
        </Routes>
    </div>
  );
}

