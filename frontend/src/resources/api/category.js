export async function getCategories(setCategoryList, mainPath){
    const list = [];
    const res = await fetch(`http://${window.location.host}${mainPath}/api/categories`);
    const data = await res.json();
    for(const category of data){
        list.push(category);
    }
    setCategoryList(list);
}