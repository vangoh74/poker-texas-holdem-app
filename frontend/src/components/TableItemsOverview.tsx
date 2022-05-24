import {TableItem} from "../model/TableItem";
import TableItemsView from "./TableItemsView";
import "./css/TableItemsOverview.css";

type TableItemsOverviewProps = {
    tableItems: TableItem[]
}

export default function TableItemsOverview( {tableItems} : TableItemsOverviewProps) {

    return (
        <div className={"gameRoom"}>
            {tableItems.map(item =>
                <TableItemsView key={item.id} tableItem={item} />
            )}
        </div>
    )
}
