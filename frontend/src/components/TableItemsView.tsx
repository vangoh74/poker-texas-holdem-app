import {TableItem} from "../model/TableItem";
import TableCards from "./TableCards";
import "./css/TableItemsView.css"

type TableItemsViewProps = {
    tableItem : TableItem;
}

export default function TableItemsView({tableItem} : TableItemsViewProps) {

    return (
        <div className={"game-room"}>
            <h4>Table ID: {tableItem.id}</h4>
            <div className={"grid-container"}>

                <div className={"seat-1 bg-seats"}></div>
                <div className={"seat-2 bg-seats"}></div>
                <div className={"seat-3 bg-seats"}></div>

                <div className={"seat-10 bg-seats"}></div>
                <div className={"table bg-table"}>
                    <div className={"cardBody"}>
                        {tableItem.tableCards.map(card => <TableCards key={tableItem.id} card={card} />) }
                    </div>
                </div>
                <div className={"seat-4 bg-seats"}></div>

                <div className={"seat-9 bg-seats"}></div>
                <div className={"seat-5 bg-seats"}></div>

                <div className={"seat-8 bg-seats"}></div>
                <div className={"seat-7 bg-seats"}></div>
                <div className={"seat-6 bg-seats"}></div>

            </div>
        </div>

    )
}
