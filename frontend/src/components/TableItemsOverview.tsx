import {TableItem} from "../model/TableItem";
import TableItemsView from "./TableItemsView";

type TableItemsOverviewProps = {
    tableItems: TableItem[]
}

export default function TableItemsOverview( {tableItems} : TableItemsOverviewProps) {
    return (
        <>
            {tableItems.map(item =>
                <TableItemsView key={item.id} tableItem={item} />
            )}
        </>
    )
}
