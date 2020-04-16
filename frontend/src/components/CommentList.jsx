import React from "react";
import {getComments} from "../resources/api/comment";
import {Media, Box} from "react-bulma-components";
import Comment from "./Comment";

export default function CommentList({parentId, user}) {

    const [comments, setComments] = React.useState([]);
    React.useEffect(() => {
        getComments(parentId, setComments)
    }, []);

    //Funkar, men lite okonventionellt och omständigt
/*     function ListComments({comments}) {
        return comments.map((comment, index) => {
            return <Box key={`comment-${index}`}>
                <Media>
                    <Media.Content>
                        <Media.Item><Comment comment={comment} user={user}/></Media.Item>
                    </Media.Content>
                </Media>
            </Box>
        })
    }

    return (
        <ul>
            <ListComments comments={comments}/>
        </ul>
    ) */

    //alternativ:
    const content = comments.map((comment, index) => (
        <Box key={`comment-${index}`}>
            <Media>
                <Media.Content>
                    <Media.Item><Comment comment={comment} user={user}/></Media.Item>
                </Media.Content>
            </Media>
        </Box>));
       
    return <ul>{content}</ul>

}