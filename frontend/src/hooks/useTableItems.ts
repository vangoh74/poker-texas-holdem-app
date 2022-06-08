import {useContext, useEffect, useState} from "react";
import {TableItem} from "../model/TableItem";
import {getAllTableItems, getTableItemsBy} from "../service/api-service";
import {AuthContext} from "../context/AuthProvider";
import {toast} from "react-toastify";


export default function useTableItems() {
    const [tableItems, setTableItems] = useState<TableItem[]>([]);
    const {token} = useContext(AuthContext)

    useEffect( () => {
        if (token) {
            getAllTableItems(token)
                .then(allTableItems => setTableItems(allTableItems));
        }
    }, [token])

    return tableItems;
}
