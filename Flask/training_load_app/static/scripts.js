let chart = null;

async function fetchTrainingHistory() {
    const response = await fetch('/training_history');
    const result = await response.json();

    // データを日付順にソート
    const data = result.dates.map((date, index) => ({
        date,
        load: result.loads[index],
        sets: result.sets[index],
        reps: result.reps[index],
        weight: result.weights[index]
    })).sort((a, b) => new Date(a.date) - new Date(b.date));

    const sortedDates = data.map(entry => entry.date);
    const sortedLoads = data.map(entry => entry.load);
    const sortedSets = data.map(entry => entry.sets);
    const sortedReps = data.map(entry => entry.reps);
    const sortedWeights = data.map(entry => entry.weight);

    const ctx = document.getElementById('trainingChart').getContext('2d');

    if (chart) {
        chart.destroy();
    }

    chart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: sortedDates,
            datasets: [
                {
                    label: 'Training Load',
                    data: sortedLoads,
                    borderColor: 'rgba(75, 192, 192, 1)',
                    backgroundColor: 'rgba(75, 192, 192, 0.2)',
                    yAxisID: 'y1',
                    fill: true
                },
                {
                    label: 'Sets',
                    data: sortedSets,
                    borderColor: 'rgba(255, 99, 132, 1)',
                    backgroundColor: 'rgba(255, 99, 132, 0.2)',
                    yAxisID: 'y2',
                    fill: false
                },
                {
                    label: 'Reps',
                    data: sortedReps,
                    borderColor: 'rgba(54, 162, 235, 1)',
                    backgroundColor: 'rgba(54, 162, 235, 0.2)',
                    yAxisID: 'y2',
                    fill: false
                },
                {
                    label: 'Weight',
                    data: sortedWeights,
                    borderColor: 'rgba(255, 206, 86, 1)',
                    backgroundColor: 'rgba(255, 206, 86, 0.2)',
                    yAxisID: 'y2',
                    fill: false
                }
            ]
        },
        options: {
            scales: {
                x: {
                    title: {
                        display: true,
                        text: 'Date'
                    }
                },
                y1: {
                    id: 'y1',
                    position: 'left',
                    title: {
                        display: true,
                        text: 'Training Load'
                    },
                    ticks: {
                        beginAtZero: true
                    }
                },
                y2: {
                    id: 'y2',
                    position: 'right',
                    title: {
                        display: true,
                        text: 'Other Values'
                    },
                    grid: {
                        drawOnChartArea: false
                    },
                    ticks: {
                        beginAtZero: true
                    }
                }
            }
        }
    });
}

// 入力チェック関数
function checkInputs() {
    const date = document.getElementById('date').value;
    const sets = document.getElementById('sets').value;
    const reps = document.getElementById('reps').value;
    const weight = document.getElementById('weight').value;
    const button = document.getElementById('addEntryButton');

    if (date && sets && reps && weight) {
        button.classList.add('enabled');
        button.removeAttribute('disabled');
    } else {
        button.classList.remove('enabled');
        button.setAttribute('disabled', true);
    }
}

// 入力欄にイベントリスナーを追加
document.getElementById('date').addEventListener('input', checkInputs);
document.getElementById('sets').addEventListener('input', checkInputs);
document.getElementById('reps').addEventListener('input', checkInputs);
document.getElementById('weight').addEventListener('input', checkInputs);

document.getElementById('trainingForm').addEventListener('submit', async function(event) {
    event.preventDefault();
    const sets = document.getElementById('sets').value;
    const reps = document.getElementById('reps').value;
    const weight = document.getElementById('weight').value;
    const date = document.getElementById('date').value;
    const addButton = document.querySelector('button');

    if (sets && reps && weight && date) {
        addButton.classList.add('active'); // クラスを追加して色を変える
        addButton.disabled = false; // クリックを有効にする
    } else {
        addButton.classList.remove('active'); // クラスを削除して元の色に戻す
        addButton.disabled = true; // クリックを無効にする
    }

    const response = await fetch('/add_entry', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({date, sets, reps, weight})
    });

    if (response.ok) {
        document.getElementById('trainingForm').reset();
        checkInputs(); // フォームリセット後にボタンを再度無効化
        fetchTrainingHistory();
    } else {
        alert('Error adding entry');
    }
});

// ページロード時にトレーニング履歴を取得してグラフを表示
fetchTrainingHistory();
