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
        <header className={"header-box"}>
            <Title />
            <div>
                <button className={"logout-button"} onClick={onClickLogout}>Logout</button>
                <button className={"logout-button"} onClick={() => navigate(`/`)}>Back to Poker Room</button>
            </div>
        </header>
    )
}
