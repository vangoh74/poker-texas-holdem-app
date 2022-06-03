import {TableItem} from "../model/TableItem";
import axios from "axios";

export const getAllTableItems: (token: string) =>
    Promise<TableItem[]> = (token) => {
        return axios.get("/api/tableitems", {headers: {"Authorization": token}})
            .then(response => response.data)

    }