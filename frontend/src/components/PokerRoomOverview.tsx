import Table from "./Table";
import UseTableItems from "../hooks/useTableItems";
import "./css/PokerRoomOverview.css"

export default function PokerRoomOverview() {

    const tableItems = UseTableItems();

    return (
        <div className={"room-background"}>
            <div className={"gallery"}>
                {
                    tableItems.map(tableItem =>
                        <Table key={tableItem.id} tableItem={tableItem} />)
                }
            </div>
        </div>

    );
}