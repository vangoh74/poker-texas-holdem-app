import {TableItem} from "../model/TableItem";

type TableItemsOverviewProps = {
    tableItems: TableItem[]
}

export default function TableItemsOverview( {tableItems} : TableItemsOverviewProps) {
    return (
        <>
        </>
    )

/*
    return (
        <div>
            {tableItems.map(item =>
                {item.tableCards.map( ({rank, suit}) => (
                    <p key={rank}> {rank}, {suit}</p>
                    )

                )}
            )}
        </div>
    )*/
}