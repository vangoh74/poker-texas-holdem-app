import {AuthContext} from "../context/AuthProvider";
import {useContext} from "react";
import {useNavigate} from "react-router-dom";

type LogoutPageProps = {
    setUsername: (arg0: string) => void;
}

export default function LogoutPage({setUsername}: LogoutPageProps) {

    const {logout} = useContext(AuthContext);
    const navigate = useNavigate();

    const onClickLogout = () => {
        logout();
        setUsername("");
    }

    return (
        <div className={"logout-page"}>
            <button onClick={onClickLogout}>Logout</button>
        </div>
    )

}
