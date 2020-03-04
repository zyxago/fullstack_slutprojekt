import React from "react";

export function TableRowIngredient({ingredient}) {
    return (
        <tr>
            <td>{ingredient.name}</td>
            <td>{ingredient.amount}</td>
            <td>{ingredient.measurement}</td>
        </tr>
    )
}