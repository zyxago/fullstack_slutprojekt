import User from "../../entities/User";

export async function getToken(code, setUser){
    const res = await fetch("/fullstack_slutprojekt-1.0/api/authenticate",{
        method: "GET",
        headers:{
            'Authorization': code
        }
    });
    const data = await res.json();
    window.localStorage.setItem("token", data.token);
    setUser(new User(data));
}