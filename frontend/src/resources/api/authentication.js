import User from "../../entities/User";

export async function getToken(code, setUser){
    const res = await fetch(`api/authenticate`,{
        method: "GET",
        headers:{
            'Authorization': code
        }
    });
    const data = await res.json();
    window.localStorage.setItem("token", data.token);
    setUser(new User(data));
}