const form = document.getElementById("bikeForm");
const registros = document.getElementById("registros");
const historico = document.getElementById("historico");

const dados = [];

// Carrega histórico ao abrir o site
document.addEventListener("DOMContentLoaded", carregarHistorico);

form.addEventListener("submit", async function (e) {
  e.preventDefault();

  const nome = document.getElementById("nome").value;
  const ra = document.getElementById("ra").value;
  const modelo = document.getElementById("modelo").value;

  const agora = new Date();
  const dataHora = agora.toLocaleString("pt-BR");

  const registro = { nome, ra, modelo, dataHora };

  try {
    const response = await fetch("http://localhost:8080/api/bikes", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(registro),
    });

    if (!response.ok) {
      throw new Error("Erro ao registrar. Verifique o backend.");
    }

    const resultado = await response.json();

    dados.push(resultado);
    localStorage.setItem("bicicletas", JSON.stringify(dados));

    form.reset();
    mostrarHistorico();
  } catch (error) {
    alert(error.message);
  }
});

function carregarHistorico() {
  fetch("http://localhost:8080/api/bikes")
    .then((res) => res.json())
    .then((dados) => {
      registros.innerHTML = "";
      historico.style.display = dados.length > 0 ? "block" : "none";

      dados.forEach((item) => {
        const div = document.createElement("div");
        div.className = "registro";
        div.innerHTML = `
          <p><strong>${item.modelo}</strong> - Código: <span style="color:#00ffc3">#${item.codigo || '00000'}</span></p>
          <p>👤 ${item.nome} - RA: ${item.ra}</p>
          <p>Data: ${item.dataHora}</p>
          <hr style="margin: 10px 0;" />
        `;
        registros.appendChild(div);
      });
    })
    .catch((err) => {
      console.error("Erro ao buscar histórico:", err);
    });
}

function retirarBike() {
  const ra = document.getElementById("raRetirada").value;
  const codigo = document.getElementById("codigoRetirada").value;

  fetch("http://localhost:8080/api/bikes/retirar", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ ra, codigo }),
  })
    .then((res) => {
      if (res.ok) {
        alert("Bicicleta retirada com sucesso!");
        carregarHistorico();
      } else {
        alert("Erro ao retirar. Verifique RA e código.");
      }
    })
    .catch((err) => {
      console.error("Erro ao retirar:", err);
      alert("Erro ao retirar bicicleta.");
    });
}
