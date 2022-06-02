import TableItemsView from "./TableItemsView";
import "./css/TableItemsOverview.css";
import UseTableItems from "../hooks/useTableItems";

export default function TableItemsOverview() {
    const tableItems = UseTableItems();
    return (
        <div className={"gameRoom"}>
            {tableItems.map(item =>
                <TableItemsView key={item.id} tableItem={item} />
            )}
        </div>
    )
}
