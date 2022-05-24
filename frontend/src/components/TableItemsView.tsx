import {TableItem} from "../model/TableItem";
import TableCards from "./TableCards";

type TableItemsViewProps = {
    tableItem : TableItem
}

export default function TableItemsView({tableItem} : TableItemsViewProps) {

    return (
        <>
            <h3>Table ID: {tableItem.id}</h3>
            <h3>Round {tableItem.roundNbr}</h3>
            <div className={"tableMidCards"}>{tableItem.tableCards.map(card => <TableCards key={card.rank} card={card} />) }</div>

        </>
    )
}
