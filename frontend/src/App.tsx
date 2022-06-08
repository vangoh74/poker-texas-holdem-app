import React from 'react';
import './App.css';
import Header from "./components/Header";
import {Route, Routes} from "react-router-dom";
import LoginPage from "./pages/LoginPage";
import {ToastContainer} from "react-toastify";
import "react-toastify/dist/ReactToastify.css"
import RequireAuth from "./routing/RequireAuth";
import PokerRoomOverview from "./components/PokerRoomOverview";
import DetailsPage from "./pages/DetailsPage";

export default function App() {

  return (
    <>
        <ToastContainer />
        <Header />
        <Routes>

            <Route element={<RequireAuth />}>
                <Route path="/"
                       element={<PokerRoomOverview />} />
                <Route path="/tableItems/:id"
                       element={<DetailsPage />} />
            </Route>

            <Route path={"/login"} element={<LoginPage />} />

        </Routes>
    </>
  )
}
