import Comment from "../../entities/Comment";

export async function getComments(id, setCommentList, mainPath) {
    const comments = [];
    const res = await fetch(`http://${window.location.host}${mainPath}/api/comments/${id}`);
    if(res.status === 200){
    const data = await res.json();
    for (const comment of data) {
        comments.push(new Comment(comment));
    }
    setCommentList(comments);
    }
}

export async function postComment(comment, mainPath) {
    const res = await fetch(`http://${window.location.host}${mainPath}/api/comment`, {
        method: "POST",
        headers: {
            'Authorization': window.localStorage.getItem("token"),
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(comment)
    });
    let status = res.status;
    if (status === 201) {
        alert("Comment posted!");
    } else {
        alert(("Failed to post comment!"));
    }
}

export async function deleteComment(id, mainPath) {
    const res = await fetch(`http://${window.location.host}${mainPath}/api/comment/${id}`, {
        method: "DELETE",
        headers: {
            'Authorization': window.localStorage.getItem("token")
        }
    });
    let status = res.status;
    if (status === 200) {
        alert("Comment removed!");
    } else {
        alert(("Failed to remove comment!"));
    }
}

export async function modifyComment(comment) {
    const res = await fetch(`api/comment/`, {
        method: "PUT",
        headers: {
            'Authorization': window.localStorage.getItem("token"),
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(comment)
    });
    let status = res.status;
    if (status === 200) {
        alert("Comment modified!");
    } else {
        alert(("Failed to modify comment!"));
    }
}

export async function likeComment(id, userId) {
    const res = await fetch(`api/comment/like/${id}/${userId}`, {
        method: "PUT",
        headers: {
            'Authorization': window.localStorage.getItem("token")
        }
    });
}

export async function reportComment(id, userId) {
    const res = await fetch(`api/comment/report/${id}/${userId}`, {
        method: "PUT",
        headers: {
            'Authorization': window.localStorage.getItem("token")
        }
    });
}