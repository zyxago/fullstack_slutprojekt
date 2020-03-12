export async function getIngredients(setIngredientList, mainPath) {
    const list = [];
    const result = await fetch(`http://${window.location.host}${mainPath}/api/ingredients`);
    const data = await result.json();
    for (const ingredient of data) {
        list.push(ingredient);
    }
    setIngredientList(list);
}

export async function getMeasurements(setMeasurementList, mainPath) {
    const list = [];
    const result = await fetch(`http://${window.location.host}${mainPath}/api/ingredients/measurement`);
    const data = await result.json();
    for (const measurement of data) {
        list.push(measurement);
    }
    setMeasurementList(list);
}