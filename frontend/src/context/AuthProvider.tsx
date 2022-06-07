import {createContext, ReactElement, useState} from "react";
import axios from "axios";
import {toast} from "react-toastify"
import {useNavigate} from "react-router-dom";

const AUTH_KEY = "tableItemsAuthTokenKey123";

export const AuthContext = createContext<{
    token: string | undefined,
    login: (credentials: {username: string, password: string}) => void,
    logout: () => void }> ({
        token: undefined,
        login: () => {toast.error("Login not initialized!")},
        logout: () => {toast.info("Welcome back to the poker room!!")} });

export type AuthProviderProps = {
    children : ReactElement;
}

export default function AuthProvider({children} : AuthProviderProps) {

    const [token, setToken] = useState<string | undefined>(localStorage.getItem(AUTH_KEY) ?? undefined);
    const navigate = useNavigate();

    const login = (credentials : {username: string, password: string}) => {
        axios.post("auth/login", credentials)
            .then(response => response.data)
            .then((newToken) => {
                setToken(newToken);
                localStorage.setItem(AUTH_KEY, newToken);
            })
            .then(() => navigate("/"))
            .catch(() => toast.error("Login failed. Credentials invalid!"));
    }

    const logout = () => {
        localStorage.removeItem(AUTH_KEY);
        setToken("")
    }

    return (
        <AuthContext.Provider value={{token, login, logout}}>
            {children}
        </AuthContext.Provider>
    )
}
