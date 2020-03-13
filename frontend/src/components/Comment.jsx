import React from "react";
import {submitComment} from "../logic/comment";
import {deleteComment, likeComment, modifyComment, reportComment} from "../resources/api/comment";
import CommentList from "./CommentList";
import "../resources/css/comment.scss";


export default function Comment({comment, user}) {

    function editComment() {
        let newText = prompt("Edit ", comment.text);
        if (newText !== null && newText !== "") {
            comment.text = newText;
            modifyComment(comment);
        }
    }

    return (
        <div className="comment">
            <div className="commentHeader">
                <div>
                    {`${comment.username}: ${comment.text}`}
                </div>
                <div>
                    {user && <>
                        <button className="button"
                                onClick={() => likeComment(comment.id, user.id)}>Like {comment.likes}</button>
                        <button className="button" onClick={() => reportComment(comment.id, user.id)}>Report</button>
                        {user.id === comment.writerId && <>
                            <button className="button" onClick={() => editComment()}>Edit</button>
                            <button className="button" onClick={() => deleteComment(comment.id)}>Remove Comment</button>
                        </>}
                    </>}
                </div>
            </div>
            {user && <> <input className="input" type="text" placeholder="Respond..." id={`comment${comment.id}`}/>
                <button className="button"
                        onClick={() => submitComment(document.getElementById(`comment${comment.id}`).value, user, comment.id)}>Comment
                </button>
            </>}
            <CommentList parentId={comment.id} user={user}/>
        </div>
    )
}