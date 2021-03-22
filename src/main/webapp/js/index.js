

const endpoints = []

const pingServices = () => {
	var query = document.getElementById("input").value;
	endpoints.push("http://localhost:9080/core?query="+query);
    const ping = (url) => {
        return new Promise((resolve, reject) => {
            const req = new XMLHttpRequest();
            req.onreadystatechange = function() {
                if (req.readyState != 4) return; // Not there yet
                if (req.status != 200) {
                    resolve({url, status:req.status, res:req.statusText});
                    return;
                }
                resolve({url, status:req.status, res:req.responseText});
            };
            req.open("GET", url, true);
            req.send();
        });
    };

    const pong = endpoints.map((url)=>{return ping(url);});

    Promise.all(pong).then((vals)=>{
        const div = document.querySelector('._results');
        div.innerHTML = '';
        vals.forEach((r) => {
            div.innerHTML += `[${r.status}] ${r.url} :: <br>${r.res}<br/>`;
        });
    })
	
	
};

