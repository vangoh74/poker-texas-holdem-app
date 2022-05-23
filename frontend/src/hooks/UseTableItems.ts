import {useEffect, useState} from "react";
import {TableItem} from "../model/TableItem";
import {getAllTableItems} from "../service/Api-Service";


export default function useTableItems() {
    const [tableItems, setTableItems] = useState<TableItem[]>([]);

    useEffect( () => {
        getAllTableItems()
            .then(allTableItems => setTableItems(allTableItems));
    }, [])

    return tableItems;
}
