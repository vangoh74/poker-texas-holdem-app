import TableMiniature from "./TableMiniature";
import useTableItems from "../hooks/useTableItems";
import "./css/PokerRoomOverview.css";

export default function PokerRoomOverview() {

    const tableItems = useTableItems();

    return (
        <div className={"room-background"}>
            <div className={"gallery"}>
                {
                    tableItems.map(tableItem =>
                        <TableMiniature key={tableItem.id} tableItem={tableItem} />)
                }
            </div>
        </div>

    )
}
