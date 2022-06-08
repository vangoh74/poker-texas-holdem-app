import {useContext, useState} from "react";
import {TableItem} from "../model/TableItem";
import {getTableItemsBy} from "../service/api-service";
import {toast} from "react-toastify";
import {AuthContext} from "../context/AuthProvider";

export default function useDetailedTableItems() {

    const [detailedTableItems, setDetailedTableItems] = useState<TableItem>();
    const {token} = useContext(AuthContext);

    const getTableItemsByTableId = (id: string) => {
        if (token) {
            getTableItemsBy(id, token)
                .then(data => setDetailedTableItems(data))
                .catch(error => toast.error(error));
        }
    };

    return { detailedTableItems, getTableItemsByTableId, setDetailedTableItems }

}
