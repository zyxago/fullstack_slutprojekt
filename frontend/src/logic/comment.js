import {postComment} from "../resources/api/comment";

export function submitComment(text, user, parentId, mainPath) {
    let comment = {
        writerId: user.id,
        text: text,
        username: user.username,
        parentId: parentId
    };
    postComment(comment, mainPath);
}