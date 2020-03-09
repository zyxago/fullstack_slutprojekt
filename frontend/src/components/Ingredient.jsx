import React from "react";

export function TableRowIngredient({ingredient}) {
    return (
        <tr key={ingredient.name}>
            <td>{ingredient.name}</td>
            <td>{ingredient.amount}</td>
            <td>{ingredient.measurement}</td>
        </tr>
    )
}