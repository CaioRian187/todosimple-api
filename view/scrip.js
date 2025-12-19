const url = "http://localhost:8080/task/user/6";

function hideLoader(){
    document.getElementById("loading").style.display = "none";
}

function show(tasks){
    let header = ` 
        <tr>
            <th scope="col">#</th>
            <th scope="col">Description</th>
            <th scope="col">Username</th>
            <th scope="col">User Id</th>
        </tr>
    `;
    let body = '';

    for (let task of tasks) {
        body += `
            <tr>
                <td scobe="row">${task.id}</td>
                <td>${task.description}</td>
                <td>${task.user.username}</td>
                <td>${task.user.id}</td>
            </tr>
        `;
    }
    document.getElementById("tasks-header").innerHTML = header;
    document.getElementById("tasks-body").innerHTML = body;
}

async function getAPI(url){
    try {
        const response = await fetch(url, { method : "GET" });

        if (!response.ok) { 
            // Lança um erro se o status HTTP for de falha
            throw new Error(`Erro HTTP: ${response.status}`);
        }

        var data = await response.json();
        console.log(data);
        
        hideLoader();
        show(data);

    } catch (error) {
        // Exibe o erro se a requisição falhar ou o status não for OK
        console.error("Erro ao buscar API:", error);
        hideLoader(); // Esconde o loader mesmo que haja erro
        // Opcional: Exibir mensagem de erro ao usuário
        document.getElementById("tasks").innerHTML = `<p style="color: red;">Falha ao carregar dados: ${error.message}</p>`;
    }
}

/*
async function getAPI(url){
    const response = await fetch(url, { method : "GET" });

    var data = await response.json();
    console.log(data);

    if (response) hideLoader();
    show(data);
}*/

getAPI(url);