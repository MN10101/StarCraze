function getHoroscope() {
    console.log('Button clicked');
    const birthdate = document.getElementById("birthdate").value;
    fetch(`http://127.0.0.1:5500/horoscope?birthdate=${birthdate}`)
        .then(response => response.json())
        .then(data => {
            console.log('Data received:', data);
            displayHoroscope(data);
        })
        .catch(error => console.error('Error:', error));
}
function displayHoroscope(data) {
    const horoscopeInfoDiv = document.getElementById("horoscope-info");
    horoscopeInfoDiv.innerHTML = '';
    for (const [key, value] of Object.entries(data)) {
        const infoParagraph = document.createElement("p");
        infoParagraph.innerHTML = `<strong>${key}:</strong> ${value}`;
        horoscopeInfoDiv.appendChild(infoParagraph);
    }
}