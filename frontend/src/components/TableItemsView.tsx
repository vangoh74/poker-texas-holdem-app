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
                <div className={"empty-lt bg-empty"}></div>
                <div className={"seat-1 bg-seats"}>seat 1</div>
                <div className={"seat-2 bg-seats"}>seat 2</div>
                <div className={"seat-3 bg-seats"}>seat 3</div>
                <div className={"empty-rt bg-empty"}></div>
                <div className={"seat-10 bg-seats"}>seat 10</div>
                <div className={"table bg-table"}>
                    <div className={"cardBody"}>
                        {tableItem.tableCards.map(card => <TableCards key={tableItem.id} card={card} />) }
                    </div>
                </div>
                <div className={"seat-4 bg-seats"}>seat 4</div>
                <div className={"seat-9 bg-seats"}>seat 9</div>
                <div className={"seat-5 bg-seats"}>seat 5</div>
                <div className={"empty-lb bg-empty"}></div>
                <div className={"seat-8 bg-seats"}>seat 8</div>
                <div className={"seat-7 bg-seats"}>seat 7</div>
                <div className={"seat-6 bg-seats"}>seat 6</div>
                <div className={"empty-rb bg-empty"}></div>
            </div>
        </div>

    )
}
