export async function getComments(id, setCommentList){
    const res = await fetch(`/api/comments/${id}`);
    const data = await res.json();
}