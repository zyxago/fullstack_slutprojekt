import React from "react";
import {getComments} from "../resources/api/comment";
import {Media} from "react-bulma-components";

export default function CommentList({recipeId}) {

    const [comments, setComments] = React.useState([]);
    React.useEffect(() => {
        getComments(recipeId, setComments)
    }, []);

    function ListComments({comments}) {
        return comments.map((comment, index) => {
            return <Media key={`comment-${index}`}>
                <Media.Content>
                    <Media.Item> {`${comment.username}: ${comment.text}`}</Media.Item>
                </Media.Content>
            </Media>
        })
    }

    return (
        <ul>
            <ListComments comments={comments}/>
        </ul>
    )
}