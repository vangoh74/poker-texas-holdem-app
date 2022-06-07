import {TableItem} from "../model/TableItem";
import TableCards from "./TableCards";
import "./css/TableItemsView.css"

type TableItemsViewProps = {
    tableItem : TableItem
}

export default function TableItemsView({tableItem} : TableItemsViewProps) {

    return (
        <div className={"table"}>
            <h3>Table ID: {tableItem.id}</h3>
            <h3>Round {tableItem.roundNumber}</h3>
            <div className={"cardBody"}>
                {tableItem.tableCards.map(card => <TableCards key={card.rank} card={card} />) }
            </div>
        </div>
    )
}
