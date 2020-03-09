export async function getIngredients(setIngredientList) {
    const list = [];
    const result = await fetch("/fullstack_slutprojekt-1.0/api/ingredients");
    const data = await result.json();
    for (const ingredient of data) {
        list.push(ingredient);
    }
    setIngredientList(list);
}

export async function getMeasurements(setMeasurementList) {
    const list = [];
    const result = await fetch("/fullstack_slutprojekt-1.0/api/ingredients/measurement");
    const data = await result.json();
    for (const measurement of data) {
        list.push(measurement);
    }
    setMeasurementList(list);
}