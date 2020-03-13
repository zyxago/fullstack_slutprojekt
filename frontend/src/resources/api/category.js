export async function getCategories(setCategoryList){
    const list = [];
    const res = await fetch(`api/categories`);
    const data = await res.json();
    for(const category of data){
        list.push(category);
    }
    setCategoryList(list);
}