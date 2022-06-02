import {FormEvent, useContext, useState} from "react";
import "./css/LoginPage.css";
import {AuthContext} from "../context/AuthProvider";

export default function LoginPage() {
    const [username, setUsername] = useState<string>("");
    const [password, setPassword] = useState<string>("");

    const {login} = useContext(AuthContext);

    const onSubmit = (event:FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        login({username: username, password: password});
    }

    return (
        <div className={"login-root"}>
            <div className={"loginBackground padding-top-40"}>
                <form onSubmit={onSubmit}>
                    <div className={"padding-bottom-15"}>
                        <span>Sign in to your poker room</span>
                    </div>
                    <div className={"field padding-bottom-24"}>
                        <input
                            type={"text"}
                            value={username}
                            placeholder={"Username"}
                            onChange={ (event) => setUsername(event.target.value) }/>
                    </div>
                    <div className={"field padding-bottom-24"}>
                        <input
                            type={"password"}
                            value={password}
                            placeholder={"Password"}
                            onChange={ (event) => setPassword(event.target.value) }/>
                    </div>
                    <div className={"field padding-bottom-24"}>
                        <input type={"submit"} value={"Login"} />
                    </div>
                </form>
            </div>
        </div>
    )
 }
