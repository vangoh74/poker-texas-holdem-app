import Title from "./Title";
import "./css/Header.css"
import {useNavigate} from "react-router-dom";
import useUsername from "../hooks/useUsername";
import {useContext} from "react";
import {AuthContext} from "../context/AuthProvider";

export default function Header() {

    const navigate = useNavigate();
    const {setUsername} = useUsername();
    const {logout} = useContext(AuthContext);

    const onClickLogout = () => {
        logout();
        setUsername("");
    }

    return (
        <header className={"headerDesign"}>
            <Title />
            <div>
                <button className={"return-btn"} onClick={onClickLogout}>Logout</button>
            </div>
        </header>
    )
}
