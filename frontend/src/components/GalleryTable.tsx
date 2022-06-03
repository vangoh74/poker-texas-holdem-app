import {TableItem} from "../model/TableItem";

type GalleryTableProps = {
    tableItem : TableItem;
}

export default function GalleryTable({tableItem} : GalleryTableProps) {
    return (
        <div className={"gallery-table"}>
            <div className={"mini-table"}>
                <p>{tableItem.id}</p>
                <p>{tableItem.bigBlind}</p>
                <p>{tableItem.freeSeats}</p>
                <p></p>
            </div>
        </div>
    );
}