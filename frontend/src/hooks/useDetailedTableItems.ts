import {useState} from "react";
import {TableItem} from "../model/TableItem";
import {getTableItemsBy} from "../service/api-service";
import {toast} from "react-toastify";

export default function useDetailedTableItems() {

    const [detailedTableItems, setDetailedTableItems] = useState<TableItem>();

    const getTableItemsByTableId = (id: string) => {
        getTableItemsBy(id)
            .then(data => setDetailedTableItems(data))
            .catch(error => toast.error(error));
    };

    return { detailedTableItems, getTableItemsByTableId, setDetailedTableItems }

}
