import {FormEvent, useState} from "react";
import "./css/LoginPage.css";

export default function LoginPage() {
    const [username, setUsername] = useState<string>("");
    const [password, setPassword] = useState<string>("");

    const onSubmit = (event:FormEvent<HTMLFormElement>) => {
        event.preventDefault();
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
