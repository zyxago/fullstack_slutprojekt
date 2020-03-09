import Comment from "../../entities/Comment";

export async function getComments(id, setCommentList) {
    const comments = [];
    const res = await fetch(`/fullstack_slutprojekt-1.0/api/comments/${id}`);
    const data = await res.json();
    for (const comment of data) {
        comments.push(new Comment(comment));
    }
    setCommentList(comments);
}

export async function postComment(comment) {
    const res = await fetch(`/fullstack_slutprojekt-1.0/api/comment`, {
        method:"POST",
        headers:{
            'Authorization': window.localStorage.getItem("token"),
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(comment)
    });
}