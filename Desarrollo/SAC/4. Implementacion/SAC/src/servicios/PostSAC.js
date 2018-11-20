const url1 = 'https://sac-rest.herokuapp.com/API/asistencia/insertarAsistencia';
const url2 = 'https://sac-rest.herokuapp.com/API/miembro/insertarMiembro';
let url;
export default function PostData(data,tipo) {
    if(tipo === 'A'){
        url = url1;
    }else {
        url = url2;
    }
    return new Promise((resolve, reject) => {
        fetch(url, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data)
        })
            .then(res => res.json())
            .then(res => {
                resolve(res)
            })
            .catch((error) => {
                reject(error);
            });
    })
}