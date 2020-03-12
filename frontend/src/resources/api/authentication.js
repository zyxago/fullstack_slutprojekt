import User from "../../entities/User";

export async function getToken(code, setUser, mainPath){
    const res = await fetch(`http://${window.location.host}${mainPath}/api/authenticate`,{
        method: "GET",
        headers:{
            'Authorization': code
        }
    });
    const data = await res.json();
    window.localStorage.setItem("token", data.token);
    setUser(new User(data));
}