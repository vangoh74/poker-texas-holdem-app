import {TableItem} from "../model/TableItem";

type TableItemsOverviewProps = {
    tableItems: TableItem[]
}
export default function TableItemsOverview( props : TableItemsOverviewProps) {
    return (
        <div>
            {props.tableItems.map( item => item.roundNbr )}
        </div>)
}