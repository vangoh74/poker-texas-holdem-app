import {TableItem} from "../model/TableItem";
import axios from "axios";

export const getAllTableItems: () =>
    Promise<TableItem[]> = () => {
    return axios.get("/api/tableitem")
        .then(response => response.data)
}