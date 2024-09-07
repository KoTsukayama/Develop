document.getElementById('predictionForm').addEventListener('submit', async function (event) {
    event.preventDefault();
    const sets = document.getElementById('sets').value;
    const reps = document.getElementById('reps').value;
    const weight = document.getElementById('weight').value;

    const response = await fetch('/predict', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ sets, reps, weight })
    });

    const result = await response.json();
    document.getElementById('result').innerText = `Predicted Muscle Growth: ${result.predicted_growth.toFixed(2)} cm`;
});
